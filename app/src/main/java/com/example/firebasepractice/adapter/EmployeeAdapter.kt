package com.example.firebasepractice.adapter

import android.R
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.firebasepractice.databinding.EmployeeDetailsItemBinding
import com.example.firebasepractice.model.EmployeeData
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions


class EmployeeAdapter(
    options: FirebaseRecyclerOptions<EmployeeData>

) : FirebaseRecyclerAdapter<EmployeeData, EmployeeAdapter.EmployeeViewHolder>(options) {

    class EmployeeViewHolder(
        private val binding: EmployeeDetailsItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(model: EmployeeData) {
            binding.tvName.text = model.name
            binding.tvRole.text = model.role
            binding.tvProgrammingLanguage.text = model.programmingLanguage
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeViewHolder {
        val binding =
            EmployeeDetailsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EmployeeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EmployeeViewHolder, position: Int, model: EmployeeData) {
        holder.bind(model)
    }
}