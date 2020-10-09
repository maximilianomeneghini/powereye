package com.unlam.powereye.entity

import android.os.Parcel
import android.os.Parcelable
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class IngresoCliente(sexo: String, edad: String, producto: String) :
    Parcelable {
    var sexo: String
    var edad: String
    var producto: String
    init {
        this.sexo = sexo
        this.edad = edad
        this.producto = producto
    }

    constructor(source: Parcel) : this(
        source.readString()!!,
        source.readString()!!,
        source.readString()!!
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(sexo)
        writeString(edad)
        writeString(producto)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<IngresoCliente> = object : Parcelable.Creator<IngresoCliente> {
            override fun createFromParcel(source: Parcel): IngresoCliente =
                IngresoCliente(source)
            override fun newArray(size: Int): Array<IngresoCliente?> = arrayOfNulls(size)
        }
    }
}