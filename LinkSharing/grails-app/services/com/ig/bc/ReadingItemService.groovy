package com.ig.bc

class ReadingItemService {

    def initializeReadingItems(Topic topic, User subscriber) {
        Set<Resource> resources = topic.resources
        resources.each { resource ->
            ReadingItem readingItem = new ReadingItem(user: subscriber, resource: resource, isFavorite: false, isRead: false)
            readingItem.save(failOnError: true)
        }
    }

    def removeReadingItems(List<ReadingItem> readingItems) {
        readingItems.each { readingItem ->
            readingItem.delete(flush: true)
        }
    }

    def markUnmarkFav(Long id) {
        String returnStatus
        ReadingItem readingItem = ReadingItem.get(id)
        if (readingItem.isFavorite) {
            readingItem.isFavorite = false
            returnStatus=false
        } else {
            readingItem.isFavorite = true
            returnStatus = "true"
        }
        readingItem.save(failOnError: true)
        return returnStatus
    }

    def markCurrentRead(Long id) {
        ReadingItem readingItem = ReadingItem.get(id)
        readingItem.isRead = true
        readingItem.save(failOnError: true)
    }
}
