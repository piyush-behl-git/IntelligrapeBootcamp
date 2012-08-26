package com.ig.bc

class LoginController {

    def beforeInterceptor = [action: this.&checkSession, except: 'logout']

    private checkSession() {
        boolean returnStatus
        if (session.email) {
            redirect(controller: "user", action: "dashboard")
            returnStatus=false
        }else {
            returnStatus=true
        }
        return returnStatus
    }

    def index() {

    }

    def login() {
        render(view: 'login')
    }

    def register() {

    }

    def logout() {
        session.invalidate()
        redirect(action: 'login')
    }
}
