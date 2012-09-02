class BootStrap {

    def bootstrapService

    def init = { servletContext ->
        bootstrapService.initializeApplicationWithTestData()
    }
    def destroy = {
    }
}