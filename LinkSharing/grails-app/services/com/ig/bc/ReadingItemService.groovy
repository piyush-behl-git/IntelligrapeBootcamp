package com.ig.bc

class ReadingItemService {

    def serviceMethod() {

    }
    def initializeReadingItems() {

        println "Initializing reading items... "
        println "Searching for topics..."

        List topics = Topic.list()

        println "Topics found."
        println topics*.name


        for (topic in topics) {

            println "Searching for resources in ${topic}..."

            Set<Resource> resources = topic.resources

            println "Search completed."
            println resources*.title

            User subscriber

            println "Seaching for subscriptions..."

            Set<Subscription> subscriptions = topic.subscriptions

            println "Subscriptions found."
            println subscriptions

            for (subscription in subscriptions) {

                println "Searching for subscriber..."

                subscriber = subscription.subscriber

                println "${subscriber.fullName} found."
                println "Initializing reading items..."

                for (resource in resources) {

                    ReadingItem readingItem = new ReadingItem(user: subscriber, resource: resource, isFavorite: false, isRead: false)
                    readingItem.save(failOnError: true)

                    println "Item ${readingItem.id} intialized with ${resource.title} by ${subscriber.fullName}"
                }
            }
        }
    }

    void markRandomlyAsRead(list) {

        println "fetxhing all reading items..."

        List<ReadingItem> readingItems = ReadingItem.list()

        println "fetch completed."
        println readingItems
        println "shuffling list..."

        Collections.shuffle(readingItems)

        println "Shuffle completed."
        println readingItems
        println "Randomly marking reading items as isRead"

        for (index in list) {
            ReadingItem readingItem = readingItems[index]
            readingItem.isRead = true
            readingItem.save(failOnError: true)
        }

        println "Mark isRead completed"
    }
}
