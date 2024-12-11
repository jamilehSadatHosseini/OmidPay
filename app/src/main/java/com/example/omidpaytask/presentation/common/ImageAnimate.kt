package com.example.omidpaytask.presentation.common

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun AnimatedImageScreen() {
    val isAnimated = remember { mutableStateOf(false) }
    val imageOffset by animateDpAsState(if (isAnimated.value) 0.dp else 200.dp, label = "")
    val imageSize by animateFloatAsState(if (isAnimated.value) 0.2f else 1f, label = "")

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = android.R.drawable.ic_menu_camera),
            contentDescription = "Moving Image",
            modifier = Modifier
                .size(150.dp * imageSize)
                .offset(y = imageOffset)
        )

        Button(
            onClick = {
                isAnimated.value = !isAnimated.value // Toggle animation state
            },
            modifier = Modifier.align(Alignment.BottomCenter).padding(16.dp)
        ) {
            Text("Animate Image")
        }
    }
}
