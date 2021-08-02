package com.example.mandamax.data.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class ResponseUsuario(
    @SerializedName(value = "status") val status : Int,
    @SerializedName(value="description") val message:String,
    @SerializedName(value = "data") val data:LogUsuario
)

@Entity
@Parcelize
data class LogUsuario(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="idUsuario")
    val idUsuario: Int,
        @ColumnInfo(name="Usuario")
        val Usuario:String,
        @ColumnInfo(name="Token")
        val Token:String
): Parcelable




@Entity(tableName = "Usuario")
@Parcelize
data class Usuario (
    @PrimaryKey
    @ColumnInfo(name="idUsuario")
    @SerializedName(value="Id") val idUsuario: Int,

    @ColumnInfo(name = "Nombre")
    @SerializedName(value="Nombre") val Nombre:String,

    @ColumnInfo(name="Apellido")
    @SerializedName(value="Apellido") val Apellido:String,

    @ColumnInfo(name="Numero_celular")
    @SerializedName(value="Numero") val celular:Int,

    @ColumnInfo(name="Direccion")
    @SerializedName(value="Direccion") val direccion:String,
    @SerializedName (value="token") val token :String

) : Parcelable


