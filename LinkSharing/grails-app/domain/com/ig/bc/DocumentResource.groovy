package com.ig.bc

class DocumentResource extends Resource {
    String fileName
    String contentType
    static constraints = {
        fileName(nullable: false, blank: false)
        contentType(nullable: true, blank: false)
    }
    String toString() {
        fileName
    }
}
