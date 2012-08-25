package com.ig.bc

import org.springframework.dao.DataIntegrityViolationException

class UserController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    static defaultAction = "dashboard"

    def subscriptionService

    def index() {
    }

    def loginHandler() {
        println params.email
        User user = User.findByEmailAndPassword(params.email, params.password)
        if (user) {
            session.email = params.email
            println session.email
            redirect action: "dashboard"
        }
        render view: "/login/login"
    }

    def dashboard() {
        println session.email

        User user = User.findByEmail(session.email)
        println "Looking for user.."

        println "User found: ${user.fullName}"
        println "Looking for ${user.fullName} resources"

        List<ReadingItem> unreadItems = ReadingItem.findAllByUserAndIsRead(user, false)
        List<Subscription> subscriptions = Subscription.findAllBySubscriber(user)
        List<Topic> topics = Topic.findAllByOwner(user)
        Topic highestSubscribedTopic = subscriptionService.findHighestSubscribedPublicTopic().topic


        println unreadItems
        println subscriptions
        println topics

        [highestSubscribedTopic: highestSubscribedTopic]
    }



    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [userInstanceList: User.list(params), userInstanceTotal: User.count()]
    }

    def create() {
        [userInstance: new User(params)]
    }

    def save() {
        println "Params ${params}"
        def userInstance = new User(params)
        println "User : ${userInstance.confirmPassword} >>>> ${userInstance.password} >>> ${userInstance.confirmPassword == userInstance.password}"
        if (!userInstance.save(flush: true)) {
            render(view: "create", model: [userInstance: userInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'user.label', default: 'User'), userInstance.id])
        redirect(action: "show", id: userInstance.id)
    }

    def show(Long id) {
        def userInstance = User.get(id)
        if (!userInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), id])
            redirect(action: "list")
            return
        }

        [userInstance: userInstance]
    }

    def edit(Long id) {
        def userInstance = User.get(id)
        if (!userInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), id])
            redirect(action: "list")
            return
        }

        [userInstance: userInstance]
    }

    def update(Long id, Long version) {
        def userInstance = User.get(id)
        if (!userInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (userInstance.version > version) {
                userInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                        [message(code: 'user.label', default: 'User')] as Object[],
                        "Another user has updated this User while you were editing")
                render(view: "edit", model: [userInstance: userInstance])
                return
            }
        }

        userInstance.properties = params

        if (!userInstance.save(flush: true)) {
            render(view: "edit", model: [userInstance: userInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'user.label', default: 'User'), userInstance.id])
        redirect(action: "show", id: userInstance.id)
    }

    def delete(Long id) {
        def userInstance = User.get(id)
        if (!userInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), id])
            redirect(action: "list")
            return
        }

        try {
            userInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'user.label', default: 'User'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'user.label', default: 'User'), id])
            redirect(action: "show", id: id)
        }
    }

}
