package com.example.githubuser.data.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.example.githubuser.data.response.ListFollowResponseItem
import com.example.githubuser.data.retrofit.ApiConfig
import com.example.githubuser.databinding.FragmentFollowingBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class following : Fragment() {
    private var _binding: FragmentFollowingBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFollowingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val tab = arguments?.getInt("tab")
        val username = arguments?.getString("username")
        binding.rvReview.adapter = FollowAdapter()

        username?.let {
            val client =
                when (tab) {
                    0 -> ApiConfig.getApiService().getListFollower(username)
                    else -> ApiConfig.getApiService().getListFollowing(username)
                }
            binding.progressBar.isVisible = true
            client.enqueue(object : Callback<List<ListFollowResponseItem>> {
                override fun onResponse(
                    call: Call<List<ListFollowResponseItem>>,
                    response: Response<List<ListFollowResponseItem>>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            val adapter = FollowAdapter().apply {
                                submitList(it)
                            }
                            binding.progressBar.isVisible = false
                            binding.rvReview.adapter = adapter
                        }
                    }
                }

                override fun onFailure(call: Call<List<ListFollowResponseItem>>, t: Throwable) {
                    TODO("Not yet implemented")
                }
            })
        }
    }
}