package com.example.omidpaytask.domain.useCases

import com.example.omidpaytask.data.local.LocalDataSource
import javax.inject.Inject

class GetFavoriteProductsID @Inject constructor(
    private val LocalDataSource: LocalDataSource
) {
    suspend operator fun invoke() = LocalDataSource.getFavoriteProductIds()

}