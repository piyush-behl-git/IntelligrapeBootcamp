package com.ig.bc.co

class InvitationCommand {
    String email1
    String email2
    String email3
    static constraints = {
        email1(email: true)
        email2(email: true)
        email3(email: true)
    }
}