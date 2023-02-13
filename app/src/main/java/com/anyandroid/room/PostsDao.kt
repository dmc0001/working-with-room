package com.anyandroid.room

import android.database.Observable
import androidx.room.Dao
import androidx.room.Query
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
interface PostsDao {
    fun insertPost(post: Post):Completable
    @Query("SELECT * FROM posts_table")
    fun getPosts():Single<List<Post>>
}