package viacheslav.chugunov.appalet.ui.view

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import viacheslav.chugunov.appalet.extension.onBackground
import viacheslav.chugunov.appalet.ui.theme.LocalTheme

@Composable
fun TextView(
    modifier: Modifier = Modifier,
    text: String = "",
    color: Color = LocalTheme.current.onBackground,
    size: TextUnit = 16.sp,
    weight: FontWeight = FontWeight.Normal,
    spacing: TextUnit = 1.5.sp,
    singleLine: Boolean = true,
    align: TextAlign? = null
) {
    Text(
        text = text,
        fontSize = size,
        color = color,
        fontWeight = weight,
        maxLines = if (singleLine) 1 else Int.MAX_VALUE,
        modifier = modifier,
        textAlign = align,
        letterSpacing = spacing
    )
}