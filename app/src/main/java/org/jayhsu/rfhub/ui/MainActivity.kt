package org.jayhsu.rfhub.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import org.jayhsu.rfhub.ui.theme.RfHubTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RfHub()
        }
    }
}

@Composable
fun RfHub(){
    RfHubTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            val navController = rememberNavController()
            val appNavigation = remember(navController){ AppNavigation(navController) }
            AppNavGraph(
                navController = navController,
                appNavigation = appNavigation
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RfHubPreview() {
    RfHub()
}