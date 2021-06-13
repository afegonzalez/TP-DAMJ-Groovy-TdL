package fiublix

class User {

    String name
    String email

    static constraints = {
        name blank: false, unique: true, minSize: 1, maxSize: 150
        email blank: false, unique: true, email: true
    }

}
