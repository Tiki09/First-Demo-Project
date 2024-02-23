package com.example.firebasepractice.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.firebasepractice.adapter.EmployeeAdapter
import com.example.firebasepractice.databinding.ActivityEmployeeListBinding
import com.example.firebasepractice.model.EmployeeData
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.FirebaseDatabase


class EmployeeListActivity : AppCompatActivity() {


    private val binding : ActivityEmployeeListBinding by lazy {
        ActivityEmployeeListBinding.inflate(layoutInflater)
    }
    private lateinit var adapter: EmployeeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.rcvVwEmpList.layoutManager = LinearLayoutManager(this)

        val options: FirebaseRecyclerOptions<EmployeeData> =
            FirebaseRecyclerOptions.Builder<EmployeeData>()
                .setQuery(
                    FirebaseDatabase.getInstance().reference.child("Employee"),
                    EmployeeData::class.java
                )
                .build()

        adapter = EmployeeAdapter(options)
        binding.rcvVwEmpList.adapter = adapter
    }

    override fun onStart() {
        super.onStart()
        adapter.startListening()
    }

    override fun onStop() {
        super.onStop()
        adapter.stopListening()
    }
}