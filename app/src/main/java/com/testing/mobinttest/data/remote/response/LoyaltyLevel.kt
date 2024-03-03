package com.testing.mobinttest.data.remote.response

data class LoyaltyLevel(
    val cashToMark: Int,
    val markToCash: Int,
    val name: String,
    val number: Int,
    val requiredSum: Int
)