package com.example.omidpaytask.data

import androidx.room.TypeConverter
import com.example.omidpaytask.domain.model.Rating
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {

    @TypeConverter
    fun fromRating(rating: Rating?): String {
        return Gson().toJson(rating)
    }

    @TypeConverter
    fun toRating(ratingString: String): Rating {
        val type = object : TypeToken<Rating>() {}.type
        return Gson().fromJson(ratingString, type)
    }
}
