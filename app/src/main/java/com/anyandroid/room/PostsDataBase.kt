package com.anyandroid.room


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Post::class], version = 1)
abstract class PostsDataBase : RoomDatabase() {

    abstract val postsDao: PostsDao

    companion object {

        @Volatile
        private var INSTANCE: PostsDataBase? = null

        fun getInstance(context: Context): PostsDataBase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        PostsDataBase::class.java,
                        "posts_database"
                    ).build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}


