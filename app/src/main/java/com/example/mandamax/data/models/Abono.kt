package com.example.mandamax.data.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


data class ResponsePostAbono(
    @SerializedName("status") val status:String,
    @SerializedName("description") val description:String,
    @SerializedName("data") val data:String
)

data class ResponseGetAbono(
        @SerializedName("status") val status: String,
        @SerializedName("description") val description: String,
        @SerializedName("data") val data: List<Abono>

)

@Entity
@Parcelize
data class Abono(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="ID") val ID:String,
    @ColumnInfo(name="Saldo") val Saldo:String,
    @ColumnInfo(name="Cliente") val Cliente: String,
    @ColumnInfo(name="Fecha") val Fecha:String,
    @ColumnInfo(name="Cantidad") val Cantidad:Double,
    @ColumnInfo(name="Cobrador") val Cobrador:String,
    @ColumnInfo(name="Tip_Abono") val Tip_Abono:String,
    @ColumnInfo(name="num_paquete") val num_paquete:String
) :Parcelable