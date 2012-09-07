package com.ig.bc

class AdminController {

    def index() {
        redirect(action: "stats")
    }

    def beforeInterceptor = [action: this.&checkAdmin, except: 'accessDenied']

    private checkAdmin() {
        boolean accessForStats;
        if (session.email == "admin@intelligrape.com")
            accessForStats = true
        else
            accessForStats = false
        if (!accessForStats) {
            redirect action: "accessDenied"
        }
        return accessForStats
    }

    def stats() {
        [userInstanceList: User.getAllUsers(), userInstanceTotal: User.count]
    }

    def loadSubscriptions() {
        Long id = Long.parseLong(params.id)
        User subscriber = User.get(id)
        List<Subscription> subscriptions = []
        if (subscriber)
              subscriptions = subscriber.subscriptions as List<Subscription>
        render template: '/admin/subscriptionList', model: [list: subscriptions]
    }

    def accessDenied() {
        render view: 'acessdenied'
    }
}
