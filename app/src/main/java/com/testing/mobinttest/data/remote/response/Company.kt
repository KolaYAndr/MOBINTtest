package com.testing.mobinttest.data.remote.response

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "companies")
data class Company(
    @Embedded @SerializedName("company")
    val companyX: CompanyX,
    @Embedded
    val customerMarkParameters: CustomerMarkParameters,
    @Embedded
    val mobileAppDashboard: MobileAppDashboard,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
)