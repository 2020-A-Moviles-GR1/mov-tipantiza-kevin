import java.io.File
import java.io.FileWriter
import java.io.IOException
import javax.swing.JOptionPane

fun main(args: Array<String>){
    val user = JOptionPane.showInputDialog("   Bienvenido a las Matriculas " +
            "\n" +
            "Ingrese su nombre de usuario:" +
            "")
    val password = JOptionPane.showInputDialog("Ingrese su password:")
    if (validar(user,password)){


        if (user == "admin"){
            crud()
        }
        else{
            tomarMateria(user) }
    }
    else{
        println("No se encontro Usuario")
    }
}


//METODO VALIDAR USUSARIO
fun validar(usuario:String,contraseña:String):Boolean{
    val path = "./src/Estudiantes.txt"
    val arreglo_usuarios = ArrayList<String>()
    File(path).forEachLine { linea ->
        arreglo_usuarios.add(linea)
    }
    var split_usuarios = listOf<String>()

    arreglo_usuarios.forEach { leida ->
        split_usuarios = leida.split(",")
        if (split_usuarios[0]==usuario && split_usuarios[1] == contraseña) {
            return true
        }
    }
    return false
}

//TOMAR UNA MATERIA

fun tomarMateria(nombre:String){
    val path = "./src/Materias.txt"
    JOptionPane.showMessageDialog(null,"Bienvenido $nombre \n Presiona en OK para continuar ")
    do {
        val accion: Int = Integer.parseInt(JOptionPane.showInputDialog(
            "¿Que desea hacer? \n" +
                    "1. Mostrar Materias\n" +
                    "2. Tomar una Materia\n" +
                    "3. Salir"
        ))

        when(accion){
            1 ->  file_handler(path, "Mostrar")
            2 ->  tomar(nombre)
        }

    }while(accion!=3)
}

//EL ESTUDIANTE PUEDE TOMAR UNA MATERIA
fun tomar(user: String) {
    val path = "./src/Estudiantes.txt"
    val pathM = "./src/Materias.txt"
    val pathMU = "./src/Estudiante_Materia.txt"
    val arreglo_estudiantes = ArrayList<String>()
    val arreglo_materias = ArrayList<String>()
    val arreglo_estudiante_materia = ArrayList<String>()

    val indiceMateriaTomar = JOptionPane.showInputDialog("Ingrese el Indice de la materia a tomar")
    File(pathM).forEachLine { linea ->
        arreglo_materias.add(linea)
    }
    val indicValidar = arreglo_materias.indexOfFirst{ linea -> linea.split(",")[4] == indiceMateriaTomar }
    if (indicValidar > 0) {


        File(path).forEachLine { linea ->
            arreglo_estudiantes.add(linea)
        }
        val indiceEstudiante = arreglo_estudiantes.indexOfFirst {
                linea -> linea.split(",")[0] == user
        }
//CREAR EL ARCHIVO ESTUDIANTE _MATERIAS.TXT
        try {
            val fw = FileWriter(pathMU, true)
            //muestra 3,1 ---  comenzando desde cero (Estudainte, codigoMateria)
            fw.write("$indiceEstudiante,$indiceMateriaTomar\n")
            fw.close()
            JOptionPane.showMessageDialog(null, "Materia Tomada!")
        } catch (e: IOException) {
            JOptionPane.showMessageDialog(null, "Materia NO se pudo tomar!")
        }


    } else {

        JOptionPane.showMessageDialog(null, "La materia ingresada NO existe")

    }
}
//SOLO PARA ADMIN-------PUEDE REALIZAR TODAS LAS OPERACIONES

fun crud(){
    val path = "./src/Materias.txt"
    do {
        val accion:Int = Integer.parseInt(JOptionPane.showInputDialog(
            "¿Que desea realizar? \n" +
                    "1. Mostrar Materias\n" +
                    "2. Crear Materias\n" +
                    "3. Editar Materias\n" +
                    "4. Eliminar Materias\n" +
                    "5. Salir"
        ))

        when(accion){
            1 -> file_handler(path,"Mostrar")
            2 -> file_handler(path,"Crear")
            3 -> file_handler(path,"Editar")
            4 -> file_handler(path,"Eliminar")
        }



    }while(accion != 5)

}

//SOLO PARA ADMIN

