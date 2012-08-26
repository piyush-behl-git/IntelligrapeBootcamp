package com.ig.bc

class DocumentResourceService {

    def resourcesService

    def serviceMethod() {

    }

    def getCurrentUserDocumentResourceList(String currentUserEmail) {
        List<Resource> currentUserResourceList = resourcesService.getCurrentUserResources(currentUserEmail)
        List<DocumentResource> currentUserDocumentResourceList = currentUserResourceList.collect { resource->
                    if(resource.instanceOf(DocumentResource))
                        return resource
        }
    }
}
