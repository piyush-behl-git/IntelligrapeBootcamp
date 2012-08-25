package com.ig.bc

import com.ig.bc.co.FileCommand

class DocumentResourceService {

    def serviceMethod() {

    }

    def saveDocumentDetails(FileCommand fileCommand, params) {
        DocumentResource documentResource = new DocumentResource(fileName:'test', title: 'test', contentType: 'test', summary: 'test')
        documentResource.save(failOnError: true)
        return documentResource
    }

    def uploadDocument(FileCommand fileCommand) {
    }
}
