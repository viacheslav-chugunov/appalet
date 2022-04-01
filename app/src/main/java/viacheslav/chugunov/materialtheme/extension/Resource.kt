package viacheslav.chugunov.materialtheme.extension

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

val Int.stringRes: String
    @Composable
    get() = stringResource(this)