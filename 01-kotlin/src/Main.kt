import java.util.*
import java.util.function.Consumer
import kotlin.collections.ArrayList

fun main(args:Array<String>){
    print("Hola")
    //codigo en JAVA
   // Int edad = 31;

    //KOTLIN

    //VARIABLES MUTABLES
    var edadProfesor = 31 //NO especificamos el tipo de dato
                            //; NO es necesario
    //DUCK TYPING

    // Declarar variable que no se va a instanciar, SE DEBE DEFINIR EL TIPO DE DATO
    var edadCachorro:Int
    edadCachorro = 3

    //MODIFICANDO VARIABLES SIN PROBLEMAS
    edadProfesor = 32

    edadCachorro = 4

    //VARIABLES INMUTABLES

    val numerCuenta = 123456
   // numerCuenta = 123

    //TIPOS DE VARIABLES

    val nombreProfesor: String = "kevin fabricio"
    val  sueldo: Double = 12.20
    val apellidoProfesor: Char = 'a'
    val fechaNacimiento = Date() //new Date


    //COMPARACIONES
    if (sueldo == 12.20){

    }else{

    }

    when (sueldo){
        12.20 -> println("sueldo normal")
        -12.20 -> println("sueldo negativo")
        else -> println("No se reconoce el sueldo")
    }

    val esSueldoMayorAlEstablecido = if(sueldo == 12.20) true else false
    //JAVA --> EXPRESION ? X: Y
    // calcularSueldo(1000.00, 14.00) --> se debe escribir asi , pero el edito me escribe las palabras
    calcularSueldo( 100.00, 14.00)
    calcularSueldo(tasa = 16.00, sueldo = 800.00) // Named Parameters
    calcularSueldo(700.00)
    calcularSueldo(sueldo = 650.00)

    //ARREGLOS
    val arregloConstante: Array<Int> = arrayOf(1,2,3)
    val arregloCumpleanos:ArrayList<Int> = arrayListOf(30,31,22,23,20)
    arregloCumpleanos.add(24)
    print(arregloCumpleanos)
    arregloCumpleanos.remove(30)
    print(arregloCumpleanos)

    //RECORRER UN ARREGLO
    arregloCumpleanos.forEach{
        println("valor de la iteacion " + it)
    }

    arregloCumpleanos.forEach(
            {
                valorIteracion: Int->
                println("valor de la iteacion " + valorIteracion)
            }
    )

    arregloCumpleanos.forEach{
                valorIteracion: Int->
                println("valor de la iteacion " + valorIteracion)
            }

    arregloCumpleanos.forEach{ iteracion:Int ->
        println("valor de la iteacion " + iteracion)
    }

    arregloCumpleanos.forEachIndexed{index:Int, it:Int ->
        println("valor de iteracion "+ it)
    }

    //OPERADORES
    //FOREACH -> no devuelve nada
    arregloCumpleanos.forEach{ iteracion:Int ->
        println("valor de la iteacion " + iteracion)

        println(" valor con -1 = ${iteracion * -1} ")
    }

    println(arregloCumpleanos)

   val respuesatArregloForEach= arregloCumpleanos.forEachIndexed{index:Int, it:Int ->
        println("valor de iteracion "+ it)
    }
    println(respuesatArregloForEach) //void unit


    //MAP -> Muta el arreglo (cambia el arreglo)
    // 1)Enviemos el nuevo valot de la iteracion
    //2) nos devulve es un nuevo arreglo con los valores modificados

    val respuestaMap = arregloCumpleanos.map{iterador : Int ->
        iterador * -1
    }
    println(respuestaMap)
    println(arregloCumpleanos)

    val respuestaMapDos:List<Int> = arregloCumpleanos.map{
        iterador : Int ->
        val nuevoValor =  iterador * -1
        val otroValor = nuevoValor * 2

        return@map otroValor
    }
    println(respuestaMap)
    println(respuestaMapDos)
    println(arregloCumpleanos)


    // FILTER -> FILTRAR EL ARREGLO
    //1) devolver una expresion (TRUE O FALSE)
    //2) Nuevo arreglo que cumpla esa expresion
    val respuestaFilter: List<Int> = arregloCumpleanos
            .filter {
        iteracion:Int ->
        val esMayorA23:Boolean = iteracion > 23
        return@filter esMayorA23
    }
    arregloCumpleanos.filter {
        iteracion:Int -> iteracion > 23
    }
    println(respuestaFilter)
    println(arregloCumpleanos)

    //ANY -> FIlTRAR EL ARREGLO
    //ANY ->  OR (SOME)
    //ALL -> AND (EVERY)
    //AND--> TRUE, TODO LO DEMAS ES FALSO
    // OR-> TODO SI ES FALSO TODO ES FALSO, LO DEMAS ERA VERDADERO
    //1) DEVOLVER UNA EXPRESION (TRUE O FALSE)
    //2) DEVUELVE UN BOOLENANO

    val respuestaAny:Boolean = arregloCumpleanos.any{
        iterator:Int ->
        return@any iterator < 25
    }

    println(respuestaAny)


    val respuestaAll:Boolean = arregloCumpleanos.all{
        iterator:Int ->
        return@all iterator > 65
    }

    println(respuestaAll)

    //REDUCE
    //1) DEVUELVE EL ACUMULADO
    //2) EN QUE VALOR EMPIEZA
    //DEVULVE UN NUMERO
    //("a","b","c","d") -> "abcd" concatenera string

    val respuestaReduce = arregloCumpleanos //Acumulador siempre empiza vacio
            .reduce { acumulador, iteracion ->

        return@reduce acumulador + iteracion
    }
    println(respuestaReduce)

    val respuestaFold:Int = arregloCumpleanos
            .fold(
                    100,
                    { acumulador, iteracion ->
                        return@fold acumulador - iteracion
                    }
            )
    println(respuestaFold)


    //SITUACION
    // REDUCIR EL DAÑO EN 20%
    // 18
    val vidaActual: Double = arregloCumpleanos
            .map {it * 0.8} //reducir en el 20%
            .filter {it > 18}
            .fold(
                    100.00,
                    {
                        acumulador, iterador -> acumulador -iterador
                    }
            )
           // .also { print(it) }


 println(vidaActual)

}


