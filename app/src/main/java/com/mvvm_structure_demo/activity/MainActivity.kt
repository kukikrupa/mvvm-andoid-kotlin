package com.mvvm_structure_demo.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.snackbar.Snackbar
import com.mvvm_structure_demo.R
import com.mvvm_structure_demo.databinding.ActivityMainBinding
import com.mvvm_structure_demo.injection.ViewModelFactory
import com.mvvm_structure_demo.utils.validator.Validator
import com.mvvm_structure_demo.viewmodel.SignUpViewModel

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private var mBinding: ActivityMainBinding? = null
    private lateinit var viewModel: SignUpViewModel
    private var errorSnackbar: Snackbar? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setClickLister()

        viewModel = ViewModelProviders.of(this, ViewModelFactory(this)).get(SignUpViewModel::class.java)
        viewModel.errorMessage.observe(this, Observer { errorMessage ->
            if (errorMessage != null) showError(errorMessage) else hideError()
        })

    }

    fun setClickLister() {
        mBinding!!.btnSignUp.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when (view) {


            mBinding!!.btnSignUp -> {
                checkSignUpValidation(mBinding!!.edtName.text.toString(),mBinding!!.edtMobileNumber.text.toString()
                    ,mBinding!!.edtEmail.text.toString())
            }
        }
    }

    private fun showError(errorMessage: String) {
        errorSnackbar = Snackbar.make(mBinding!!.root, errorMessage, Snackbar.LENGTH_LONG)
        errorSnackbar?.show()
    }

    private fun hideError(){
        errorSnackbar?.dismiss()
    }

    private fun checkSignUpValidation(name: String,mobile_number :String,email:String) {
        Validator.validateFirstName(name)?.let {
            mBinding!!.edtName.setError(getString(it.msg))
            mBinding!!.edtName.requestFocus()

            return
        }
        mBinding!!.edtName.setError(null)

        Validator.validateTelephone(mobile_number)?.let {
            mBinding!!.edtMobileNumber.setError(getString(it.msg))
            mBinding!!.edtMobileNumber.requestFocus()

            return
        }
        mBinding!!.edtMobileNumber.setError(null)

        Validator.validateEmail(email)?.let {
            mBinding!!.edtEmail.setError(getString(it.msg))
            mBinding!!.edtEmail.requestFocus()

            return
        }
        mBinding!!.edtEmail.setError(null)


        viewModel.apiSignUp(name,mobile_number,email)
    }

}
