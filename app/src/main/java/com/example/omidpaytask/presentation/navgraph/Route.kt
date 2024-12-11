package com.example.omidpaytask.presentation.navgraph


sealed class Route(val route: String)
{
    object ProductListScreen: Route("ProductListScreen")
    object ProductDetailsScreen : Route("ProductDetailsScreen")
}
