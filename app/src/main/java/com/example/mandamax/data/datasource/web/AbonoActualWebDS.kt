package com.example.mandamax.data.datasource.web

import androidx.lifecycle.Observer
import com.example.mandamax.data.api.ApiServices
import com.example.mandamax.data.models.ResponseAbonoActual

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class AbonoActualWebDS(private val apiServices: ApiServices?) {
    fun getAbonos(token:String, observer: Observer<Any>){
        apiServices?.getAbonoClientes(token)?.enqueue(object : Callback<ResponseAbonoActual>{
            override fun onResponse(
                call: Call<ResponseAbonoActual>,
                response: Response<ResponseAbonoActual>
            ) {
                if(response.isSuccessful){
                    if(response.body()?.status==200){
                        if(response.body()?.data!!.isNotEmpty()){
                            observer.onChanged(response.body())
                        }
                        else{
                            observer.onChanged(response.body()?.message)
                        }
                    }
                    else{
                        observer.onChanged(response.body()?.message)
                    }
                }
            }
            override fun onFailure(call: Call<ResponseAbonoActual>, t: Throwable) {
                observer.onChanged(t.message)
            }
        })
    }

}