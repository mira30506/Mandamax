package com.example.mandamax.repository

import android.content.Context
import androidx.lifecycle.Observer
import com.example.mandamax.data.datasource.memory.AbonoActualMemoryDS
import com.example.mandamax.data.datasource.web.AbonoActualWebDS
import com.example.mandamax.data.models.ResponseAbonoActual

import com.example.mandamax.sys.utils.utils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AbonoAtualRepository(private val abonoActualMemoryDS: AbonoActualMemoryDS,
                           private val abonoActualWebDS: AbonoActualWebDS,
                            private val context: Context

                           ) {

    fun getAbonosFrolLocal(observer:Observer<Any>):Observer<Any>{
       return Observer {
           CoroutineScope(Dispatchers.IO).launch {
                observer.onChanged(abonoActualMemoryDS.getAbonos())
       }  }
    }
    private fun saveAbonos(observer: Observer<Any>):Observer<Any>{
        return Observer {
            CoroutineScope(Dispatchers.IO).launch {
                if (it is ResponseAbonoActual){
                    if(it.data.isNotEmpty()){
                        abonoActualMemoryDS.trucante()
                        abonoActualMemoryDS.saveAbonos(it.data)
                        observer.onChanged(it.data)
                    }
                    else
                        observer.onChanged("No hay informacion")

                }
                else if(it is String)
                    observer.onChanged(it)
            }
        }
    }

    fun getAbonosfromWeb(token:String,observer: Observer<Any>){
       if(utils.isInternetConection(context))
            abonoActualWebDS.getAbonos(token,saveAbonos(observer))
        else
            getAbonosFrolLocal(observer)
        }

}