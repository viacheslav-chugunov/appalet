package viacheslav.chugunov.materialtheme.ui.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun ClickableIconView(
    iconId: Int,
    contentPadding: PaddingValues = PaddingValues(all = 8.dp),
    onPerform: () -> Unit
) {
    Icon(
        painter = painterResource(iconId),
        contentDescription = null,
        modifier = Modifier
            .clickable { onPerform() }
            .padding(contentPadding),
    )
}