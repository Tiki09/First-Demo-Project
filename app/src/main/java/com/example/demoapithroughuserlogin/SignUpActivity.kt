package com.example.demoapithroughuserlogin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.demoapithroughuserlogin.databinding.ActivitySignUpBinding
import com.example.demoapithroughuserlogin.viewmodel.SignUpViewModel

class SignUpActivity : AppCompatActivity() {

    val binding:ActivitySignUpBinding by lazy{
        ActivitySignUpBinding.inflate(layoutInflater)
    }

    val viewModel=SignUpViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
       // Log.e("aaa", "onCreate: ${viewModel.returnString()}")

        binding.tvAlreadyAccount.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        binding.btnSubmit.setOnClickListener {

            val name = binding.edUserName.text.trim().toString()
            val mob = binding.edMobileNumber.text.trim().toString()
            val password = binding.edPassword.text.trim().toString()
            val confirmPassword = binding.edConfirmPassword.text.trim().toString()

            Log.e("check","name:-${viewModel.isNameValidate(name)}")
            Log.e("check","mobile:-${viewModel.isMobValidate(mob)}")
            Log.e("check","password:-${viewModel.isPasswordValidate(password)}")
            Log.e("check","confirm password:-${viewModel.isBothPasswordAreSame(password, confirmPassword)}")

            if (viewModel.isNameValidate(name) &&
                viewModel.isMobValidate(mob) &&
                viewModel.isPasswordValidate(password) &&
                    viewModel.isBothPasswordAreSame(password, confirmPassword)){
                val toast = Toast.makeText(this, "SignUp Successfully", Toast.LENGTH_SHORT)
                toast.show()

                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
            }
            else{
                val toast = Toast.makeText(this, "Can not SignUp", Toast.LENGTH_SHORT)
                toast.show()
            }
        }

    }
}