package com.ig.bc.co

import org.springframework.web.multipart.MultipartFile

class FileCommand {
    MultipartFile file
    static constraints = {
        file(validator: {val->
           if(val.coontentType=="application/pdf") {
              return true
           }
            else {
               flash.message="Only pdfs are allowed."
           }
        })
    }
}
