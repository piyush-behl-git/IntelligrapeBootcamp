package com.ig.bc

class User {
    String email
    String password
    String fullName
    Date dateOfBirth
    Date dateCreated
    Date lastUpdated
    boolean isMale
    static hasMany = [topics: Topic, subscriptions: Subscription]
    static constraints = {
        email (unique: true, blank: false, nullable: false, email: true)
        dateOfBirth(nullable: true)
    }
    String toString() {
        fullName
    }
}