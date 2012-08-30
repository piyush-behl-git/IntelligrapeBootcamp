package com.ig.bc

import com.ig.bc.enums.Visibility
import com.ig.bc.enums.Seriousness

class TopicService {

    def userService

    def serviceMethod() {

    }

    def getCurrentLoggedInUserAllOwnedOrPublicTopics(String currentLoggedInUserEmail) {
        User currentLoggedInUser = userService.getCurrentUser(currentLoggedInUserEmail)
        List<Topic> currentOwnedOrPublicTopics = Topic.findAllByOwnerOrVisibility(currentLoggedInUser, Visibility.PUBLIC)
        return currentOwnedOrPublicTopics
    }

    def getAllResources(Topic topic) {
        Set resources = topic.resources
        return resources
    }
}