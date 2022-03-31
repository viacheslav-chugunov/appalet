package viacheslav.chugunov.materialtheme.ui.theme

import android.app.Activity
import android.view.Window
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import viacheslav.chugunov.core.model.domain.Theme
import viacheslav.chugunov.materialtheme.extension.background

@Composable
fun MaterialThemeTheme(
    theme: Theme,
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalTheme provides theme,
        LocalWindow provides (LocalContext.current as Activity).window
    ) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = theme.background
        ) {
            MaterialTheme(content = content)
        }
    }
}

val LocalTheme: ProvidableCompositionLocal<Theme> =
    compositionLocalOf { Theme.Empty }

val LocalWindow: ProvidableCompositionLocal<Window?> =
    compositionLocalOf { null }