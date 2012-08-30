package com.ig.bc

import com.ig.bc.enums.Visibility
import com.ig.bc.vo.TopicSubscriptionCount
import com.ig.bc.enums.Seriousness
import com.ig.bc.vo.TopicSubscriberVO

class SubscriptionService {

    def userService
    def asynchronousMailService
    def resourceService
    def groovyPageRenderer

    //TODO  refactor
    def findHighestSubscribedPublicTopic() {
        def publicSubscriptionCountByTopicList = Subscription.createCriteria().list {
            projections {
                groupProperty('topic')
                count('topic', 't')
            }
            'topic' {
                eq("visibility", Visibility.PUBLIC)
            }
            maxResults 10
            order('t', 'desc')
        }
        def publicSubscriptionCountByTopic = publicSubscriptionCountByTopicList.get(0)
        Topic topic = publicSubscriptionCountByTopic.first()
        Integer subscriptionCount = publicSubscriptionCountByTopic.last()
        TopicSubscriptionCount topicSubscriptionCount = new TopicSubscriptionCount(topic: topic, subscriptionCount: subscriptionCount)
        return topicSubscriptionCount
    }

    //TODO
    def getCurrentLoggedInUserSubscriptions(String currentLoggedInUserEmail) {
        User currentUserInstance = userService.getCurrentUser(currentLoggedInUserEmail)
        List<Subscription> currentLoggedInUserSubscriptions = Subscription.findAllBySubscriber(currentUserInstance)
        return currentLoggedInUserSubscriptions
    }

    //TODO
    def countCurrentLoggedInUserTotalSubscriptions(String currentLoggedInUserEmail) {
        User currentLoggedInUser = userService.getCurrentUser(currentLoggedInUserEmail)
        Integer currentLoggedInUserTotalSubscriptions = Subscription.createCriteria().count {
            eq("subscriber", currentLoggedInUser)
        }
        return currentLoggedInUserTotalSubscriptions

    }

    //TODO
    def getCurrentLoggedInUserAllSubscribedTopics(String currentUserEmail) {
        List<Topic> currentLoggedInUserAllSubscribedTopics = getCurrentLoggedInUserSubscriptions(currentUserEmail).collect {
            it.topic
        }
        return currentLoggedInUserAllSubscribedTopics
    }

    //TODO
    def getAllVerySeriousSubscriptions() {
        def allVerySeriousSubscriptions = Subscription.createCriteria().list {
            projections {
                property("subscriber")
                property("topic")
            }
            eq("seriousness", Seriousness.VERY_SERIOUS)
        }
        List<TopicSubscriberVO> topicSubscriberVOList = []
        for (subscription in allVerySeriousSubscriptions) {
            User subscriber = subscription.first()
            Topic topic = subscription.last()
            TopicSubscriberVO topicSubscriberVO = new TopicSubscriberVO(topic: topic, subscriber: subscriber)
            topicSubscriberVOList << topicSubscriberVO
        }
        def subscribersTopicMap = topicSubscriberVOList.groupBy {
            it.subscriber
        }
        return subscribersTopicMap
    }

    //TODO
    def getAllImportantTopics(String email) {
        User subscriber = userService.getCurrentUser(email)
        List<Topic> topics = Subscription.createCriteria().list {
                projections {
                    property("topic")
                }
            eq("seriousness", Seriousness.VERY_SERIOUS)
            eq("subscriber", subscriber)
        }
        return topics
    }

    //TODO name refactor, userService to User domain
    def subscriptionAlerts() {
        List<String> emails = userService.getAllRegisteredEmails()
        for (email in emails) {
            List<Resource> unreadResourceList = resourceService.allUnreadResources(email)
            Integer totalResources = unreadResourceList.size()
            asynchronousMailService.sendAsynchronousMail {
                to email
                subject "Subscription Reminder Link Sharing"
                html(groovyPageRenderer.render(template: "reminder/resources", model: [unreadResourceList: unreadResourceList]))
            }
        }
    }
}
