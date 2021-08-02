package com.example.mandamax.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.Observer
import com.example.mandamax.data.datasource.memory.LoginMemoryDS
import com.example.mandamax.data.datasource.web.loginWebDS
import com.example.mandamax.data.models.LogUsuario
import com.example.mandamax.data.models.ResponseUsuario
import com.example.mandamax.data.models.Usuario
import com.example.mandamax.sys.utils.utils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class UserRepository (
        private val loginWebDS: loginWebDS,
        private val loginMemoryDS: LoginMemoryDS,
        private val context:Context?
)

{
    fun login(nombre:String, password:String, mac:String,fecha:String,observer: Observer<Any>){
        if (utils.isInternetConection(context))
             loginWebDS.login(nombre,password,mac,fecha,saveUser(observer))
        else
            observer.onChanged("No tienes acceso a internet")
    }
    private fun saveUser( observer: Observer<Any>):Observer<Any>{
        return Observer {
            CoroutineScope(Dispatchers.IO).launch {
                if(it is String){
                    observer.onChanged(it)
                }
                else if(it is ResponseUsuario){
                    loginMemoryDS.truncate()
                    loginMemoryDS.saveUser(it.data)
                    observer.onChanged(it)
                }
            }
        }
    }






    fun getUser(observer: Observer<Any>){
            CoroutineScope(Dispatchers.IO).launch {
                observer.onChanged(loginMemoryDS.getUser())
            }
    }
}