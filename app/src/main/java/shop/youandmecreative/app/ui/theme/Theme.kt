package shop.youandmecreative.app.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    primary = DarkPrimary,
    secondary = DarkAccent,
    tertiary = AccentLight,
    background = DarkBackground,
    surface = DarkSurface,
    onPrimary = OnPrimary,
    onSecondary = DarkOnSurface,
    onBackground = DarkOnSurface,
    onSurface = DarkOnSurface,
    error = ErrorColor,
    outline = Divider,
)

private val LightColorScheme = lightColorScheme(
    primary = Primary,
    secondary = Accent,
    tertiary = AccentLight,
    background = Background,
    surface = Surface,
    onPrimary = OnPrimary,
    onSecondary = OnSurface,
    onBackground = OnSurface,
    onSurface = OnSurface,
    error = ErrorColor,
    outline = Divider,
    surfaceVariant = Background,
)

@Composable
fun ProductAppYNMCRTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