//FUNCONES

fun calcularSueldo(
        sueldo: Double, // Requeridos!
        tasa: Double = 12.00, // Requeridos! Tiene valor defecto
        //calculoEspecial: Boolean? //Opcional!!
        calculoEspecial: Int? = null //Opcional!!! Pueden ser nulos
): Double { //lo que vamos a devolver si pones
    if (calculoEspecial != null) {
        return sueldo * tasa * calculoEspecial
    } else {
        return sueldo * tasa
    }
}

fun imprimirMensaje():Unit { //unit = void
    println("")
}

//CLASES ABSTRACTAS

/*abstract class NumerosJava{
    val numeroUno: Int
    val numeroDos: Int
    constructor(uno: Int, dos: Int){
        numeroUno = uno
        numeroDos = dos
    }
}*/

abstract class NumerosKotlin( //val nuevosNuemeros = Numeros(1,2)
       protected val numeroUno: Int,
        protected val numeroDos: Int
){

}

class Suma(
       uno : Int, //parametro
       dos:Int //parametro
):NumerosKotlin(uno, dos ) //mandamos construtor de numerosKotlin - calse heredada
{
    fun sumar(): Int{
        return this.numeroUno + this.numeroDos
    }

}

class SumaDos(
       public var  uno : Int, //propiedades
       public var dos:Int //propiedades
):NumerosKotlin(uno, dos ) //mandamos construtor de numerosKotlin - calse heredada
{

    fun sumar(): Int{
        this.uno
        this.dos
        return this.numeroUno + this.numeroDos
    }

}









