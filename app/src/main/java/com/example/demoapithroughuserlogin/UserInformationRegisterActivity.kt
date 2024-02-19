package com.example.demoapithroughuserlogin

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.demoapithroughuserlogin.database.SignUpDatabase
import com.example.demoapithroughuserlogin.databinding.ActivityUserInformationRegisterBinding
import com.example.demoapithroughuserlogin.model.UserDetails
import com.example.demoapithroughuserlogin.viewmodel.UserRegistrationViewModel


class UserInformationRegisterActivity : AppCompatActivity() {

    private val binding: ActivityUserInformationRegisterBinding by lazy {
        ActivityUserInformationRegisterBinding.inflate(layoutInflater)
    }

    private var viewmodel = UserRegistrationViewModel()

    private lateinit var database: SignUpDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val userId = intent.getLongExtra("userId", 0L)
        database = SignUpDatabase.getDatabse(this)

        binding.btnSubmit.setOnClickListener {
            val id = binding.edId.text.trim().toString()
            val name = binding.edName.text.trim().toString()
            val address = binding.edAddress.text.trim().toString()
            val contact = binding.edConatct.text.trim().toString()
            val email = binding.edEmail.text.trim().toString()

            if (!viewmodel.isNameValidate(name)) {
                Toast.makeText(this, "Name must contains atleast 3 character", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }

            if (!viewmodel.isAddressValidate(address)) {
                Toast.makeText(this, "Address must be filled", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }

            if (!viewmodel.isMobValidate(contact)) {
                Toast.makeText(this, "Please Enter 10 digit mobile number", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }

            viewmodel.isEmailValidate(email) { isValidEmail, message ->
                if (!isValidEmail) {
                    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
                    return@isEmailValidate
                }
            }

            viewmodel.userRegistrationData(
                id = userId,
                address,
                email,
                database
            )

            val intent = Intent()
            intent.putExtra("result", UserDetails(id, name, address, contact, email))
            setResult(RESULT_OK, intent)
            finish()
        }


    }

}