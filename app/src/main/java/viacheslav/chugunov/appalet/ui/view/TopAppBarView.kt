package viacheslav.chugunov.appalet.ui.view

import androidx.compose.animation.*
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import viacheslav.chugunov.appalet.extension.primaryOnRegular
import viacheslav.chugunov.appalet.extension.primaryRegular
import viacheslav.chugunov.appalet.ui.animation.slideInLeft
import viacheslav.chugunov.appalet.ui.animation.slideInRight
import viacheslav.chugunov.appalet.ui.animation.slideOutLeft
import viacheslav.chugunov.appalet.ui.animation.slideOutRight
import viacheslav.chugunov.appalet.ui.theme.LocalTheme

@Composable
fun TopAppBarView(
    title: String,
    visible: Boolean = true,
    backgroundColor: Color = LocalTheme.current.primaryRegular,
    contentColor: Color = LocalTheme.current.primaryOnRegular,
) {
    if (visible) {
        TopAppBar(
            backgroundColor = backgroundColor,
            contentColor = contentColor,
        ) {
            TextView(
                text = title,
                size = 20.sp,
                weight = FontWeight.SemiBold,
                modifier = Modifier.fillMaxWidth(),
                color = contentColor,
                align = TextAlign.Center
            )
        }
    }
}