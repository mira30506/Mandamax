package com.example.mandamax.iu.clientes

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.mandamax.data.models.AbonoActual
import com.example.mandamax.repository.AbonoAtualRepository

class AbonosClientesViewModel (private val repository: AbonoAtualRepository):ViewModel() {

        var mutableLiveDataAbonos:MutableLiveData<List<AbonoActual>>
        var errorAbonos:MutableLiveData<String>

        init{
            mutableLiveDataAbonos=MutableLiveData()
            errorAbonos= MutableLiveData()
        }

    fun getAbonos(token:String){
        repository.getAbonosfromWeb(token, Observer {
            if(it is String)
                errorAbonos.postValue(it)
            else
                mutableLiveDataAbonos.postValue(it as List<AbonoActual>)

        });
    }




}