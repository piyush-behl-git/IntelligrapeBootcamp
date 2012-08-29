package com.ig.bc

class UserService {

    def serviceMethod() {

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

    List<String> getAllRegisteredEmails() {
        List<User> userList = User.list()
        List<String> registeredEmails = userList*.email
        return registeredEmails
    }
}
