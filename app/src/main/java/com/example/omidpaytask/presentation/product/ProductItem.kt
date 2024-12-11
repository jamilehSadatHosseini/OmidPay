import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.omidpaytask.R
import com.example.omidpaytask.domain.model.ProductsDtoItem

@Composable
fun ProductItem(
    product: ProductsDtoItem,
    isBookmarked: Boolean,
    onBookmarkChange: (ProductsDtoItem, Boolean) -> Unit,
    onItemClick: (ProductsDtoItem, Boolean) -> Unit
) {
    val isBookmarkedState = remember { mutableStateOf(isBookmarked) }
    val iconColor by animateColorAsState(
        targetValue = if (isBookmarkedState.value) Color.Blue else Color.Gray,
        animationSpec = tween(durationMillis = 300), label = ""
    )

    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onItemClick(product, isBookmarked) }
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            Image(
                painter = rememberAsyncImagePainter(
                    model = product.image,
                    placeholder = rememberAsyncImagePainter(R.drawable.ic_android_black_24dp),
                    error = rememberAsyncImagePainter(R.drawable.ic_android_black_24dp)
                ),
                contentDescription = "Product Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )

            IconButton(
                onClick = {
                    isBookmarkedState.value = !isBookmarkedState.value
                    onBookmarkChange(product, isBookmarkedState.value)
                },
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(4.dp)
            ) {
                if (isBookmarkedState.value) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_bookmark_24),
                        contentDescription = "Bookmark Icon",
                        tint = iconColor
                    )
                } else {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_bookmark_border_24),
                        contentDescription = "Bookmark Icon",
                        tint = iconColor
                    )
                }
            }
        }

        Text(
            text = product.title ?: "No Name",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(horizontal = 8.dp, vertical = 4.dp)
        )
        Text(
            text = "Price: $${product.price}",
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal,
            color = Color.Gray,
            modifier = Modifier
                .padding(horizontal = 8.dp, vertical = 4.dp)
        )
    }
}
