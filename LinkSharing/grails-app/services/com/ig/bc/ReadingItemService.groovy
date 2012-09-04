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

    def markFav(Long id) {
        ReadingItem readingItem = ReadingItem.get(id)
        readingItem.isFavorite = true
        readingItem.save(failOnError: true)
    }

    def unmarkFav(Long id) {
        ReadingItem readingItem = ReadingItem.get(id)
        readingItem.isFavorite = false
        readingItem.save(failOnError: true)
    }

    def markCurrentRead(Long id) {
        ReadingItem readingItem = ReadingItem.get(id)
        readingItem.isRead=true
        readingItem.save(failOnError: true)
    }

    def markCurrentUnread(Long id) {
        ReadingItem readingItem = ReadingItem.get(id)
        readingItem.isRead=false
        readingItem.save(failOnError: true)
    }
}
