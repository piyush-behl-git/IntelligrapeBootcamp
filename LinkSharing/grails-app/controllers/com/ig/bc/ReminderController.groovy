package com.ig.bc

class ReminderController {

    def showUnreadResources() {
        String currentLoggedInUserEmail = session.email
        User currentUser = User.findByEmail(currentLoggedInUserEmail)
        List<Resource> unreadResources = currentUser.getUnreadResources()
        Integer totalUnreadResources = currentUser.totalUnreadResources()
        render(view: "showUnreadResources", model:[unreadResourceList: unreadResources, totalResources: totalUnreadResources])
    }
}
