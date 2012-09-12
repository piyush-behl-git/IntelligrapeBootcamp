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
        String message = "Topics subscribed successfully"
        if (errors)
            message = errors
        render message
    }

    def unsubscribe() {
        List<String> topicIds = params.ids.split(',')
        List<Long> idList = topicIds.collect {
            Long.parseLong(it)
        }
        String currentLoggedInUserEmail = session.email
        User subscriber = User.findByEmail(currentLoggedInUserEmail)
        subscriptionService.unsubscribe(subscriber, idList)
        String message = "Topics unsubscribed successfully"
        render message
    }

    def subscribeIndividualTopic() {
        String message
        Long id = Long.parseLong(params.id)
        Topic topic = Topic.get(id)
        List<Topic> topics = []
        topics << topic
        User subscriber = User.findByEmail(session.email)
        String errors = subscriptionService.subscribe(subscriber, topics)
        if (errors)
            message = errors
        else
            message = "Topic Subscribed"
        render message
    }
}
