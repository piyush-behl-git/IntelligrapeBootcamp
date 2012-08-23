package com.ig.bc

class InvitationService {
    static transactional = false

    def serviceMethod() {

    }

    void invite() {

    }

    def invitation(InvitationCommand invitation) {

        println invitation.email1
        println invitation.email2
        println invitation.email3

        if (invitation.hasErrors())     {
            println invitation.errors
            log.info "Errors in BookCommand : " + invitation.errors
        }
    }
}
