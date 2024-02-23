package org.jayhsu.rfhub.ui

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import org.jayhsu.rfhub.ui.about.AboutScreen
import org.jayhsu.rfhub.ui.home.HomeScreen
import org.jayhsu.rfhub.ui.home.HomeViewModel
import org.jayhsu.rfhub.ui.settings.SettingsScreen

object AppDestinations {
    const val HOME = "home"
    const val DEVICE = "device"
    const val HUB = "hub"
    const val SETTINGS = "settings"
    const val ABOUT = "about"
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AppNavGraph (
    navController: NavHostController,
    appNavigation: AppNavigation
) {
    val appBottomNavState = remember { mutableStateOf(AppBottomNavType.HOME) }
    NavHost(
        navController = navController,
        startDestination = AppDestinations.HOME,
        modifier = Modifier,
        enterTransition = {
            fadeIn(animationSpec = tween(750))
        },
        exitTransition = {
            fadeOut(animationSpec = tween(750))
        },
        popEnterTransition = {
            fadeIn(animationSpec = tween(750))
        },
        popExitTransition = {
            fadeOut(animationSpec = tween(750))
        }
    ){
        composable(AppDestinations.HOME) {
            val homeViewModel: HomeViewModel = viewModel(
                factory = HomeViewModel.provideFactory()
            )
            HomeScreen(
                appBottomNavState = appBottomNavState,
                appNavigation = appNavigation,
                viewModel = homeViewModel
            )
        }
        composable(AppDestinations.HUB){
            SettingsScreen(
                appNavigation = appNavigation
            )
        }
        composable(AppDestinations.SETTINGS){
            SettingsScreen(
                appNavigation = appNavigation
            )
        }
        composable(AppDestinations.ABOUT){
            AboutScreen(
                appNavigation = appNavigation
            )
        }
    }
}