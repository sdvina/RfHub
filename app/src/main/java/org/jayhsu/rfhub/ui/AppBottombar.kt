package org.jayhsu.rfhub.ui

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Devices
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ViewCozy
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import org.jayhsu.rfhub.R
import org.jayhsu.rfhub.ui.theme.RfHubTheme

enum class AppBottomNavType {
    DEVICE,
    HOME,
    HUB,
}

@Composable
 fun AppBottomBar(
    modifier: Modifier = Modifier,
    appBottomNavState: MutableState<AppBottomNavType>,
    appNavigation: AppNavigation
 ) {
    BottomAppBar(
        actions = {
            NavigationBar(
                modifier = modifier
            ) {
                NavigationBarItem(
                    selected = appBottomNavState.value == AppBottomNavType.DEVICE,
                    onClick = {
                        appBottomNavState.value = AppBottomNavType.DEVICE
                        appNavigation.navigateToHome()
                    },
                    icon = { Icon(imageVector = Icons.Filled.Devices, contentDescription = null) },
                    label = { Text(text = stringResource(R.string.device)) }
                )
                NavigationBarItem(
                    selected = appBottomNavState.value == AppBottomNavType.HOME,
                    onClick = {
                        appBottomNavState.value = AppBottomNavType.HOME
                        appNavigation.navigateToHome()
                    },
                    icon = { Icon(imageVector = Icons.Filled.Home, contentDescription = null) },
                    label = { Text(text = stringResource(R.string.home)) }
                )
                NavigationBarItem(
                    selected = appBottomNavState.value == AppBottomNavType.HUB,
                    onClick = {
                        appBottomNavState.value = AppBottomNavType.HUB
                        appNavigation.navigateToHub
                    },
                    icon = { Icon(imageVector = Icons.Filled.ViewCozy, contentDescription = null) },
                    label = { Text(text = stringResource(R.string.hub)) }
                )
            }
        },
        modifier = modifier
    )
}

@Preview
@Composable
fun AppBottomBarPreview(){
    val navController = rememberNavController()
    val appNavigation = remember(navController){ AppNavigation(navController) }
    RfHubTheme {
        AppBottomBar(
            appBottomNavState = remember { mutableStateOf(AppBottomNavType.HOME) },
            appNavigation = appNavigation
        )
    }
}