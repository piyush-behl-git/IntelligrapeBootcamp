package com.ig.bc

class LoginController {
    static final dateFormat = 'dd/mm/yyyy'

    def beforeInterceptor = [action: this.&checkSession, except: 'logout']

    private checkSession() {
        boolean returnStatus
        if (session.email) {
            redirect(controller: "user", action: "dashboard")
            returnStatus = false
        } else {
            returnStatus = true
        }
        return returnStatus
    }

    def login() {
        render(view: 'login')
    }

    def register() {

    }

    def registrationHandler() {
        params.dateOfBirth = Date.parse(dateFormat, params.dateOfBirth)
        User userInstance = new User(params);
        userInstance.save(failOnError: true)
        flash.message = "User registered successfully."
        render(view: "register")
    }

    def checkEmailUrl() {
        String renderStatus
        String emailId = params.email
        User user = User.findByEmail(emailId)
        if (user) {
            renderStatus = "false"
        }
        else
            renderStatus = "true"
        render(renderStatus)
    }

    def logout() {
        session.invalidate()
        redirect(action: 'login')
    }
}
