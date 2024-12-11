package com.example.omidpaytask.presentation.product

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.omidpaytask.R

@Composable
fun ProductDetailsScreen(viewModel: ProductViewModel) {
    val productDetail = viewModel.selectedProduct.collectAsState()
    val favorites = viewModel.favoriteProductIds.collectAsState()

    val isBookmarkedState = remember {
        mutableStateOf(favorites.value.contains(productDetail.value?.id ?: -1))
    }
    val iconColor by animateColorAsState(
        targetValue = if (isBookmarkedState.value) Color.Blue else Color.Gray,
        animationSpec = tween(durationMillis = 300), label = ""
    )


    LaunchedEffect(productDetail.value) {
        isBookmarkedState.value = favorites.value.contains(productDetail.value?.id ?: -1)
    }
    val scrollState = rememberScrollState() // اضافه کردن وضعیت اسکرول

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(424.dp)
                .clip(RoundedCornerShape(16.dp))
        ) {

            val painter = rememberAsyncImagePainter(
                model = productDetail.value?.image,
                placeholder = rememberAsyncImagePainter(R.drawable.default_cat_image),
                error = rememberAsyncImagePainter(R.drawable.ic_android_black_24dp)
            )

            Image(
                painter = painter,
                contentDescription = "Product Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp)
                    .clip(RoundedCornerShape(16.dp)),
                contentScale = ContentScale.Crop
            )

            IconButton(
                modifier = Modifier.align(Alignment.BottomCenter),
                onClick = {
                    isBookmarkedState.value = !isBookmarkedState.value
                    productDetail.value?.let {
                        viewModel.toggleFavorite(it, isBookmarkedState.value)
                    }
                }
            ) {
                if (isBookmarkedState.value) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_bookmark_24), // Use your drawable resource
                        contentDescription = "Bookmark Icon",
                        tint = iconColor
                    )
                } else {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_bookmark_border_24), // Use your drawable resource
                        contentDescription = "Bookmark Icon",
                        tint = iconColor
                    )
                }
            }
        }

        productDetail.value?.let { product ->
            Text(
                text = product.title ?: "نام محصول",
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "قیمت: ${product.price}",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = product.Productegory ?: "دسته بندی نامشخص",
                style = MaterialTheme.typography.bodyMedium
            )

            Text(
                text = "تعداد: ${product.rating?.count}",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "امتیاز: ${product.rating?.rate}",
                style = MaterialTheme.typography.bodyMedium
            )



            Text(
                text = product.description ?: "توضیحات موجود نیست.",
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}


