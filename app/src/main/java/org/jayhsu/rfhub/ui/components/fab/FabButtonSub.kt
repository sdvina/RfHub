package org.jayhsu.rfhub.ui.components.fab

import androidx.compose.ui.graphics.Color
import org.jayhsu.rfhub.ui.theme.md_theme_light_onSecondary
import org.jayhsu.rfhub.ui.theme.md_theme_light_primary

interface FabButtonSub {
    val iconTint: Color
    val backgroundTint: Color
}

private class FabButtonSubImpl(
    override val iconTint: Color,
    override val backgroundTint: Color
) : FabButtonSub

fun FabButtonSub(
    backgroundTint: Color = md_theme_light_primary,
    iconTint: Color = md_theme_light_onSecondary
): FabButtonSub = FabButtonSubImpl(iconTint, backgroundTint)