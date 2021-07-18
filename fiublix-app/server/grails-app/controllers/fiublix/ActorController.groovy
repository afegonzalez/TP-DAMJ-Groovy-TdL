package fiublix


import grails.rest.*
import grails.converters.*


import groovy.transform.CompileStatic
import grails.plugin.springsecurity.SpringSecurityService
import grails.plugin.springsecurity.annotation.Secured
import grails.plugin.springsecurity.userdetails.GrailsUser

import grails.gorm.transactions.Transactional

@Transactional
@Secured('isAuthenticated()')
class ActorController {
	static responseFormats = ['json', 'xml']
	
    def index(Integer max) {
        params.max = Math.min(max?: 10,100) //todo lo que viene por request esta en params
        respond Actor.list(params)
    }

}
