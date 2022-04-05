package viacheslav.chugunov.appalet

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

sealed class ScreenMetrics(val size: Dp = Int.MAX_VALUE.dp) {
    val isSmall: Boolean get() = this is Small
    val isMedium: Boolean get() = this is Medium
    val isLarge: Boolean get() = this is Large
    val isSmallOrMedium: Boolean get() = isSmall || isMedium
    val isMediumOrLarge: Boolean get() = isMedium || isLarge

    object Small: ScreenMetrics(size = 400.dp)
    object Medium : ScreenMetrics(size = 800.dp)
    object Large : ScreenMetrics()

    companion object {
        fun of(size: Dp): ScreenMetrics = when {
            size <= Small.size -> Small
            else -> Medium
        }
    }
}