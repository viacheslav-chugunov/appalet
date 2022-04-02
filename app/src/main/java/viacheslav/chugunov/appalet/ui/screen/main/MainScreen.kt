package viacheslav.chugunov.appalet.ui.screen.main

import androidx.compose.animation.*
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import viacheslav.chugunov.appalet.ui.theme.MaterialThemeTheme
import viacheslav.chugunov.core.util.Screen
import viacheslav.chugunov.appalet.extension.*
import viacheslav.chugunov.appalet.ui.animation.*
import viacheslav.chugunov.appalet.ui.screen.colors.ColorsScreen
import viacheslav.chugunov.appalet.ui.screen.preview.list.ListPreviewScreen
import viacheslav.chugunov.appalet.ui.screen.preview.input.InputPreviewScreen
import viacheslav.chugunov.appalet.ui.screen.preview.dialog.DialogPreviewScreen
import viacheslav.chugunov.appalet.ui.theme.LocalWindow
import viacheslav.chugunov.appalet.ui.view.BottomAppBarView
import viacheslav.chugunov.appalet.ui.view.ClickableIconView
import viacheslav.chugunov.appalet.ui.view.FloatingActionButtonView
import viacheslav.chugunov.appalet.ui.view.TopAppBarView
import viacheslav.chugunov.appalet.R
import java.lang.IllegalStateException

@ExperimentalComposeUiApi
@ExperimentalAnimationApi
@Composable
fun MainScreen() {
    val viewModel: MainViewModel = hiltViewModel()
    val model = viewModel.modelFlow.collectAsState().value
    val navController = rememberAnimatedNavController()

    DrawScreen(
        navHostController = navController,
        model = model,
        onChangeThemePerform = viewModel::changeTheme,
        onModeDayPerform = viewModel::changeDayMode,
        onPreviousPerform = {
            navController.navigate(model.preview.previous)
            viewModel.updatePreviewToPrevious()
        },
        onNextPerform = {
            navController.navigate(model.preview.next)
            viewModel.updatePreviewToNext()
        },
        onColorsPerform = {
            navController.navigate(Screen.Colors)
        },
        onCurrentScreenChanged = { viewModel.updateModel(currentScreen = it) }
    )
}

