package fiublix

class Movie {
    //https://v2.sg.media-imdb.com/suggestion/t/titanic.json

    String name
    String image
    List<String> protagonists
    String director
    String year

    static constraints = {
        name blank: false, minSize: 1, maxSize: 150
        image nullable: true
        protagonists nullable: true
        director nullable: true
        year nullable: true
    }

    def Movie(name) {
        def url = "https://v2.sg.media-imdb.com/suggestion/t/${name}.json"
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
        
        this.name = name
    }
}
