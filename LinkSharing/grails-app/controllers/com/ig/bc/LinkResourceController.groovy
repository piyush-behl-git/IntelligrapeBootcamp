package com.ig.bc

import org.springframework.dao.DataIntegrityViolationException

class LinkResourceController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [linkResourceInstanceList: LinkResource.list(params), linkResourceInstanceTotal: LinkResource.count()]
    }

    def create() {
        [linkResourceInstance: new LinkResource(params)]
    }

    def save() {
        def linkResourceInstance = new LinkResource(params)
        if (!linkResourceInstance.save(flush: true)) {
            render(view: "create", model: [linkResourceInstance: linkResourceInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'linkResource.label', default: 'LinkResource'), linkResourceInstance.id])
        redirect(action: "show", id: linkResourceInstance.id)
    }

    def show(Long id) {
        def linkResourceInstance = LinkResource.get(id)
        if (!linkResourceInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'linkResource.label', default: 'LinkResource'), id])
            redirect(action: "list")
            return
        }

        [linkResourceInstance: linkResourceInstance]
    }

    def edit(Long id) {
        def linkResourceInstance = LinkResource.get(id)
        if (!linkResourceInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'linkResource.label', default: 'LinkResource'), id])
            redirect(action: "list")
            return
        }

        [linkResourceInstance: linkResourceInstance]
    }

    def update(Long id, Long version) {
        def linkResourceInstance = LinkResource.get(id)
        if (!linkResourceInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'linkResource.label', default: 'LinkResource'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (linkResourceInstance.version > version) {
                linkResourceInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'linkResource.label', default: 'LinkResource')] as Object[],
                          "Another user has updated this LinkResource while you were editing")
                render(view: "edit", model: [linkResourceInstance: linkResourceInstance])
                return
            }
        }

        linkResourceInstance.properties = params

        if (!linkResourceInstance.save(flush: true)) {
            render(view: "edit", model: [linkResourceInstance: linkResourceInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'linkResource.label', default: 'LinkResource'), linkResourceInstance.id])
        redirect(action: "show", id: linkResourceInstance.id)
    }

    def delete(Long id) {
        def linkResourceInstance = LinkResource.get(id)
        if (!linkResourceInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'linkResource.label', default: 'LinkResource'), id])
            redirect(action: "list")
            return
        }

        try {
            linkResourceInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'linkResource.label', default: 'LinkResource'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'linkResource.label', default: 'LinkResource'), id])
            redirect(action: "show", id: id)
        }
    }
}
