package fiublix

import grails.gorm.transactions.Transactional

@Transactional
class MovieService {

    def addMovie(Movie movie) {
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

        movie.save flush:true
    }

    Movie getMovie(Long movieId){
        Movie movie =  Movie.findById(movieId);
        return movie
    }
}
