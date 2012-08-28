package com.ig.bc



class SubscriptionAlertJob {

    def readingItemService

    static triggers = {
        cron(name: "subscriptionAlertTrigger", cronExpression: "0 0 22 * * ?")
    }

    def execute() {
        asynchronousMailService.sendAsynchronousMail {
            to
            subject "Linksharing Invitation"
            body invitation.content

        }
    }
}
