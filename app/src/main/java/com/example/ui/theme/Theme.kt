package com.example.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = TurquoisePrimary,
    onPrimary = Color(0xFF00363F),
    primaryContainer = TurquoiseContainer,
    onPrimaryContainer = OnTurquoiseContainer,
    secondary = TurquoiseSecondary,
    onSecondary = Color(0xFF00373E),
    secondaryContainer = Color(0xFF004F58),
    onSecondaryContainer = Color(0xFF97F0FF),
    tertiary = TurquoiseTertiary,
    onTertiary = Color(0xFF00363D),
    background = DarkBackground,
    onBackground = TextPrimary,
    surface = DarkSurface,
    onSurface = TextPrimary,
    surfaceVariant = DarkSurfaceVariant,
    onSurfaceVariant = TextSecondary,
    outline = Color(0xFF37474F),
    outlineVariant = Color(0xFF263238),
    error = DangerCoral,
    errorContainer = DangerContainer,
    onError = Color.White,
    onErrorContainer = OnDangerContainer
)

@Composable
fun MyApplicationTheme(
    content: @Composable () -> Unit,
) {
    MaterialTheme(
        colorScheme = DarkColorScheme,
        typography = Typography,
        content = content
    )
}

