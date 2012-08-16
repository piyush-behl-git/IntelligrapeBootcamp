package com.ig.bc

class Topic {
    String name
    Visibility visibility
    static belongsTo = [owner: User]
    static hasMany = [subscriptions: Subscription, resources: Resource]
    static constraints = {
        name(nullable: false, blank: false, unique: true)
    }
}
