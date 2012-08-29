package com.ig.bc.vo

import com.ig.bc.User
import com.ig.bc.ReadingItem
import com.ig.bc.Topic

class TopicReadingItem {
    Topic topic
    List<ReadingItem> readingItems

    String toString() {
        "${topic}:[${readingItems}]"
    }
}
