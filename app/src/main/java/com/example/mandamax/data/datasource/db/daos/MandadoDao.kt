package com.example.mandamax.data.datasource.db.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mandamax.data.models.Mandado


@Dao
interface MandadoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(lista:List<Mandado>)

    @Query("select * from Mandado")
    fun getMandados():List<Mandado>

//ffdgdfgfdgfdg
}