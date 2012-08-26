package com.ig.bc

class AdminController {

    def index() {
        redirect(action: "stats")
    }

    def beforeInterceptor = [action: this.&checkAdmin, except: 'accessDenied']

    private checkAdmin() {
        boolean accessForStats;
        if (session.email=="admin@intelligrape.com")
            accessForStats=true
        else
            accessForStats=false
        if (!accessForStats) {
             redirect action: "accessDenied"
        }
        return accessForStats
    }

    def stats() {
        Integer numberOfUsers = User.count
        Integer numberOfSubscriptions = Subscription.count
        render view: "stats", model: [numberOfUsers: numberOfUsers, numberOfSubscriptions: numberOfSubscriptions]
    }

    def accessDenied() {
        render view: 'acessdenied'
    }
}
