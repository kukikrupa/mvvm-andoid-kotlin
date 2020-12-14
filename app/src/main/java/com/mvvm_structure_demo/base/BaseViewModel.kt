package com.mvvm_structure_demo.base

import androidx.lifecycle.ViewModel
import com.mvvm_structure_demo.injection.component.DaggerViewModelInjector
import com.mvvm_structure_demo.injection.component.ViewModelInjector
import com.mvvm_structure_demo.injection.module.NetworkModule
import com.mvvm_structure_demo.viewmodel.SignUpViewModel


abstract class BaseViewModel:ViewModel(){


    private val injector: ViewModelInjector = DaggerViewModelInjector
            .builder()
            .networkModule(NetworkModule)
            .build()

    init {
        inject()
    }

    /**
     * Injects the required dependencies
     */
    private fun inject() {
        when (this) {

            is SignUpViewModel -> injector.inject(this)
        }
    }
}