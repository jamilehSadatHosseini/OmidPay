package com.example.omidpaytask.data.local

import androidx.lifecycle.LiveData
import com.example.omidpaytask.domain.model.ProductsDtoItem

interface LocalDataSource {
    suspend fun getProductByID(id:String):ProductsDtoItem?
    suspend fun insertProductToFavorites(productEntity: ProductsDtoItem)
    suspend fun deleteProductFromFavorites(productId: Int)
    suspend fun getFavoriteProductIds():LiveData<List<Int>>

}