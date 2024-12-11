package com.example.omidpaytask.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import com.example.omidpaytask.domain.model.ProductsDtoItem

@Dao
interface FavoriteDao{
    @Upsert
    suspend fun insertAll(movies: List<ProductsDtoItem>)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduct(product: ProductsDtoItem)
    @Query("SELECT * FROM favorite_Products WHERE id = :productId")
     suspend fun getProductByID( productId:String): ProductsDtoItem?

    @Query("DELETE FROM favorite_Products WHERE id = :id")
    suspend fun deleteProductByID(id: Int)

    @Query("DELETE FROM favorite_Products")
    suspend fun clearAll()

    @Query("SELECT id FROM favorite_Products")
    fun getFavoriteProductIds(): LiveData<List<Int>>

}