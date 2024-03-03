package com.testing.mobinttest.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.testing.mobinttest.presentation.screens.CardsScreen
import com.testing.mobinttest.presentation.screens.CardsViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun Navigation(cardsViewModel: CardsViewModel = getViewModel()) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = CARDS_SCREEN) {
        composable(CARDS_SCREEN) {
            val companies = cardsViewModel.companyPagingFlow.collectAsLazyPagingItems()
            CardsScreen(companies)
        }
    }
}

private const val CARDS_SCREEN = "cards_screen"