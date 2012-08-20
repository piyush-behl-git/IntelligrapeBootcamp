package com.ig.bc

class AdminController {

    def index() { }

    def beforeInterceptor = {
        String email = session.email
        println email
        if(email!="admin@intelligrape.com")  {
            render("Access Denied!")
            //redirect back to home or input
            return false
        }
        else if (!email){
            println "Please enter your email"
            //redirect back to login
            return false
        }
        return true
    }

    def stats() {
        Integer numberOfUsers = User.count
        Integer numberOfSubscriptions = Subscription.count
        render "No of users : ${numberOfUsers} No of subscriptions : ${numberOfSubscriptions}"
    }
}
