package viacheslav.chugunov.appalet.ui.screen.preview.dialog

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import viacheslav.chugunov.core.model.Theme
import viacheslav.chugunov.appalet.ui.view.DialogView
import viacheslav.chugunov.appalet.R
import viacheslav.chugunov.appalet.extension.*
import viacheslav.chugunov.appalet.ui.theme.LocalTheme
import viacheslav.chugunov.appalet.ui.view.ColoredButtonView
import viacheslav.chugunov.appalet.ui.view.TextView

@Composable
fun DialogPreviewScreen(
    modeDay: Boolean,
    onChangeThemePerform: () -> Unit,
    onChangeModeDayPerform: () -> Unit,
    onPreviousScreenPerform: () -> Unit,
    onNextScreenPerform: () -> Unit
) {
    val viewModel: DialogPreviewViewModel = hiltViewModel()
    val model = viewModel.modelFlow.collectAsState().value

    DrawScreen(
        model = model,
        modeDay = modeDay,
        onChangeThemePerform = onChangeThemePerform,
        onChangeModeDayPerform = onChangeModeDayPerform,
        onPreviousScreenPerform = onPreviousScreenPerform,
        onNextScreenPerform = onNextScreenPerform,
        onShowDialogChanged = { viewModel.updateModel(showDialog = it) }
    )
}

@Composable
private fun DrawScreen(
    model: DialogPreviewModel,
    modeDay: Boolean,
    onChangeThemePerform: () -> Unit,
    onChangeModeDayPerform: () -> Unit,
    onPreviousScreenPerform: () -> Unit,
    onNextScreenPerform: () -> Unit,
    onShowDialogChanged: (Boolean) -> Unit,
    theme: Theme = LocalTheme.current,
) {
    Column(modifier = Modifier.fillMaxSize()) {
        DialogView(
            visible = model.showDialog,
            maxHeight = 600.dp,
            title = R.string.title.stringRes,
            buttons = {
                ColoredButtonView(
                    leftIconId = if (modeDay) R.drawable.ic_day else R.drawable.ic_night,
                    backgroundColor = theme.secondaryRegular,
                    contentColor = theme.secondaryOnRegular,
                    onClick = onChangeModeDayPerform,
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(8.dp))
                ColoredButtonView(
                    leftIconId = R.drawable.ic_back,
                    backgroundColor = theme.secondaryRegular,
                    contentColor = theme.secondaryOnRegular,
                    onClick = {
                        onShowDialogChanged(false)
                        onPreviousScreenPerform()
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
                        onNextScreenPerform()
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