package com.example.omidpaytask.data

import com.example.omidpaytask.data.local.LocalDataSource
import com.example.omidpaytask.data.remote.RemoteDataSource
import com.example.omidpaytask.domain.Repository
import com.example.omidpaytask.domain.model.ProductsDtoItem

class RepositoryImp(private val localDataSource: LocalDataSource,private val remoteDataSource: RemoteDataSource):Repository {
    override suspend fun getProducts() = remoteDataSource.getProducts()
    override suspend fun getProductsDetailByProductID(id: Int)=remoteDataSource.getProductDetailByProductID(id)

    override suspend fun getFavoriteProducts()=localDataSource.getFavoriteProductIds()

    override suspend fun addProductToFavorite(product: ProductsDtoItem) = localDataSource.insertProductToFavorites(productEntity = product)

    override suspend fun deleteProductFromFavorite(id: Int) = localDataSource.deleteProductFromFavorites(id)


}