package com.ig.bc

class ResourceService {

    def subscriptionService

    def getCurrentUserResources(String currentUserEmail) {
        List<Topic> currentUserSubscribedTopics = subscriptionService.getCurrentUserSubscribedTopics(currentUserEmail)
        List<Resource> currentUserResourceList = currentUserSubscribedTopics.collect { topic ->
            topic.resources
        }
        return currentUserResourceList
    }

    def allUnreadResources(String email) {
        List<Topic> topicsSubList = subscriptionService.getAllImportantTopics(email)
        List<Resource> unreadResources = Resource.createCriteria().list {
            inList("topic", topicsSubList)
            'readingItems' {
                eq("isRead", false)
            }
        }
        return unreadResources
    }

    def allUpdatesAboutUserSubscriptions(String email) {

        List<Topic> topicsSubList = subscriptionService.getAllImportantTopics(email)
        List<Resource> newResources = Resource.createCriteria().list {
            inList("topic", topicsSubList)
            lt("dateCreated", new Date())
        }
        Map<Topic, Resource> topicResourceMap = newResources.groupBy { resource ->
            resource.topic
        }
        return topicResourceMap
    }
}

