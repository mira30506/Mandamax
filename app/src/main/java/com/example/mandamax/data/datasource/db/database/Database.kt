package com.example.mandamax.data.datasource.db.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mandamax.data.datasource.db.daos.AbonoActualDao
import com.example.mandamax.data.datasource.db.daos.LogUsuarioDao
import com.example.mandamax.data.datasource.db.daos.MandadoDao
import com.example.mandamax.data.datasource.db.daos.UsuarioDao
import com.example.mandamax.data.models.*

@Database(entities = [Mandado::class, Usuario::class,LogUsuario::class, AbonoActual::class],version = 4,exportSchema = false)
abstract class Database : RoomDatabase() {
      abstract  fun mandadoDao(): MandadoDao
      abstract fun UsuarioDao(): UsuarioDao
      abstract fun LogUsuarioDao(): LogUsuarioDao
      abstract  fun AbonoActualDao():AbonoActualDao
}