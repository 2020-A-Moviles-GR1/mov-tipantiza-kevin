package com.example.moviles

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.beust.klaxon.Klaxon
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPost
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

        btn_crear.setOnClickListener{
            crearUsuario()
        }
    }


    fun crearUsuario(){
        val url = urlPrincipal + "/Usuario"
        //crear una lista
        val parametrosUsuario:List<Pair<String, String>> = listOf(
            "cedula" to "1234567891",
            "nombre" to "henry",
            "correo" to "h@h.com",
            "estadoCivil" to "Casado",
            "password" to "A123456789b"

        )
        url.httpPost(parametrosUsuario)
            .responseString{
                request, response, result ->
                when(result){
                    is Result.Failure -> {
                        val error = result.getException()
                        Log.i("http-klaxon", "Error: {$error}")

                    }
                    is Result.Success ->{
                        var usuarioString = result.get()
                        Log.i("http-klaxon","${usuarioString}")

                    }
                }
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
                    /*
                   //DEBER KLAXON

                                       if(usuarios != null){
                                           usuarios.forEach{
                                               Log.i(
                                                   "http-klaxon",
                                                   "\nNombre_Usuario: ${it.nombre}\n"

                                               )

                                               if(it.pokemons is List<*>){
                                                   if(it.pokemons!!.size > 0){
                                                       it.pokemons!!.forEach{
                                                           it as PokemonHttp

                                                           Log.i(
                                                               "http-klaxon",
                                                               "id_Usuario: ${it.usuario} ------- Nombre_Pokemon: ${it.nombre}\n"
                                                           )
                                                       }
                                                   }
                                               }

                                           }
                                       }
                                       Log.i("http-klaxon","\n\nCONSULTAR POKEMONS\n\n")
                                       obtenerPokemons()

                                       //DEBER KLAXON
                   */

                is Result.Failure ->{
                    val ex = result.getException()
                    Log.i("http-klaxon","Error: ${ex.message}")
                }
            }
        }

    }

/*
//DEBER KLAXON
    fun obtenerPokemons() {

        val url = urlPrincipal + "/pokemon"

        url
            .httpGet()
            .responseString{
                    request, response, result ->

                when(result){
                    is Result.Success ->{
                        val data = result.get()

                        val pokemons = Klaxon()
                            .converter(PokemonHttp.myConverter)
                            .parseArray<PokemonHttp>(data)


                        if(pokemons != null){
                            pokemons.forEach{
                                Log.i("http-klaxon", "NOMBRE POKEMON: ${it.nombre}" + ", Usuario: ${it.usuario}\n"

                                )

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

//DEBER KLAXON
*/

}
