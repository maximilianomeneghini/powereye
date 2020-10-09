package com.unlam.powereye.fragment

import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.unlam.powereye.R
import com.unlam.powereye.activities.MainActivity
import com.unlam.powereye.databinding.DialogIngresoClienteBinding
import com.unlam.powereye.databinding.FragmentMainBinding
import com.unlam.powereye.dialogs.IngresoClienteDialog
import com.unlam.powereye.entity.IngresoCliente
import com.unlam.powereye.entity.Producto
import com.unlam.powereye.service.PowereyeService
import com.unlam.powereye.utils.DialogUtil
import java.text.SimpleDateFormat
import java.util.*
import kotlin.concurrent.fixedRateTimer

class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    private lateinit var dialogProcesando: Dialog
    private lateinit var dialogIngresoCliente: IngresoClienteDialog
    companion object {
        var MINUT_FOR_RUN: Long = 1
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMainBinding.inflate(inflater, container, false)
        dialogProcesando = DialogUtil.crearDialogoProcesando(activity!!)
        dialogIngresoCliente = IngresoClienteDialog()

        fixedRateTimer("timer", false, 0L, 60 * 1000 * MINUT_FOR_RUN ) {
            run {
                PowereyeService.getInstance(requireContext()).getClientesIngresados(
                    ::bindIngresoClientes,
                    ::showMessage
                )
            }
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dialogProcesando.show()

        binding.btRecargar.setOnClickListener {
            dialogProcesando.show()
            PowereyeService.getInstance(requireContext()).getProductoMasImpacto(
                ::bindProductoMasImpacto,
                ::showMessage
            )
        }

        PowereyeService.getInstance(requireContext()).getProductoMasImpacto(
            ::bindProductoMasImpacto,
            ::showMessage
        )
    }

    @SuppressLint("SimpleDateFormat")
    private fun bindProductoMasImpacto(producto: Producto) {
        binding.tvCantVistas.text = producto.cantidadVistas.toString()
        binding.tvProducto.text = producto.nombre
        binding.tvFecha.text =  SimpleDateFormat("dd/M/yyyy hh:mm:ss").format(Date())
        dialogProcesando.dismiss()
    }

    private fun bindIngresoClientes(clientesIngresados: List<IngresoCliente>) {
        if (!clientesIngresados.isNullOrEmpty()) {
            dialogIngresoCliente.setClientesIngresados(clientesIngresados)
            if (!dialogIngresoCliente.isVisible)
                dialogIngresoCliente.show(fragmentManager!!,IngresoClienteDialog.TAG)
        }
    }

    private fun showMessage(error: String) {
        DialogUtil.mostrarMensaje(
            activity!!, R.string.info, error, false
        ) { _, _ ->  }
        dialogProcesando.dismiss()
    }


}
