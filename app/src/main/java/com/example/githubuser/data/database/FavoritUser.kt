package com.example.githubuser.data.database

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class FavoritUser (
    @PrimaryKey(autoGenerate = false)
    var username : String = "",

    @ColumnInfo(name = "avatarUrl")
    var avatarUrl : String? = null


):Parcelable