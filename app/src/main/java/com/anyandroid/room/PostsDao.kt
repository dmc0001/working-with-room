package com.anyandroid.room


import androidx.room.*
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
interface PostsDao {
     @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPost(post: Post):Completable
    @Query("select * from posts_table")
    fun getPosts():Single<List<Post>>
    @Delete
    fun deletePost(post: Post): Completable
    @Update
    fun updatePost(post: Post): Completable
}