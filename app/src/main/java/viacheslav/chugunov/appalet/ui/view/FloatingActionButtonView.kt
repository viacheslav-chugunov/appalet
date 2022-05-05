package viacheslav.chugunov.appalet.ui.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import viacheslav.chugunov.appalet.extension.secondaryOnRegular
import viacheslav.chugunov.appalet.extension.secondaryRegular
import viacheslav.chugunov.appalet.ui.theme.LocalTheme

@Composable
fun FloatingActionButtonView(
    modifier: Modifier = Modifier,
    iconId: Int? = null,
    text: String? = null,
    textSize: TextUnit = 16.sp,
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
            contentColor = contentColor,
            modifier = modifier
        ) {
            if (loading) {
                CircularProgressIndicator(
                    color = contentColor
                )
            } else {
                if (text != null) {
                    TextView(
                        text = text,
                        weight = FontWeight.SemiBold,
                        size = textSize,
                        color = contentColor
                    )
                } else if (iconId != null) {
                    Icon(
                        painter = painterResource(iconId),
                        contentDescription = null,
                        modifier = Modifier.size(48.dp)
                    )
                }
            }
        }
    }
}