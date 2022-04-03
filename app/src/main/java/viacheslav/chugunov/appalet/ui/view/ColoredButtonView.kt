package viacheslav.chugunov.appalet.ui.view

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import viacheslav.chugunov.appalet.extension.primaryLight
import viacheslav.chugunov.appalet.extension.primaryOnLight
import viacheslav.chugunov.appalet.extension.primaryOnRegular
import viacheslav.chugunov.appalet.extension.primaryRegular
import viacheslav.chugunov.appalet.ui.theme.LocalTheme

@Composable
fun ColoredButtonView(
    modifier: Modifier = Modifier,
    text: String? = null,
    leftIconId: Int? = null,
    rightIconId: Int? = null,
    iconsOnEdge: Boolean = false,
    enabled: Boolean = true,
    contentPadding: PaddingValues = PaddingValues(12.dp),
    backgroundColor: Color = LocalTheme.current.primaryRegular,
    contentColor: Color = LocalTheme.current.primaryOnRegular,
    iconsColor: Color = contentColor,
    disabledBackgroundColor: Color = LocalTheme.current.primaryLight,
    disabledContentColor: Color = LocalTheme.current.primaryOnLight,
    onClick: () -> Unit,
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        contentPadding = contentPadding,
        colors =  ButtonDefaults.buttonColors(
            backgroundColor = backgroundColor,
            contentColor = contentColor,
            disabledBackgroundColor = disabledBackgroundColor,
            disabledContentColor = disabledContentColor
        ),
        enabled = enabled
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            leftIconId?.let {
                IconView(
                    id = leftIconId,
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .size(24.dp),
                    tint = iconsColor
                )
            }
            text?.let {
                TextView(
                    text = text,
                    color = contentColor,
                    weight = FontWeight.SemiBold,
                    modifier = if (iconsOnEdge) Modifier.fillMaxWidth() else Modifier,
                    align = TextAlign.Center
                )
            }
            rightIconId?.let {
                IconView(
                    id = rightIconId,
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .size(24.dp),
                    tint = iconsColor
                )
            }
        }
    }
}