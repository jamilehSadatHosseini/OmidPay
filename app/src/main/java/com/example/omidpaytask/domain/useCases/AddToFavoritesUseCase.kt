package com.example.omidpaytask.domain.useCases

import com.example.omidpaytask.domain.Repository
import com.example.omidpaytask.domain.model.ProductsDtoItem
import javax.inject.Inject

class AddToFavoritesUseCase @Inject constructor(
   private val repository: Repository
) {
    suspend operator fun invoke(productEntity: ProductsDtoItem
    ) {
        repository.addProductToFavorite(productEntity)
    }
}