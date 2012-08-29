package com.ig.bc.vo

import com.ig.bc.Topic
import com.ig.bc.User

class TopicSubscriberVO {
    User subscriber
    Topic topic

    String toString() {
       return "${subscriber}_${topic}"
    }

}
