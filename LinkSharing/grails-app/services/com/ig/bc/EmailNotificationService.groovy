package com.ig.bc

import com.ig.bc.co.InvitationCommand
import com.ig.bc.vo.TopicResourceVO

class EmailNotificationService {
    def asynchronousMailService
    def resourceService
    def userService
    def groovyPageRenderer

    def invitation(InvitationCommand invitation) {

        asynchronousMailService.sendAsynchronousMail {
            to invitation.email1, invitation.email2, invitation.email3
            subject "Linksharing Invitation"
            body invitation.content

        }
        if (invitation.hasErrors()) {
            log.info "Errors in BookCommand : " + invitation.errors
        }
    }

    def newResourceEmailAlerts() {
        List<String> emails = userService.getAllRegisteredEmails()
        println emails
        for (email in emails) {
            Map<Topic, Resource> topicResourceMap = resourceService.allUpdatesAboutUserSubscriptions(email)
            println topicResourceMap
            Set<Topic> topics = topicResourceMap.keySet()
            List<TopicResourceVO> newTopicResourceList = []
            for (topic in topics) {
               newTopicResourceList << new TopicResourceVO(topic: topic, resources: topicResourceMap[topic])
            }
            asynchronousMailService.sendAsynchronousMail {
                to email
                subject "LinkSharing Updates"
                html "${groovyPageRenderer.render(template: "/mail/newResources", model: [newTopicResourceList: newTopicResourceList])}"

            }
        }
    }
}
