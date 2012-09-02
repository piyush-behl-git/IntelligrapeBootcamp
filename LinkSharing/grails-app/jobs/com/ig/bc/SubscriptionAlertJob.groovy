package com.ig.bc



class SubscriptionAlertJob {
    def emailNotificationService

    static triggers = {
//        simple repeatInterval: 1000*60
//        cron(name: "subscriptionAlertTrigger", cronExpression: "0 0 22 * * ?")
    }

    def execute() {
        emailNotificationService.subscriptionAlerts()
    }
}
