package com.ig.bc

import com.ig.bc.enums.Seriousness

class SubscriptionService {

    def readingItemService

    def subscribe(User subscriber, List<Topic> topics) {
        topics.each {  topic->
            Subscription subscription = new Subscription(subscriber: subscriber, topic: topic, seriousness: Seriousness.SERIOUS)
            readingItemService.initializeReadingItems(topic, subscriber)
            subscription.save(failOnError: true)
        }
    }

    def unsubscribe(User subscriber, List<Topic> topics) {
        List<Subscription> subscriptions = Subscription.findAllBySubscriberAndTopicInList(subscriber, topics)

        List<ReadingItem> readingItems = ReadingItem.createCriteria().list {
            eq('user',subscriber)
            'resource' {
                inList('topic',topics)
            }
        }
        readingItemService.removeReadingItems(readingItems)
        subscriptions.each {subscription->
            subscription.delete(flush: true)
        }

    }
}
