package com.ig.bc

class UtilController {

    def userService

    def index() {
       userService.createUser()
    }
}
