package fiublix

class User {

    String userName
    String name
    String lastName
    String email

    static hasMany = [friends: User, favoriteMovies: Movie, recommendedMovies: Movie]

    static constraints = {
        name blank: false, minSize: 1, maxSize: 150
        lastName blank: false, minSize: 1, maxSize: 150
        userName blank: false, unique: true, minSize: 1, maxSize: 20
        email blank: false, unique: true, email: true
    }

}
