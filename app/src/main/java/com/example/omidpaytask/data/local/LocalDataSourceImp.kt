package com.example.omidpaytask.data.local

import com.example.omidpaytask.data.local.dao.FavoriteDao
import com.example.omidpaytask.domain.model.ProductsDtoItem

class LocalDataSourceImp(private val favoriteDao: FavoriteDao) : LocalDataSource {
    override suspend fun getProductByID(id: String) = favoriteDao.getProductByID(id)


    override suspend fun insertProductToFavorites(procuctEntity: ProductsDtoItem) =
        favoriteDao.insertProduct(procuctEntity)


    override suspend fun deleteProductFromFavorites(productId: Int) =
        favoriteDao.deleteProductByID(productId)


    override suspend fun getFavoriteProductIds() = favoriteDao.getFavoriteProductIds()

}