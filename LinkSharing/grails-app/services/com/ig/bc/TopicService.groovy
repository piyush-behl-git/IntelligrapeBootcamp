package com.ig.bc

import com.ig.bc.enums.Visibility

class TopicService {

    def userService

    def serviceMethod() {

    }

    def getCurrentLoggedInUserAllOwnedOrPublicTopics(String currentLoggedInUserEmail) {
        User currentLoggedInUser = userService.getCurrentUser(currentLoggedInUserEmail)
        List<Topic> currentOwnedOrPublicTopics = Topic.findAllByOwnerOrVisibility(currentLoggedInUser, Visibility.PUBLIC)
        return currentOwnedOrPublicTopics
    }
}
