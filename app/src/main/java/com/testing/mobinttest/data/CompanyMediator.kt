package com.testing.mobinttest.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.testing.mobinttest.data.local.CompanyDatabase
import com.testing.mobinttest.data.remote.BonusmoneyApi
import com.testing.mobinttest.data.remote.request.RequestBody
import com.testing.mobinttest.data.remote.response.Company
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class CompanyMediator(private val api: BonusmoneyApi, private val db: CompanyDatabase) :
    RemoteMediator<Int, Company>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, Company>
    ): MediatorResult {
        return try {
            val offset = when (loadType) {
                LoadType.REFRESH -> 0
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                    lastItem?.id ?: 0
                }
            }
            val response = api.getAllCardsIdeal(requestBody = RequestBody(offset = offset))

            db.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    db.dao.deleteAll()
                }
                db.dao.insertAll(response.companies)
            }

            MediatorResult.Success(
                endOfPaginationReached = false
            )
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }
}