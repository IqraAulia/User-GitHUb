package com.example.githubuser.data.ui.setting

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.githubuser.data.ui.favorit.FavoritViewModel
import com.example.githubuser.data.helper.Injection
import com.example.githubuser.data.repository.RepositoryUser
import com.example.githubuser.data.ui.main.DetailViewModel

class ViewModelFactory private constructor(private val repositoryUser: RepositoryUser) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(repositoryUser) as T
        }else if(modelClass.isAssignableFrom(DetailViewModel::class.java)){
            return DetailViewModel(repositoryUser) as T
        }else if(modelClass.isAssignableFrom(FavoritViewModel::class.java)){
            return FavoritViewModel(repositoryUser) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null
        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideRepository(context))
            }.also { instance = it }
    }
}