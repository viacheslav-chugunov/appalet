package viacheslav.chugunov.appalet.extension

import android.content.res.Resources
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import java.lang.Exception

val Int.stringRes: String
    @Composable
    get() = stringResource(this)