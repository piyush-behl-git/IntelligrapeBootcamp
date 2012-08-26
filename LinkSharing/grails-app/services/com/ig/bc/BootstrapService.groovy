package com.ig.bc

class BootstrapService {

    def topicSubscriptionService
    def readingItemService
    def resourcesService
    def userService

    def serviceMethod() {

    }

    def initializeData() {
        userService.initializeUsers()
        topicSubscriptionService.initializeTopics()
        resourcesService.initializeResources()
        readingItemService.initializeReadingItems()
        readingItemService.markRandomlyAsRead([1, 5, 8])
    }
}