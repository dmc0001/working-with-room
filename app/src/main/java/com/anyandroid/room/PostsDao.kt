package com.anyandroid.room


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single

@Dao
interface PostsDao {
    @Insert
    fun insertPost(post: Post):Completable
    @Query("select * from posts_table")
    fun getPosts():Single<List<Post>>
}