package com.ig.bc

class LoginController {

    static  defaultAction = "index"


    def index() {
        if (!session.email)
            redirect(uri: "/pogin.html")
    }

    def login() {
        String email
        String password

        email = params.email
        password = params.password

        User user = User.findByEmailAndPassword(email,password)
        if (user==null) {
            println "Invalid login!"
            redirect action: 'register', params: [email:email]
        }else {
            println "Login Successful...:)"
        }
        println params
        redirect(controller: "user", action: "loginHandler", params: params)
    }

    def logout() {
        session.invalidate()
        redirect(action: "index")
    }

    def register() {

    }
}