@ExperimentalComposeUiApi
@ExperimentalAnimationApi
@Composable
private fun DrawScreen(
    navHostController: NavHostController,
    model: MainModel,
    onChangeThemePerform: () -> Unit,
    onModeDayPerform: () -> Unit,
    onPreviousPerform: () -> Unit,
    onNextPerform: () -> Unit,
    onColorsPerform: () -> Unit,
    onCurrentScreenChanged: (Screen) -> Unit
) = MaterialThemeTheme(model.theme) {

    LocalWindow.current?.apply {
        statusBarColor = model.theme.primaryDark.toArgb()
        navigationBarColor = model.theme.primaryDark.toArgb()
    }

    Scaffold(
        topBar = {
            TopAppBarView(
                visible = model.currentScreen.hasTitle,
                title = model.currentScreen.getTitle(LocalContext.current)
            )
        },
        bottomBar = {
            BottomAppBarView(visible = model.currentScreen.showButtonBar) {
                ClickableIconView(
                    iconId = if (model.modeDay) R.drawable.ic_day else R.drawable.ic_night,
                    onPerform = onModeDayPerform
                )
                ClickableIconView(
                    iconId = R.drawable.ic_back,
                    onPerform = onPreviousPerform
                )
                ClickableIconView(
                    iconId = R.drawable.ic_next,
                    onPerform = onNextPerform
                )
                Spacer(modifier = Modifier.weight(1f))
                ClickableIconView(
                    iconId = R.drawable.ic_colors,
                    onPerform = onColorsPerform
                )
            }
        },
        floatingActionButton = {
            FloatingActionButtonView(
                visible = model.currentScreen.showButtonBar,
                iconId = R.drawable.ic_refresh,
                onPerform = onChangeThemePerform
            )
        },
        isFloatingActionButtonDocked = true,
        floatingActionButtonPosition = FabPosition.Center,
        backgroundColor = model.theme.background
    ) {
        AnimatedNavHost(
            navController = navHostController,
            startDestination = Screen.Route.INPUT,
            modifier = Modifier.padding(top = it.calculateTopPadding(), bottom = it.calculateBottomPadding())
        ) {
            composable(
                route = Screen.Route.COLORS,
                enterTransition = {
                    when (val route = initialState.destination.route) {
                        Screen.Route.COLORS -> fadeIn()
                        Screen.Route.INPUT,
                        Screen.Route.LIST,
                        Screen.Route.DIALOG -> slideInTop()
                        else -> throw IllegalStateException("Navigated from $route")
                    }
                },
                popExitTransition = {
                    when (val route = targetState.destination.route) {
                        Screen.Route.COLORS -> fadeOut()
                        Screen.Route.INPUT,
                        Screen.Route.LIST,
                        Screen.Route.DIALOG -> slideOutTop()
                        else -> throw IllegalStateException("Navigated from $route")
                    }
                }
            ) {
                onCurrentScreenChanged(Screen.Colors)
                ColorsScreen()
            }
            composable(
                route = Screen.Route.INPUT,
                enterTransition = {
                    when (val route = initialState.destination.route) {
                        Screen.Route.INPUT -> fadeIn()
                        Screen.Route.DIALOG -> slideInLeft()
                        Screen.Route.LIST -> slideInRight()
                        Screen.Route.COLORS -> slideInBottom()
                        else -> throw IllegalStateException("Navigated from $route")
                    }
                },
                exitTransition = {
                    when (val route = targetState.destination.route) {
                        Screen.Route.INPUT -> fadeOut()
                        Screen.Route.DIALOG -> slideOutLeft()
                        Screen.Route.LIST -> slideOutRight()
                        Screen.Route.COLORS -> slideOutBottom()
                        else -> throw IllegalStateException("Navigated from $route")
                    }
                }
            ) {
                onCurrentScreenChanged(Screen.Input)
                InputPreviewScreen()
            }
            composable(
                route = Screen.Route.LIST,
                enterTransition = {
                    when (val route = initialState.destination.route) {
                        Screen.Route.LIST -> fadeIn()
                        Screen.Route.INPUT -> slideInLeft()
                        Screen.Route.DIALOG -> slideInRight()
                        Screen.Route.COLORS -> slideInBottom()
                        else -> throw IllegalStateException("Navigated from $route")
                    }
                },
                exitTransition = {
                    when (val route = targetState.destination.route) {
                        Screen.Route.LIST -> fadeOut()
                        Screen.Route.INPUT -> slideOutLeft()
                        Screen.Route.DIALOG -> slideOutRight()
                        Screen.Route.COLORS -> slideOutBottom()
                        else -> throw IllegalStateException("Navigated from $route")
                    }
                }
            ) {
                onCurrentScreenChanged(Screen.List)
                ListPreviewScreen()
            }
            composable(
                route = Screen.Route.DIALOG,
                enterTransition = {
                    when (val route = initialState.destination.route) {
                        Screen.Route.DIALOG -> fadeIn()
                        Screen.Route.LIST -> slideInLeft()
                        Screen.Route.INPUT -> slideInRight()
                        Screen.Route.COLORS -> slideInBottom()
                        else -> throw IllegalStateException("Navigated from $route")
                    }
                },
                exitTransition = {
                    when (val route = targetState.destination.route) {
                        Screen.Route.DIALOG -> fadeOut()
                        Screen.Route.LIST -> slideOutLeft()
                        Screen.Route.INPUT -> slideOutRight()
                        Screen.Route.COLORS -> slideOutBottom()
                        else -> throw IllegalStateException("Navigated from $route")
                    }
                }
            ) {
                onCurrentScreenChanged(Screen.Dialog)
                DialogPreviewScreen(
                    modeDay = model.modeDay,
                    onChangeThemePerform = onChangeThemePerform,
                    onChangeModeDayPerform = onModeDayPerform,
                    onPreviousScreenPerform = onPreviousPerform,
                    onNextScreenPerform = onNextPerform
                )
            }
        }
    }
}