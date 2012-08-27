package com.ig.bc

class ResourcesService {

    def subscriptionService

    def serviceMethod() {

    }

    def getCurrentUserResources(String currentUserEmail) {
        List<Topic> currentUserSubscribedTopics = subscriptionService.getCurrentUserSubscribedTopics(currentUserEmail)
        List<Resource> currentUserResourceList = currentUserSubscribedTopics.collect { topic ->
            topic.resources
        }
        return currentUserResourceList
    }
}

