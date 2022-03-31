package viacheslav.chugunov.materialtheme.ui.view

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.BottomAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import viacheslav.chugunov.core.model.ui.MainState.Empty.theme
import viacheslav.chugunov.materialtheme.R
import viacheslav.chugunov.materialtheme.extension.primaryOnRegular
import viacheslav.chugunov.materialtheme.extension.primaryRegular
import viacheslav.chugunov.materialtheme.ui.theme.LocalTheme

@Composable
fun BottomAppBarView(
    backgroundColor: Color = LocalTheme.current.primaryRegular,
    contentColor: Color = LocalTheme.current.primaryOnRegular,
    content: @Composable RowScope.() -> Unit
) {
    BottomAppBar(
        backgroundColor = backgroundColor,
        contentColor = contentColor,
        content = content
    )
}