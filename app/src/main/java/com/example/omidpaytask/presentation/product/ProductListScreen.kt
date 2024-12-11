package com.example.omidpaytask.presentation.product

import ProductItem
import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.omidpaytask.domain.model.ProductsDtoItem
import com.example.omidpaytask.presentation.Dimens
import com.example.omidpaytask.presentation.Dimens.ExtraSmallPadding2
import com.example.omidpaytask.presentation.common.ErrorScreen
import com.example.omidpaytask.presentation.common.ProgressIndiProductor
import com.example.omidpaytask.presentation.common.UIState
import com.example.omidpaytask.presentation.navgraph.Route

@Composable
fun ProductListScreen(viewModel: ProductViewModel, navController: NavController) {
    val uiState = viewModel.uiState.collectAsState()
    val favorites = viewModel.favoriteProductIds.collectAsState()
    val searchQuery = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) {
        OutlinedTextField(
            value = searchQuery.value,
            onValueChange = { query ->
                searchQuery.value = query
                viewModel.onSearchQueryChanged(query)
            },
            label = { Text(text = "Search Products") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )

        when (val state = uiState.value) {
            is UIState.Loading -> {
                ProgressIndiProductor()
            }
            is UIState.Failure -> {
                ErrorScreen(error = state.exception, onRetryClick = { viewModel.loadProducts() })
            }
            is UIState.Success -> {
                ShowData(
                    products = state.data,
                    favorites = favorites.value,
                    onFavoriteChange = { product, isFavorite ->
                        viewModel.toggleFavorite(product, isFavorite)
                    },
                    onItemClick = { product, _ ->
                        viewModel.selectProduct(product)
                        navController.navigate(Route.ProductDetailsScreen.route)
                                        }
                )
            }
        }
    }
}

@Composable
fun ShowData(
    products: List<ProductsDtoItem>,
    favorites: List<Int>,
    onFavoriteChange: (ProductsDtoItem, Boolean) -> Unit,
    onItemClick: (ProductsDtoItem, Boolean) -> Unit
) {
    val isLandScape = LocalConfiguration.current.orientation == Configuration.ORIENTATION_LANDSCAPE
    val columnCount = if (isLandScape) Dimens.LandscapeColumnCount else Dimens.ColumnCount

    LazyVerticalGrid(
        columns = GridCells.Fixed(columnCount),
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                top = 0.dp,
                bottom = 0.dp,
                end = ExtraSmallPadding2,
                start = ExtraSmallPadding2
            ),
        contentPadding = PaddingValues(
            start = 0.dp,
            end = 0.dp,
            top = 8.dp,
            bottom = 8.dp
        ),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(products.size) { index ->
            val product = products[index]
            val isFavorite = favorites.contains(product.id)
            ProductItem(
                product = product,
                isBookmarked = isFavorite,
                onBookmarkChange = onFavoriteChange,
                onItemClick = onItemClick
            )
        }
    }
}
