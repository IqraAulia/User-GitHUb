package com.example.githubuser.data.ui.favorit

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.githubuser.data.database.FavoritUser
import com.example.githubuser.data.repository.RepositoryUser

class FavoritViewModel(private val repositoryUser: RepositoryUser) : ViewModel(){
    fun getUsers() : LiveData<List<FavoritUser>>{
        return repositoryUser.getUsers()
    }
}