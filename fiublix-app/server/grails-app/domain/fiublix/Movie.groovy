package fiublix

class Movie {
    //https://v2.sg.media-imdb.com/suggestion/t/titanic.json

    String name
    String image
    List<String> protagonists
    String director
    String year

    static hasMany = [user: User]
    static belongsTo = [user: User]

    static constraints = {
        name blank: false, minSize: 1, maxSize: 150
        image nullable: true
        protagonists nullable: true
        director nullable: true
        year nullable: true
    }
}
