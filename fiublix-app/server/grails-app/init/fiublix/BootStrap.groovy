package fiublix

class BootStrap {

    UserDataService userDataService

    def init = { servletContext ->
        /*new User(userName: 'andz', name: 'Andres', lastName: 'Gonzalez', email:'afgonzalez@hotmail.ar',
                createUserDate: new Date()).save()
        new User(userName: 'dani1', name: 'Daniela', lastName: 'Volpellier', email: 'daniela@hotmail.ar',
                createUserDate: new Date()).save()
        new User(userName: 'joako2', name: 'Joaquin', lastName: 'Singer', email: 'joaquin@hotmail.ar',
                createUserDate: new Date()).save()
        new User(userName: 'martu23', name: 'Martin', lastName: 'La Rosa', email: 'martina@hotmail.ar',
                createUserDate: new Date()).save()*/
        User user = userDataService.save('prueba1', 'asdasd1', true, false, false, false, )

    }
    def destroy = {
    }
}
