package com.testing.mobinttest.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.testing.mobinttest.data.local.converters.LoyaltyLevelConverters
import com.testing.mobinttest.data.local.dao.CompanyDao
import com.testing.mobinttest.data.remote.response.Company

@Database(entities = [Company::class], version = 1)
@TypeConverters(LoyaltyLevelConverters::class)
abstract class CompanyDatabase : RoomDatabase() {
    abstract val dao: CompanyDao

    companion object {
        const val DATABASE_NAME = "CompanyDatabase"
    }
}