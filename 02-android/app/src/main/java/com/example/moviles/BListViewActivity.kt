package com.example.moviles

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_b_list_view.*
import kotlinx.android.synthetic.main.activity_ciclo_vida.*

class BListViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_b_list_view)

        //CREAR LISTA DE ENTRENADORES

        val listaEntrenadores = arrayListOf<Entrenador>()

        listaEntrenadores.add(Entrenador("Adrian","Eguez"))
        listaEntrenadores.add(Entrenador("kevin","Tipantiza"))
        listaEntrenadores.add(Entrenador("Roger","Laza"))
        listaEntrenadores.add(Entrenador("Ronald","Alvarado"))
        listaEntrenadores.add(Entrenador("Juan","Gomez"))
        listaEntrenadores.add(Entrenador("Pedro","Salazar"))
        listaEntrenadores.add(Entrenador("Maria","Duran"))

        //mostrar la lista de entrenadores en el emulador(dispositivo)
        val adaptador = ArrayAdapter(
            this, //enviamos el contexto
            android.R.layout.simple_list_item_1, //enviamos el nombre del layout
            listaEntrenadores //es la lista
        )
        lv_numeros.adapter = adaptador

        lv_numeros.onItemClickListener = AdapterView.OnItemClickListener{
            parent, view, position, id ->
            Log.i("list-view", "Posicion $position")
        }



        //añador listener para cuando se de click hacer algo

        btn_anadir_entrenador.setOnClickListener{
            anadirEntrenador(adaptador,listaEntrenadores)
        }

    }

    fun anadirEntrenador(adaptador: ArrayAdapter<Entrenador>,listaEntrenadores:ArrayList<Entrenador>){

        listaEntrenadores.add(Entrenador("nuevo","Entrenador"))
        adaptador.notifyDataSetChanged()
    }

}
