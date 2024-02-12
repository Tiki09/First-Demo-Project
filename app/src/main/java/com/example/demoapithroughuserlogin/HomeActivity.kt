package com.example.demoapithroughuserlogin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.demoapithroughuserlogin.adapter.AlbumAdapter
import com.example.demoapithroughuserlogin.databinding.ActivityHomeBinding
import com.example.demoapithroughuserlogin.model.Album
import com.example.demoapithroughuserlogin.repository.APIRepository
import com.example.demoapithroughuserlogin.viewmodel.AlbumViewModel
import com.example.demoapithroughuserlogin.viewmodel.ViewModelFactory

class HomeActivity : AppCompatActivity() {
    private val binding: ActivityHomeBinding by lazy {
        ActivityHomeBinding.inflate(layoutInflater)
    }


    private lateinit var albumAdapter: AlbumAdapter
    private lateinit var albumViewModel: AlbumViewModel
    private var albumList= Album()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initRecyclerView()
        val repo = APIRepository()
        val viewModelFactory = ViewModelFactory(repo)
        albumViewModel = ViewModelProvider(this, viewModelFactory)[AlbumViewModel::class.java]
        albumViewModel.fetchAlbumData()
        albumViewModel.albumMutableLiveData.observe(
            this, Observer {
                albumAdapter.setData(it)
                binding.rcvVwAlbum.visibility = View.VISIBLE
            }
        )
    }

    private fun initRecyclerView() {
        albumAdapter = AlbumAdapter(albumList)
        binding.rcvVwAlbum.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@HomeActivity)
            adapter = albumAdapter
        }
    }
}