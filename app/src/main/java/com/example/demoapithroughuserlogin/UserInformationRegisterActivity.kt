package com.example.demoapithroughuserlogin

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.demoapithroughuserlogin.databinding.ActivityUserInformationRegisterBinding
import com.example.demoapithroughuserlogin.model.UserDetails


class UserInformationRegisterActivity : AppCompatActivity() {

    private val binding: ActivityUserInformationRegisterBinding by lazy {
        ActivityUserInformationRegisterBinding.inflate(layoutInflater)
    }

    //lateinit var arrList : ArrayList<UserDetails>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnSubmit.setOnClickListener {
            val id = binding.edId.text.trim().toString()
            val name = binding.edName.text.trim().toString()
            val address = binding.edAddress.text.trim().toString()
            val contact = binding.edConatct.text.trim().toString()
            val email = binding.edEmail.text.trim().toString()

            val intent = Intent()
            intent.putExtra("result", UserDetails(id, name, address, contact, email))
            setResult(RESULT_OK, intent)
            finish()

            //arrList.add(UserDetails(id, name, address, contact, email))

        }



    }
//
//    interface CallListListener{
//        fun listData(list:ArrayList<UserDetails>)
//    }
}