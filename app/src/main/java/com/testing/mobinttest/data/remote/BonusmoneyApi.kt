package com.testing.mobinttest.data.remote

import com.testing.mobinttest.data.remote.request.RequestBody
import com.testing.mobinttest.data.remote.response.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface BonusmoneyApi {
    @POST("getAllCompaniesIdeal")
    suspend fun getAllCardsIdeal(@Header("TOKEN") token: String = "123", @Body requestBody: RequestBody) : Response

    @POST("getAllCompaniesLong")
    suspend fun getAllCardsLong(@Header("TOKEN") token: String = "123", @Body requestBody: RequestBody) : Response

    @POST("getAllCompaniesError")
    suspend fun getAllCardsError(@Header("TOKEN") token: String = "123", @Body requestBody: RequestBody) : Response

    companion object {
        const val BASE_URL = "http://devapp.bonusmoney.pro/mobileapp/"
    }
}