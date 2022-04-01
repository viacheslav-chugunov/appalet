package viacheslav.chugunov.materialtheme.ui.view

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import viacheslav.chugunov.materialtheme.extension.onBackground
import viacheslav.chugunov.materialtheme.ui.theme.LocalTheme

@Composable
fun TextView(
    modifier: Modifier = Modifier,
    text: String = "",
    color: Color = LocalTheme.current.onBackground,
    size: TextUnit = 16.sp,
    weight: FontWeight = FontWeight.Normal,
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
        textAlign = align
    )
}