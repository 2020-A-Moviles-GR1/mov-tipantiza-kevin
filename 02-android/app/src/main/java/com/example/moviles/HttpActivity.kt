package com.example.moviles

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.beust.klaxon.Klaxon
import com.github.kittinunf.fuel.httpGet
import kotlinx.android.synthetic.main.activity_http.*
import com.github.kittinunf.result.Result

class HttpActivity : AppCompatActivity() {
val urlPrincipal = "http://192.168.100.29:1337"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_http)


       btn_obtener.setOnClickListener{
           obtenerusuario()
       }
    }


    fun obtenerusuario(){
        //pokemon
    val pokemonString ="""
        {
            "createdAt": 1597711643258,
            "updatedAt": 1597711643258,
            "id": 2,
            "nombre": "Bulbasaur",
            "usuario": 1
        }
        """.trimIndent()
        //instancia del pokemon
        val pokemonInstancia =Klaxon().parse<PokemonHttp>(pokemonString)

        if (pokemonInstancia != null){
            Log.i("http-klaxon","nombre: ${pokemonInstancia.nombre}")
            Log.i("http-klaxon","nombre: ${pokemonInstancia.fechaCreacion}")
        }




        val url = urlPrincipal + "/Usuario"
        url.httpGet().responseString{
            request, response, result ->
            when(result){
                is Result.Success ->{
                    val data = result.get()
                    Log.i("http-klaxon", "Data: ${data}")

                    val usuarios= Klaxon().parseArray<UsuarioHttp>(data)

                    if (usuarios != null){
                        usuarios.forEach{
                            Log.i("http-klaxon", "nombre: ${it.nombre} " + "Estado civil: ${it.estadoCivil}" )

                            if(it.pokemons.size>0){
                                it.pokemons.forEach{
                                    Log.i("http-klaxon","nombre:${it.nombre}")
                                }
                            }
                        }
                    }
                }
                is Result.Failure ->{
                    val ex = result.getException()
                    Log.i("http-klaxon","Error: ${ex.message}")
                }
            }
        }


    }
}
