package com.example.mandamax.data.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


data class ResponseAbonoActual(
    @SerializedName("status") val status:Int,
    @SerializedName("description") val message:String,
    @SerializedName("data") val data:List<AbonoActual>
)

@Entity
@Parcelize
data class AbonoActual(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="Id")
    val Id:Int,
    @ColumnInfo(name="Folio")val Folio:Int,
    @ColumnInfo(name="Cliente")val Cliente: String=" ",
    @ColumnInfo(name="Fecha")val Fecha:String,
    @ColumnInfo(name="Cantidad")val Cantidad:Float,
    @ColumnInfo(name="SaldoAnt")val SaldoAnt:Float,
    @ColumnInfo(name="SaldoAct")val SaldoAct:Float,
    @ColumnInfo(name="TipAbono")val TipAbono:Int,
    @ColumnInfo(name="numpaquete")val numpaquete:Int,
    @ColumnInfo(name="Nombre") val Nombre:String,
    @ColumnInfo(name="Direccion") val Direccion:String,
    @ColumnInfo(name="Colonia") val Colonia:String,
    @ColumnInfo(name="Estatus") val Estatus:String
):Parcelable