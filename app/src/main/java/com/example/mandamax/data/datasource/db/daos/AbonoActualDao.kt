package com.example.mandamax.data.datasource.db.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.mandamax.data.models.AbonoActual



@Dao
interface AbonoActualDao {

    @Insert
    fun SaveAll(abonoList:List<AbonoActual>)

    @Query("delete from AbonoActual")
    fun truncate()

    @Query("select * from AbonoActual where Folio= :Folio")
    fun getAbono(Folio:Int): AbonoActual

    @Query("select * from AbonoActual")
    fun getAbonos(): List<AbonoActual>

}