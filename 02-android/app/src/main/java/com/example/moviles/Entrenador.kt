package com.example.moviles

class Entrenador(
    var nombre:String,
    var apellido:String)
{
//override
    override fun toString():String{
    return "${this.nombre} ${this.apellido}"
}



}