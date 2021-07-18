class Persona{
    def nombre
    def apellido
    def edad
    def numerosFavoritos = []
    def numerosDeAmigos = ["Joaquin":25, "Martina":26]

    def agregarEdad(int edadDada){
        if ((0 .. 99).contains(edadDada)) {
            this.edad = edadDada
        }
    }

   def agregarNumerosFavoritos(numeros) {
       numerosFavoritos += numeros
   }

}

def andres = new Persona()

andres.nombre = "Andres"

andres.apellido = "Gonzalez"

andres.agregarEdad(127)

andres.agregarNumerosFavoritos(127)
andres.agregarNumerosFavoritos([128, 129, 3])

andres.numerosFavoritos[2] = 1

println(andres.numerosDeAmigos["Joaquin"])
println(andres.numerosDeAmigos["Martina"])
