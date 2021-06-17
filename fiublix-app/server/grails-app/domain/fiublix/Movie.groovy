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
    }

    def Movie(name) {
        def url = "https://v2.sg.media-imdb.com/suggestion/t/${name}.json"

        def json = new groovy.json.JsonSlurper().parseText(url.toURL().getText())

        this.name = name
        this.image = json['d'][0]['i']['imageUrl']
        this.protagonists = json['d'][0]['s'].split(',')
        this.director = json['d'][0]['v'][-1]['l']
        this.year = json['d'][0]['y']
    }
}
