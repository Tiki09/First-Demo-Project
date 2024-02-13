package com.example.demoapithroughuserlogin.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.demoapithroughuserlogin.databinding.UserDetailsItemLayoutBinding
import com.example.demoapithroughuserlogin.model.UserDetails

class UserDetailsAdapter(
    var userlist: MutableList<UserDetails>
): RecyclerView.Adapter<UserDetailsAdapter.UserViewHolder>() {

    class UserViewHolder(val binding:UserDetailsItemLayoutBinding):RecyclerView.ViewHolder(binding.root) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = UserDetailsItemLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return UserViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return userlist.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        with(holder){
            with(userlist[position]){
                binding.tvId.text=id
                binding.tvName.text=name
                binding.tvAddress.text=address
                binding.tvContact.text=contact.toString()
                binding.tvEmail.text=email
            }
        }
    }
    fun setData(user: MutableList<UserDetails>){
        this.userlist=user
        notifyDataSetChanged()
    }

}