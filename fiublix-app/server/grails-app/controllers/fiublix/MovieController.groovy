package fiublix

import groovy.transform.CompileStatic
import grails.plugin.springsecurity.SpringSecurityService
import grails.plugin.springsecurity.annotation.Secured
import grails.plugin.springsecurity.userdetails.GrailsUser

import grails.gorm.transactions.Transactional

@Transactional
@Secured('isAuthenticated()')
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
                respond([movie:movie], status: 201)
            } catch (Exception e) {
                render(text: e.message, status: 400)
            }
        }
    }

    /*
    POST: Buscar pelicula similar a la dada.
    - movieId
    */
    @Transactional
    def searchSimilarMovie(Long movieId) {
        try {
            Long requestedMovieId = request.JSON.movieId
            Movie movie = movieService.getMovie(requestedMovieId)
            
            List<Movie> similarMovies = movieService.searchSimilarMovie();

            respond([movies: similarMovies], status: OK)
        } catch (RuntimeException e) {
            render(text: e.message, status: BAD_REQUEST)
            log.error "Error: ${e.message}", e
        }
    }



}
