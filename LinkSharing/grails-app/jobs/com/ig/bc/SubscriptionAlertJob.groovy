package com.ig.bc



class SubscriptionAlertJob {
    def asynchronousMailService
    def resourceService

    static triggers = {
//        simple repeatInterval: 1000*60
//        cron(name: "subscriptionAlertTrigger", cronExpression: "0 0 22 * * ?")
    }

    def execute() {
        resourceService.subscriptionAlerts()
    }
}
