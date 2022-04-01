package viacheslav.chugunov.materialtheme.ui.view

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import viacheslav.chugunov.materialtheme.extension.primaryLight
import viacheslav.chugunov.materialtheme.extension.primaryOnLight
import viacheslav.chugunov.materialtheme.extension.primaryOnRegular
import viacheslav.chugunov.materialtheme.extension.primaryRegular
import viacheslav.chugunov.materialtheme.ui.theme.LocalTheme

@Composable
fun ColoredButtonView(
    modifier: Modifier = Modifier,
    text: String? = null,
    leftIconId: Int? = null,
    rightIconId: Int? = null,
    enabled: Boolean = true,
    contentPadding: PaddingValues = PaddingValues(12.dp),
    backgroundColor: Color = LocalTheme.current.primaryRegular,
    contentColor: Color = LocalTheme.current.primaryOnRegular,
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
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
            }
            text?.let {
                TextView(
                    text = text,
                    color = contentColor,
                    weight = FontWeight.SemiBold
                )
            }
            rightIconId?.let {
                IconView(
                    id = rightIconId,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
            }
        }
    }
}