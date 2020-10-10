package com.unlam.powereye.entity

import android.os.Parcel
import android.os.Parcelable
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class Producto(cantidadVistas: Int, precio: Double?, nombre: String) :
    Parcelable {
    var cantidadVistas: Int
    var precio: Double?
    var nombre: String
    init {
        this.cantidadVistas = cantidadVistas
        this.precio = precio
        this.nombre = nombre
    }

    constructor(source: Parcel) : this(
        source.readInt(),
        source.readDouble(),
        source.readString()!!
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeInt(cantidadVistas)
        writeDouble(precio!!)
        writeString(nombre)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Producto> = object : Parcelable.Creator<Producto> {
            override fun createFromParcel(source: Parcel): Producto =
                Producto(source)
            override fun newArray(size: Int): Array<Producto?> = arrayOfNulls(size)
        }
    }
}