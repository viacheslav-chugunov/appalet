package viacheslav.chugunov.appalet.ui.view

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import viacheslav.chugunov.appalet.extension.secondaryOnRegular
import viacheslav.chugunov.appalet.extension.secondaryRegular
import viacheslav.chugunov.appalet.ui.theme.LocalTheme

@Composable
fun FloatingActionButtonView(
    iconId: Int,
    visible: Boolean = true,
    loading: Boolean = false,
    backgroundColor: Color = LocalTheme.current.secondaryRegular,
    contentColor: Color = LocalTheme.current.secondaryOnRegular,
    shape: Shape = CircleShape,
    onPerform: () -> Unit
) {
    if (visible) {
        FloatingActionButton(
            onClick = { if (!loading) onPerform() },
            backgroundColor = backgroundColor,
            shape = shape,
            contentColor = contentColor
        ) {
            if (loading) {
                CircularProgressIndicator(
                    color = contentColor
                )
            } else {
                Icon(
                    painter = painterResource(iconId),
                    contentDescription = null
                )
            }
        }
    }
}