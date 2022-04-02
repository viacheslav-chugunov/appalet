package viacheslav.chugunov.appalet.ui.animation

import androidx.compose.animation.*
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.VisibilityThreshold
import androidx.compose.animation.core.spring
import androidx.compose.runtime.Stable
import androidx.compose.ui.unit.IntOffset

@Stable
fun slideInTop(): EnterTransition =
    slideInVertically(
        spring(
            stiffness = Spring.StiffnessLow,
            visibilityThreshold = IntOffset.VisibilityThreshold
        )
    ) { it } + fadeIn(
        spring(stiffness = Spring.StiffnessVeryLow)
    )

@Stable
fun slideOutTop(): ExitTransition =
    slideOutVertically(
        spring(
            stiffness = Spring.StiffnessLow,
            visibilityThreshold = IntOffset.VisibilityThreshold
        )
    ) { it } + fadeOut(
        spring(stiffness = Spring.StiffnessVeryLow)
    )

@Stable
fun slideInBottom(): EnterTransition =
    slideInVertically(
        spring(
            stiffness = Spring.StiffnessLow,
            visibilityThreshold = IntOffset.VisibilityThreshold
        )
    ) { 0 } + fadeIn(
        spring(stiffness = Spring.StiffnessVeryLow)
    )

@Stable
fun slideOutBottom(): ExitTransition =
    slideOutVertically(
        spring(
            stiffness = Spring.StiffnessLow,
            visibilityThreshold = IntOffset.VisibilityThreshold
        )
    ) { 0 } + fadeOut(
        spring(stiffness = Spring.StiffnessVeryLow)
    )