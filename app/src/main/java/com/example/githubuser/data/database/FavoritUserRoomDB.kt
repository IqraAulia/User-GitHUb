package com.example.githubuser.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [FavoritUser::class], version = 1)
abstract class FavoritUserRoomDB : RoomDatabase() {
    abstract fun favoritUserDao () : FavoritUserDao

    companion object {
        @Volatile
        private var INSTANCE: FavoritUserRoomDB? = null
        @JvmStatic
        fun getDatabase(context: Context): FavoritUserRoomDB {
            if (INSTANCE == null) {
                synchronized(FavoritUserRoomDB::class.java) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        FavoritUserRoomDB::class.java, "note_database")
                        .build()
                }
            }
            return INSTANCE as FavoritUserRoomDB
        }
    }
}