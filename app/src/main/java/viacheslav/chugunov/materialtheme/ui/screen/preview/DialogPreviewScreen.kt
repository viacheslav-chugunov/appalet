package viacheslav.chugunov.materialtheme.ui.screen.preview

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import viacheslav.chugunov.core.model.domain.Theme
import viacheslav.chugunov.materialtheme.ui.view.DialogView
import viacheslav.chugunov.materialtheme.R
import viacheslav.chugunov.materialtheme.extension.*
import viacheslav.chugunov.materialtheme.ui.theme.LocalTheme
import viacheslav.chugunov.materialtheme.ui.view.ColoredButtonView
import viacheslav.chugunov.materialtheme.ui.view.TextView

@Composable
fun DialogPreviewScreen(
    modeDay: Boolean,
    onChangeThemePerform: () -> Unit,
    onModeDayPerform: () -> Unit,
    onPreviousPerform: () -> Unit,
    onNextPerform: () -> Unit
) {
    val theme = LocalTheme.current
    var showDialog by rememberSaveable { mutableStateOf(true) }

    DrawScreen(
        theme = theme,
        modeDay = modeDay,
        showDialog = showDialog,
        onChangeThemePerform = onChangeThemePerform,
        onModeDayPerform = onModeDayPerform,
        onPreviousPerform = onPreviousPerform,
        onNextPerform = onNextPerform,
        onShowDialogChanged = { showDialog = it }
    )
}

@Composable
private fun DrawScreen(
    theme: Theme,
    modeDay: Boolean,
    showDialog: Boolean,
    onChangeThemePerform: () -> Unit,
    onModeDayPerform: () -> Unit,
    onPreviousPerform: () -> Unit,
    onNextPerform: () -> Unit,
    onShowDialogChanged: (Boolean) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        DialogView(
            visible = showDialog,
            maxHeight = 600.dp,
            title = R.string.title.stringRes,
            buttons = {
                ColoredButtonView(
                    leftIconId = if (modeDay) R.drawable.ic_day else R.drawable.ic_night,
                    backgroundColor = theme.secondaryRegular,
                    contentColor = theme.secondaryOnRegular,
                    onClick = onModeDayPerform,
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(8.dp))
                ColoredButtonView(
                    leftIconId = R.drawable.ic_back,
                    backgroundColor = theme.secondaryRegular,
                    contentColor = theme.secondaryOnRegular,
                    onClick = {
                        onShowDialogChanged(false)
                        onPreviousPerform()
                    },
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(8.dp))
                ColoredButtonView(
                    leftIconId = R.drawable.ic_next,
                    backgroundColor = theme.secondaryRegular,
                    contentColor = theme.secondaryOnRegular,
                    onClick = {
                        onShowDialogChanged(false)
                        onNextPerform()
                    },
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(8.dp))
                ColoredButtonView(
                    leftIconId = R.drawable.ic_refresh,
                    backgroundColor = theme.secondaryRegular,
                    contentColor = theme.secondaryOnRegular,
                    onClick = onChangeThemePerform,
                    modifier = Modifier.weight(1f)
                )
            },
            content = {
                Column(modifier = Modifier
                    .weight(1f)
                    .verticalScroll(state = ScrollState(0))
                ) {
                    TextView(
                        text = R.string.multiline_text.stringRes,
                        singleLine = false
                    )
                }
            }
        )
    }
}