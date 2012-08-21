package com.ig.bc

class AdminController {

    def index() { }

    def beforeInterceptor = {
        String email = session.email
        println email
        if (email != "admin@intelligrape.com") {
            println "Interceptor"
            render view:'acessdenied'
            return false
        } else {
            if (!email) {
                println "Please enter your email"
                return false
            }
        }
    }

    def stats() {
        Integer numberOfUsers = User.count
        Integer numberOfSubscriptions = Subscription.count
        render view: "stats", model: [numberOfUsers: numberOfUsers, numberOfSubscriptions: numberOfSubscriptions]
    }
}
