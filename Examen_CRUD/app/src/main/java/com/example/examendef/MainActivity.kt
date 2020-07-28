package com.example.examendef

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_crear_estudiante.*
import java.util.*

class MainActivity : AppCompatActivity() {
    companion object objetoCompartido
    {
        var nombre:String="prueba"
        var contadorEstudiateId:Int=1
        var contadorMateriaId:Int=1
        var dbEstudiante = ArrayList<Estudiante>()
        var dbMateria = ArrayList<Materia>()

        fun crearaux(){
            val estudiante= Estudiante(
                nombres = "Anderson",
                apellidos = "Muñoz",
                fechaNacimiento = Date(),
                semestreActual = 1,
                graduado = true,
                id = 0


            )
            val estudiante3= Estudiante(
                nombres = "Laura",
                apellidos = "Sanchez",
                fechaNacimiento = Date(),
                semestreActual = 2,
                graduado = true,
                id = 0


            )
            dbEstudiante.add(estudiante)
            dbEstudiante.add(estudiante3)
            var x= dbEstudiante;

            val materia= Materia(
                id = 0,
                nombre = "Móviles",
                codigo = "mov-123",
                descripcion = "Aprendiendo moviles :)",
                activo = true,
                fechaCreacion = Date(),
                numeroHoras = 4,
                estudianteId = 0
            )
            dbMateria.add(materia)
        }

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        btn_entrar.setOnClickListener{
            objetoCompartido.crearaux()
            enviarNombre()
        }


    }
    fun enviarNombre(){

        objetoCompartido.nombre=input_Nombre.text.toString();
        val intent= Intent(
            this, MenuEstudiante::class.java
        )
        startActivity(intent);
    }
  
}
