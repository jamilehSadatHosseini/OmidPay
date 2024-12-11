package com.example.omidpaytask.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.omidpaytask.data.Converters
import com.example.omidpaytask.data.local.dao.FavoriteDao
import com.example.omidpaytask.domain.model.ProductsDtoItem


@Database(entities =[ProductsDtoItem::class], version = 1)
@TypeConverters(Converters::class)


abstract class ProductDataBase: RoomDatabase() {
abstract val favoriteDao: FavoriteDao
}