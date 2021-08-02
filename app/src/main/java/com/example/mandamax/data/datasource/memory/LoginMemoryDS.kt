package com.example.mandamax.data.datasource.memory

import com.example.mandamax.data.datasource.db.daos.LogUsuarioDao
import com.example.mandamax.data.datasource.db.daos.UsuarioDao
import com.example.mandamax.data.models.LogUsuario
import com.example.mandamax.data.models.Usuario

class LoginMemoryDS(private val  diskDS: UsuarioDao?,private val logUsuarioDao: LogUsuarioDao?) {

    fun getUsuario()= diskDS?.getUsuario()
     fun insertUser( usuario:List<Usuario>){
         diskDS?.insert(usuario)
     }


    fun saveUser(logUsuario: LogUsuario){
        logUsuarioDao?.insert(logUsuario);
    }
    fun truncate(){
        logUsuarioDao?.truncate()
    }

    fun getUser(): LogUsuario? {
      return   logUsuarioDao?.getUsuario()
    }
}