package com.mvvm_structure_demo.injection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.appcompat.app.AppCompatActivity
import com.mvvm_structure_demo.viewmodel.SignUpViewModel


class ViewModelFactory(private val activity: AppCompatActivity) : ViewModelProvider.Factory {


    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(SignUpViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SignUpViewModel(activity) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}