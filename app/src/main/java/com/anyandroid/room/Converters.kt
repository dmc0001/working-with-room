package com.anyandroid.room

import androidx.room.TypeConverter
import com.google.gson.Gson

class Converters {
    @TypeConverter
    fun fromUserToString(user:User):String {
        // convert user to json
        return Gson().toJson(user)
    }
    @TypeConverter
    fun fromStringToUser(gson:String):User {
        // convert json to user
        return Gson().fromJson(gson, User::class.java)
    }
}