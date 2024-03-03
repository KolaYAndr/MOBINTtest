package com.testing.mobinttest.data.local.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.testing.mobinttest.data.remote.response.LoyaltyLevel

class LoyaltyLevelConverters {
    @TypeConverter
    fun fromJson(json: String): LoyaltyLevel {
        val type = object : TypeToken<LoyaltyLevel>() {}.type
        return Gson().fromJson(json, type)
    }

    @TypeConverter
    fun toJson(loyaltyLevel: LoyaltyLevel): String {
        return Gson().toJson(loyaltyLevel)
    }
}