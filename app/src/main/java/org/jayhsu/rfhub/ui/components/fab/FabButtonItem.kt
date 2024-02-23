package org.jayhsu.rfhub.ui.components.fab

import androidx.compose.ui.graphics.vector.ImageVector

data class FabButtonItem(
    val iconRes: ImageVector,
    val label: String,
    val onClick: () -> Unit
)