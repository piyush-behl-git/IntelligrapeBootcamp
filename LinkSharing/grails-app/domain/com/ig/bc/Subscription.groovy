package com.ig.bc

import com.ig.bc.enums.Seriousness
import com.ig.bc.dto.TopicSubscriptionDTO
import com.ig.bc.enums.Visibility

class Subscription {
    Date dateCreated
    Date lastUpdated
    Seriousness seriousness

    static belongsTo = [subscriber: User, topic: Topic]
    static final MAX_RESULT_COUNT = 10
    static constraints = {
    }

    List<TopicSubscriptionDTO> getTopicAndSubscriptionCount() {
        List<TopicSubscriptionDTO> topicSubscriptionDTOs = Subscription.createCriteria().list {
            projections {
                groupProperty('topic')
                count('topc', 't')
            }
            'topic' {
                eq("visibility", Visibility.PUBLIC)
            }
            maxResults MAX_RESULT_COUNT
            order('t', 'desc')
        }
        return topicSubscriptionDTOs
    }

    String toString() {
        subscriber.fullName + "_" + topic.name
    }
}
