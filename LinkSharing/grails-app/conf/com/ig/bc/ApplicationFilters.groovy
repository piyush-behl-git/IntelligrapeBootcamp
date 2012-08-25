package com.ig.bc

class ApplicationFilters {

    def filters = {

        blockAllDeleteActions(controller: 'user', action: 'delete') {
            before = {
                redirect(controller: "admin", action: "accessDenied")
                return false
            }
        }

        blockAccessToCreateAndUpdateUser(controller: "user", action: "create|edit|update|save") {
            before = {
                if (session.email != "admin@intelligrape.com") {
                    redirect(controller: "admin", action: "accessDenied")
                    return false
                }
                return
            }
        }

        blockAccessToUserTopicAndResourceList(action: 'list') {
            before = {
                if (session.email != "admin@intelligrape.com") {
                    redirect(controller: "admin", action: "accessDenied")
                    return false
                }
                return
            }
        }

        allExceptLoginRegisterLoginHandlerRegisterHandler(controller: '*', action: 'login|loginHandler|register', invert: true) {
            before = {
                if (!session.email) {
                    redirect controller: 'login', action: 'login'
                    return false
                }
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
