package com.example.omidpaytask.presentation.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.omidpaytask.presentation.Dimens

@Composable
fun ProgressIndiProductor() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(Dimens.MediumPadding1)
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .align(Alignment.Center)
                .size(Dimens.ProgressSize)
        )
    }
}