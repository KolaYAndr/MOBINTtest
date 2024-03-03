package com.testing.mobinttest.di

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.room.Room
import com.testing.mobinttest.data.CompanyMediator
import com.testing.mobinttest.data.local.CompanyDatabase
import com.testing.mobinttest.data.remote.BonusmoneyApi
import com.testing.mobinttest.data.remote.response.Company
import com.testing.mobinttest.presentation.screens.CardsViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@OptIn(ExperimentalPagingApi::class)
val appModule = module {
    single<OkHttpClient> {
        OkHttpClient.Builder().apply {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            addInterceptor(loggingInterceptor)
        }.build()
    }
    single<BonusmoneyApi> {
        Retrofit.Builder()
            .baseUrl(BonusmoneyApi.BASE_URL)
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BonusmoneyApi::class.java)
    }

    single<CompanyDatabase> {
        Room.databaseBuilder(
            context = get(),
            CompanyDatabase::class.java,
            CompanyDatabase.DATABASE_NAME
        ).build()
    }

    single<CompanyMediator> {
        CompanyMediator(api = get(), db = get())
    }

    single<Pager<Int, Company>> {
        val db = get<CompanyDatabase>()
        val api = get<BonusmoneyApi>()

        Pager(
            config = PagingConfig(pageSize = 10),
            remoteMediator = CompanyMediator(db = db, api = api),
            pagingSourceFactory = { db.dao.pagingSource() }
        )
    }

    viewModel {
        CardsViewModel(get<Pager<Int, Company>>())
    }
}