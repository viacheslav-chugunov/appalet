package viacheslav.chugunov.materialtheme.ui.screen.main

import androidx.compose.animation.*
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
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
import viacheslav.chugunov.materialtheme.ui.animation.slideInLeft
import viacheslav.chugunov.materialtheme.ui.animation.slideInRight
import viacheslav.chugunov.materialtheme.ui.animation.slideOutLeft
import viacheslav.chugunov.materialtheme.ui.animation.slideOutRight
import viacheslav.chugunov.materialtheme.ui.screen.preview.ListScreen
import viacheslav.chugunov.materialtheme.ui.screen.preview.InputScreen
import viacheslav.chugunov.materialtheme.ui.screen.preview.DialogScreen
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
        onChangeThemePerform = viewModel::changeTheme,
        onModeDayPerform = viewModel::changeDayMode,
        onPreviousPerform = {
            navController.navigate(model.preview.previous)
            viewModel.updatePreviewToPrevious()
        },
        onNextPerform = {
            navController.navigate(model.preview.next)
            viewModel.updatePreviewToNext()
        }
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
    onChangeThemePerform: () -> Unit,
    onModeDayPerform: () -> Unit,
    onPreviousPerform: () -> Unit,
    onNextPerform: () -> Unit
) = MaterialThemeTheme(theme) {

    LocalWindow.current?.apply {
        statusBarColor = theme.primaryDark.toArgb()
        navigationBarColor = theme.primaryDark.toArgb()
    }

    Scaffold(
        topBar = {
            TopAppBarView(title = "${preview.number} / ${Screen.Preview.COUNT}")
        },
        bottomBar = {
            BottomAppBarView {
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
            }
        },
        floatingActionButton = {
            FloatingActionButtonView(
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
                route = Screen.Route.INPUT,
                enterTransition = {
                    when (val route = initialState.destination.route) {
                        Screen.Route.INPUT -> fadeIn()
                        Screen.Route.DIALOG -> slideInLeft()
                        Screen.Route.LIST -> slideInRight()
                        else -> throw IllegalStateException("Navigated from $route")
                    }
                },
                exitTransition = {
                    when (val route = targetState.destination.route) {
                        Screen.Route.INPUT -> fadeOut()
                        Screen.Route.DIALOG -> slideOutLeft()
                        Screen.Route.LIST -> slideOutRight()
                        else -> throw IllegalStateException("Navigated from $route")
                    }
                }
            ) {
                InputScreen()
            }
            composable(
                route = Screen.Route.LIST,
                enterTransition = {
                    when (val route = initialState.destination.route) {
                        Screen.Route.LIST -> fadeIn()
                        Screen.Route.INPUT -> slideInLeft()
                        Screen.Route.DIALOG -> slideInRight()
                        else -> throw IllegalStateException("Navigated from $route")
                    }
                },
                exitTransition = {
                    when (val route = targetState.destination.route) {
                        Screen.Route.LIST -> fadeOut()
                        Screen.Route.INPUT -> slideOutLeft()
                        Screen.Route.DIALOG -> slideOutRight()
                        else -> throw IllegalStateException("Navigated from $route")
                    }
                }
            ) {
                ListScreen()
            }
            composable(
                route = Screen.Route.DIALOG,
                enterTransition = {
                    when (val route = initialState.destination.route) {
                        Screen.Route.DIALOG -> fadeIn()
                        Screen.Route.LIST -> slideInLeft()
                        Screen.Route.INPUT -> slideInRight()
                        else -> throw IllegalStateException("Navigated from $route")
                    }
                },
                exitTransition = {
                    when (val route = targetState.destination.route) {
                        Screen.Route.DIALOG -> fadeOut()
                        Screen.Route.LIST -> slideOutLeft()
                        Screen.Route.INPUT -> slideOutRight()
                        else -> throw IllegalStateException("Navigated from $route")
                    }
                }
            ) {
                DialogScreen(
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