package fiublix

import grails.core.GrailsApplication

class ExceptionController {
    def idNotFound(RuntimeException e) {
        render(text: "ID not found", status: 404)
        log.error "Error: ID not found", e
    }

    def userNotSave(Exception e) {
        render(text: "User could not save", status: 404)
        log.error "Error: ", e
    }

    def nullUserWithThatId(UserNotFoundException e) {
        render(text: "User not found", status: 404)
        log.error "Error: User not found", e
    }
}