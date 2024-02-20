package com.example.demoapithroughuserlogin

import android.app.Application
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.demoapithroughuserlogin.database.SignUpDatabase
import com.example.demoapithroughuserlogin.databinding.ActivitySignUpBinding
import com.example.demoapithroughuserlogin.repository.SignUpRepository
import com.example.demoapithroughuserlogin.viewmodel.SignUpViewModel

class SignUpActivity : AppCompatActivity() {

    val binding: ActivitySignUpBinding by lazy {
        ActivitySignUpBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val database = SignUpDatabase.getDatabse(this)
        val repository = SignUpRepository(database)
        val viewModel = SignUpViewModel(repository)

        binding.tvAlreadyAccount.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        binding.btnSubmit.setOnClickListener {

            val name = binding.edUserName.text.trim().toString()
            val mob = binding.edMobileNumber.text.trim().toString()
            val password = binding.edPassword.text.trim().toString()
            val confirmPassword = binding.edConfirmPassword.text.trim().toString()

            Log.e("check", "name:-${viewModel.isNameValidate(name)}")
            Log.e("check", "mobile:-${viewModel.isMobValidate(mob)}")
            Log.e("check", "password:-${viewModel.isPasswordValidate(password)}")
            Log.e(
                "check",
                "confirm password:-${viewModel.isBothPasswordAreSame(password, confirmPassword)}"
            )

            if (viewModel.isNameValidate(name) &&
                viewModel.isMobValidate(mob) &&
                viewModel.isPasswordValidate(password) &&
                viewModel.isBothPasswordAreSame(password, confirmPassword)
            ) {
                viewModel.insertUserIfNotExists(
                    mob,
                    name,
                    password
                ) { isInserted, message, username, userId, userMobile ->
                    if (isInserted) {
                        val intent = Intent(this, UserDetailsActivity::class.java)
                        intent.putExtra("name", username)
                        intent.putExtra("userId", userId)
                        intent.putExtra("userMobile", userMobile)
                        startActivity(intent)

                        Toast.makeText(this, "$message", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "$message", Toast.LENGTH_SHORT).show()
                    }
                }

            } else {
                Toast.makeText(this, "Can not SignUp", Toast.LENGTH_SHORT).show()
            }
        }

    }

}