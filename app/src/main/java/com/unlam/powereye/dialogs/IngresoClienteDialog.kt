package com.unlam.powereye.dialogs

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.nikartm.button.FitButton
import com.google.android.gms.common.api.Api
import com.unlam.powereye.R
import com.unlam.powereye.adapter.ClientesIngresadosAdapter
import com.unlam.powereye.entity.IngresoCliente

class IngresoClienteDialog : DialogFragment() {

    companion object {
        const val TAG = "IngresoClienteDialog"
    }

    lateinit var v: View
    lateinit var buttonAceptar: FitButton
    lateinit var recyclerView: RecyclerView
    var clientesIngresadosAdapter = ClientesIngresadosAdapter()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        v = inflater.inflate(R.layout.dialog_ingreso_cliente, container, false)
        buttonAceptar = v.findViewById(R.id.btn_dialog_whatsapp_aceptar)
        recyclerView = v.findViewById(R.id.rv_clientes)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = clientesIngresadosAdapter
        return v
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return dialog
    }

    override fun onStart() {
        super.onStart()
        buttonAceptar.setOnClickListener {
            dismiss()
        }
    }

    fun setClientesIngresados(clientesIngresados: List<IngresoCliente>) {
        clientesIngresadosAdapter.setData(clientesIngresados)
    }

}