fun file_handler(path: String, accion:String){
    var mostrar = "Código:            Horario:              Periodo:            Tipo:            Nombre:\n"
    val arreglo_usuarios = ArrayList<String>()
    if (accion == "Mostrar" ) {

        File(path).forEachLine { linea ->
            arreglo_usuarios.add(linea)

        }

        arreglo_usuarios.forEach {
            mostrar = mostrar + "       "+it.split(',')[4]+"                "+it.split(',')[3]+"                 "+it.split(',')[2]+ "                 "+it.split(',')[1]+"                "+ it.split(',')[0]+"\n"
        }
        JOptionPane.showMessageDialog(null, mostrar)
    }
    else if(accion == "Crear"){

        val nombreMateriaCreada = JOptionPane.showInputDialog("Ingrese el Nombre de la materia a creár")
        val tipoMateriaCreada = JOptionPane.showInputDialog("Ingrese el Tipo de la materia a creár")
        val indiceMateriaCreada = JOptionPane.showInputDialog("Ingrese el Indice de la materia a creár")
        val horarioMateria = JOptionPane.showInputDialog("Ingrese el Horario de la materia a creár")
        val periodoMateria = JOptionPane.showInputDialog("Ingrese el Periodo de la materia a creár")



        try {
            val fw = FileWriter(path, true)
            fw.write("$nombreMateriaCreada,$tipoMateriaCreada,$periodoMateria,$horarioMateria,$indiceMateriaCreada\n")
            fw.close()
            JOptionPane.showMessageDialog(null,"Materia Creada!")
        } catch (e: IOException) {
            JOptionPane.showMessageDialog(null,"Materia No se pudó Crear!")
        }

    }

    else if(accion == "Editar"){
        val indiceEditar = JOptionPane.showInputDialog("Ingrese el indice de la Materia a editar")
        File(path).forEachLine { linea ->
            arreglo_usuarios.add(linea)
        }
        val indiceEncontrado = arreglo_usuarios.indexOfFirst {linea -> linea.split(",")[4]==indiceEditar  }
        if(indiceEncontrado>0){
            val nuevoNombre = JOptionPane.showInputDialog("Ingrese el nuevo nombre")
            val nuevoTipo = JOptionPane.showInputDialog("Ingrese el nuevo Tipo de Materia")
            val nuevoIndice = JOptionPane.showInputDialog("Ingrese el nuevo indice de la Materia")
            val NuevohorarioMateria = JOptionPane.showInputDialog("Ingrese el nuevo Horario de la Materia")
            val NuevoperiodoMateria = JOptionPane.showInputDialog("Ingrese el nuevo Periodo de la Materia ")
            arreglo_usuarios[indiceEncontrado] = "$nuevoNombre,$nuevoTipo,$NuevoperiodoMateria,$NuevohorarioMateria,$nuevoIndice"

        }else{

            JOptionPane.showMessageDialog(null, "No existe el indice ingresado")
        }
        try {
            val fw = FileWriter(path, true)

            File(path).writeText("")
            arreglo_usuarios.forEach {

                fw.write(it+"\n")
            }
            fw.close()
            JOptionPane.showMessageDialog(null,"Materia Editada!")
        } catch (e: IOException) {
            JOptionPane.showMessageDialog(null,"Materia No se pudo Editar!")
        }
    }
    else if(accion == "Eliminar"){
        val indiceBorrar = JOptionPane.showInputDialog("Ingrese el indice de la Materia a Borrar")
        File(path).forEachLine { linea ->
            arreglo_usuarios.add(linea)
        }
        val indiceEncontrado = arreglo_usuarios.indexOfFirst {linea -> linea.split(",")[4]==indiceBorrar  }
        if(indiceEncontrado>0){

            arreglo_usuarios.removeAt(indiceEncontrado)

        }else{

            JOptionPane.showMessageDialog(null, "No se encontro el indice mencionado")
        }
        try {
            val fw = FileWriter(path, true)

            File(path).writeText("")
            arreglo_usuarios.forEach {

                fw.write(it+"\n")
            }
            fw.close()
            JOptionPane.showMessageDialog(null,"Materia Eliminada!")
        } catch (e: IOException) {
            JOptionPane.showMessageDialog(null,"Materia No se pudo Eliminar!")
        }
    }
}
