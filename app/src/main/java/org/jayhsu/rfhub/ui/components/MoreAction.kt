package org.jayhsu.rfhub.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import org.jayhsu.rfhub.R
import org.jayhsu.rfhub.ui.AppNavigation

@Composable
fun MoreAction(
    appNavigation: AppNavigation
) {
    MoreActionsButton {
        DropdownMenuItem(
            text = { Text(text = stringResource(R.string.settings)) },
            onClick = { appNavigation.navigateToSettings() },
            leadingIcon = { Icon(imageVector = Icons.Filled.Settings, contentDescription = null) }
        )
        HorizontalDivider()
        DropdownMenuItem(
            text = { Text(text = stringResource(R.string.about)) },
            onClick = { appNavigation.navigateToAbout() },
            leadingIcon = { Icon(imageVector = Icons.Filled.Info, contentDescription = null) }
        )
    }
}