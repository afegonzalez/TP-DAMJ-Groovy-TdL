class Persona{
    def nombre
    def apellido
    def edad


    def agregarEdad(int edadDada){
        this.edad = edadDada
    }
    
}

def andres = new Persona()

andres.nombre = "Andres"

andres.apellido = "Gonzalez"

andres.agregarEdad(27)


println(andres.edad)
