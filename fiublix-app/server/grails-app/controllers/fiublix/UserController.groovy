package fiublix

import groovy.transform.CompileStatic
import grails.plugin.springsecurity.SpringSecurityService
import grails.plugin.springsecurity.annotation.Secured
import grails.plugin.springsecurity.userdetails.GrailsUser

import grails.gorm.transactions.Transactional

@Transactional
@Secured('isAuthenticated()')
class UserController extends ExceptionController {

    UserService userService;

	static responseFormats = ['json', 'xml']
	
    def index(Integer max) {
        params.max = Math.min(max?: 10,100) //todo lo que viene por request esta en params
        respond User.list(params)
     }


    def show(User user) {
        if (user == null) {
            throw new UserNotFoundException()
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
                throw new Exception("User could not save")
            }
        }
    }

        /*
    POST: Agregar amigos.
    - userId
    - friendID
    */
    @Transactional
    def addFriend(Long userId) {
        Long friendId = request.JSON.friendId
        User user = userService.addFriend(userId,friendId)
        respond([user: user], status: 200)
    }

    /*
    POST: Agregar película a favoritos.
    - userId
    - nameMovie
    */
    @Transactional
    def addMovie(Long userId) {
        Long movieId = request.JSON.movieId
        User user = userService.addMovie(userId, movieId)
        respond([user: user], status: 200)
    }

    /*
    GET: Buscar peliculas del circulo de amigos.
    - movieId
    */
    @Transactional
    def getCircleMovies(Long userId) {
        List<Movie> circleMovies = userService.getCircleMovies(userId);
        respond([circleMovies: circleMovies], status: 200)
    }

    /*
    POST: Recomendar película a amigo.
    - userId
    - friendId
    - nameMovie
    */
    @Transactional
    def recommendMovie(Long userId) {
        Long movieId = request.JSON.movieId
        Long friendId = request.JSON.friendId
        User friend = userService.addRecommendedMovie(friendId, movieId)
        respond([friend: friend], status: 200)
    }

    def connectException(final ConnectException exception) {
        log.error "Error: ", exception
        render(text: "Connection error", status: 503)
    }

}
