package com.ig.bc

import org.springframework.dao.DataIntegrityViolationException
import org.springframework.web.multipart.MultipartFile
import com.ig.bc.co.FileCommand

class DocumentResourceController {

    def documentResourceService

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [documentResourceInstanceList: DocumentResource.list(params), documentResourceInstanceTotal: DocumentResource.count()]
    }

    def create() {
        User currentUserInstance = User.findByEmail("${session.email}")
        [documentResourceInstance: new DocumentResource(params), currentUserInstance: currentUserInstance]
    }

    def save(FileCommand fileCommand) {
        if (fileCommand.file.contentType == 'application/pdf') {
            params.contentType = fileCommand.file.contentType
            def documentResourceInstance = new DocumentResource(params)
            if (!documentResourceInstance.save(flush: true)) {
                render(view: "create", model: [documentResourceInstance: documentResourceInstance])
                return
            }
            File fileToSave = new File("${grailsApplication.config.uploadPath}/${documentResourceInstance.id}")
            fileCommand.file.transferTo(fileToSave)
            flash.message = message(code: 'default.created.message', args: [message(code: 'documentResource.label', default: 'DocumentResource'), documentResourceInstance.id])
            redirect(action: "show", id: documentResourceInstance.id)
        } else {
            flash.message = "Sorry! couldn't create document resource. Only pdf are allowed."
            redirect(action: "create")
        }

    }

    def show(Long id) {
        def documentResourceInstance = DocumentResource.get(id)
        if (!documentResourceInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'documentResource.label', default: 'DocumentResource'), id])
            redirect(action: "list")
            return
        }

        [documentResourceInstance: documentResourceInstance]
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
}