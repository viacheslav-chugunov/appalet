package viacheslav.chugunov.materialtheme.ui.screen.main

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.graphics.toArgb
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import viacheslav.chugunov.core.model.domain.Theme
import viacheslav.chugunov.materialtheme.ui.theme.MaterialThemeTheme
import viacheslav.chugunov.materialtheme.R
import viacheslav.chugunov.core.util.Screen
import viacheslav.chugunov.materialtheme.extension.*
import viacheslav.chugunov.materialtheme.ui.theme.LocalWindow
import viacheslav.chugunov.materialtheme.ui.view.BottomAppBarView
import viacheslav.chugunov.materialtheme.ui.view.ClickableIconView
import viacheslav.chugunov.materialtheme.ui.view.FloatingActionButtonView

@ExperimentalAnimationApi
@Composable
fun MainScreen() {
    val viewModel: MainViewModel = hiltViewModel()
    val model = viewModel.modelFlow.collectAsState().value
    val navController = rememberAnimatedNavController()

    model.openScreen?.also { screen ->
        viewModel.updateModel(openScreen = null)
        navController.navigate(screen)
    }

    DrawScreen(
        navHostController = navController,
        theme = model.theme,
        modeDay = model.modeDay,
        onChangeThemePerform = viewModel::changeTheme,
        onDayModePerform = viewModel::changeDayMode,
        onPreviousPerform = viewModel::showPreviousPreview,
        onNextPerform = viewModel::showNextPreview
    )
}

@ExperimentalAnimationApi
@Composable
private fun DrawScreen(
    navHostController: NavHostController,
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
            BottomAppBarView {
                ClickableIconView(
                    iconId = if (modeDay) R.drawable.ic_day else R.drawable.ic_night,
                    onPerform = onDayModePerform
                )
                ClickableIconView(
                    iconId = R.drawable.ic_back,
                    onPerform = onPreviousPerform
                )
                ClickableIconView(
                    iconId = R.drawable.ic_next,
                    onPerform = onNextPerform
                )
            }
        },
        floatingActionButton = {
            FloatingActionButtonView(iconId = R.drawable.ic_refresh, onPerform = onChangeThemePerform)
        },
        isFloatingActionButtonDocked = true,
        floatingActionButtonPosition = FabPosition.Center,
        backgroundColor = theme.background
    ) {
        AnimatedNavHost(
            navController = navHostController,
            startDestination = Screen.Route.LOGIN
        ) {
            composable(
                route = Screen.Route.LOGIN
            ) {
                Text(text = "login")
            }
            composable(
                route = Screen.Route.LIST
            ) {
                Text(text = "list")
            }
        }
    }
}