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

    arregloCumpleanos.forEachIndexed{index:Int, it:Int ->
        println("valor de iteracion "+ it)
    }



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




