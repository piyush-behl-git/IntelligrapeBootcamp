package com.ig.bc

import grails.converters.JSON

class SearchController {

    def index() { }

    def searchUsers() {
        List<String> userNames = User.findAllByFullNameLike("%${params.searchField_startsWith}%")*.fullName
        List<String> topicNames = Topic.findAllByName("%${params.searchField_startsWith}%")*.name
        List<String> results = []
        results = userNames + topicNames
        render([results: results] as JSON)
    }
}
