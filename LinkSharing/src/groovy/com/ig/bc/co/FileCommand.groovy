package com.ig.bc.co

import org.springframework.web.multipart.MultipartFile
import grails.validation.Validateable

@Validateable
class FileCommand {
    MultipartFile file
    static constraints = {
        file(validator: {val->
           if(val.contentType=="application/pdf") {
              return true
           }
        })
    }
}
