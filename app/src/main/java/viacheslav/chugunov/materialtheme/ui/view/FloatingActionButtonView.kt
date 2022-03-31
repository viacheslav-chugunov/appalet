package viacheslav.chugunov.materialtheme.ui.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import viacheslav.chugunov.materialtheme.extension.secondaryOnRegular
import viacheslav.chugunov.materialtheme.extension.secondaryRegular
import viacheslav.chugunov.materialtheme.ui.theme.LocalTheme

@Composable
fun FloatingActionButtonView(
    iconId: Int,
    visible: Boolean = true,
    backgroundColor: Color = LocalTheme.current.secondaryRegular,
    contentColor: Color = LocalTheme.current.secondaryOnRegular,
    shape: Shape = CircleShape,
    onPerform: () -> Unit
) {
    if (visible) {
        FloatingActionButton(
            onClick = onPerform,
            backgroundColor = backgroundColor,
            shape = shape,
            contentColor = contentColor
        ) {
            Icon(
                painter = painterResource(iconId),
                contentDescription = null
            )
        }
    }
}