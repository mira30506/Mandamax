package com.example.mandamax.data.datasource.memory

import com.example.mandamax.data.datasource.db.daos.AbonoActualDao
import com.example.mandamax.data.models.AbonoActual


class AbonoActualMemoryDS(private val diskDS:AbonoActualDao?) {

    fun getAbonos() = diskDS?.getAbonos()

    fun getAbonoByFolio(Folio:Int)=diskDS?.getAbono(Folio)

    fun trucante(){
        diskDS?.truncate()
    }

    fun saveAbonos(listAbonosActual: List<AbonoActual>){
        diskDS?.SaveAll(listAbonosActual)
    }

}