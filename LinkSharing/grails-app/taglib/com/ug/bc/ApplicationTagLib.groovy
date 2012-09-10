package com.ug.bc

import com.ig.bc.ReadingItem
import com.ig.bc.User
import com.ig.bc.Subscription
import com.ig.bc.Topic

class ApplicationTagLib {

    def readingItemService
    static namespace = "ls"

    def unreadItems = {  attrs ->
        User user = User.findByEmail(session.email)
        List<ReadingItem> unreadItems = user.getUnreadReadingItems()
        Map readingItemsGroupedByTopic = readingItemService.getTopicReadingItems(unreadItems)
        out << render(template: "/user/readingItems", model: [list: readingItemsGroupedByTopic])
    }

    def subscribedTopics = {  attrs ->
        User user = User.findByEmail(session.email)
        List<Subscription> subscriptions = Subscription.findAllBySubscriber(user)
        List<Topic> subscribedTopics = subscriptions*.topic
        out << render(template: "/user/topics", model: [list: subscribedTopics, listName: attrs.listName])
    }

    def ownedTopics = { attrs, body ->
        out << "<strong><em>" << body() << "</strong></em>"
        User user = User.findByEmail(session.email)
        List<Topic> topics = Topic.findAllByOwner(user, [max: attrs.count])
        out << render(template: "/user/topics", model: [list: topics, listName: attrs.listName])

    }

    def formattedDate = { attrs ->
        Date date = attrs.date
        if (date)
            out << date.format("dd/MM/yyyy")
    }

    def ifAdmin = {attrs, body ->
        if (session.email == "admin@intelligrape.com") {
            out << body()
        }
    }

    def ifLoggedIn = { attrs, body ->
        if (session.email) {
            out << body()
        }
    }
}
