package com.anyandroid.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "posts_table")
data class Post( private val userId:Int, var title:String, var body:String) {
    @PrimaryKey(autoGenerate = true) private val id:Int = 0
}