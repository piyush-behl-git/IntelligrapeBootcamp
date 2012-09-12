package com.ig.bc.co

class EmailCO {
    String email
    static constraints = {
         email(nullable: false, blank: false, email:true)
    }
}
