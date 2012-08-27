package com.ig.bc

class ReadingItemService {

    def userService

    def serviceMethod() {

    }





    def getCurrentUserResources(String currentUserEmail) {
        User currentUserInstance = userService.getCurrentUser(currentUserEmail)
        List<ReadingItem> currentUserReadingItems = ReadingItem.findAllByUser(currentUserInstance)
        List<Resource> currentUserResources = currentUserReadingItems.collect { readingItem ->
            readingItem.resource
        }
        return currentUserReadingItems
    }

    def currentUserTotalResources(String currentUserEmail) {
    }

}
