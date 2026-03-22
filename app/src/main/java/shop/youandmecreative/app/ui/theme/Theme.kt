package shop.youandmecreative.app.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val LightColorScheme = lightColorScheme(
    primary = Primary,
    onPrimary = OnPrimary,
    secondary = Accent,
    onSecondary = OnPrimary,
    tertiary = AccentLight,
    background = Background,
    onBackground = OnSurface,
    surface = Surface,
    onSurface = OnSurface,
    error = Error,
    onError = OnPrimary,
    outline = Divider,
    onSurfaceVariant = MutedText,
    surfaceVariant = Background,
)

private val DarkColorScheme = darkColorScheme(
    primary = PrimaryDarkTheme,
    onPrimary = OnPrimary,
    secondary = AccentDarkTheme,
    onSecondary = OnSurface,
    tertiary = AccentLight,
    background = BackgroundDark,
    onBackground = OnSurfaceDark,
    surface = SurfaceDark,
    onSurface = OnSurfaceDark,
    error = Error,
    onError = OnPrimary,
    outline = DividerDark,
    onSurfaceVariant = MutedTextDark,
    surfaceVariant = SurfaceDark,
)

@Composable
fun YouAndMeCreativeTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}

@Composable
fun ProductAppYNMCRTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) = YouAndMeCreativeTheme(darkTheme = darkTheme, dynamicColor = dynamicColor, content = content)
