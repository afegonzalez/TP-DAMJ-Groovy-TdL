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

        /*
    POST: Agregar amigos.
    - userId
    - friendID
    */
    @Transactional
    def addFriend(Long userId) {
        try {
            Long friendId = request.JSON.friendId
            User user = userService.addFriend(userId,friendId)
            respond([user: user], status: OK)
        } catch (RuntimeException e) {
            render(text: e.message, status: BAD_REQUEST)
        }
    }

    /*
    POST: Agregar película a favoritos.
    - userId
    - nameMovie
    */
    @Transactional
    def addMovie(Long userId) {
        try {
            Long movieId = request.JSON.movieId
            User user = userService.addMovie(userId, movieId)
            respond([user: user], status: OK)
        } catch (RuntimeException e) {
            render(text: e.message, status: BAD_REQUEST)
        }
    }

    /*
    GET: Buscar peliculas del circulo de amigos.
    - movieId
    */
    @Transactional
    def getCircleMovies(Long userId) {
        try {
            List<Movie> circleMovies = userService.getCircleMovies(userId);
            respond([circleMovies: circleMovies], status: OK)
        } catch (RuntimeException e) {
            render(text: e.message, status: BAD_REQUEST)
            log.error "Error: ${e.message}", e
        }
    }

}
