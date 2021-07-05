package fiublix

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString
import grails.compiler.GrailsCompileStatic

@GrailsCompileStatic
@EqualsAndHashCode(includes='username')
@ToString(includes='username', includeNames=true, includePackage=false)
class User implements Serializable {

    private static final long serialVersionUID = 1

    String username
    String password
    String name
    String lastName
    String email

    boolean enabled = true
    boolean accountExpired
    boolean accountLocked
    boolean passwordExpired

    static hasMany = [friends: User, favoriteMovies: Movie, recommendedMovies: Movie]

    Set<Role> getAuthorities() {
        (UserRole.findAllByUser(this) as List<UserRole>)*.role as Set<Role>
    }

    static constraints = {
        username nullable: false, blank: false, unique: true, minSize: 1, maxSize: 20
        password nullable: false, blank: false, password: true
        name blank: false, minSize: 1, maxSize: 150
        lastName blank: false, minSize: 1, maxSize: 150
        email blank: false, unique: true, email: true
    }

    static mapping = {
	    password column: '`password`'
    }

    List<Movie> getFriendsMovies(){
        List<Movie> friendsMovies = this.favoriteMovies as List<Movie>;
        friends.each {friend -> friend.favoriteMovies.each {movie -> friendsMovies.add(movie)}}
        return friendsMovies;
    }

}
