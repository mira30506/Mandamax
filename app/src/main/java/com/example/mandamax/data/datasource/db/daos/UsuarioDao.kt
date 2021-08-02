package com.example.mandamax.data.datasource.db.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mandamax.data.models.Usuario

@Dao
interface UsuarioDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(u:List<Usuario>)

    @Query("SELECT * FROM usuario ")
    fun getUsuario():Usuario


}