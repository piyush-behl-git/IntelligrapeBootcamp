package com.ig.bc

import com.ig.bc.enums.Seriousness
import com.ig.bc.enums.Visibility

class UtilController {

    def readingItemService
    def subscriptionService

    def index() { }

    def checkBaseDir() {
        render grailsApplication.config.uploadPath
    }

    def test() {
        Topic html = Topic.findByName("html")
        String currentLoggedInUserEmail = session.email
        User user =
//        subscriptionService.getCurrentLoggedInUserAllSubscribedTopics(currentLoggedInUserEmail)
        readingItemService.currentUserSubscribedTopicsMostReadResources(currentLoggedInUserEmail)
    }

    def currentUser() {
        render("${session.email}")
    }
}
