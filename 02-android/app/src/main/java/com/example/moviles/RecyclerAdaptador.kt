package com.example.moviles
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView

//Para usar el RecyclerView necesitamos migrar a Android X y añadir la dependencia:
// implementation 'com.android.support:recyclerview-v7:28.0.0'
class RecyclerAdaptador(
    private val listaUsuarios: List<UsuarioHttp>,
    private val contexto : RecyclerVIewActivity,
    private val recyclerView: androidx.recyclerview.widget.RecyclerView
) : androidx.recyclerview.widget.RecyclerView.Adapter<RecyclerAdaptador.MyViewHolder>() {
    inner class MyViewHolder(view: View) :
        androidx.recyclerview.widget.RecyclerView.ViewHolder(view) {

        val nombreTextView: TextView
        val cedulaTextView: TextView
        val accionButton: Button

        init {
            nombreTextView = view.findViewById(R.id.tv_nombre)
            cedulaTextView = view.findViewById(R.id.tv_cedula)
            accionButton = view.findViewById(R.id.btn_accion)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdaptador.MyViewHolder {
        //definimos la interfaz que vamos a usar
      val itemView = LayoutInflater.from(parent.context).inflate(
          R.layout.adaptador_persona, //Recursos adaptador _persona.xml
          parent,
          false
      )
        return MyViewHolder(itemView)

    }

    //#item que se tiene en la lista
    override fun getItemCount(): Int {
        return listaUsuarios.size
    }
//la funcion OnBindViewHolder es una funcion que se va a ejecutar con cada uno de los items, es decir, es iterable
    override fun onBindViewHolder(holder: MyViewHolder, //clase implementada (arriba)
                                  position: Int) { //posicion

        val usuario =listaUsuarios[position]
        holder.nombreTextView.text = usuario.nombre
        holder.cedulaTextView.text = usuario.cedula
        holder.accionButton.text ="Like ${usuario.nombre}"
    }
}