package com.ig.bc

import com.ig.bc.vo.TopicResourceCount
import com.ig.bc.vo.ResourceVO

class ReadingItemService {

    def userService
    def subscriptionService

    def serviceMethod() {

    }

    //TODO move to User domain
    def getCurrentLoggedInUserReadingItems(String currentLoggedInUserEmail) {
        User currentLoggedInUserInstance = userService.getCurrentUser(currentLoggedInUserEmail)
        List<ReadingItem> currentLoggedInUserReadingItems = ReadingItem.findAllByUser(currentLoggedInUserInstance)
        return currentLoggedInUserReadingItems
    }

    //TODO move to User domain
    def countCurrentLoggedInUserTotalReadingItems(String currentLoggedInUserEmail) {
        User currentLoggedInUser = userService.getCurrentUser(currentLoggedInUserEmail)
        Integer currentUserTotalReadingItems = ReadingItem.createCriteria().count {
            eq("user", currentLoggedInUser)
        }
        return currentUserTotalReadingItems
    }

    //TODO move to Topic domain & specify <Type> in list
    List getMostReadResourcesForTopic(Topic topic) {
        Set<Resource> topicSpecificSubscribedResourceList = topic.resources
        List resourcesAndCounts = ReadingItem.createCriteria().list {
            projections {
                groupProperty("resource")
                count("resource", "r")
            }
            inList("resource", topicSpecificSubscribedResourceList)
            //TODO no magic
            maxResults 10
            order("r", "desc")
        }
        List<ResourceVO> resourceAndReadingCountList = []
        for (resourceAndCount in resourcesAndCounts) {
            Resource resource = resourceAndCount.first()
            Integer readCount = resourceAndCount.last()
            ResourceVO resourceVO = new ResourceVO(resource: resource, subscriptionCount: readCount)
            resourceAndReadingCountList << resourceVO
        }
        //TODO name refactor
        return resourceAndReadingCountList
    }

    //TODO def ??????
    def currentUserSubscribedTopicsMostReadResources(String currentLoggedInUserEmail) {
        List<TopicResourceCount> topicResourceCountList = []
        //TODO def ?????
        def topics = subscriptionService.getCurrentLoggedInUserAllSubscribedTopics(currentLoggedInUserEmail)
        for (topic in topics) {
            List<ResourceVO> resourceAndCountList = getMostReadResourcesForTopic(topic)
            TopicResourceCount topicResourceCount = new TopicResourceCount(topic: topic, resourceAndCountList: resourceAndCountList)
            topicResourceCountList << topicResourceCount
        }
        return topicResourceCountList
    }

    //TODO def ???? also move to user
    def getAllUnreadReadingItems(User subscriber, List<Resource> topicResources) {
        List<ReadingItem> unreadReadingItems = ReadingItem.createCriteria().list {
            eq("user", subscriber)
            inList("resource", topicResources)
        }
        return unreadReadingItems
    }
}
