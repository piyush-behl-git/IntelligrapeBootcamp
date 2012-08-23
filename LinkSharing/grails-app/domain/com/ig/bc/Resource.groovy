package com.ig.bc

abstract class Resource {
    Date dateCreated
    Date lastUpdated
    String title
    String summary
    static hasMany = [readingItems: ReadingItem]
    static constraints = {
        summary(size:0..1024, nullable: true)
        title(unique: true, blank: false, nullable: true)
    }
    static mapping = {
        summary(type: 'text')
        tablePerHierarchy false
    }
    String toString() {
        title
    }
}
