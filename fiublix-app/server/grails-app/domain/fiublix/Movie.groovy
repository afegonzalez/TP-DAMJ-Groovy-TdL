package fiublix

class Movie {

    String name
    String image
    List<String> protagonists
    String director
    String year
    String description
    Long idTmdb
    String originalLanguage
    Date releaseDate
    Boolean forAdults

    static hasMany = [user: User]
    static belongsTo = [user: User]

    static constraints = {
        name blank: false, minSize: 1, maxSize: 150
        image nullable: true
        protagonists nullable: true
        director nullable: true
        year nullable: true
        idTmdb nullable: true
        description nullable: true
        originalLanguage nullable: true
        releaseDate nullable: true
        forAdults nullable: true
    }
}
