package com.ig.bc

import com.ig.bc.enums.Seriousness
import com.ig.bc.enums.Visibility

class TopicSubscriptionService {
    def userService

    //TODO move to Subscription domain as static method
    def getAllSubscriptions() {
        List<Subscription> subscriptionList = Subscription.list()
        subscriptionList
    }

    //TODO move to User domain
    def getAllSubscribedTopicsByUser(String currentUserEmail) {
        User user = userService.getCurrentUser(currentUserEmail)
        List<Topic> currentUserSubscribedTopics = Subscription.createCriteria().list {

        }
    }
}
