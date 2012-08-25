package com.ug.bc

import com.ig.bc.ReadingItem
import com.ig.bc.User
import com.ig.bc.Subscription
import com.ig.bc.Topic

class ApplicationTagLib {

    static  namespace = "ls"

    def unreadItems = {  attrs->
        println "In taglib"
        User user = User.findByEmail(session.email)
        List<ReadingItem> unreadItems = ReadingItem.findAllByUserAndIsRead(user, false, [max: attrs.count])
        out << render(template: "/readingItem/list", model: [list: unreadItems])
    }

    def subscribedTopics = {  attrs->
        User user = User.findByEmail(session.email)
        List<Subscription> subscriptions = Subscription.findAllBySubscriber(user)
        List<Topic> subscribedTopics = subscriptions*.topic
        out << render(template: "/topic/list", model: [list: subscribedTopics])
    }

    def ownedTopics = { attrs, body->
        out << "<strong><em>" << body() << "</strong></em>"
        User user = User.findByEmail(session.email)
        List<Topic> topics = Topic.findAllByOwner(user, [max:  attrs.count])
        out << render(template: "/topic/list", model: [list: topics])

    }

    def highestSubscribedTopic = { attrs->

    }

    def formattedDate = { attrs->
        Date date = attrs.date
        if (date!=null)
        out << date.format("dd/MM/yyyy")
        else
           out<<date

    }
}
