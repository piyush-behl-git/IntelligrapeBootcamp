package com.ig.bc

class UserService {

    def serviceMethod() {

    }

    def initializeUsers() {

        println "Initializing Users"

        def vijay = new User(email: 'javajooba@gmail.com', password: 'admin123', confirmPassword: 'admin123', fullName: "Vijay Kumar", isMale: true)
        vijay.save(failOnError: true)

        def puneet = new User(email: 'puneet.behl007@gmail.com', password: '123456789', confirmPassword: '123456789', fullName: 'Puneet Behl', isMale: true)
        puneet.save(failOnError: true)

        def admin = new User(email: 'admin@intelligrape.com', password: 'igdefault', confirmPassword: 'igdefault', fullName: 'Administrator', isMale: true)
        admin.save(failOnError: true)

        def hr = new User(email: 'hr@ig.com', password: 'hr', confirmPassword: 'hr', fullName: 'hr', isMale: true)
        hr.save(failOnError: true)

        5.times {
            def user = new User(email: it + "@intelligrape.com", password: 'igdefault', confirmPassword: 'igdefault', fullName: "Name${it}", isMale: false)
            println user.email
            user.save(failOnError: true)
        }
        println "Users initialized"
    }

    def validateUser(String email, String password) {
        boolean userStatus
        User user = User.findByEmailAndPassword(email, password)
        if (user)
            userStatus = true
        else
            userStatus = false
        return userStatus
    }

    User getCurrentUser(String currentUserEmail) {
        User currentUser = User.findByEmail(currentUserEmail)
        return currentUser
    }
}
