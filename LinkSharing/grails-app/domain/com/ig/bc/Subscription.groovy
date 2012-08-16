package com.ig.bc

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
