package com.example.mandamax.data.datasource.db.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.mandamax.data.models.LogUsuario

@Dao
interface LogUsuarioDao {
    @Insert
    fun insert(logUsuario: LogUsuario)

    @Query("select * from LogUsuario")
    fun getUsuario(): LogUsuario

    @Query("delete from LogUsuario")
    fun truncate()


}