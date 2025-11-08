package com.example.fantastika.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
data class FantastikaColorScheme(
    val background: Color,
    val onBackground: Color,
    val primary: Color,
    val onPrimary: Color,
    val secondary: Color,
    val onSecondary: Color,
    val tertiary: Color,
    val onTertiary: Color,
    val orange: Color
)

private val darkColorScheme = FantastikaColorScheme(
    background = Color(0xFF1C1C1E), // Darker gray
    onBackground = Color(0xFFE5E5E7), // Light gray text
    primary = Color(0xFF7986CB), // Light Indigo (stands out on dark)
    onPrimary = Color.White,
    secondary = Color(0xFF4A4A4C), // Mid-gray
    onSecondary = Color(0xFFE5E5E7),
    tertiary =  Color(0xFF4A4A4C), // Mid-gray
    onTertiary =  Color(0xFF8C1515),
    orange = Color(0xFFFFB74D)
)

private val lightColorScheme = FantastikaColorScheme(
    background = Color.White,
    onBackground = Color(0xFF1C1B1F), // Almost black text
    primary = Color(0xFF3F51B5), // Deep Indigo (primary accent)
    onPrimary = Color(0xFF7D7D80),
    secondary = Color(0xFFE3E0E0), // Very light gray
    onSecondary = Color(0xFF1C1B1F),
    tertiary = Color(0xFFC9C6C6),// Very light gray
    onTertiary = Color(0xFFFF8A80),
    orange = Color(0xFFFF9800)
)
val LocalFantastikaColorScheme = staticCompositionLocalOf { lightColorScheme }

@Composable
fun FantastikaTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
){
    val customColorScheme = if(darkTheme) darkColorScheme else lightColorScheme

    val m3ColorScheme = if(darkTheme) {
        darkColorScheme(
            primary = customColorScheme.primary,
            onPrimary = customColorScheme.onPrimary,
            secondary = customColorScheme.secondary,
            onSecondary = customColorScheme.onSecondary,
            background = customColorScheme.background,
            onBackground = customColorScheme.onBackground,
            surface = customColorScheme.background,
            onSurface = customColorScheme.onBackground,
            tertiary = customColorScheme.tertiary,
        )
    } else {
        lightColorScheme(
            primary = customColorScheme.primary,
            onPrimary = customColorScheme.onPrimary,
            secondary = customColorScheme.secondary,
            onSecondary = customColorScheme.onSecondary,
            background = customColorScheme.background,
            onBackground = customColorScheme.onBackground,
            surface = customColorScheme.background,
            onSurface = customColorScheme.onBackground,
            tertiary = customColorScheme.tertiary
        )
    }

    CompositionLocalProvider(
        LocalFantastikaColorScheme provides customColorScheme,
    ) {
        MaterialTheme(
            colorScheme = m3ColorScheme,
            typography = MaterialTheme.typography,
            content = content
        )
    }
}

object FantastikaTheme{
    val color: FantastikaColorScheme
        @Composable get() = LocalFantastikaColorScheme.current
}