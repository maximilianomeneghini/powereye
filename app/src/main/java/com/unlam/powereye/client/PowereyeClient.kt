package com.unlam.powereye.client

import com.unlam.powereye.entity.IngresoCliente
import com.unlam.powereye.entity.Producto
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

interface PowereyeClient {

    @GET("impact")
    fun getProductoMasImpacto(): Deferred<Response<Producto>>

    @GET("detection/clientes-ingresados")
    fun getClientesIngresados(): Deferred<Response<List<IngresoCliente>>>
}