package com.ig.bc

import com.ig.bc.enums.Seriousness
import com.ig.bc.enums.Visibility

class SubscriptionService {

    def readingItemService

    def subscribe(User subscriber, List<Topic> topics) {
        String errors = ""
        topics.each {  topic ->
            Subscription subscription = new Subscription(subscriber: subscriber, topic: topic, seriousness: Seriousness.SERIOUS)
            if (topic.visibility == Visibility.PUBLIC || topic.owner == subscriber) {
                readingItemService.initializeReadingItems(topic, subscriber)
                subscription.save(failOnError: true)
            }
            else {
                errors += "Cannot Subscribe ${topic.name}" + "\n"
            }
        }
        return errors
    }

    def unsubscribe(User subscriber, List<Topic> topics) {
        List<Subscription> subscriptions = Subscription.findAllBySubscriberAndTopicInList(subscriber, topics)

        List<ReadingItem> readingItems = ReadingItem.createCriteria().list {
            eq('user', subscriber)
            'resource' {
                inList('topic', topics)
            }
        }
        readingItemService.removeReadingItems(readingItems)
        subscriptions.each {subscription ->
            subscription.delete(flush: true)
        }

    }
}
