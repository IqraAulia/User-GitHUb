package com.example.githubuser.data.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.isVisible
import com.example.githubuser.R
import com.example.githubuser.data.ui.favorit.FavoritActivity
import com.example.githubuser.data.response.GithubResponse
import com.example.githubuser.data.retrofit.ApiConfig
import com.example.githubuser.data.ui.setting.MainViewModel
import com.example.githubuser.data.ui.setting.SettingActivity
import com.example.githubuser.data.ui.setting.ViewModelFactory
import com.example.githubuser.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    private val mainViewModel : MainViewModel by viewModels { ViewModelFactory.getInstance(this) }
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        mainViewModel.getThemeSettings().observe(this) { isDarkModeActive: Boolean ->
            if (isDarkModeActive) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)

            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

            }
        }

        ApiConfig.getApiService()
            .getSearch("a")
            .enqueue(object : Callback<GithubResponse> {
                override fun onResponse(
                    call: Call<GithubResponse>,
                    response: Response<GithubResponse>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            val adapter = ReviewAdapter().apply {
                                submitList(it.items)
                                View.VISIBLE
                            }
                            binding.progressBar.isVisible = false
                            binding.rvReview.adapter = adapter
                        }
                    }
                }
                override fun onFailure(call: Call<GithubResponse>, t: Throwable) {
                    TODO("Not yet implemented")
                }
            })



        with(binding) {
            searchView.setupWithSearchBar(searchBar)
            searchBar.inflateMenu(R.menu.option_menu)
            searchBar.setOnMenuItemClickListener { menuItem ->
                // Handle menuItem click.
                when (menuItem.itemId) {
                    R.id.favorite -> {
                        val intent = Intent(this@MainActivity, FavoritActivity::class.java)
                        startActivity(intent)
                        true
                    }

                    R.id.setting -> {
                        val intent = Intent(this@MainActivity, SettingActivity::class.java)
                        startActivity(intent)
                        true
                    }

                    else -> false
                }
            }
            searchView.editText.setOnEditorActionListener { textView, actionId, event ->
                searchBar.text = searchView.text
                binding.progressBar.isVisible = true
                ApiConfig.getApiService()
                    .getSearch(searchView.text.toString())
                    .enqueue(object : Callback<GithubResponse> {
                        override fun onResponse(
                            call: Call<GithubResponse>,
                            response: Response<GithubResponse>
                        ) {
                            if (response.isSuccessful) {
                                response.body()?.let {
                                    val adapter = ReviewAdapter().apply {
                                        submitList(it.items)
                                        View.VISIBLE
                                    }
                                    binding.progressBar.isVisible = false
                                    binding.rvReview.adapter = adapter
                                }
                            }
                        }
                        override fun onFailure(call: Call<GithubResponse>, t: Throwable) {
                            TODO("Not yet implemented")
                        }
                    })
                searchView.hide()
                Toast.makeText(this@MainActivity, searchView.text, Toast.LENGTH_SHORT).show()
                false
            }
        }
    }
}