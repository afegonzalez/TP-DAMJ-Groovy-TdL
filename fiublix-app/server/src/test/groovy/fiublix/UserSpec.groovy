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
        def user2 = new User(username: 'joako2', password: 'asdasd', name: 'Joaquin', lastName: 'Singer', email: 'joaquin@hotmail.ar',
                createUserDate: new Date()).save()

        user1.addToFriends(user2)

        expect:
        user1.friends.contains(user1)
    }

}
