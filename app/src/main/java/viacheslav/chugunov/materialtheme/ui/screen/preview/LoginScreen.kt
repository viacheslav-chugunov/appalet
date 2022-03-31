package viacheslav.chugunov.materialtheme.ui.screen.preview

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import viacheslav.chugunov.core.model.domain.Theme
import viacheslav.chugunov.materialtheme.R
import viacheslav.chugunov.materialtheme.extension.primaryRegular
import viacheslav.chugunov.materialtheme.ui.theme.LocalTheme

@Composable
fun LoginScreen() {
    DrawScreen()
}

@Composable
private fun DrawScreen(theme: Theme = LocalTheme.current) {
    Box {
        TopAppBar(
            modifier = Modifier.fillMaxWidth(),
            contentColor = theme.primaryRegular
        ) {
            Text(
                text = stringResource(R.string.text),
                style = TextStyle(fontSize = 24.sp)
            )
        }
    }
}