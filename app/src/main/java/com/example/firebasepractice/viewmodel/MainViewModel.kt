package com.example.firebasepractice.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.firebasepractice.model.EmployeeData
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.getValue

class MainViewModel : ViewModel() {
    val db = FirebaseDatabase.getInstance()
    val node = db.getReference("Employee")

    var hashMap = HashMap<String, String>()


    private lateinit var employeeData: EmployeeData

    fun loginData(
        empId: String,
        name: String,
        role: String,
        programmingLang: String,
        callBack: (String) -> Unit
    ) {
        employeeData = EmployeeData(name, role, programmingLang)
        node.child(empId).setValue(employeeData).addOnCompleteListener {
            callBack("Data Inserted")
        }.addOnFailureListener {
            callBack("Couldn't able to Insert")
        }

    }

    fun readRealTimeData(
        callBack: (EmployeeData) -> Unit
    ) {
        node.child("1").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val name = snapshot.child("name").getValue(String::class.java).toString()
                val role = snapshot.child("role").getValue(String::class.java).toString()
                val programmingLanguage =
                    snapshot.child("programmingLanguage").getValue(String::class.java).toString()

                employeeData = EmployeeData(name, role, programmingLanguage)

                callBack(employeeData)
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

    fun readOneTimeData(
        callBack: (EmployeeData) -> Unit
    ) {
        node.child("11").addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                var name = snapshot.child("name").getValue(String::class.java).toString()
                var role = snapshot.child("role").getValue(String::class.java).toString()
                var programmingLanguage =
                    snapshot.child("programmingLanguage").getValue(String::class.java).toString()

                employeeData = EmployeeData(name, role, programmingLanguage)

                callBack(employeeData)
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

    fun readRealTimeDataThroughChildEvent() {
        node.child("90").addChildEventListener(

                object : ChildEventListener {

                    var name :String=""
                    var role :String=""
                    var programmingLang :String=""
                    override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                        Log.e("snapshot","${snapshot.getValue(EmployeeData::class.java)}")

//                        hashMap.put(snapshot.key.toString(), snapshot.value.toString())
//
//                        val key = snapshot.key // Get the key of the child
//                        val employee = snapshot.getValue<String>() // Get the value of the child
////                    Log.d("aaa", "onChildAdded: key=$key, employee=$employee")
//
//
//                        name = snapshot.child(key!!).getValue(String::class.java).toString()
//                        if (name != null) {
//                            Log.d("TAG", "Name: $name")
//                            // Display the name in your app (e.g., update a TextView)
//                        } else {
//                            Log.d("TAG", "Name not found or is null")
//                        }
////                    name = snapshot.child("name").getValue<String>().toString()

//                    for (i in 0 until hashMap.size){
//                        Log.e("aaa", "onChildAdded: ${hashMap.get("name")!![i]}", )
//                        name = hashMap.get("name")
//                        role = snapshot.child("role").getValue(String::class.java).toString()
//                        programmingLanguage = snapshot.child("programmingLanguage").getValue(String::class.java).toString()
//                    }


//                    val employeeString = "Name: ${snapshot.child("name")}\nRole: ${snapshot.child("role")}\nPosition: ${snapshot.child("programmingLanguage")}\n"
//                    binding.tvReadData.append(employeeString)


                        // Update the TextView with the fetched data
//                    val userInfo = "Role: $role\nName: $name\nprogrammingLanguage: $programmingLanguage"
//                    binding.tvReadData.text = userInfo
//                    //Log.e("snapshot", "$value")
//
//                    val employee = snapshot.getValue(EmployeeData::class.java)
//                    if(employee != null){
//                        val data = binding.tvReadData.text.toString()
//                        val newData = "$data\nName: ${employee.name}\nRole: ${employee.role}\nProgramming Language: ${employee.programmingLanguage}"
//                        binding.tvReadData.text = newData
//                    }


//                    value = snapshot.value
//                    hashMap = value.toString() as HashMap<String, String>
//                    name = hashMap.get("name").toString()
//                    role = hashMap.get("role").toString()
//                    programmingLanguage = hashMap.get("programmingLanguage").toString()


//                    name = snapshot.child("name").getValue(String::class.java).toString()
//                    role = snapshot.child("role").getValue(String::class.java).toString()
//                    programmingLanguage = snapshot.child("programmingLanguage").getValue(String::class.java).toString()


                    }

                    override fun onChildChanged(
                        snapshot: DataSnapshot, previousChildName: String?
                    ) {
                        role = snapshot.child("role").getValue(String::class.java).toString()
                        name = snapshot.child("name").getValue(String::class.java).toString()
                        programmingLang =
                            snapshot.child("programmingLanguage").getValue(String::class.java).toString()


                        val displayText =
                            "Name: $name\nRole: $role\nprogrammingLanguage: $programmingLang"

                        println(displayText)


                    }

                    override fun onChildRemoved(snapshot: DataSnapshot) {
                        TODO("Not yet implemented")
                    }

                    override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
                        TODO("Not yet implemented")
                    }

                    override fun onCancelled(error: DatabaseError) {
                        TODO("Not yet implemented")
                    }

                })


    }
}