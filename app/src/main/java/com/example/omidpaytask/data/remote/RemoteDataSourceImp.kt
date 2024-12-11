package com.example.omidpaytask.data.remote

import com.example.omidpaytask.domain.utils.ResultResponse
import com.example.omidpaytask.data.remote.api.ApiService
import com.example.omidpaytask.domain.model.ProductsDtoItem
import javax.inject.Inject

class RemoteDataSourceImp @Inject constructor(private val requestApi: ApiService): RemoteDataSource {

    override suspend fun getProducts(): ResultResponse<List<ProductsDtoItem>> {
        return try {
            val response = requestApi.getProducts()
            ResultResponse.Success(response)
        } catch (e: Exception) {
            ResultResponse.Failure(e)
        }
    }

    override suspend fun getProductDetailByProductID(id:Int): ResultResponse<ProductsDtoItem> {
        return try {
            val response = requestApi.getProductDetail(id)
            ResultResponse.Success(response)
        } catch (e: Exception) {
            ResultResponse.Failure(e)
        }    }
}


