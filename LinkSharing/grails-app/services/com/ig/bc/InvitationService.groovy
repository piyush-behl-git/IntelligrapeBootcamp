package com.ig.bc

import com.ig.bc.co.InvitationCommand

import com.ig.bc.vo.TopicReadingItem
import com.ig.bc.vo.SubscriberTopicReadingItem

class InvitationService {
    static transactional = false

    def asynchronousMailService
    def subscriptionService
    def topicService
    def readingItemService
    def resourcesService
    def userService

    void invite() {

    }

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

    def unreadResourcesAlert() {
        def subscribersTopicMap = subscriptionService.getAllVerySeriousSubscriptions()
        Set<User> subscribers = subscribersTopicMap.keySet()
        List<SubscriberTopicReadingItem> subscriberTopicReadingItemList = []
        SubscriberTopicReadingItem subscriberTopicReadingItem
        List<TopicReadingItem> topicReadingItemList = []
        for (subscriber in subscribers) {
            subscriberTopicReadingItem = new SubscriberTopicReadingItem(subscriber: subscriber)
            List<Topic> topics = subscribersTopicMap[subscriber]*.topic
            TopicReadingItem topicReadingItem
            for (topic in topics) {
                topicReadingItem = new TopicReadingItem(topic: topic)
                List<Resource> topicResources = topicService.getAllResources(topic) as List
                List<ReadingItem> topicAllUnreadReadingItems = readingItemService.getAllUnreadReadingItems(subscriber, topicResources)
                topicReadingItem.readingItems = topicAllUnreadReadingItems
                topicReadingItemList << topicReadingItem
            }
            subscriberTopicReadingItem.topicReadingItemList = topicReadingItemList
            subscriberTopicReadingItemList << subscriberTopicReadingItem
        }
        return subscriberTopicReadingItemList
    }

    def subscriptionAlerts() {
        List<String> emails = userService.getAllRegisteredEmails()
        for (email in emails) {
            List<Resource> unreadResourceList = resourcesService.allUnreadResources(email)
            Integer totalResources = unreadResourceList.size()
            asynchronousMailService.sendAsynchronousMail {
                to email
                subject "Subscription Reminder Link Sharing"
                body( view:"/reminder/unreadResources",
                      model:[resourceList: unreadResourceList])
//                html g.render(template: 'reminder/list', model: [resourceList: unreadResourceList])
//                body(view: "reminder/unreadResources", model: [unreadResourceList: unreadResourceList, totalResources: totalResources])

            }
        }
    }
}

