package com.example.omidpaytask.domain.useCases

data class UseCases(
    val getProducts: GetProductsUseCase,
    val getProductDetail: GetProductDetailsUseCase,
    val getFavoriteProductsIDs: GetFavoriteProductsID,
    val addFavorite: AddToFavoritesUseCase,
    val deleteFavorite: DeleteFromFavoritesUseCase
)
