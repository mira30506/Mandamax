package com.example.mandamax

import android.content.Context
import androidx.room.Room
import com.example.mandamax.data.api.ApiServices
import com.example.mandamax.data.datasource.db.daos.AbonoActualDao
import com.example.mandamax.data.datasource.db.daos.LogUsuarioDao
import com.example.mandamax.data.datasource.db.daos.UsuarioDao
import com.example.mandamax.data.datasource.db.database.Database
import com.example.mandamax.data.datasource.memory.AbonoActualMemoryDS
import com.example.mandamax.data.datasource.memory.LoginMemoryDS
import com.example.mandamax.data.datasource.web.AbonoActualWebDS
import com.example.mandamax.data.datasource.web.loginWebDS
import com.example.mandamax.iu.clientes.AbonosClientesViewModel
import com.example.mandamax.repository.AbonoAtualRepository
import com.example.mandamax.repository.UserRepository
import com.example.mandamax.sys.utils.Globals
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit.SECONDS

class AppContainer(val context: Context, val databaseName: String) {

        private  var retrofit: ApiServices? = null
        private  var db:Database? = null
        private  var gson: Gson? = null
        private  var okHttpClient: OkHttpClient? = null
        private var loginContainer:LoginContainer?=null
        private var abonoContainer:AbonoContainer?=null
        private var mainActivityViewModelFactory:MainActivityViewModelFactory?=null
          private fun providesOkHttpClient():OkHttpClient? {
            if(okHttpClient==null){
                val interceptor = HttpLoggingInterceptor()
                interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
                return OkHttpClient.Builder()
                        .connectTimeout(Globals.TIME_SECONDS, SECONDS)
                        .writeTimeout(Globals.TIME_SECONDS, SECONDS)
                        .readTimeout(Globals.TIME_SECONDS, SECONDS)
                        .addInterceptor(interceptor)
                        .build()
            }
            return okHttpClient
        }

         private fun providesGson():Gson? {
            if(gson==null){
                gson=GsonBuilder().create()
            }
            return gson
        }

        private fun getApiServices():ApiServices? {
            if (retrofit==null){
                retrofit = Retrofit.Builder()
                        .client(providesOkHttpClient())
                        .addConverterFactory(GsonConverterFactory.create(providesGson()))
                        .baseUrl("http://edjuarezestrada-001-site1.gtempurl.com/")
                        .build()
                        .create(ApiServices::class.java)
            }
            return  retrofit

        }
         private fun getDatabase():Database? {
            if(db==null){
                db = Room.databaseBuilder(
                        context,
                        Database::class.java, databaseName
                ).fallbackToDestructiveMigration()
                        .build()
            }
            return db
        }
    fun providesMainActivityViewModelFactory():MainActivityViewModelFactory{
        if(mainActivityViewModelFactory==null){
            mainActivityViewModelFactory=MainActivityViewModelFactory(providesLoginContainer().providesUserRepository())
        }

        return mainActivityViewModelFactory as MainActivityViewModelFactory
    }

    fun providesLoginContainer():LoginContainer{
        if(loginContainer==null){
            loginContainer=LoginContainer(getApiServices(),getDatabase()?.UsuarioDao(),context,getDatabase()?.LogUsuarioDao())
        }
        return loginContainer as LoginContainer
    }

    fun providesAbonosContainer():AbonoContainer{
        if(abonoContainer == null){
            abonoContainer= AbonoContainer(getApiServices(),getDatabase()?.AbonoActualDao(),context)
        }
        return abonoContainer as AbonoContainer
    }
    companion object{
        var appContainer: AppContainer?=null
        fun getAppContainer(context:Context,nombre:String):AppContainer{
            if(appContainer==null){
                appContainer= AppContainer(context,nombre)
            }
            return appContainer as AppContainer
        }

    }
}



class AbonoContainer(val apiServices: ApiServices?,val abonoActualDao: AbonoActualDao?,val context: Context){
    private var abonoActualWebDS : AbonoActualWebDS?=null
    private var abonoActualMemoryDS : AbonoActualMemoryDS?=null
    private var abonoAtualRepository : AbonoAtualRepository?=null
    private var abonosClientesViewModelFactory : AbonosViewModelFactory?=null

    fun providesAbonoActualWebDS() : AbonoActualWebDS{
        if (abonoActualWebDS == null){
            abonoActualWebDS = AbonoActualWebDS(apiServices)
        }
        return abonoActualWebDS as AbonoActualWebDS;
    }

    fun providesAbonoActualMemoryDS() : AbonoActualMemoryDS {
        if (abonoActualMemoryDS == null) {
            abonoActualMemoryDS = AbonoActualMemoryDS(abonoActualDao)
        }
        return abonoActualMemoryDS as AbonoActualMemoryDS
    }

    fun providesAbonoAtualRepository() : AbonoAtualRepository{
        if(abonoAtualRepository == null) {
            abonoAtualRepository = AbonoAtualRepository(providesAbonoActualMemoryDS(),providesAbonoActualWebDS(),context)
        }
        return abonoAtualRepository as AbonoAtualRepository
    }

    fun providesAbonoClientesViewModel() : AbonosViewModelFactory{
        if(abonosClientesViewModelFactory == null){
            abonosClientesViewModelFactory = AbonosViewModelFactory(providesAbonoAtualRepository())
        }
        return abonosClientesViewModelFactory as AbonosViewModelFactory
    }




}

class LoginContainer
    (   private val apiServices: ApiServices?,
    private val usuarioDao: UsuarioDao?,
    private val context: Context?,
    private val logUsuarioDao: LogUsuarioDao?
    )
{
        private var loginWebDS : loginWebDS? = null
        private var loginMemoryDS : LoginMemoryDS?=null
        private var userRepository : UserRepository?=null
        private var   loginViewModelFactory:LoginViewModelFactory?=null
        private fun providesLoginWebDS(): loginWebDS {
            if (loginWebDS == null) {
                loginWebDS = loginWebDS(apiServices)
            }
            return loginWebDS as loginWebDS
        }

        fun providesLoginMemoryDS():LoginMemoryDS{
            if(loginMemoryDS==null){
                loginMemoryDS= LoginMemoryDS(usuarioDao,logUsuarioDao)
            }
            return loginMemoryDS as LoginMemoryDS
        }
        fun providesUserRepository():UserRepository{
            if(userRepository==null){
                userRepository= UserRepository(providesLoginWebDS(),providesLoginMemoryDS(),context)
            }
            return userRepository as UserRepository
        }

        fun providesLoginViewModelFactory():LoginViewModelFactory{
            if(loginViewModelFactory==null){
                loginViewModelFactory=LoginViewModelFactory(providesUserRepository())
            }

            return loginViewModelFactory as LoginViewModelFactory
        }



}