package com.ig.bc

class UserService {
    def resourceService

    def createUser() {
        User user = new User(fullName: "Ramandeep Sodhi", password: "123", confirmPassword: "123", email: "raman@ig.com", isMale: true);
        user.save(failOnError: true)
        resourceService.myFunction()
    }
}
