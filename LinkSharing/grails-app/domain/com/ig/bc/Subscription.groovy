package com.ig.bc

import com.ig.bc.dto.TopicSubscriptionDTO
import com.ig.bc.enums.Seriousness
import com.ig.bc.enums.Visibility

class Subscription {
    Date dateCreated
    Date lastUpdated
    Seriousness seriousness

    static belongsTo = [subscriber: User, topic: Topic]
    static final MAX_RESULT_COUNT = 10
    static constraints = {
    }

    static List<TopicSubscriptionDTO> getHighestSubscribedTopics() {
        List<Object[]> topicAndSubscriptionCountList = topicAndSubscriptionCriteria()
        List<TopicSubscriptionDTO> highestSubscribedTopics = []
        for (topicAndSubscriptionCount in topicAndSubscriptionCountList) {
            highestSubscribedTopics << new TopicSubscriptionDTO(topic: topicAndSubscriptionCount.first(), subscriptionCount: topicAndSubscriptionCount.last())
        }
        return highestSubscribedTopics
    }

    private static List<Object[]> topicAndSubscriptionCriteria() {
        List topicAndSubscriptionCountList = Subscription.createCriteria().list {
            projections {
                groupProperty('topic')
                count('topic', 't')
            }
            'topic' {
                eq("visibility", Visibility.PUBLIC)
            }
            maxResults MAX_RESULT_COUNT
            order('t', 'desc')
        }
        topicAndSubscriptionCountList
    }

    String toString() {
        subscriber.fullName + "_" + topic.name
    }
}
