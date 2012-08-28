package com.ig.bc

import com.ig.bc.co.InvitationCommand

class InvitationService {
    static transactional = false

    def asynchronousMailService

    void invite() {

    }

    def invitation(InvitationCommand invitation) {

        asynchronousMailService.sendAsynchronousMail {
            to invitation.email1, invitation.email2, invitation.email3
            subject "Linksharing Invitation"
            body invitation.content

        }
        if (invitation.hasErrors()) {
            log.info "Errors in BookCommand : " + invitation.errors
        }
    }

    def sendInvites() {

    }
}
