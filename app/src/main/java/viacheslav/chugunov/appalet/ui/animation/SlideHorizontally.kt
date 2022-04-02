package viacheslav.chugunov.appalet.ui.animation

import androidx.compose.animation.*
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.VisibilityThreshold
import androidx.compose.animation.core.spring
import androidx.compose.runtime.Stable
import androidx.compose.ui.unit.IntOffset

@Stable
fun slideInLeft(): EnterTransition =
    slideInHorizontally(
        spring(stiffness = Spring.StiffnessLow, visibilityThreshold = IntOffset.VisibilityThreshold)
    ) { it } + fadeIn(
        spring(stiffness = Spring.StiffnessVeryLow)
    )

@Stable
fun slideInRight(): EnterTransition =
    slideInHorizontally(
        spring(stiffness = Spring.StiffnessLow, visibilityThreshold = IntOffset.VisibilityThreshold)
    ) { -it } + fadeIn(
        spring(stiffness = Spring.StiffnessVeryLow)
    )

@Stable
fun slideOutLeft(): ExitTransition =
    slideOutHorizontally(
        spring(stiffness = Spring.StiffnessLow, visibilityThreshold = IntOffset.VisibilityThreshold)
    ) { it } + fadeOut(
        spring(stiffness = Spring.StiffnessVeryLow)
    )

@Stable
fun slideOutRight(): ExitTransition =
    slideOutHorizontally(
        spring(stiffness = Spring.StiffnessLow, visibilityThreshold = IntOffset.VisibilityThreshold)
    ) { -it } + fadeOut(
        spring(stiffness = Spring.StiffnessVeryLow)
    )