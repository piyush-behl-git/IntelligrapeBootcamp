package com.ig.bc

import com.ig.bc.enums.Seriousness

class User {
    String email
    String password
    String confirmPassword
    String fullName
    Date dateOfBirth
    Date dateCreated
    Date lastUpdated
    boolean isMale
    static hasMany = [topics: Topic, subscriptions: Subscription]
    static transients = ['confirmPassword']
    static constraints = {
        email (unique: true, blank: false, nullable: false, email: true)
        dateOfBirth(nullable: true)
        confirmPassword(bindable: true)
        topics(nullable: true)
        subscriptions(nullable: true)
        password(validator: {val, user->
                if(val==user.confirmPassword)
                    return true
            return false
        })
    }

    List<Topic> getAllVerySeriousTopics() {
        List<Topic> topics = Subscription.createCriteria().list {
            projections {
                property("topic")
            }
            eq("seriousness", Seriousness.VERY_SERIOUS)
            eq("subscriber", this)
        }
        return topics
    }


    String toString() {
        fullName
    }
}
