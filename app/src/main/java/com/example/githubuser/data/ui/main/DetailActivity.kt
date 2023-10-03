package com.example.githubuser.data.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.example.githubuser.R
import com.example.githubuser.data.database.FavoritUser
import com.example.githubuser.data.response.DetailuserResponse
import com.example.githubuser.data.retrofit.ApiConfig
import com.example.githubuser.data.ui.setting.ViewModelFactory
import com.example.githubuser.databinding.ActivityDetailBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailActivity : AppCompatActivity() {

    companion object {
        @StringRes
        val TAB_TITLES = intArrayOf(
            R.string.tab_text_1,
            R.string.tab_text_2

        )
    }

    private lateinit var binding: ActivityDetailBinding
    private val viewModel: DetailViewModel by viewModels { ViewModelFactory.getInstance(this) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val username = intent.getStringExtra("key_login")

        username?.let {

            val sectionsPagerAdapter = SectionsPagerAdapter(this, username)
            val viewPager: ViewPager2 = findViewById(R.id.view_pager)
            viewPager.adapter = sectionsPagerAdapter
            val tabs: TabLayout = findViewById(R.id.tabs)
            TabLayoutMediator(tabs, viewPager) { tab, position ->
                tab.text = resources.getString(TAB_TITLES[position])
            }.attach()
            supportActionBar?.elevation = 0f

            binding.progressBar.isVisible = true
            ApiConfig
                .getApiService()
                .getDetailUser(it)
                .enqueue(object : Callback<DetailuserResponse> {
                    override fun onResponse(
                        call: Call<DetailuserResponse>,
                        response: Response<DetailuserResponse>
                    ) {
                        if (response.isSuccessful) {
                            response.body()?.let { detailUser ->
                                binding.tvDetailName.text = detailUser.name
                                Glide.with(this@DetailActivity)
                                    .load(detailUser.avatarUrl)
                                    .into(binding.imgDetailPhoto)
                                binding.folower.text = "${detailUser.followers} Followers"
                                binding.folowing.text = "${detailUser.following} Follwing"
                                binding.tvDetailId.text = "${detailUser.login}"
                                viewModel.getFavoritUser(detailUser.login as String).observe(this@DetailActivity){ favoritUser ->
                                    if(favoritUser == null){
                                        binding.btnfavorite.setImageDrawable(
                                            ContextCompat.getDrawable(
                                                this@DetailActivity, R.drawable.baseline_favorite_border_24
                                            )
                                        )
                                        binding.btnfavorite.setOnClickListener {
                                            val user = FavoritUser(detailUser.login as String, detailUser.avatarUrl)
                                            viewModel.insertUser(user)
                                        }
                                    }else{
                                        binding.btnfavorite.setImageDrawable(
                                            ContextCompat.getDrawable(
                                                this@DetailActivity, R.drawable.outline_favorite
                                            )
                                        )
                                        binding.btnfavorite.setOnClickListener {
                                            viewModel.deleteUser(favoritUser)
                                        }
                                    }
                                }
                            }
                            binding.progressBar.isVisible = false
                        }
                    }

                    override fun onFailure(call: Call<DetailuserResponse>, t: Throwable) {
                        TODO("Not yet implemented")
                    }
                })
        }
    }
}