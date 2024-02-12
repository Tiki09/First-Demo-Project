package com.example.demoapithroughuserlogin.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.demoapithroughuserlogin.databinding.AlbumItemLayoutBinding
import com.example.demoapithroughuserlogin.model.Album

class AlbumAdapter(
    var albumList:Album
):RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder>() {

    class AlbumViewHolder(val binding: AlbumItemLayoutBinding):RecyclerView.ViewHolder(binding.root) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        val binding = AlbumItemLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return AlbumViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return albumList.size
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        holder.binding.apply {
            albumList[position].apply {
                tvId.text=id.toString()
                tvTitle.text=title
            }
        }
    }

    fun setData(postAlbumList: Album){
        this.albumList=postAlbumList
        notifyDataSetChanged()
    }
}