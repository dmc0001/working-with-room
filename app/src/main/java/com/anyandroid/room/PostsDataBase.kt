package com.anyandroid.room


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Post::class], version = 1)
abstract class PostsDataBase : RoomDatabase() {

    abstract val postsDao : PostsDao

    companion object {
        @Volatile
        private var instance: PostsDataBase? = null

        fun getInstance(context: Context): PostsDataBase {
            return instance ?: synchronized(this) {
                instance ?: Room.databaseBuilder(
                    context.applicationContext,
                    PostsDataBase::class.java,
                    "posts_database"
                ).fallbackToDestructiveMigration()
                    .build().also { instance = it }
            }
        }
    }
}



