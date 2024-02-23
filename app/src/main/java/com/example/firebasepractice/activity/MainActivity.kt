package com.example.firebasepractice.activity

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.firebasepractice.databinding.ActivityMainBinding
import com.example.firebasepractice.model.EmployeeData
import com.example.firebasepractice.viewmodel.MainViewModel
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.getValue
import java.util.Objects
import java.util.jar.Attributes.Name


class MainActivity : AppCompatActivity() {

    val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    val db = FirebaseDatabase.getInstance()
    val node = db.getReference("Employee")

    private var empId: String = ""
    private var name: String = ""
    private var role: String = ""
    private var programmingLanguage: String = ""

    // var simpleList  = ArrayList<String>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val viewmodel = MainViewModel()

        binding.loginButton.setOnClickListener {
            empId = binding.edEmpId.text.trim().toString()
            name = binding.edName.text.trim().toString()
            role = binding.edRole.text.trim().toString()
            programmingLanguage = binding.edLanguages.text.trim().toString()

            viewmodel.loginData(empId, name, role, programmingLanguage) { message ->
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
            }


            binding.edEmpId.setText("")
            binding.edName.setText("")
            binding.edRole.setText("")
            binding.edLanguages.setText("")

        }

        binding.readButton.setOnClickListener {

//            viewmodel.readOneTimeData() { employeeData ->
//                val empData =
//                    "Name : ${employeeData.name} \n Role : ${employeeData.role} \n Programming Language : ${employeeData.programmingLanguage}"
//                binding.tvReadData.text = empData
//            }

//            viewmodel.readRealTimeData() { employeeData ->
//                val data =
//                    "Name : ${employeeData.name} \n Role : ${employeeData.role} \n Programming Language : ${employeeData.programmingLanguage}"
//                binding.tvReadData.text = data
//            }

            viewmodel.readRealTimeDataThroughChildEvent()

        }

    }


}


