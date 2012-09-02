package com.ig.bc

class EmailNotificationService {
    def asynchronousMailService
    def groovyPageRenderer

    def invitation(InvitationCommand invitation) {
        EmailDTO invitationAlert = new EmailDTO(receiverEmail: "${invitation.email1}, ${invitation.email2}, ${invitation.email3}",
                subject: "LinkSharingInvitaion",
                body: "${invitation.content}")
        sendEmail(invitationAlert)
        if (invitation.hasErrors()) {
            log.info "Errors in Invitation : " + invitation.errors
        }
    }

    def sendResourceEmailAlerts() {
        List<String> emails = User.getRegisteredEmails()
        for (email in emails) {
            createMail(email)
        }
    }

    def subscriptionAlerts() {
        List<User> users = User.list()
        for (user in users) {
            List<Resource> unreadResources = user.getUnreadResources()
            EmailDTO subscriptionAlert = new EmailDTO(
                    receiverEmail: "${user.email}",
                    subject: "Subscription Reminder LinkSharing",
                    html: "${groovyPageRenderer.render(template: "/reminder/resources", model: [unreadResourceList: unreadResources])}"
            )
            sendEmail(subscriptionAlert)
        }
    }

    private sendEmail(EmailDTO email) {
        asynchronousMailService.sendAsynchronousMail {
            to email.receiverEmail
            subject email.subject
            body email.body
            html email.html

        }
    }

    private void createMail(String email) {
        User user = User.findByEmail(email)
        Map<Topic, List<Resource>> topicResourcesMap = user.getSubscriptionUpdates()
        Set<Topic> topics = topicResourcesMap.keySet()
        List<TopicResourceDTO> newTopicResourceList = []
        for (topic in topics) {
            newTopicResourceList << new TopicResourceDTO(topic: topic, resources: topicResourcesMap[topic])
        }
        EmailDTO resourceAlert = new EmailDTO(receiverEmail: email, subject: "LinkSharing Updates", html: "${groovyPageRenderer.render(template: "/mail/newResources", model: [newTopicResourceList: newTopicResourceList])}")
        sendEmail(resourceAlert)
    }
}

import com.ig.bc.co.InvitationCommand
import com.ig.bc.dto.EmailDTO
import com.ig.bc.dto.TopicResourceDTO

