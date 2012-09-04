package com.ig.bc

import com.ig.bc.dto.TopicResourceDTO
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

    Map<Topic, Resource> getSubscriptionUpdates() {
        List<Topic> verySeriousTopics = getVerySeriousTopics()
        List<Resource> newResources = Resource.createCriteria().list {
            inList("topic", verySeriousTopics)
            lt("dateCreated", new Date())
        }
        Map<Topic, List<Resource>> topicResource = newResources.groupBy { resource ->
            resource.topic
        }
        return topicResource
    }

    List<Resource> getResources() {
        return Resource.findAllByOwner(this)
    }

    List<DocumentResource> getDocumentResources() {
        List<Resource> resources = getResources()
        List<DocumentResource> documentResources = []
        resources.each {  resource->
            if(resource.instanceOf(DocumentResource))        {
                documentResources << (DocumentResource) resource
            }
        }
        return documentResources



    }

    List<LinkResource> getLinkResources() {
        List<Resource> resources = getResources()
        List<LinkResource> linkResources = []
        resources.each {  resource->
            if(resource.instanceOf(LinkResource))        {
                linkResources << (LinkResource) resource
            }
        }
        return linkResources
    }

    List<ReadingItem> getReadingItems() {
        List<ReadingItem> readingItems = ReadingItem.findAllByUser(this)
        return readingItems
    }

    Integer getReadingItemCount() {
        return ReadingItem.countByUser(this)
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

    List<TopicResourceDTO> getTopicsMostReadResources() {
        List<TopicResourceDTO> topicsMostReadResources = []
        for (topic in this.topics) {
            topicsMostReadResources << new TopicResourceDTO(topic: topic, resources: topic.getMostReadResources())
        }
        return topicsMostReadResources
    }

    List<ReadingItem> getUnreadReadingItems() {
        List<ReadingItem> unreadReadingItems = ReadingItem.findAllByUserAndIsRead(this, false)
        return unreadReadingItems
    }

    List<Topic> getOwnedTopics() {
        List<Topic> ownedTopics = Topic.createCriteria().list {
            eq('owner', this)
        }
        return ownedTopics
    }

    String toString() {
        fullName
    }
}