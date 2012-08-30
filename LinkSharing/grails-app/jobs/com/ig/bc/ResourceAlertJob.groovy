package com.ig.bc



class ResourceAlertJob {
    def resourceService

    static triggers = {
      //simple repeatInterval: 5000l // execute job once in 5 seconds
    }

    def execute() {
        resourceService.allUpadatesAboutUserSubscriptons()
    }
}
