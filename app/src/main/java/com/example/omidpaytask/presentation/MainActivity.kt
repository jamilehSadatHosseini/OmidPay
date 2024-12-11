package com.example.omidpaytask.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.omidpaytask.presentation.navgraph.NavGraph
import com.example.omidpaytask.presentation.navgraph.Route
import com.example.omidpaytask.ui.theme.ProductApplicationTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProductApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize() ,
                    color = MaterialTheme.colorScheme.background)
                {
                    NavGraph(startDestination = Route.ProductListScreen.route)
                }
            }
        }
    }
}