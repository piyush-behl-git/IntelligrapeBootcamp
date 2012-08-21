package com.ig.bc

class LoginController {


    def beforeInterceptor = [action: this.&auth, except: 'logout']
/*
    def beforeInterceptor = {
        Boolean isActionRestricted = ['logout'].contains(actionName)
        if (!isActionRestricted && session.email)             {
            redirect controller: "user", action: "dashboard"
            return false
        }*/
//        render( view: "login")



    private auth() {
        println session.email
        if (session.email) {
            redirect controller: "user", action: "dashboard"
            return false
        }
    }

    def index() {
          render(view: 'login')
    }

    def login() {
        String email
        String password

        email = params.email
        password = params.password

        User user = User.findByEmailAndPassword(email,password)
        if (user) {
            println "Login Successful...:)"
            println params
            redirect(controller: "user", action: "loginHandler", params: params)

        }else {
            println "Invalid login!"
            redirect action: 'register', params: [email:email]
        }
    }

    def register() {

    }

    def logout() {
        session.invalidate()
        redirect(action: 'index')
    }
}
