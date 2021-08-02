package com.example.mandamax.data.datasource.web

import androidx.lifecycle.Observer
import com.example.mandamax.data.api.ApiServices
import com.example.mandamax.data.models.ResponseUsuario
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class loginWebDS(val apiServices: ApiServices?) {

    fun login(nombre:String,contraseña:String, mac:String, fecha:String,observer: Observer<Any>){
            apiServices?.getLogin(nombre,contraseña,mac,fecha)?.enqueue(object : Callback<ResponseUsuario> {
                override fun onResponse(call: Call<ResponseUsuario>, response: Response<ResponseUsuario>) {
                    if (response.code() == 200) {
                        if (response.body() != null) {
                            if(response.body()!!.status==200){
                            observer.onChanged(response.body())
                            }
                            else if(response.body()!!.status==400){
                                observer.onChanged(response.body()!!.message)
                            }
                        }

                    } else if (!response.isSuccessful) {
                        observer.onChanged("no se puede conectar")
                    }
                }

                override fun onFailure(call: Call<ResponseUsuario>, t: Throwable) {
                    observer.onChanged(t.message)
                }
            });

    }

}