package com.example.demoapithroughuserlogin

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.demoapithroughuserlogin.adapter.UserDetailsAdapter
import com.example.demoapithroughuserlogin.databinding.ActivityUserDetailsBinding
import com.example.demoapithroughuserlogin.model.UserDetails
import com.example.demoapithroughuserlogin.viewmodel.UserDetailsViewModel

class UserDetailsActivity : AppCompatActivity() {

    private val binding: ActivityUserDetailsBinding by lazy {
        ActivityUserDetailsBinding.inflate(layoutInflater)
    }

    private lateinit var userAdapter: UserDetailsAdapter

    val viewModel = UserDetailsViewModel()

    val LAUNCH_SECOND_ACTIVITY = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val nameData = intent.getStringExtra("name")
        binding.tvWelcomeUser.text = "Welcome " + nameData

        val userId = intent.getLongExtra("userId", 0L)

        // initRecyclerView()
        binding.rcvVwUserDetails.layoutManager = LinearLayoutManager(this)
        userAdapter = UserDetailsAdapter(viewModel.userList)
        binding.rcvVwUserDetails.adapter = userAdapter

        binding.btnAddDetails.setOnClickListener {
            val intent = Intent(this, UserInformationRegisterActivity::class.java)
            intent.putExtra("userId", userId)
            startActivityForResult(intent, LAUNCH_SECOND_ACTIVITY)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == LAUNCH_SECOND_ACTIVITY) {
            if (resultCode == Activity.RESULT_OK) {
                val result = data?.getParcelableExtra<UserDetails>("result")
                result?.let {
                    viewModel.userList.add(it)
                }
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                Toast.makeText(this, "request cancelled!", Toast.LENGTH_SHORT).show()
            }
            userAdapter.setData(viewModel.userList)
            binding.rcvVwUserDetails.visibility = View.VISIBLE
           // Log.e("check", "${viewModel.userList}")
        }
    }


}