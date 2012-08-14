package com.ig.bc

class User {
    String name
    Integer age
    Long lastLogin
    Date dateCreated
    Date lastUpdated

    static constraints = {
        lastLogin(nullable: true)
        dateCreated(nullable: true)
        lastUpdated(nullable: true)
    }
}
