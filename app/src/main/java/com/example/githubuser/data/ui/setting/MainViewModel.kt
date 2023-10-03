package com.example.githubuser.data.ui.setting


import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.githubuser.data.repository.RepositoryUser
import kotlinx.coroutines.launch

class MainViewModel(private val repositoryUser: RepositoryUser) : ViewModel() {
    fun getThemeSettings(): LiveData<Boolean> {
        return repositoryUser.getThemeSetting().asLiveData()
    }

    fun saveThemeSetting(isDarkModeActive: Boolean) {
        viewModelScope.launch {
            repositoryUser.saveThemeSetting(isDarkModeActive)
        }
    }


}