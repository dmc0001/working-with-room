package com.anyandroid.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "posts_table")
data class Post(
    val user: User,
    var title: String,
    var body: String
)
{
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}

