package com.ig.bc

class LinkResource extends Resource {
    String url

    static constraints = {
        url(url: true, blank: false, nullable: false)
    }
}
