package org.jayhsu.rfhub.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddAlert
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ListAlt
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import org.jayhsu.rfhub.R
import org.jayhsu.rfhub.ui.AppBottomBar
import org.jayhsu.rfhub.ui.AppBottomNavType
import org.jayhsu.rfhub.ui.AppNavigation
import org.jayhsu.rfhub.ui.components.MoreAction
import org.jayhsu.rfhub.ui.components.fab.FabButtonItem
import org.jayhsu.rfhub.ui.components.fab.FabButtonMain
import org.jayhsu.rfhub.ui.components.fab.MultiFloatingActionButton


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    appBottomNavState: MutableState<AppBottomNavType>,
    appNavigation: AppNavigation,
    viewModel: HomeViewModel
) {
    val listState = rememberLazyListState()
    val viewState by viewModel.sate.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(R.string.app_name))},
                modifier = modifier,
                navigationIcon = { Icon(painter = painterResource(R.drawable.ic_launcher_foreground), contentDescription = null)},
                actions = { MoreAction(appNavigation = appNavigation) }
            )
        },
        floatingActionButton = { RfHubFab(modifier = modifier, viewModel = viewModel) },
        bottomBar = {
            AppBottomBar(
                appBottomNavState = appBottomNavState,
                appNavigation = appNavigation
            )
        },
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { innerPadding ->
        MainContent(modifier = modifier.padding(innerPadding))

        if(viewState.messages.isNotEmpty()){
            val message = remember(viewState) { viewState.messages[0] }
            val messageText = stringResource(message.messageId, *message.formatArgs)
            LaunchedEffect(message.id, messageText, snackbarHostState) {
                snackbarHostState.showSnackbar(messageText)
                viewModel.messageShown(message.id)
            }
        }
    }
}

@Composable
fun MainContent(
    modifier: Modifier = Modifier
){
    Column(modifier = modifier) {
        Text(text = stringResource(R.string.home))
        Button(
            modifier = Modifier,
            onClick = { /*TODO*/ }) {
            Text(text = "Connet Device")
        }
    }
}

@Composable
fun RfHubFab(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel
){
    MultiFloatingActionButton(
        modifier = modifier,
        items = listOf(
            FabButtonItem(
                iconRes = Icons.Filled.Home,
                label = "111111",
                onClick = {
                    // TODO
                    viewModel.showMessage(R.string.msg_action, arrayOf("11111111"))
                }
            ),
            FabButtonItem(
                iconRes = Icons.Filled.ListAlt,
                label = "",
                onClick = {
                    // TODO
                }
            ),
            FabButtonItem(
                iconRes = Icons.Filled.AddAlert,
                label = "",
                onClick = {
                    // TODO
                }
            )
        ),
        fabIcon = FabButtonMain(),
        onFabItemClicked = {fabItem ->
            //  TODO  fix  通用
        }
    )
}

@Preview(showBackground = true)
@Composable
fun MainContentPreview() {
    MainContent()
}

@Preview(showBackground = true)
@Composable
fun RfHubFabPreview() {
    RfHubFab(viewModel = viewModel(factory = HomeViewModel.provideFactory()))
}