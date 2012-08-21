package com.ig.bc

import com.ig.bc.enums.Seriousness

class Subscription {
    User subscriber
    Topic topic
    Date dateCreated
    Date lastUpdated
    Seriousness seriousness
    static constraints = {
    }
    String toString() {
        subscriber.fullName+"_"+topic.name
    }
}
