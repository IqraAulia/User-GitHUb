package com.example.githubuser.data.repository


import androidx.lifecycle.LiveData
import com.example.githubuser.data.database.FavoritUser
import com.example.githubuser.data.database.FavoritUserDao
import com.example.githubuser.data.ui.setting.SettingPreferences
import kotlinx.coroutines.flow.Flow



class RepositoryUser(
    val favoritUserDao: FavoritUserDao,
    val preferences: SettingPreferences
) {


    suspend fun insert(favoritUser: FavoritUser) {
        favoritUserDao.insert(favoritUser)
    }

    suspend fun delete(favoritUser: FavoritUser) {
        favoritUserDao.delete(favoritUser)
    }

    fun getUser(username: String): LiveData<FavoritUser> {
        return favoritUserDao.getFavoriteUser(username)
    }

    fun getUsers(): LiveData<List<FavoritUser>> {
        return favoritUserDao.getFavoritedUsers()
    }

    fun getThemeSetting(): Flow<Boolean> {
        return preferences.getThemeSetting()
    }

    suspend fun saveThemeSetting(darkModeActive: Boolean) {
        preferences.saveThemeSetting(darkModeActive)
    }

    companion object {
        @Volatile
        private var instance: RepositoryUser? = null
        fun getInstance(
            favoritUserDao: FavoritUserDao,
            preferences: SettingPreferences,

            ): RepositoryUser =
            instance ?: synchronized(this) {
                instance ?: RepositoryUser(favoritUserDao, preferences)
            }.also { instance = it }
    }


}