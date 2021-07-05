package fiublix

class BootStrap {

    def init = { servletContext ->
        new User(username: 'andz', password: 'asdasd', name: 'Andres', lastName: 'Gonzalez', email:'afgonzalez@hotmail.ar',
                createUserDate: new Date()).save()
        new User(username: 'dani1', password: 'asdasd', name: 'Daniela', lastName: 'Volpellier', email: 'daniela@hotmail.ar',
                createUserDate: new Date()).save()
        new User(username: 'joako2', password: 'asdasd', name: 'Joaquin', lastName: 'Singer', email: 'joaquin@hotmail.ar',
                createUserDate: new Date()).save()
        new User(username: 'martu23', password: 'asdasd', name: 'Martin', lastName: 'La Rosa', email: 'martina@hotmail.ar',
                createUserDate: new Date()).save()
    }
    def destroy = {

    }
}
