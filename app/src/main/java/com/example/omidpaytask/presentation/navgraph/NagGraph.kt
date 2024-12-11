package com.example.omidpaytask.presentation.navgraph

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.omidpaytask.presentation.productDetail.ProductDetailViewModel
import com.example.omidpaytask.presentation.product.ProductDetailsScreen
import com.example.omidpaytask.presentation.product.ProductListScreen
import com.example.omidpaytask.presentation.common.ErrorScreen
import com.example.omidpaytask.presentation.product.ProductViewModel

@Composable
fun NavGraph(
    startDestination: String
) {
    val viewModel: ProductViewModel = hiltViewModel() // تعریف ViewModel خارج از کامپوزبل‌ها
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = startDestination) {

        composable(route = Route.ProductListScreen.route) {
            ProductListScreen(viewModel = viewModel, navController)
        }

        composable(route = Route.ProductDetailsScreen.route) {
                ProductDetailsScreen(viewModel= viewModel)
        }
    }
}
