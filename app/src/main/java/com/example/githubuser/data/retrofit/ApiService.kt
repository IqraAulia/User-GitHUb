package com.example.githubuser.data.retrofit

import com.example.githubuser.data.response.DetailuserResponse
import com.example.githubuser.data.response.GithubResponse

import com.example.githubuser.data.response.ListFollowResponseItem
import retrofit2.Call
import retrofit2.http.*



interface ApiService {
    @GET("search/users")
    fun getSearch(
        @Query("q") username: String
    ): Call<GithubResponse>
    @GET("users/{username}")
    fun getDetailUser(
        @Path("username") username : String
    ): Call<DetailuserResponse>
    @GET("users/{username}/followers")
    fun getListFollower(
        @Path("username") username: String
    ):Call<List<ListFollowResponseItem>>
    @GET("users/{username}/following")
    fun getListFollowing(
        @Path("username") username: String
    ):Call<List<ListFollowResponseItem>>

}
