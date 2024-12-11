package com.example.omidpaytask.domain.useCases

import com.example.omidpaytask.domain.Repository
import javax.inject.Inject

class GetProductsUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke() = repository.getProducts()
}