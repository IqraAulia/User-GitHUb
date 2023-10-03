package com.example.githubuser.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FavoritUserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(favoritUser: FavoritUser)

    @Delete
    suspend fun delete(favoriteUser: FavoritUser)

    @Query("SELECT * FROM favorituser")
    fun getFavoritedUsers(): LiveData<List<FavoritUser>>

    @Query("SELECT * FROM favorituser WHERE username = :username")
    fun getFavoriteUser(username: String): LiveData<FavoritUser>


}