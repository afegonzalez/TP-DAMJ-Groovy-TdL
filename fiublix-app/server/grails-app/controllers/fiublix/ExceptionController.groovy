package fiublix

import grails.core.GrailsApplication

class ExceptionController {
    def idNotFound(RuntimeException e) {
        render(text: "ID not found", status: 404)
        log.error "Error: ID not found", e
    }
}