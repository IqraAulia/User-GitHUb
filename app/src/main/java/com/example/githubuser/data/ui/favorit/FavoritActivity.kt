package com.example.githubuser.data.ui.favorit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.githubuser.data.ui.setting.ViewModelFactory
import com.example.githubuser.databinding.ActivityFavoritBinding

class FavoritActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFavoritBinding
    private val viewModel: FavoritViewModel by viewModels { ViewModelFactory.getInstance(this) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoritBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.getUsers().observe(this) {
            binding.rvFavorit.adapter = FavoritAdapter(it)
        }
    }
}