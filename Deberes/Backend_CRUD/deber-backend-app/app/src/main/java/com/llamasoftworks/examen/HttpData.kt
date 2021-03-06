package com.llamasoftworks.examen

import android.util.Log
import com.beust.klaxon.Klaxon
import com.github.kittinunf.fuel.httpDelete
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.fuel.httpPut
import com.github.kittinunf.result.Result

class HttpData {
    companion object{
        var cartasList = mutableListOf<String>()
    }
    var urlPrincipal = "http://192.168.100.29:1337"
    fun readCard(posicion:Int):List<*>{
        val nombre = cartasList[posicion]
        var listaDeDatosCarta = mutableListOf("","",0,true,0.01)
        val url = urlPrincipal + "/estudiante?nombre="+nombre
        val (request, response, result) = url
            .httpGet()
            .responseString()
        when(result){
            is Result.Success -> {
                val data = result.get()
                val carta = Klaxon().parseArray<Carta>(data)
                if (carta != null){
                    //Log.i("http-klaxon","Datos: ${carta[0].nombre}")
                    listaDeDatosCarta = mutableListOf(carta[0].nombre,carta[0].id,carta[0].level,carta[0].graduado,carta[0].precio)
                }
            }
            is Result.Failure -> {
                val ex = result.getException()
                //Log.i("http-klaxon","Error: ${ex.cause}")
            }
        }
        return listaDeDatosCarta
    }

    fun createCard(carta:Carta){
        val url = urlPrincipal + "/estudiante"
        val parametrosCarta = listOf(
            "nombre" to carta.nombre,
            "id" to carta.id,
            "nivel" to carta.level,
            "graduado" to carta.graduado,
            "precio" to carta.precio
        )
        url.httpPost(parametrosCarta)
            .responseString{
                    req, res, result ->
                when(result){
                    is Result.Failure ->{
                        val error = result.getException()
                    }
                    is Result.Success -> {
                        val usuarioString = result.get()
                    }
                }
            }
    }

    fun readCardsNames():ArrayList<String>{
        var list = arrayListOf<String>()
        val (request, response, result) = "http://192.168.100.29:1337/estudiante"
            .httpGet()
            .responseString()
        when(result){
            is Result.Success -> {
                val data = result.get()
                val cartas = Klaxon().parseArray<Carta>(data)
                if (cartas != null){
                    cartasList.clear()
                    cartas.forEach{
                        cartasList.add(it.nombre)
                        list.add(it.nombre)
                    }
                    //Log.i("http-klaxon","Error: ${HttpData.cartasList}")
                }
            }
            is Result.Failure -> {
                val ex = result.getException()
                Log.i("http-klaxon","Error: ${ex.cause}")
            }
        }
        return list
    }

    fun deleteCard (idCarta:String){
        val url = urlPrincipal + "/estudiante/"+idCarta
        url.httpDelete()
            .responseString{
                    req, res, result ->
                when(result){
                    is Result.Failure ->{
                        val error = result.getException()
                    }
                    is Result.Success -> {
                        val usuarioString = result.get()
                    }
                }
            }
    }

    fun updateCard(newName:String, oldId:String,level:Int, graduado:Boolean, precio:Double){
        val url = urlPrincipal + "/estudiante/"+oldId
        val parametrosCarta = listOf(
            "nombre" to newName,
            "id" to oldId,
            "level" to level,
            "graduado" to graduado,
            "precio" to precio
        )
        url.httpPut(parametrosCarta)
            .responseString{
                    req, res, result ->
                when(result){
                    is Result.Failure ->{
                        val error = result.getException()
                    }
                    is Result.Success -> {
                        val usuarioString = result.get()
                    }
                }
            }
    }

    fun getIdByName(posicion:Int):List<*>{
        val nombre = cartasList[posicion]
        var listaDeDatosCarta = mutableListOf("","",0,true,0.01)
        val url = urlPrincipal + "/estudiante?nombre="+nombre
        val (request, response, result) = url
            .httpGet()
            .responseString()
        when(result){
            is Result.Success -> {
                val data = result.get()
                val carta = Klaxon().parseArray<Carta>(data)
                if (carta != null){
                    //Log.i("http-klaxon","Datos: ${carta[0].nombre}")
                    listaDeDatosCarta = mutableListOf(carta[0].nombre,carta[0].id,carta[0].level,carta[0].graduado,carta[0].precio)
                }
            }
            is Result.Failure -> {
                val ex = result.getException()
                //Log.i("http-klaxon","Error: ${ex.cause}")
            }
        }
        return listaDeDatosCarta
    }
}