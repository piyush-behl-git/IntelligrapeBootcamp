package com.ig.bc

class ReminderController {
    def resourcesService
    def userService
    def asynchronousMailService

    def index() {

    }

    def unreadResources() {
        String email = session.email
        List<Resource> unreadResourceList = resourcesService.allUnreadResources(email)
        Integer totalResources = unreadResourceList.size()
        render(view: "unreadResources", model:[unreadResourceList: unreadResourceList, totalResources: totalResources])
    }
}
