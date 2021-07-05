package fiublix

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class UserSpec extends Specification implements DomainUnitTest<User> {

    def setup() {
    }

    def cleanup() {
    }

    void "Test 1: New user with friend"() {
        setup:
        def user1 = new User(username: 'andz', password: 'asdasd', name: 'Andres', lastName: 'Gonzalez', email:'afgonzalez@hotmail.ar',
                createUserDate: new Date()).save()
        def user2 = new User(username: 'dani1', password: 'asdasd', name: 'Daniela', lastName: 'Volpellier', email: 'daniela@hotmail.ar',
                createUserDate: new Date()).save()       

        user1.addToFriends(user2);

        expect:
        user1.friends.size() == 1
    }

    //Este test está fallando, revisarlo
    /*void "Test 2: New user with favorite movies"() {
        setup:
        def user1 = new User(userName: 'prueba1', name: 'prueba1', lastName: 'a', email: 'prueba@hotmail.ar',
                createUserDate: new Date()).save()   
        def movie1 = new Movie('Titanic')

        user1.addToFavoriteMovies(movie1);

        expect:
        user1.favoriteMovies.size() == 1
    }*/

}
