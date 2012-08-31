package com.ig.bc

import com.ig.bc.enums.Visibility
import com.ig.bc.vo.TopicSubscriptionCount
import com.ig.bc.enums.Seriousness
import com.ig.bc.vo.TopicSubscriberVO

class SubscriptionService {

    def userService
    def asynchronousMailService
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
}
