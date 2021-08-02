package com.example.mandamax

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.mandamax.iu.Abono.adapter.AbonosFragment
import com.example.mandamax.iu.LoginFragment
import com.example.mandamax.iu.clientes.AbonosClientesFragment

class MainActivity2 : AppCompatActivity() {
    var appContainer:AppContainer?=null
    var sharedPref:SharedPreferences?=null

//    var mainActivityViewModel:MainActivityViewModel

    override fun onBackPressed() {
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        with (sharedPref?.edit()) {
            this?.putString(getString(R.string.user), "")
            this?.commit()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main4)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        appContainer=(application as MainApplication).appContainer
        sharedPref = this?.getPreferences(Context.MODE_PRIVATE) ?: return
        val Usuario  = sharedPref?.getString(getString(R.string.user), "")
        if(Usuario.equals("")|| Usuario!!.isEmpty()) {
                navView.visibility=View.GONE
                supportFragmentManager.beginTransaction()
                        .replace(R.id.nav_host_fragment, LoginFragment())
                        .addToBackStack(null)
                        .commit()
            }
            else {
            navView.visibility = View.VISIBLE
            when (navView.selectedItemId) {
                R.id.navigation_abono ->
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.nav_host_fragment, AbonosClientesFragment())
                        .commit()
                R.id.navigation_Clientes ->
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.nav_host_fragment, AbonosFragment.newInstance("",""))
                        .commit()
            }
        }
    }
}