package com.example.omidpaytask.domain

import androidx.lifecycle.LiveData
import com.example.omidpaytask.domain.utils.ResultResponse
import com.example.omidpaytask.domain.model.ProductsDtoItem

interface Repository {
    suspend fun getProducts(): ResultResponse<List<ProductsDtoItem>>
   suspend fun getProductsDetailByProductID(id:Int): ResultResponse<ProductsDtoItem>

   suspend fun getFavoriteProducts():LiveData<List<Int>>

    suspend fun addProductToFavorite(product:ProductsDtoItem)

   suspend fun deleteProductFromFavorite(id: Int)
}