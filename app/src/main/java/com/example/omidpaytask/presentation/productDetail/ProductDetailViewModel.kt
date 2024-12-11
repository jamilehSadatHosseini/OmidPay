package com.example.omidpaytask.presentation.productDetail


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.omidpaytask.domain.model.ProductsDtoItem
import com.example.omidpaytask.domain.useCases.UseCases
import com.example.omidpaytask.presentation.common.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(
    private val useCases: UseCases
) : ViewModel() {

    private val _uiState = MutableStateFlow<UIState<ProductsDtoItem>>(UIState.Loading)
    val uiState: StateFlow<UIState<ProductsDtoItem>> = _uiState

    fun getProductDetails(ProductId: Int) {
//        viewModelScope.launch {
//            _state.value = ProductDetailState.Loading
//            try {
//                val detail = useCases.getProductDetail(ProductId)
//                if(detail==null)
//                {
//                    _state.value = ProductDetailState.Empty
//                } else {
//                    _state.value = ProductDetailState.Success(detail)
//                }
//            } Productch (e: Exception) {
//                _state.value = ProductDetailState.Error(e.localizedMessage ?: "Unknown Error")
//            }
        }


    fun toggleFavorite(ProductEntity: ProductsDtoItem, isFavorite: Boolean) {

//        viewModelScope.launch {
//            if (isFavorite) {
//                useCases.addFavorite(ProductEntity)
//            } else {
//                useCases.deleteFavorite(ProductEntity.id)
//            }
//        }
    }
}
