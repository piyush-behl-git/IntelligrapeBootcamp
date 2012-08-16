package com.ig.bc

abstract class Resource {
    Date dateCreated
    Date lastUpdated
    String title
    String summary
    static hasMany = [readingItems: ReadingItem]
    static constraints = {
        summary(size:0..1024)
        title(unique: true, blank: false, nullable: false)
    }
}
