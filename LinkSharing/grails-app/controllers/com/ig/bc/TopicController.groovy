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
        String currentLoggedUserEmail = session.email
        User currentUser = User.findByEmail(currentLoggedUserEmail)
        params.max = Math.min(max ?: 10, 100)
        [topicInstanceList: currentUser.generateAndReturnTopicVOs(), topicInstanceTotal: Topic.count()]
    }

    def create() {
    }

    def save() {
        def topicInstance = new Topic(params)
        User currentUser = User.findByEmail(session.email)
        topicInstance.owner=currentUser
        if (!topicInstance.save(flush: true)) {
            render(view: "create", model: [topicInstance: topicInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'topic.label', default: 'Topic'), topicInstance.id])
        redirect(action: "show", id: topicInstance.id)
    }

    def show(Long id) {

        def topicInstance = Topic.get(id)
        if (!topicInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'topic.label', default: 'Topic'), id])
            redirect(action: "list")
            return
        }
        User user = User.findByEmail(session.email)
        String subscriptionStatus
        if (Subscription.findBySubscriberAndTopic(user, topicInstance))
            subscriptionStatus="true"
        [topicInstance: topicInstance, subscriptionStatus: subscriptionStatus]
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

    def deleteResource() {
        String result="false"
        Long id=Long.parseLong(params.id)
        Resource resource = Resource.findById(id)
        User user = User.findByEmail(session.email)
        if (resource) {
            if (session.email=="admin@intelligrape.com"||resource.owner==user)  {
                resource.delete(flush: true)
                result="true"
            }
        }
        render result

    }
}

