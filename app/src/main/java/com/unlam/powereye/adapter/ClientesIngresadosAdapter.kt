package com.unlam.powereye.adapter


import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.res.ColorStateListInflaterCompat
import androidx.recyclerview.widget.RecyclerView
import com.unlam.powereye.R
import com.unlam.powereye.entity.IngresoCliente

class ClientesIngresadosAdapter() :
    RecyclerView.Adapter<ClientesIngresadosAdapter.ClientesIngresadosHolder>() {

    private var clientesIngresados: List<IngresoCliente> = ArrayList()

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): ClientesIngresadosHolder {
        var view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_ingreso_cliente_list, parent, false)
        return (ClientesIngresadosHolder(
            view
        ))
    }

    override fun getItemCount(): Int {
        return clientesIngresados.size
    }


    fun setData(clientesIngresados: List<IngresoCliente>) {
        this.clientesIngresados = clientesIngresados
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ClientesIngresadosHolder, position: Int) {
        holder.setEdad(clientesIngresados[position].edad)
        holder.setSexo(clientesIngresados[position].sexo)
        holder.setProducto(clientesIngresados[position].producto)
    }

    class ClientesIngresadosHolder(v: View) : RecyclerView.ViewHolder(v) {

        private var view: View = v

        fun setSexo(sexo: String) {
            val textView: TextView = view.findViewById(R.id.tv_sexo)
            textView.text = sexo
        }

        fun setEdad(edad: String) {
            val textView: TextView = view.findViewById(R.id.tv_edad)
            textView.text = edad
        }

        fun setProducto(producto: String) {
            val textView: TextView = view.findViewById(R.id.tv_producto)
            textView.text = producto
        }

    }

}