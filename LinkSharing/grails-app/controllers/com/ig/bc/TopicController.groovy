package com.ig.bc

import com.ig.bc.co.InvitationCommand
import org.springframework.dao.DataIntegrityViolationException

class TopicController {

    def subscriptionService
    def emailNotificationService

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [topicInstanceList: Topic.list(params), topicInstanceTotal: Topic.count()]
    }

    def create() {
        String currentLoggedInUserEmail = session.email
        User currentUser = User.findByEmail(currentLoggedInUserEmail)
        [topicInstance: new Topic(params), currentUserInstance: currentUser]
    }

    def save() {
        def topicInstance = new Topic(params)
        if (!topicInstance.save(flush: true)) {
            render(view: "create", model: [topicInstance: topicInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'topic.label', default: 'Topic'), topicInstance.id])
        redirect(action: "show", id: topicInstance.id)
    }

    def show(Long id) {
        flash.message = message(code: 'default.deleted.message', args: [message(code: 'subscription.label', default: 'Subscription'), id])

        def topicInstance = Topic.get(id)
        if (!topicInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'topic.label', default: 'Topic'), id])
            redirect(action: "list")
            return
        }

        [topicInstance: topicInstance]
    }

    def edit(Long id) {
        def topicInstance = Topic.get(id)
        if (!topicInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'topic.label', default: 'Topic'), id])
            redirect(action: "list")
            return
        }

        [topicInstance: topicInstance]
    }

    def update(Long id, Long version) {
        def topicInstance = Topic.get(id)
        if (!topicInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'topic.label', default: 'Topic'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (topicInstance.version > version) {
                topicInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                        [message(code: 'topic.label', default: 'Topic')] as Object[],
                        "Another user has updated this Topic while you were editing")
                render(view: "edit", model: [topicInstance: topicInstance])
                return
            }
        }

        topicInstance.properties = params

        if (!topicInstance.save(flush: true)) {
            render(view: "edit", model: [topicInstance: topicInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'topic.label', default: 'Topic'), topicInstance.id])
        redirect(action: "show", id: topicInstance.id)
    }

    def delete(Long id) {
        def topicInstance = Topic.get(id)
        if (!topicInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'topic.label', default: 'Topic'), id])
            redirect(action: "list")
            return
        }

        try {
            topicInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'topic.label', default: 'Topic'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'topic.label', default: 'Topic'), id])
            redirect(action: "show", id: id)
        }
    }

    def bindInvitation(InvitationCommand invitationCommand) {
        emailNotificationService.invitation(invitationCommand)
        flash.message = "Invitations sent"
        redirect(action: 'list')
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

