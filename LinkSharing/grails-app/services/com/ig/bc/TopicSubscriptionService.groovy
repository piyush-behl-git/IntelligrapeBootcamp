package com.ig.bc

import com.ig.bc.enums.Seriousness
import com.ig.bc.enums.Visibility

class TopicSubscriptionService {
    def userService

    def serviceMethod() {

    }

    def getAllSubscriptions() {
        List<Subscription> subscriptionList = Subscription.list()
        subscriptionList
    }

    def getAllSubscribedTopicsByUser(String currentUserEmail) {
        User user = userService.getCurrentUser(currentUserEmail)
        List<Topic> currentUserSubscribedTopics = Subscription.createCriteria().list {

        }
    }
}
