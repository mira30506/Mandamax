package com.example.mandamax

import android.app.Application
import android.content.Context


class MainApplication: Application(){
    companion object{

    }

    override fun onCreate() {
//        xxx = this

        appContainer= AppContainer(applicationContext,"mandamax")
        super.onCreate()
    }

   lateinit var appContainer: AppContainer
}