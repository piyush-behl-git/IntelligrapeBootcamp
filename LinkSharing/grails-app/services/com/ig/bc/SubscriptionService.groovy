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
            maxResults 1
            order('t', 'desc')
        }
        def publicSubscriptionCountByTopic = publicSubscriptionCountByTopicList.get(0)
        Topic topic = publicSubscriptionCountByTopic.first()
        Integer subscriptionCount = publicSubscriptionCountByTopic.last()
        TopicSubscriptionCount topicSubscriptionCount = new TopicSubscriptionCount(topic: topic, subscriptionCount: subscriptionCount)
        return topicSubscriptionCount
    }

    def getCurrentUserSubscriptions(String currentUserEmail) {
        User currentUserInstance = userService.getCurrentUser(currentUserEmail)
        List<Subscription> currentUserSubscriptions = Subscription.findAllBySubscriber(currentUserInstance)
        return currentUserSubscriptions
    }

    def getCurrentUserSubscribedTopics(String currentUserEmail) {
        List<Topic> currentUserSubscribedTopics = getCurrentUserSubscriptions(currentUserEmail).collect{
            it.topic
        }
        println currentUserSubscribedTopics
    }
}
