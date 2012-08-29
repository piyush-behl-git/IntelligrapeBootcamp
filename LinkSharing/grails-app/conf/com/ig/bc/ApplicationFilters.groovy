package com.ig.bc

class ApplicationFilters {

    def filters = {

        allExceptLoginRegisterLoginHandlerRegisterHandler(controller: '*', action: 'checkEmailUrl|login|loginHandler|register|registrationHandler', invert: true) {
            before = {
                if (!session.email) {
                    if (actionName) {
                        redirect controller: 'login', action: 'login'
                        return false
                    }else {
                        redirect controller: 'login', action: 'login'
                    }
                }
            }
        }

        blockAllDeleteActions(controller: 'user', action: 'delete') {
            before = {
                redirect(controller: "admin", action: "accessDenied")
                return false
            }
        }

        blockAccessToCreateAndUpdateUser(controller: "user", action: "create|edit|update") {
            before = {
                if (session.email != "admin@intelligrape.com") {
                    redirect(controller: "admin", action: "accessDenied")
                    return false
                }
                return
            }
        }

        blockAccessToUserTopicAndResourceList(controller: "user", action: 'list') {
            before = {
                if (session.email != "admin@intelligrape.com") {
                    redirect(controller: "admin", action: "accessDenied")
                    return false
                }
                return
            }
        }

        all(controller: '*', action: '*') {
            before = {

            }
            after = { Map model ->

            }
            afterView = { Exception e ->

            }
        }
    }
}
