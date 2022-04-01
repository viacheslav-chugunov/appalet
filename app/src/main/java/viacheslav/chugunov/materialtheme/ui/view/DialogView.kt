package viacheslav.chugunov.materialtheme.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import viacheslav.chugunov.materialtheme.extension.background
import viacheslav.chugunov.materialtheme.extension.onBackground
import viacheslav.chugunov.materialtheme.extension.secondaryOnRegular
import viacheslav.chugunov.materialtheme.extension.secondaryRegular
import viacheslav.chugunov.materialtheme.ui.theme.LocalTheme

@Composable
fun DialogView(
    title: String,
    visible: Boolean = true,
    maxHeight: Dp = Dp.Unspecified,
    surfaceColor: Color = LocalTheme.current.secondaryRegular,
    onSurfaceColor: Color = LocalTheme.current.secondaryOnRegular,
    backgroundColor: Color = LocalTheme.current.background,
    onDismiss: () -> Unit = {},
    buttons: @Composable RowScope.() -> Unit,
    content: @Composable ColumnScope.() -> Unit,
) {
    if (visible) {
        Dialog(onDismissRequest = onDismiss) {
            Column(
                modifier = Modifier
                    .background(backgroundColor)
                    .heightIn(max = maxHeight)
            ) {
                Box {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(surfaceColor)
                    ) {
                        TextView(
                            text = title,
                            size = 18.sp,
                            color = onSurfaceColor,
                            weight = FontWeight.SemiBold,
                            align = TextAlign.Center,
                            modifier = Modifier
                                .padding(all = 16.dp)
                                .fillMaxWidth()
                        )
                    }
                }
                Column(modifier = Modifier.padding(all = 8.dp)) {
                    content()
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(content = buttons)
                }
            }
        }
    }
}