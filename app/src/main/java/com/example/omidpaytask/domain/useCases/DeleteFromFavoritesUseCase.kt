package com.example.omidpaytask.domain.useCases

import com.example.omidpaytask.domain.Repository
import javax.inject.Inject

class DeleteFromFavoritesUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke(productId: Int) {
        repository.deleteProductFromFavorite(productId)
    }
}
