package com.example.omidpaytask.data.remote

import com.example.omidpaytask.domain.utils.ResultResponse
import com.example.omidpaytask.domain.model.ProductsDtoItem

interface RemoteDataSource {
    suspend fun getProducts(): ResultResponse<List<ProductsDtoItem>>
    suspend fun getProductDetailByProductID(id:Int): ResultResponse<ProductsDtoItem>

}
