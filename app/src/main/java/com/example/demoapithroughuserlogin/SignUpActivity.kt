package com.example.demoapithroughuserlogin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.demoapithroughuserlogin.database.SignUpDatabase
import com.example.demoapithroughuserlogin.databinding.ActivitySignUpBinding
import com.example.demoapithroughuserlogin.entity.SignUpDetailsEntity
import com.example.demoapithroughuserlogin.viewmodel.SignUpViewModel

class SignUpActivity : AppCompatActivity() {

    val binding: ActivitySignUpBinding by lazy {
        ActivitySignUpBinding.inflate(layoutInflater)
    }

    val viewModel = SignUpViewModel()
    lateinit var database: SignUpDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        // Log.e("aaa", "onCreate: ${viewModel.returnString()}")

        database = SignUpDatabase.getDatabse(applicationContext)

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
               viewModel.insertUserIfNotExists(mob,name,password, database){
                   isInserted, message, username, userId->
                   if (isInserted)
                   {
                       val intent = Intent(this, UserDetailsActivity::class.java)
                       intent.putExtra("name",username)
                       intent.putExtra("userId",userId)
                       startActivity(intent)

                       Toast.makeText(this, "$message", Toast.LENGTH_SHORT).show()
                   }
                   else{
                       Toast.makeText(this, "$message", Toast.LENGTH_SHORT).show()
                   }
               }



            } else {
                Toast.makeText(this, "Can not SignUp", Toast.LENGTH_SHORT).show()
            }
        }

    }



}