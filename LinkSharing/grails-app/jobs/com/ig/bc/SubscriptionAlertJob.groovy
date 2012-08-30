package com.ig.bc



class SubscriptionAlertJob {
    def asynchronousMailService
    def subscriptionService

    static triggers = {
        simple repeatInterval: 1000*60
//        cron(name: "subscriptionAlertTrigger", cronExpression: "0 0 22 * * ?")
    }

    def execute() {
        subscriptionService.subscriptionAlerts()
    }
}
