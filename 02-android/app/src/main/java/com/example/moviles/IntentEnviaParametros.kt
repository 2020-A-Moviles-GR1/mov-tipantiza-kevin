package com.example.moviles

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_intent_envia_parametros.*

class IntentEnviaParametros : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intent_envia_parametros)

        val numeroEncontrado = intent.getIntExtra(
            "numero",0
        )

        //validacion
    if(numeroEncontrado != 0){
        Log.i("intents","El numero Encontrado es: ${numeroEncontrado}")
    }

        //es el texto compartido

        val textoCompartido: String ? = intent.getStringExtra(Intent.EXTRA_TEXT)

        if(textoCompartido != null){
            Log.i("intents","El texto es: ${textoCompartido}")
        }

        btn_devolver_respuesta.setOnClickListener{
            //metodo de la clase this.finish()
            //podemos terminar una actividad
            finish()
        }



    }
}
