class BootStrap {

    def bootstrapService

    def init = { servletContext ->

       bootstrapService.initializeData()

    }
    def destroy = {
    }
}