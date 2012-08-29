package com.ig.bc.vo

import com.ig.bc.User

class SubscriberTopicReadingItem {
    User subscriber
    List<TopicReadingItem> topicReadingItemList

    String toString() {
        return "${subscriber}:[${topicReadingItemList}]"
    }
}
