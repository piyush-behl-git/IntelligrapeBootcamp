package com.ig.bc

import org.springframework.dao.DataIntegrityViolationException

class SubscriptionController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def subscriptionService

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        String currentLoggedInUserEmail = session.email
        User currentUser = User.findByEmail(currentLoggedInUserEmail)
        params.max = Math.min(max ?: 10, 100)
        [subscriptionInstanceList: currentUser.getSubscriptions(),
                subscriptionInstanceTotal: currentUser.getSubscriptionCount()]
    }

    def create() {
        String currentLoggedInUserEmail = session.email
        User currentUser = User.findByEmail(currentLoggedInUserEmail)
        List<Topic> subscribedTopics = currentUser.getSubscribedTopics()
        [subscriptionInstance: new Subscription(params), topicInstanceList: subscribedTopics, currentLoggedInUser: currentUser]
    }

    def save() {
        def subscriptionInstance = new Subscription(params)
        if (!subscriptionInstance.save(flush: true)) {
            render(view: "create", model: [subscriptionInstance: subscriptionInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'subscription.label', default: 'Subscription'), subscriptionInstance.id])
        redirect(action: "show", id: subscriptionInstance.id)
    }

    def show(Long id) {
        def subscriptionInstance = Subscription.get(id)
        if (!subscriptionInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'subscription.label', default: 'Subscription'), id])
            redirect(action: "list")
            return
        }

        [subscriptionInstance: subscriptionInstance]
    }

    def edit(Long id) {
        def subscriptionInstance = Subscription.get(id)
        if (!subscriptionInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'subscription.label', default: 'Subscription'), id])
            redirect(action: "list")
            return
        }

        [subscriptionInstance: subscriptionInstance]
    }

    def update(Long id, Long version) {
        def subscriptionInstance = Subscription.get(id)
        if (!subscriptionInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'subscription.label', default: 'Subscription'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (subscriptionInstance.version > version) {
                subscriptionInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                        [message(code: 'subscription.label', default: 'Subscription')] as Object[],
                        "Another user has updated this Subscription while you were editing")
                render(view: "edit", model: [subscriptionInstance: subscriptionInstance])
                return
            }
        }

        subscriptionInstance.properties = params

        if (!subscriptionInstance.save(flush: true)) {
            render(view: "edit", model: [subscriptionInstance: subscriptionInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'subscription.label', default: 'Subscription'), subscriptionInstance.id])
        redirect(action: "show", id: subscriptionInstance.id)
    }

    def delete(Long id) {
        def subscriptionInstance = Subscription.get(id)
        if (!subscriptionInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'subscription.label', default: 'Subscription'), id])
            redirect(action: "list")
            return
        }

        try {
            subscriptionInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'subscription.label', default: 'Subscription'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'subscription.label', default: 'Subscription'), id])
            redirect(action: "show", id: id)
        }
    }

    def subscribe() {
        List<String> topicIds = params.ids.split(',')
        List<Long> idList = topicIds.collect {
            Long.parseLong(it)
        }
        String currentLoggedInUserEmail = session.email
        User currentUser = User.findByEmail(currentLoggedInUserEmail)
        List<Topic> topics = Topic.getAll(idList)
        String errors = subscriptionService.subscribe(currentUser, topics)
        flash.message = "Topics subscribed successfully"
        if (errors)
            flash.message = errors
        render(template: "/topic/list", model: [list: currentUser.getTopics()])
    }

    def unsubscribe() {
        List<String> topicIds = params.ids.split(',')
        List<Long> idList = topicIds.collect {
            Long.parseLong(it)
        }
        List<Topic> topics = Topic.getAll(idList)
        String currentLoggedInUserEmail = session.email
        User subscriber = User.findByEmail(currentLoggedInUserEmail)
        subscriptionService.unsubscribe(subscriber, topics)
        flash.message = "Topics unsubscribed successfully"

        render(template: "/topic/list", model: [list: subscriber.getTopics()])
    }
}
