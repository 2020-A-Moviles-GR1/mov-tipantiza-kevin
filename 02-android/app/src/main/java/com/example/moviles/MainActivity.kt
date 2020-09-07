package com.example.moviles

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_ciclo_vida.setOnClickListener { boton ->
            irCicloDeVida()

        }


        btn_list_view.setOnClickListener { boton ->
            irListView()

        }

        btn_intent_respuesta.setOnClickListener{
            irAinterntConRespuesta()
        }
        btn_intent_implicito.setOnClickListener{
            enviarIntentConRespuesta()
        }

        btn_resp_propia.setOnClickListener{
            enviarIntentConRespuestaPropia()
        }

        btn_http.setOnClickListener{

            abrirActividadHttp()
        }

        btn_recycler.setOnClickListener{
            abrirRecyclerViewActivity()


        }

        btn_mapa.setOnClickListener{
            abrirMapaActivity()


        }


    }

    fun abrirMapaActivity(){
        val intentExplicito = Intent(
            this,
            MapsActivity::class.java
        )

        startActivity(intentExplicito)
    }

    fun abrirRecyclerViewActivity(){
        val intentExplicito = Intent(
            this,
            RecyclerVIewActivity::class.java
        )

        startActivity(intentExplicito)
    }





    fun abrirActividadHttp(){
        val intentExplicito = Intent(
            this,
            HttpActivity::class.java
        )

        startActivity(intentExplicito)
    }

    fun enviarIntentConRespuestaPropia(){
        val intentExplicito = Intent(
            this,
            IntentEnviaParametros::class.java
        )

        startActivityForResult(intentExplicito,305)
    }

    fun enviarIntentConRespuesta() {
        val intentConRespuesta = Intent(
            Intent.ACTION_PICK,
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI
        )

        //this.stratActivityForResultado

        //304 lo pusimo nosotros, no es ningun numero en especial
        startActivityForResult(intentConRespuesta,304)
    }

    //sobreEscribir la funcion para obtener los datos que queremos

    override fun onActivityResult(requestCode: Int, //numero que enviamos
                                  resultCode: Int,
                                  data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        //reesultCode devuelve algo al no hacer nada
        when(resultCode){
           RESULT_OK ->{
                Log.i("resultado", "ok")

               //obtener los datos
               when(requestCode){
                   304 ->{//Contactos
                       val uri = data?.data
                       if(uri != null) {
                           val cursor = contentResolver.query(
                               uri,
                               null,
                               null,
                               null,
                               null,
                               null
                           )
                           cursor?.moveToFirst()
                           val indiceTelefono = cursor?.getColumnIndex(
                               ContactsContract.CommonDataKinds.Phone.NUMBER
                           )
                           val telefono = cursor?.getString(indiceTelefono!!)
                           cursor?.close()
                           Log.i("resultado","Telefono ${telefono}")}
                   }
                   305 ->{
                       if (data != null){
                           val nombre = data.getStringExtra("nombre");
                           val edad = data.getIntExtra("edad",0)
                           Log.i("resultado","nombre: ${nombre} edad : ${edad}")
                       }
                   }


               }
            }
            RESULT_CANCELED ->{
                Log.i("resultado", "=(")
            }
        }
    }


    fun irAinterntConRespuesta(){

    val intentExplicito = Intent (
        this,
        IntentEnviaParametros::class.java
    )
        intentExplicito.putExtra("numero",2)
        //crear un usuario
        val kevin = Usuario("kevin",24, Date(),1.0)

        val cachetes = Mascota ("Cachetes", kevin)

        val arregloMascotas = arrayListOf<Mascota>(cachetes)

        intentExplicito.putExtra("cachetes", cachetes)
        intentExplicito.putExtra("arregloMascotas", arregloMascotas)
        startActivity(intentExplicito)
    }

    //ir ciclo de vida

    fun irCicloDeVida(){
        val intentExplicito = Intent(
            this,
            CicloVida::class.java

        )

        this.startActivity(intentExplicito)
    }

    fun irListView(){
        val intentExplicito = Intent(
            this,
            BListViewActivity::class.java

        )

       // this.startActivity(intentExplicito)
        startActivity(intentExplicito)
    }






}


