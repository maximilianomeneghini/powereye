package com.unlam.powereye.service

import android.content.Context
import android.util.Log
import com.unlam.powereye.client.PowereyeClient
import com.unlam.powereye.entity.IngresoCliente
import com.unlam.powereye.entity.Producto
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.net.HttpURLConnection

class PowereyeService(context: Context) : CoreService(context) {

    private var powereyeClient: PowereyeClient = retrofit.create(
        PowereyeClient::class.java)

    companion object {
        private const val TAG = "PowereyeService"
        private var INSTANCE: PowereyeService? = null

        fun getInstance(context: Context): PowereyeService {
            if (INSTANCE == null) INSTANCE =
                PowereyeService(context)
            return INSTANCE!!
        }
    }


    fun getProductoMasImpacto(
        success: (producto: Producto) -> Unit,
        error: (message: String) -> Unit
    ) {
        val parentJob = Job()
        val coroutineScope = CoroutineScope(Dispatchers.Main + parentJob)
        coroutineScope.launch {
            try {
                val response =
                    powereyeClient.getProductoMasImpacto()
                        .await()
                if (response.code() == HttpURLConnection.HTTP_OK)
                    success(response.body()!!)
                else
                    error("Ops, a ocurrido un error en al obtener el Producto con mas impacto, intente mas tarde...")

            } catch (e: Exception) {
                Log.e(TAG, "Error en getProductoMasImpacto()", e)
            }
        }
    }

    fun getClientesIngresados(
        success: (clientesIngresados: List<IngresoCliente>) -> Unit,
        error: (message: String) -> Unit
    ) {
        val parentJob = Job()
        val coroutineScope = CoroutineScope(Dispatchers.Main + parentJob)
        coroutineScope.launch {
            try {
                val response =
                    powereyeClient.getClientesIngresados()
                        .await()
                if (response.code() == HttpURLConnection.HTTP_OK)
                    success(response.body()!!)
                else
                    error("Ops, a ocurrido un error al obtener los clientes ingresados...")
            } catch (e: Exception) {
                Log.e(TAG, "Error en getClientesIngresados()", e)
            }
        }
    }

}
