package org.jayhsu.rfhub.ui.components.fab

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

sealed class FabButtonState {
    data object Collapsed : FabButtonState()
    data object Expand : FabButtonState()

    fun isExpanded() = this == Expand

    fun toggleValue() = when(isExpanded()) {
        true -> Collapsed
        false -> Expand
    }
}

@Composable
fun rememberMultiFabState() = remember {
    mutableStateOf<FabButtonState>(FabButtonState.Collapsed)
}