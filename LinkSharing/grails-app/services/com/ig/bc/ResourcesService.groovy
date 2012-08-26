package com.ig.bc

class ResourcesService {

    def subscriptionService

    def serviceMethod() {

    }

    void initializeResources() {
        println "Seaching for all topics....."
        List topics = Topic.list()

        println "Topics found"
        println topics*.name

        LinkResource linkResource
        DocumentResource documentResource

        for (topic in topics) {
            int count = 1
            println "Adding document resource ..."
            documentResource = new DocumentResource(fileName: "Doc" + topic.name)
            documentResource.save(failOnError: true)
            topic.addToResources(documentResource)
            topic.save(failOnError: true)
            count++


            println "Adding link resouce to ${topic.name}..."

            10.times {
                linkResource = new LinkResource(title: "${topic.name} ${it}", url: "http://www.enfopedia.com/${it}", summary: "Details of topic ${topic.name} ${topic.id}")
                topic.addToResources(linkResource)

                linkResource.save(failOnError: true)
                println "Resource ${linkResource.title} ${linkResource.url} added successfully..."
            }
            topic.save(failOnError: true)

            println "Resources successfully added to ${topic.name} ;)"
        }
    }

    def getCurrentUserResources(String currentUserEmail) {
        List<Topic> currentUserSubscribedTopics = subscriptionService.getCurrentUserSubscribedTopics(currentUserEmail)
        List<Resource> currentUserResourceList = currentUserSubscribedTopics.collect { topic ->
            topic.resources
        }
        return currentUserResourceList
    }
}

