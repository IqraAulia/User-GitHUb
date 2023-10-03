package com.example.githubuser.data.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubuser.data.database.FavoritUser
import com.example.githubuser.data.repository.RepositoryUser
import kotlinx.coroutines.launch

class DetailViewModel(private val repositoryUser: RepositoryUser):ViewModel() {
    fun insertUser(favoritUser: FavoritUser){
        viewModelScope.launch {
            repositoryUser.insert(favoritUser)
        }
    }
    fun deleteUser(favoritUser: FavoritUser){
        viewModelScope.launch {
            repositoryUser.delete(favoritUser)
        }
    }
    fun getFavoritUser(username : String) : LiveData<FavoritUser>{
        return repositoryUser.getUser(username)
    }
}