package viacheslav.chugunov.appalet.ui.view

import androidx.compose.animation.*
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import viacheslav.chugunov.appalet.extension.primaryOnRegular
import viacheslav.chugunov.appalet.extension.primaryRegular
import viacheslav.chugunov.appalet.ui.theme.LocalTheme

@Composable
fun TopAppBarView(
    title: String,
    visible: Boolean = true,
    actionIconId: Int? = null,
    onActionPerform: () -> Unit = {},
    actionClickable: Boolean = true,
    actionVisible: Boolean = true,
    backgroundColor: Color = LocalTheme.current.primaryRegular,
    contentColor: Color = LocalTheme.current.primaryOnRegular,
) {
    if (visible) {
        TopAppBar(
            backgroundColor = backgroundColor,
            contentColor = contentColor,
        ) {
            actionIconId?.let {
                Spacer(modifier = Modifier.width(40.dp))
            }
            TextView(
                text = title,
                size = 20.sp,
                weight = FontWeight.SemiBold,
                modifier = Modifier.weight(1f),
                color = contentColor,
                align = TextAlign.Center
            )
            actionIconId?.let {
                AnimatedVisibility(
                    visible = actionVisible,
                    enter = fadeIn(),
                    exit = fadeOut()
                ) {
                    ClickableIconView(
                        iconId = actionIconId,
                        onPerform = onActionPerform,
                        clickable = actionClickable
                    )
                }
            }
        }
    }
}