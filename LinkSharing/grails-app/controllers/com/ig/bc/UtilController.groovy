package com.ig.bc

class UtilController {

    def resourceService

    def index() { }

    def checkBaseDir() {
        render grailsApplication.config.uploadPath
    }

    def test() {
        String email = session.email
        def topicResourceMap = resourceService.allUpadatesAboutUserSubscriptions(email)
        Set<Topic> topics = topicResourceMap.keySet()
        for (topic in topics) {
            println "Topic : "+topic
            println "Resources : "+topicResourceMap[topic]
        }
    }

    def currentUser() {
        render("${session.email}")
    }


    def testAction() {
        String email= session.email
        resourceService.allUpadatesAboutUserSubscriptions(email)
    }
}
