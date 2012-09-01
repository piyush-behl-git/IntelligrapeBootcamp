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
        email(unique: true, blank: false, nullable: false, email: true)
        dateOfBirth(nullable: true)
        confirmPassword(bindable: true)
        topics(nullable: true)
        subscriptions(nullable: true)
        password(validator: {val, user ->
            if (val == user.confirmPassword)
                return true
            return false
        })
    }

    List<Topic> getVerySeriousTopics() {
        List<Topic> topics = Subscription.createCriteria().list {
            projections {
                property("topic")
            }
            eq("seriousness", Seriousness.VERY_SERIOUS)
            eq("subscriber", this)
        }
        return topics
    }

    List<Resource> getResources() {
        return Resource.findAllByUser(this)
    }

    List<DocumentResource> getDocumentResources() {
        List<Resource> resources = getResources()
        List<DocumentResource> documentResources = resources.collect {resource ->
            if (resource.instanceOf(DocumentResource))
                return (DocumentResource) resource
        }
    }

    List<ReadingItem> getReadingItems() {
        List<ReadingItem> readingItems = ReadingItem.findAllByUser(this)
        return readingItems
    }

    Integer getReadingItemCount() {
        return ReadingItem.countByUser(this)
    }

    List<Subscription> getSubscriptions() {
        return Subscription.findAllBySubscriber(this)
    }

    Integer getSubscriptionCount() {
        return Subscription.countBySubscriber(this)
    }

    List<Topic> getSubscribedTopics() {
        return Subscription.findBySubscriber(this)
    }

    static List<String> getRegisteredEmails() {
        return User.list()*.email as List<String>
    }

    List<Resource> getUnreadResources() {
        List<Topic> topicsSubList = getVerySeriousTopics()
        List<Resource> unreadResources = Resource.createCriteria().list {
            inList("topic", topicsSubList)
            'readingItems' {
                eq("isRead", false)
            }
        }
        return unreadResources
    }

    Integer totalUnreadResources() {
        Integer totalUnreadResources = Resource.createCriteria().count {
            inList("topic", getVerySeriousTopics())
            'readingItems' {
                eq("isRead", false)
            }
        }
        return totalUnreadResources
    }

    String toString() {
        fullName
    }
}
