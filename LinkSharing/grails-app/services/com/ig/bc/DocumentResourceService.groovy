package com.ig.bc

class DocumentResourceService {

    def resourceService

    //TODO move to User domain
    def getCurrentUserDocumentResourceList(String currentUserEmail) {
        List<Resource> currentUserResourceList = resourceService.getCurrentUserResources(currentUserEmail)
        List<DocumentResource> currentUserDocumentResourceList = currentUserResourceList.collect { resource->
                    if(resource.instanceOf(DocumentResource))
                        return resource
        }
    }
}
