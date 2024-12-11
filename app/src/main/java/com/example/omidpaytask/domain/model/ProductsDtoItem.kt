package com.example.omidpaytask.domain.model


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "favorite_Products")
data class ProductsDtoItem(
    @SerializedName("id")
    @PrimaryKey
    val id: Int,
    @SerializedName("Productegory")
    val Productegory: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("image")
    val image: String?,
    @SerializedName("price")
    val price: Double?,
    @SerializedName("rating")
    val rating: Rating?,
    @SerializedName("title")
    val title: String?
)