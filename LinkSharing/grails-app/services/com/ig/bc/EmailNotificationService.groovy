package com.ig.bc

import com.ig.bc.co.InvitationCommand
import com.ig.bc.vo.TopicResourceVO
import com.ig.bc.dto.EmailDTO

class EmailNotificationService {
    def asynchronousMailService
    def resourceService
    def userService
    def groovyPageRenderer

    //TODO name should start with verb
    def invitation(InvitationCommand invitation) {

        //TODO make common function
        asynchronousMailService.sendAsynchronousMail {
            to invitation.email1, invitation.email2, invitation.email3
            subject "Linksharing Invitation"
            body invitation.content

        }
        if (invitation.hasErrors()) {
            log.info "Errors in BookCommand : " + invitation.errors
        }
    }

    //TODO name should start with verb
    def newResourceEmailAlerts() {
        List<String> emails = userService.getAllRegisteredEmails()
        for (email in emails) {
            Map<Topic, Resource> topicResourceMap = resourceService.allUpdatesAboutUserSubscriptions(email)
            Set<Topic> topics = topicResourceMap.keySet()
            List<TopicResourceVO> newTopicResourceList = []
            for (topic in topics) {
                newTopicResourceList << new TopicResourceVO(topic: topic, resources: topicResourceMap[topic])
            }
            //TODO make common function
            asynchronousMailService.sendAsynchronousMail {
                to email
                subject "LinkSharing Updates"
                html "${groovyPageRenderer.render(template: "/mail/newResources", model: [newTopicResourceList: newTopicResourceList])}"

            }
        }
    }

    private sendEmail(EmailDTO email) {
        asynchronousMailService.sendAsynchronousMail {
            to email.receiverEmail
            subject email.subject
            html "${groovyPageRenderer.render(template: "/mail/newResources", model: [newTopicResourceList: newTopicResourceList])}"

        }
    }
}