package com.ig.bc
import com.ig.bc.enums.*

class Subscription {
    User subscriber
    Topic topic
    Date dateCreated
    Date lastUpdated
    Seriousness seriousness
    static constraints = {
        topic(unique: true)
    }
}
