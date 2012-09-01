package com.ig.bc

class UserService {

    def validateUser(String email, String password) {
        boolean userStatus
        User user = User.findByEmailAndPassword(email, password)
        if (user)
            userStatus = true
        else
            userStatus = false
        return userStatus
    }

    //TODO move to user domain
    List<String> getAllRegisteredEmails() {
        List<User> userList = User.list()
        List<String> registeredEmails = userList*.email
        return registeredEmails
    }
}
