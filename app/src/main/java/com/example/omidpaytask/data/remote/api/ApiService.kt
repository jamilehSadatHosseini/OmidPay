package com.example.omidpaytask.data.remote.api

import com.example.omidpaytask.domain.model.ProductsDtoItem
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("products")
    suspend fun getProducts(
    ): List<ProductsDtoItem>

    @GET("products/{id}")
    suspend fun getProductDetail(
        @Path("id") productId: Int
    ): ProductsDtoItem

}