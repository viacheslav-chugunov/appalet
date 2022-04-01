package viacheslav.chugunov.materialtheme.ui.view

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import viacheslav.chugunov.core.util.Screen
import viacheslav.chugunov.materialtheme.extension.primaryOnRegular
import viacheslav.chugunov.materialtheme.extension.primaryRegular
import viacheslav.chugunov.materialtheme.ui.theme.LocalTheme

@Composable
fun TopAppBarView(
    title: String,
    backgroundColor: Color = LocalTheme.current.primaryRegular,
    contentColor: Color = LocalTheme.current.primaryOnRegular,
) {
    TopAppBar(
        backgroundColor = backgroundColor,
        contentColor = contentColor,
    ) {
        TextView(
            text = title,
            modifier = Modifier.fillMaxWidth(),
            color = contentColor,
            align = TextAlign.Center
        )
    }
}