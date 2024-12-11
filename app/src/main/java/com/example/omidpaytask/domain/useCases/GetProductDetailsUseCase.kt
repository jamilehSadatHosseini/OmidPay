package com.example.omidpaytask.domain.useCases

import com.example.omidpaytask.domain.Repository
import javax.inject.Inject

class GetProductDetailsUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke(id:Int)=repository.getProductsDetailByProductID(id)
}