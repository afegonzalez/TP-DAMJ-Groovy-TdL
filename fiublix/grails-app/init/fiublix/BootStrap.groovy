package fiublix

class BootStrap {

    def init = { servletContext ->
        new User(name: 'Andres', email: 'afgonzalez@hotmail.ar', createUserDate: new Date()).save()
        new User(name: 'Daniela', email: 'daniela@hotmail.ar', createUserDate: new Date()).save()
        new User(name: 'Joaquin', email: 'joaquin@hotmail.ar', createUserDate: new Date()).save()
        new User(name: 'Martin', email: 'martina@hotmail.ar', createUserDate: new Date()).save()

    }
    def destroy = {
    }
}
