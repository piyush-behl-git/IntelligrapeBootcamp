package com.ig.bc

import com.ig.bc.enums.Visibility
import com.ig.bc.vo.TopicSubscriptionCount

class SubscriptionService {

    def userService

    def serviceMethod() {

    }

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

    def getCurrentLoggedInUserSubscriptions(String currentLoggedInUserEmail) {
        User currentUserInstance = userService.getCurrentUser(currentLoggedInUserEmail)
        List<Subscription> currentLoggedInUserSubscriptions = Subscription.findAllBySubscriber(currentUserInstance)
        return currentLoggedInUserSubscriptions
    }

    def countCurrentLoggedInUserTotalSubscriptions(String currentLoggedInUserEmail) {
        User currentLoggedInUser = userService.getCurrentUser(currentLoggedInUserEmail)
        Integer currentLoggedInUserTotalSubscriptions = Subscription.createCriteria().count {
            eq("subscriber", currentLoggedInUser)
        }
        return  currentLoggedInUserTotalSubscriptions

    }

    def getCurrentLoggedInUserAllSubscribedTopics(String currentUserEmail) {
        List<Topic> currentLoggedInUserAllSubscribedTopics = getCurrentLoggedInUserSubscriptions(currentUserEmail).collect {
            it.topic
        }
        return currentLoggedInUserAllSubscribedTopics
    }
}
