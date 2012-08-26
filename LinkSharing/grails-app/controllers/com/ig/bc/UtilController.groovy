package com.ig.bc

import com.ig.bc.enums.Seriousness
import com.ig.bc.enums.Visibility

class UtilController {

    def readingItemService

    def index() { }

    def checkBaseDir() {
        render grailsApplication.config.uploadPath
    }

    def test() {
        String currentUserEmail = session.email
        readingItemService.getCurrentUserResources(currentUserEmail)
    }

    def currentUser() {
        render("${session.email}")
    }
}
