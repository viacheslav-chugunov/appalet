package viacheslav.chugunov.appalet.ui.view

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.BottomAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import viacheslav.chugunov.appalet.extension.primaryOnRegular
import viacheslav.chugunov.appalet.extension.primaryRegular
import viacheslav.chugunov.appalet.ui.animation.slideInTop
import viacheslav.chugunov.appalet.ui.animation.slideOutBottom
import viacheslav.chugunov.appalet.ui.theme.LocalTheme

@Composable
fun BottomAppBarView(
    visible: Boolean = true,
    backgroundColor: Color = LocalTheme.current.primaryRegular,
    contentColor: Color = LocalTheme.current.primaryOnRegular,
    content: @Composable RowScope.() -> Unit
) {
    Column {
        AnimatedVisibility(
            visible = visible,
            enter = slideInTop(),
            exit = slideOutBottom(),
        ) {
            BottomAppBar(
                backgroundColor = backgroundColor,
                contentColor = contentColor,
                content = content
            )
        }
    }
}