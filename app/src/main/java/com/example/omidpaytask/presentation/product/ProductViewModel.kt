package com.example.omidpaytask.presentation.product


import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import com.example.omidpaytask.domain.model.ProductsDto
import com.example.omidpaytask.domain.model.ProductsDtoItem
import com.example.omidpaytask.domain.useCases.UseCases
import com.example.omidpaytask.domain.utils.ResultResponse
import com.example.omidpaytask.presentation.common.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(private val useCases: UseCases) : ViewModel() {

    private val _uiState = MutableStateFlow<UIState<List<ProductsDtoItem>>>(UIState.Loading)
    val uiState: StateFlow<UIState<List<ProductsDtoItem>>> = _uiState

    private val _favoriteProductIds = MutableStateFlow<List<Int>>(emptyList())
    val favoriteProductIds: StateFlow<List<Int>> get() = _favoriteProductIds
    private val _selectedProduct = MutableStateFlow<ProductsDtoItem?>(null)
    val selectedProduct: StateFlow<ProductsDtoItem?> = _selectedProduct
    private var allProducts: List<ProductsDtoItem> = emptyList()

    init {
        loadFavoriteProductIds()
        loadProducts()
    }

     fun loadProducts() {
        viewModelScope.launch {
            _uiState.value = UIState.Loading
            try {
                when (val result = useCases.getProducts()) {
                    is ResultResponse.Success -> {
                        allProducts = result.data.sortedBy { it.price }
                        _uiState.value = UIState.Success(allProducts)
                    }

                    is ResultResponse.Failure -> _uiState.value = UIState.Failure(result.exception)
                }
            } catch (e: Exception) {
                _uiState.value = UIState.Failure(e)
            }
        }
    }

    private fun loadFavoriteProductIds() {
        viewModelScope.launch {
            try {
                useCases.getFavoriteProductsIDs().asFlow().collect {
                    _favoriteProductIds.value = it
                }

            } catch (e: Exception) {
                _favoriteProductIds.value = emptyList()
            }
        }
    }

    fun toggleFavorite(product: ProductsDtoItem, isFavorite: Boolean) {
        viewModelScope.launch {
            if (isFavorite) {
                useCases.addFavorite(product)
            } else {
                useCases.deleteFavorite(product.id)
            }
        }
    }

    fun onSearchQueryChanged(query: String) {
        _uiState.value = if (query.isEmpty()) {
            UIState.Success(allProducts)
        } else {
            UIState.Success(allProducts.filter { it.title?.contains(query, ignoreCase = true) == true })
        }
    }
    fun selectProduct(product: ProductsDtoItem) {
        _selectedProduct.value = product
    }

}