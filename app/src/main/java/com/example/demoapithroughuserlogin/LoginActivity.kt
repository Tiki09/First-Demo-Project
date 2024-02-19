package com.example.demoapithroughuserlogin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import com.example.demoapithroughuserlogin.database.SignUpDatabase
import com.example.demoapithroughuserlogin.databinding.ActivityLoginBinding
import com.example.demoapithroughuserlogin.viewmodel.LoginViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {

    val binding: ActivityLoginBinding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }

    val loginViewModel = LoginViewModel()

    private lateinit var database: SignUpDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        database = SignUpDatabase.getDatabse(this)

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

            loginViewModel.loginData(mob, password, database) { isLoggedIn, username, userId ->
                if (isLoggedIn) {
                    val intent = Intent(this@LoginActivity, UserDetailsActivity::class.java)
                    intent.putExtra("name", username)
                    intent.putExtra("userId", userId)
                    startActivity(intent)
                } else {
                    Log.e("check", "onCreate: login failed ")
                    Toast.makeText(this@LoginActivity, "Login failed", Toast.LENGTH_SHORT).show()
                }
            }

        }

    }

}


