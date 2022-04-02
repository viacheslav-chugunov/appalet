package viacheslav.chugunov.appalet.ui.view

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import viacheslav.chugunov.appalet.extension.primaryOnRegular
import viacheslav.chugunov.appalet.extension.primaryRegular
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
                modifier = Modifier.fillMaxWidth(),
                color = contentColor,
                align = TextAlign.Center
            )
        }
    }
}