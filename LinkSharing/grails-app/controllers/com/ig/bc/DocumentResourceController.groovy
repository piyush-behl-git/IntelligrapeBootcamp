package com.ig.bc

import com.ig.bc.co.FileCommand
import org.springframework.dao.DataIntegrityViolationException

class DocumentResourceController {

    def documentResourceService

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        String currentLoggedInUserEmail = session.email
        User currentUser = User.findByEmail(session?.email)
        Map<Topic, List<ReadingItem>> topicResourceMap = currentUser.getDocuments().groupBy {item ->
            item.resource.topic
        }
        Integer total = topicResourceMap.size()
        [topicResourceMap: topicResourceMap ,documentResourceInstanceTotal: total]
    }

    def create() {
        User currentUserInstance = User.findByEmail("${session.email}")
        [documentResourceInstance: new DocumentResource(params), currentUserInstance: currentUserInstance]
    }

    def save(FileCommand fileCommand) {
        if (fileCommand.file.contentType == 'application/pdf') {
            params.put("contentType", fileCommand.file.contentType)
            params.put("fileName", fileCommand.file.originalFilename)
            DocumentResource documentResourceInstance = new DocumentResource(params)
            if (!documentResourceInstance.save(flush: true)) {
                flash.message = "Error! Saving to database"
            } else {
                uploadDocument(documentResourceInstance, fileCommand)
            }
        } else {
            flash.documentResource = "Sorry! couldn't create document resource. Only pdf are allowed."
        }
        redirect(controller: "topic", action: "show", id: "${params.topic.id}")
    }

    def show(Long id) {
        def documentResourceInstance = DocumentResource.get(id)
        if (!documentResourceInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'documentResource.label', default: 'DocumentResource'), id])
            redirect(action: "list")
            return
        }
        String documentUploadPath = grailsApplication.config.uploadPath + "/" + id
        [documentResourceInstance: documentResourceInstance, documentLink: documentUploadPath]

    }

    def download(Long id) {
        DocumentResource documentResource = DocumentResource.get(id)
        generateResponse(id, documentResource)
    }

    def edit(Long id) {
        def documentResourceInstance = DocumentResource.get(id)
        if (!documentResourceInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'documentResource.label', default: 'DocumentResource'), id])
            redirect(action: "list")
            return
        }
        [documentResourceInstance: documentResourceInstance]
    }

    def update(Long id, Long version) {
        def documentResourceInstance = DocumentResource.get(id)
        if (!documentResourceInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'documentResource.label', default: 'DocumentResource'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (documentResourceInstance.version > version) {
                documentResourceInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                        [message(code: 'documentResource.label', default: 'DocumentResource')] as Object[],
                        "Another user has updated this DocumentResource while you were editing")
                render(view: "edit", model: [documentResourceInstance: documentResourceInstance])
                return
            }
        }

        documentResourceInstance.properties = params

        if (!documentResourceInstance.save(flush: true)) {
            render(view: "edit", model: [documentResourceInstance: documentResourceInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'documentResource.label', default: 'DocumentResource'), documentResourceInstance.id])
        redirect(action: "show", id: documentResourceInstance.id)
    }

    def delete(Long id) {
        def documentResourceInstance = DocumentResource.get(id)
        if (!documentResourceInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'documentResource.label', default: 'DocumentResource'), id])
            redirect(action: "list")
            return
        }

        try {
            documentResourceInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'documentResource.label', default: 'DocumentResource'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'documentResource.label', default: 'DocumentResource'), id])
            redirect(action: "show", id: id)
        }
    }

    private void uploadDocument(DocumentResource documentResourceInstance, FileCommand fileCommand) {
        File fileToSave = new File("${grailsApplication.config.uploadPath}/${documentResourceInstance.id}")
        fileCommand.file.transferTo(fileToSave)
        flash.documentResource = message(code: 'default.created.message', args: [message(code: 'documentResource.label', default: 'DocumentResource'),
                documentResourceInstance.id])
    }

    private void generateResponse(long id, DocumentResource documentResource) {
        byte[] sourcePdf = new File("${grailsApplication.config.uploadPath}/${id}")?.bytes
        response.setContentType("application/pdf")
        response.setHeader("Content-disposition", "attachment; filename=" + documentResource.fileName)
        response.contentLength = sourcePdf.length
        response.outputStream << sourcePdf
    }
}