package viacheslav.chugunov.appalet.ui.theme

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
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import viacheslav.chugunov.appalet.ScreenMetrics
import viacheslav.chugunov.core.model.Theme
import viacheslav.chugunov.appalet.extension.background

@Composable
fun MaterialThemeTheme(
    theme: Theme,
    content: @Composable () -> Unit
) {
    val configuration = LocalConfiguration.current

    CompositionLocalProvider(
        LocalTheme provides theme,
        LocalWindow provides (LocalContext.current as Activity).window,
        LocalVerticalMetrics provides ScreenMetrics.of(configuration.screenHeightDp.dp),
        LocalHorizontalMetrics provides ScreenMetrics.of(configuration.screenWidthDp.dp)
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

val LocalVerticalMetrics: ProvidableCompositionLocal<ScreenMetrics> =
    compositionLocalOf { ScreenMetrics.Medium }

val LocalHorizontalMetrics: ProvidableCompositionLocal<ScreenMetrics> =
    compositionLocalOf { ScreenMetrics.Medium }