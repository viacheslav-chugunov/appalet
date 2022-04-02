package viacheslav.chugunov.materialtheme.ui.screen.main

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
import viacheslav.chugunov.core.model.domain.Theme
import viacheslav.chugunov.materialtheme.ui.theme.MaterialThemeTheme
import viacheslav.chugunov.materialtheme.R
import viacheslav.chugunov.core.util.Screen
import viacheslav.chugunov.materialtheme.extension.*
import viacheslav.chugunov.materialtheme.ui.animation.*
import viacheslav.chugunov.materialtheme.ui.screen.colors.ColorsScreen
import viacheslav.chugunov.materialtheme.ui.screen.preview.ListScreen
import viacheslav.chugunov.materialtheme.ui.screen.preview.InputScreen
import viacheslav.chugunov.materialtheme.ui.screen.preview.DialogPreviewScreen
import viacheslav.chugunov.materialtheme.ui.theme.LocalWindow
import viacheslav.chugunov.materialtheme.ui.view.BottomAppBarView
import viacheslav.chugunov.materialtheme.ui.view.ClickableIconView
import viacheslav.chugunov.materialtheme.ui.view.FloatingActionButtonView
import viacheslav.chugunov.materialtheme.ui.view.TopAppBarView
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
        theme = model.theme,
        modeDay = model.modeDay,
        preview = model.preview,
        currentScreen = model.currentScreen,
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
    theme: Theme,
    modeDay: Boolean,
    preview: Screen.Preview,
    currentScreen: Screen,
    onChangeThemePerform: () -> Unit,
    onModeDayPerform: () -> Unit,
    onPreviousPerform: () -> Unit,
    onNextPerform: () -> Unit,
    onColorsPerform: () -> Unit,
    onCurrentScreenChanged: (Screen) -> Unit
) = MaterialThemeTheme(theme) {

    LocalWindow.current?.apply {
        statusBarColor = theme.primaryDark.toArgb()
        navigationBarColor = theme.primaryDark.toArgb()
    }

    Scaffold(
        topBar = {
            TopAppBarView(
                visible = currentScreen.hasTitle,
                title = currentScreen.getTitle(LocalContext.current)
            )
        },
        bottomBar = {
            BottomAppBarView(visible = currentScreen.showButtonBar) {
                ClickableIconView(
                    iconId = if (modeDay) R.drawable.ic_day else R.drawable.ic_night,
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
                visible = currentScreen.showButtonBar,
                iconId = R.drawable.ic_refresh,
                onPerform = onChangeThemePerform
            )
        },
        isFloatingActionButtonDocked = true,
        floatingActionButtonPosition = FabPosition.Center,
        backgroundColor = theme.background
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
                InputScreen()
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
                ListScreen()
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
                    modeDay = modeDay,
                    onChangeThemePerform = onChangeThemePerform,
                    onModeDayPerform = onModeDayPerform,
                    onPreviousPerform = onPreviousPerform,
                    onNextPerform = onNextPerform
                )
            }
        }
    }
}