package com.ig.bc

import com.ig.bc.enums.Seriousness
import com.ig.bc.enums.Visibility
import com.ig.bc.vo.TopicResourceCount
import com.ig.bc.vo.SubscriberTopicReadingItem

class UtilController {

    def resourcesService
    def subscriptionService
    def invitationService

    def index() { }

    def checkBaseDir() {
        render grailsApplication.config.uploadPath
    }

    def test() {
        String email = session.email
        println email
        def a = resourcesService.allUnreadResources(email)
        println a
    }

    def currentUser() {
        render("${session.email}")
    }


    def testAction() {
        render("HEllo")
    }
}
