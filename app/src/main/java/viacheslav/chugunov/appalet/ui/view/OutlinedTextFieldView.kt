package viacheslav.chugunov.appalet.ui.view

import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import viacheslav.chugunov.appalet.extension.*
import viacheslav.chugunov.appalet.ui.theme.LocalTheme

@Composable
fun OutlinedTextFieldView(
    value: String,
    modifier: Modifier = Modifier,
    labelText: String? = null,
    enabled: Boolean = true,
    singleLine: Boolean = true,
    textSelectionHandleColor: Color = LocalTheme.current.primaryRegular,
    textSelectionBackgroundColor: Color = LocalTheme.current.primaryLight,
    textColor: Color = LocalTheme.current.onBackground,
    focusedBorderColor: Color = LocalTheme.current.secondaryRegular,
    unfocusedBorderColor: Color = LocalTheme.current.secondaryLight,
    cursorColor: Color = LocalTheme.current.secondaryRegular,
    onValueChanged: (String) -> Unit
) {
    CompositionLocalProvider(
        LocalTextSelectionColors provides TextSelectionColors(
            textSelectionHandleColor,
            textSelectionBackgroundColor
        )
    ) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChanged,
            modifier = modifier,
            singleLine = singleLine,
            enabled = enabled,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                textColor = textColor,
                focusedBorderColor = focusedBorderColor,
                unfocusedBorderColor = unfocusedBorderColor,
                cursorColor = cursorColor,
            ),
            label = {
                labelText?.let {
                    TextView(
                        text = labelText,
                        color = textColor
                    )
                }
            }
        )
    }
}