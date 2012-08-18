package com.ig.bc

import com.ig.bc.enums.Visibility

class Topic {
    String name
    Visibility visibility
    static belongsTo = [owner: User]
    static hasMany = [subscriptions: Subscription, resources: Resource]
    static constraints = {
        name(nullable: false, blank: false, unique: true)
    }
}
