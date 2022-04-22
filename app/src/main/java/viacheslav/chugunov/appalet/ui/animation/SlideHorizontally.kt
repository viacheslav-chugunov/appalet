package viacheslav.chugunov.appalet.ui.animation

import androidx.compose.animation.*
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.VisibilityThreshold
import androidx.compose.animation.core.spring
import androidx.compose.runtime.Stable
import androidx.compose.ui.unit.IntOffset

@Stable
fun slideToRight(): EnterTransition =
    slideInHorizontally(
        spring(stiffness = Spring.StiffnessLow, visibilityThreshold = IntOffset.VisibilityThreshold)
    ) { it } + fadeIn(
        spring(stiffness = Spring.StiffnessVeryLow)
    )

@Stable
fun slideToLeft(): EnterTransition =
    slideInHorizontally(
        spring(stiffness = Spring.StiffnessLow, visibilityThreshold = IntOffset.VisibilityThreshold)
    ) { -it } + fadeIn(
        spring(stiffness = Spring.StiffnessVeryLow)
    )

@Stable
fun slideFromRight(): ExitTransition =
    slideOutHorizontally(
        spring(stiffness = Spring.StiffnessLow, visibilityThreshold = IntOffset.VisibilityThreshold)
    ) { it } + fadeOut(
        spring(stiffness = Spring.StiffnessVeryLow)
    )

@Stable
fun slideFromLeft(): ExitTransition =
    slideOutHorizontally(
        spring(stiffness = Spring.StiffnessLow, visibilityThreshold = IntOffset.VisibilityThreshold)
    ) { -it } + fadeOut(
        spring(stiffness = Spring.StiffnessVeryLow)
    )