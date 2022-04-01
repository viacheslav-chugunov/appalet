package viacheslav.chugunov.materialtheme.ui.view

import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource

@Composable
fun IconView(
    id: Int,
    modifier: Modifier = Modifier,
) {
    Icon(
        painter = painterResource(id),
        contentDescription = null,
        modifier = modifier,
    )
}