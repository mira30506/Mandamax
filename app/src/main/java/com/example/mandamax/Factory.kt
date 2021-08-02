package com.example.mandamax

import com.example.mandamax.iu.LoginViewModel
import com.example.mandamax.iu.clientes.AbonosClientesViewModel
import com.example.mandamax.repository.AbonoAtualRepository
import com.example.mandamax.repository.UserRepository

interface Factory {
    fun create(): Any
}
    class LoginViewModelFactory(private val userRepository: UserRepository): Factory{
        override fun create(): LoginViewModel {
            return LoginViewModel(userRepository)
        }
    }


class AbonosViewModelFactory(private val abonoActualRepository : AbonoAtualRepository) : Factory{
    override fun create(): AbonosClientesViewModel {
        return AbonosClientesViewModel(abonoActualRepository)
    }

}

class MainActivityViewModelFactory(private val userRepository: UserRepository?):Factory{
    override fun create(): MainActivityViewModel {
        return MainActivityViewModel(userRepository)
    }
}
