package com.example.mandamax.iu

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import com.example.mandamax.*
import com.example.mandamax.databinding.FragmentLoginBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.apache.commons.codec.binary.Hex

import org.apache.commons.codec.binary.Hex.encodeHex
import org.apache.commons.codec.digest.DigestUtils

class LoginFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var loginViewModel:LoginViewModel?=null
    private lateinit var binding:FragmentLoginBinding

    private val USER_PERMITED="USUARIO_PERMITIDO"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginViewModel=(activity?.application as MainApplication)
                .appContainer
                .providesLoginContainer()
                .providesLoginViewModelFactory()
                .create()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        setObservers()
        binding= FragmentLoginBinding.inflate(layoutInflater)
        binding.btnLogin.setOnClickListener {
            getLogin()
        }
        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment LoginFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                LoginFragment().apply {
                }
    }


    fun setObservers(){
        loginViewModel?.susefullResponseLogin?.observe(viewLifecycleOwner, Observer {
            if(it.message.equals(USER_PERMITED)){
                Toast.makeText(context,"Bienvenido:"+it.data.Usuario,Toast.LENGTH_SHORT).show()
                binding.progressBar.visibility=View.GONE
                binding.login.visibility=View.VISIBLE
                binding.txtError.visibility=View.GONE
                binding.btnLogin.isEnabled=true
                CoroutineScope(Dispatchers.IO).launch {
                    val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
                    with (sharedPref?.edit()) {
                        this?.putString(getString(R.string.user), it.data.Usuario)
                        this?.putString(getString(R.string.token),it.data.Token)
                        this?.commit()
                }
                }
                startActivity(Intent(context,MainActivity2::class.java))
            }
            if(it.message.equals("CONTRASEÑA_OR_USER_INCORRECT")){
                setErrores("Contraseña o usuario incorrecto")
            }
        })
        loginViewModel?.failureResponseLogin?.observe(this, Observer {
            setErrores(it)
        })



    }

    fun getLogin(){
        val user=binding.edtUsuario.text.toString()
        val password=binding.edtPassword.text.toString()
        val mac="pruebas"
        val fecha="pruebas"
        var complete:Boolean=true
        if(user.length==0 || password.length==0){
            if(user.length==0)
                binding.edtPassword.error="El usuario no puede estar vacio"
            if(password.length==0)
                binding.edtUsuario.error="la contraseña no puede estar vacia "
            complete=false
        }
      if(complete==true){
          binding.btnLogin.isEnabled=false
          binding.login.visibility=View.GONE
          binding.progressBar.visibility=View.VISIBLE
           val  pass= String(Hex.encodeHex(DigestUtils.md5(password)))

          loginViewModel?.getLoginResponse(user,pass,"","")
      }
    }

    fun setErrores(error:String ){
        Toast.makeText(context,error,Toast.LENGTH_SHORT).show()
        binding.txtError.visibility=View.VISIBLE
        binding.txtError.text=error
        binding.progressBar.visibility=View.GONE
        binding.login.visibility=View.VISIBLE
        binding.btnLogin.isEnabled=true
    }

}