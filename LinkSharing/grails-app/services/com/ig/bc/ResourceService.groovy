package com.ig.bc

class ResourceService {

    def subscriptionService
    def asynchronousMailService
    def groovyPageRenderer
    def userService

    //TODO def ????? move to User domain
    def getCurrentUserResources(String currentUserEmail) {
        List<Topic> currentUserSubscribedTopics = subscriptionService.getCurrentUserSubscribedTopics(currentUserEmail)
        List<Resource> currentUserResourceList = currentUserSubscribedTopics.collect { topic ->
            topic.resources
        }
        return currentUserResourceList
    }

    //TODO def ??? move to User domain
    def allUnreadResources(String email) {
        List<Topic> topicsSubList = subscriptionService.getAllImportantTopics(email)
        List<Resource> unreadResources = Resource.createCriteria().list {
            inList("topic", topicsSubList)
            'readingItems' {
                eq("isRead", false)
            }
        }
        return unreadResources
    }

    //TODO def ???? also move to User domain
    def allUpdatesAboutUserSubscriptions(String email) {

        List<Topic> topicsSubList = subscriptionService.getAllImportantTopics(email)
        List<Resource> newResources = Resource.createCriteria().list {
            inList("topic", topicsSubList)
            lt("dateCreated", new Date())
        }
        Map<Topic, Resource> topicResourceMap = newResources.groupBy { resource ->
            resource.topic
        }
        return topicResourceMap
    }
    //TODO name refactor, userService to User domain
     def subscriptionAlerts() {
        //TODO move to user domain
        List<String> emails = userService.getAllRegisteredEmails()
        for (email in emails) {
            List<Resource> unreadResourceList = allUnreadResources(email)
            //TODO unused variable
            Integer totalResources = unreadResourceList.size()
            //TODO make separate function
            asynchronousMailService.sendAsynchronousMail {
                to "${email}"
                subject "Subscription Reminder Link Sharing"
                html "${groovyPageRenderer.render(template: "/reminder/resources", model: [unreadResourceList: unreadResourceList])}"
            }
        }
    }
}

