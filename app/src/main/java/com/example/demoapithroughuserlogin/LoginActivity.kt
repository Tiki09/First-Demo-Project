package com.example.demoapithroughuserlogin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.demoapithroughuserlogin.databinding.ActivityLoginBinding
import com.example.demoapithroughuserlogin.databinding.ActivitySignUpBinding
import com.example.demoapithroughuserlogin.viewmodel.LoginViewModel

class LoginActivity : AppCompatActivity() {

    val binding: ActivityLoginBinding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }

    val loginViewModel = LoginViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.tvNoAccount.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

        binding.btnLogin.setOnClickListener {
            val mob = binding.edMobileNumber.text.trim().toString()
            val password = binding.edPassword.text.trim().toString()

            if (!loginViewModel.isMobValidate(mob)) {
                Toast.makeText(this, "Please Enter 10 digit mobile number", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }
            loginViewModel.isValidPassword(password) { isPassValid, message ->
                if (!isPassValid) {
                    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
                    return@isValidPassword
                }

            }

            if (loginViewModel.mob == mob && loginViewModel.password == password) {
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "please enter valid credentials", Toast.LENGTH_SHORT).show()
            }

        }

    }


}