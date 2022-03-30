package viacheslav.chugunov.materialtheme.ui.screen.main

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import viacheslav.chugunov.core.model.Theme
import viacheslav.chugunov.materialtheme.ui.theme.MaterialThemeTheme
import viacheslav.chugunov.materialtheme.R
import viacheslav.chugunov.materialtheme.extension.*
import viacheslav.chugunov.materialtheme.ui.theme.LocalWindow

@Composable
fun MainScreen() {
    val viewModel: MainViewModel = hiltViewModel()
    val model = viewModel.modelFlow.collectAsState().value

    DrawScreen(
        theme = model.theme,
        modeDay = model.modeDay,
        onChangeThemePerform = viewModel::changeTheme,
        onDayModePerform = viewModel::changeDayMode,
        onPreviousPerform = {},
        onNextPerform = {}
    )
}

@Composable
private fun DrawScreen(
    theme: Theme,
    modeDay: Boolean,
    onChangeThemePerform: () -> Unit,
    onDayModePerform: () -> Unit,
    onPreviousPerform: () -> Unit,
    onNextPerform: () -> Unit
) = MaterialThemeTheme(theme) {

    LocalWindow.current?.apply {
        statusBarColor = theme.primaryDark.toArgb()
        navigationBarColor = theme.primaryDark.toArgb()
    }

    Scaffold(
        bottomBar = {
            BottomAppBar(
                backgroundColor = theme.primaryRegular,
                contentColor = theme.primaryOnRegular
            ) {
                Icon(
                    painter = painterResource(if (modeDay) R.drawable.ic_day else R.drawable.ic_night),
                    contentDescription = null,
                    modifier = Modifier
                        .clickable { onDayModePerform() }
                        .padding(all = 8.dp),
                )
                Icon(
                    painter = painterResource(R.drawable.ic_back),
                    contentDescription = null,
                    modifier = Modifier
                        .clickable { onPreviousPerform() }
                        .padding(all = 8.dp)
                )
                Icon(
                    painter = painterResource(R.drawable.ic_next),
                    contentDescription = null,
                    modifier = Modifier
                        .clickable { onNextPerform() }
                        .padding(all = 8.dp)
                )
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onChangeThemePerform,
                backgroundColor = theme.secondaryRegular,
                contentColor = theme.secondaryOnRegular
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_refresh),
                    contentDescription = null
                )
            }
        },
        isFloatingActionButtonDocked = true,
        floatingActionButtonPosition = FabPosition.Center,
        backgroundColor = theme.background
    ) {

    }
}