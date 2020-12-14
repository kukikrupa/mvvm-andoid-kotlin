package com.mvvm_structure_demo.injection.component

import com.mvvm_structure_demo.injection.module.NetworkModule
import com.mvvm_structure_demo.viewmodel.SignUpViewModel
import dagger.Component
import javax.inject.Singleton

/**
 * Component providing inject() methods for presenters.
 */
@Singleton
@Component(modules = [(NetworkModule::class)])
interface ViewModelInjector {

    fun inject(signUpViewModel: SignUpViewModel)

    @Component.Builder
    interface Builder {
        fun build(): ViewModelInjector

        fun networkModule(networkModule: NetworkModule): Builder
    }
}