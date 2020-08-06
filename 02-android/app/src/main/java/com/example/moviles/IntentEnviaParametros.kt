package com.example.moviles

import android.app.Activity
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


        val cachetes = intent.getParcelableExtra<Mascota>("cachetes")

        if(cachetes != null){
            Log.i("parcelable", "${cachetes.nombre} ${cachetes.duenio?.nombre}")
        }

        val arregloMascotas = intent.getParcelableArrayListExtra<Mascota>("arregloMascotas")

        if(arregloMascotas != null){
            arregloMascotas.forEach {
                if(it != null){
                    Log.i("parcelable", "EN ARREGLO")
                    Log.i("parcelable", "${it.nombre} ${it.duenio?.nombre}")
                }

            }

        }



        btn_devolver_respuesta.setOnClickListener{
            //metodo de la clase this.finish()
            //podemos terminar una actividad
            finish()
        }


        btn_resp_aceptar.setOnClickListener{
            val nombre = "Adrian"
            val edad = 31
            val intentRespuesta = Intent()
            intentRespuesta.putExtra("nombre",nombre)
            intentRespuesta.putExtra("edad",edad)
            //para devolver el resultado
            //this.setResult()

            setResult(
                Activity.RESULT_OK,
                intentRespuesta
            )
           // this.finish()
            finish()
        }

        btn_resp_cancelar.setOnClickListener{

            val intentCancelado = Intent()
            //para devolver el resultado
            //this.setResult()

            setResult(
                Activity.RESULT_CANCELED,
                intentCancelado
            )
            // this.finish()
            finish()
        }


    }
}
