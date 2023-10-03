package com.example.githubuser.data.helper

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.example.githubuser.data.database.FavoritUserRoomDB
import com.example.githubuser.data.repository.RepositoryUser
import com.example.githubuser.data.ui.setting.SettingPreferences

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

object Injection {
    fun provideRepository(context: Context): RepositoryUser{
        val database = FavoritUserRoomDB.getDatabase(context)
        val dao = database.favoritUserDao()
        val pref = SettingPreferences.getInstance(context.dataStore)
        return RepositoryUser(dao,pref)
    }
}