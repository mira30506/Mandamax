package com.example.mandamax.data.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


data class ResponseMandados(
        @SerializedName(value ="status") val status:Int,
        @SerializedName(value="message") val message:String,
        @SerializedName(value = "datos") val dato: List<Mandado>
)

data class ResponseMandado(
        @SerializedName(value = "status") val status: Int,
        @SerializedName(value = "message") val message: String,
        @SerializedName(value="datos") val dato: Mandado
)


@Entity(tableName = "Mandado")
@Parcelize
data class Mandado (
    @PrimaryKey
    @ColumnInfo(name = "idMandado")
    @SerializedName(value = "idMandado") val idMandado: Int,

    @ColumnInfo(name ="NombreRepartidor")
    @SerializedName(value="Repartidor") val Repartidos:String,

    @ColumnInfo(name="Direccion_envio")
    @SerializedName(value="Direccion_Envio") val direccion:String,

    @ColumnInfo(name = "Nombre_destinatario")
    @SerializedName(value = "Nonbre_destinatario") val Nombre_destinatario:String
) : Parcelable