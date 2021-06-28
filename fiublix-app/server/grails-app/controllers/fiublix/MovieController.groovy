package fiublix

import grails.rest.*
import grails.converters.*
import grails.gorm.transactions.Transactional
import static org.springframework.http.HttpStatus.*

class MovieController {
    MovieService movieService;

    static responseFormats = ['json', 'xml']

    def index(Integer max) {
        params.max = Math.min(max?: 10,100) //todo lo que viene por request esta en params
        respond Movie.list(params)
    }

    def show(Movie movie) {
        if (movie == null) {
            render status:404
        }
        else {
            return [movie: movie]
        }
    }

    @Transactional
    def save(Movie movie) {
        //Common controller validations: empty values, non-zero values, etc..
        if (movie.hasErrors()) {
            respond movie.errors, view:'create'
        } else {
            try {
                movieService.addMovie(movie)
                respond([movie:movie], status: CREATED)
            } catch (Exception e) {
                render(text: e.message, status: BAD_REQUEST)
            }
        }
    }
}
