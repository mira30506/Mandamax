package com.example.mandamax.data.api

import com.example.mandamax.data.models.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiServices {
    @GET("/Home/GetMandado")
    fun getMandado(): Call<ResponseMandado>

    @GET("/Home/Login")
    fun getLogin(@Query("nombre") nombre:String,
                 @Query("password") pass:String,
                 @Query("mac") mac:String,
                 @Query("fecha") fecha_hora:String
                 ) : Call<ResponseUsuario>


    @GET("/Home/GetCarteraClientes")
    fun getAbonoClientes(@Query("token") token:String):Call<ResponseAbonoActual>

    @POST("/Home/SendAbonoNormal")
    fun sendAbono(@Query("token") token:String,
                  @Body abono:Abono
                 ) : Call<ResponsePostAbono>

    @GET("/Home/GetAbonos")
    fun getAbonos(@Query("token") token:String):Call<ResponseGetAbono>

   /* @POST("/Home/newClient")
    fun newClient(@Query("token") token:String,)
    */
}