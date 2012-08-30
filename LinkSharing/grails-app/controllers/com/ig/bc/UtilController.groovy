package com.ig.bc

class UtilController {

    def resourceService

    def index() {
       resourceService.subscriptionAlerts()
    }
}
