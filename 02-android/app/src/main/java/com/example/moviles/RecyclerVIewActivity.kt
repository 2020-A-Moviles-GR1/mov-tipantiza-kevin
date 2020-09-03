package com.example.moviles

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_recycler_v_iew.*

class RecyclerVIewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_v_iew)

        val listaUsuarios = arrayListOf<UsuarioHttp>()
        listaUsuarios.add(
            UsuarioHttp(
                1,
                144141,
                5656565,
                "171010894",
                "victor",
                "v@v.com",
                "Soltero",
                "superSegur4",
                arrayListOf<PokemonHttp>()

            )
        )
        listaUsuarios.add(
            UsuarioHttp(
                1,
                144122,
                5656565,
                "171010811",
                "victoria",
                "vi@vi.com",
                "Divorciado",
                "superSegur4",
                arrayListOf<PokemonHttp>()

            )
        )
        listaUsuarios.add(
            UsuarioHttp(
                1,
                144134,
                5656565,
                "171010822",
                "Marta",
                "marta@marta.com",
                "Casado",
                "superSegur4",
                arrayListOf<PokemonHttp>()

            )
        )
        iniciarRecyclerView(
            listaUsuarios,
            this,
            rv_usuarios
        )
    }

    fun iniciarRecyclerView(
        lista:List<UsuarioHttp>,
        actividad : RecyclerVIewActivity,
        recycler_view: androidx.recyclerview.widget.RecyclerView
    ){
        val adaptadorUsuario = RecyclerAdaptador(
            lista,
            actividad,
            recycler_view
        )
        rv_usuarios.adapter = adaptadorUsuario
        rv_usuarios.itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
        rv_usuarios.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(actividad)
        adaptadorUsuario.notifyDataSetChanged()

    }
}
