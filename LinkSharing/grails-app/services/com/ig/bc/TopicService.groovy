package com.ig.bc

import com.ig.bc.enums.Visibility
import com.ig.bc.enums.Seriousness

class TopicService {

    def userService

    //TODO move to user domain & return-type
    def getCurrentLoggedInUserAllOwnedOrPublicTopics(String currentLoggedInUserEmail) {
        User currentLoggedInUser = userService.getCurrentUser(currentLoggedInUserEmail)
        List<Topic> currentOwnedOrPublicTopics = Topic.findAllByOwnerOrVisibility(currentLoggedInUser, Visibility.PUBLIC)
        return currentOwnedOrPublicTopics
    }

    //TODO move to Topic domain & def ?????????
    def getAllResources(Topic topic) {
        Set resources = topic.resources
        return resources
    }
}
