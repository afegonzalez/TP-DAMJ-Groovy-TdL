package fiublix

import grails.core.GrailsApplication
import grails.gorm.transactions.Transactional

@Transactional
class MovieService {

    GrailsApplication grailsApplication
    def addMovie(Movie movie) {
        def apiKey = grailsApplication.config.getProperty('environments.development.tmdb.apiKey')
        println(apiKey)
        def query = java.net.URLEncoder.encode(movie.name, "UTF-8")

        //www.themoviedb.org
        def urlTmdb = "https://api.themoviedb.org/3/search/movie?api_key=${apiKey}&query=${query}"
        println(urlTmdb)
        try {
            def jsonTmdb = new groovy.json.JsonSlurper().parseText(urlTmdb.toURL().getText())
            println(jsonTmdb['results'][0]['id'])
            movie.idTmdb = jsonTmdb['results'][0]['id']
            movie.description = jsonTmdb['results'][0]['overview'].take(252) + "..."
            movie.name = jsonTmdb['results'][0]['original_title']
            movie.originalLanguage = jsonTmdb['results'][0]['original_language']
            movie.releaseDate = Date.parse("yyyy-MM-dd", jsonTmdb['results'][0]['release_date'])
            movie.forAdults = jsonTmdb['results'][0]['adult'].toBoolean()
            movie.image = "https://image.tmdb.org/t/p/original" + jsonTmdb['results'][0]['poster_path']

        } catch (Exception e) {
            println("Error en tmdb")
        }

        //www.imdb.com
        /*
        def url = "https://v2.sg.media-imdb.com/suggestion/t/${movie.name}.json"
        try {
            def json = new groovy.json.JsonSlurper().parseText(url.toURL().getText())
            movie.image = json['d'][0]['i']['imageUrl']
            movie.protagonists = json['d'][0]['s'].split(',')
            movie.director = json['d'][0]['v'][-1]['l']
            movie.year = json['d'][0]['y']
        } catch (IOException e) {
            movie.image = "No disponible"
            movie.director = "No disponible"
            movie.year = "No disponible"
        }
        */
        movie.save flush:true
    }

    Movie getMovie(Long movieId){
        Movie movie =  Movie.findById(movieId);
        return movie
    }
}
