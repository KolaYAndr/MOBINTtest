package com.testing.mobinttest.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.testing.mobinttest.data.remote.response.Company

@Dao
interface CompanyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(companies: List<Company>)

    @Query("SELECT * FROM companies")
    fun pagingSource() : PagingSource<Int, Company>

    @Query("DELETE FROM companies")
    suspend fun deleteAll()
}