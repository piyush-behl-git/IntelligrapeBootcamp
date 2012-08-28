package com.ig.bc

import com.ig.bc.vo.ResourceAndCount
import com.ig.bc.vo.TopicResourceCount

class ReadingItemService {

    def userService
    def subscriptionService

    def serviceMethod() {

    }

    def getCurrentLoggedInUserReadingItems(String currentLoggedInUserEmail) {
        User currentLoggedInUserInstance = userService.getCurrentUser(currentLoggedInUserEmail)
        List<ReadingItem> currentLoggedInUserReadingItems = ReadingItem.findAllByUser(currentLoggedInUserInstance)
        return currentLoggedInUserReadingItems
    }

    def countCurrentLoggedInUserTotalReadingItems(String currentLoggedInUserEmail) {
        User currentLoggedInUser = userService.getCurrentUser(currentLoggedInUserEmail)
        Integer currentUserTotalReadingItems = ReadingItem.createCriteria().count {
            eq("user", currentLoggedInUser)
        }
        return currentUserTotalReadingItems
    }

    def getAllUnreadResourcesAndEmail() {
        def userEmailAndUnreadResources = ReadingItem.createCriteria().list {
            projections {
                groupProperty('user')
            }
        }
    }

    def getMostReadResourcesForTopic(Topic topic) {
        Set<Resource> topicSpecificSubscribedResourceList = topic.resources
        def resourcesAndCounts = ReadingItem.createCriteria().list {
            projections {
                groupProperty("resource")
                count("resource", "r")
            }
            inList("resource", topicSpecificSubscribedResourceList)
            maxResults 10
            order("r", "desc")
        }
        List<ResourceAndCount> resourceAndReadingCountList = []
        for (resourceAndCount in resourcesAndCounts) {
            Resource resource = resourceAndCount.first()
            Integer readCount = resourceAndCount.last()
            ResourceAndCount resourceAndReadCount = new ResourceAndCount(resource: resource, readCount: readCount)
            resourceAndReadingCountList<<resourceAndReadCount
        }
        return resourceAndReadingCountList
    }

    def currentUserSubscribedTopicsMostReadResources(String currentLoggedInUserEmail) {
        List<TopicResourceCount> topicResourceCountList = []
        def topics = subscriptionService.getCurrentLoggedInUserAllSubscribedTopics(currentLoggedInUserEmail)
        for (topic in topics) {
            List<ResourceAndCount> resourceAndCountList = getMostReadResourcesForTopic(topic)
            println topic
            println resourceAndCountList
            TopicResourceCount topicResourceCount = new TopicResourceCount(topic: topic, resourceAndCountList: resourceAndCountList)
            topicResourceCountList<<topicResourceCount
        }
        return topicResourceCountList
    }

}
