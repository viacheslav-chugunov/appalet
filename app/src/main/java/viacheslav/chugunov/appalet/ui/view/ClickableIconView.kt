package viacheslav.chugunov.appalet.ui.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun ClickableIconView(
    iconId: Int,
    contentPadding: PaddingValues = PaddingValues(all = 8.dp),
    tint: Color = LocalContentColor.current.copy(alpha = LocalContentAlpha.current),
    clickable: Boolean = true,
    onPerform: () -> Unit
) {
    Icon(
        painter = painterResource(iconId),
        contentDescription = null,
        modifier = Modifier
            .clickable(enabled = clickable) { onPerform() }
            .padding(contentPadding),
        tint = tint
    )
}