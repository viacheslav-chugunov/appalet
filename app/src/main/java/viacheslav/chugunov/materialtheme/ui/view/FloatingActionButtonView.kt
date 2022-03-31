package viacheslav.chugunov.materialtheme.ui.view

import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import viacheslav.chugunov.materialtheme.extension.secondaryOnRegular
import viacheslav.chugunov.materialtheme.extension.secondaryRegular
import viacheslav.chugunov.materialtheme.ui.theme.LocalTheme

@Composable
fun FloatingActionButtonView(
    iconId: Int,
    backgroundColor: Color = LocalTheme.current.secondaryRegular,
    contentColor: Color = LocalTheme.current.secondaryOnRegular,
    onPerform: () -> Unit
) {
    androidx.compose.material.FloatingActionButton(
        onClick = onPerform,
        backgroundColor = backgroundColor,
        contentColor = contentColor
    ) {
        Icon(
            painter = painterResource(iconId),
            contentDescription = null
        )
    }
}