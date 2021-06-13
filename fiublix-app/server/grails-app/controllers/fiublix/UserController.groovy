package fiublix


import grails.rest.*
import grails.converters.*
import grails.gorm.transactions.Transactional
import static org.springframework.http.HttpStatus.*


class UserController {

    UserService userService;

	static responseFormats = ['json', 'xml']
	
    def index(Integer max) {
        params.max = Math.min(max?: 10,100) //todo lo que viene por request esta en params
        respond User.list(params)
     }


    def show(User user) {
        if (user == null) {
            render status:404
        }
        else {
            return [user: user]
        }
    }


    @Transactional
    def save(User user) {

        //Common controller validations: empty values, non-zero values, etc..
        if (user.hasErrors()) {
            respond user.errors, view:'create'
        } else {
            try {
                userService.addUser(user)
                respond([user:user], status: CREATED)
            } catch (Exception e) {
                render(text: e.message, status: BAD_REQUEST)
            }
        }
    }

}
