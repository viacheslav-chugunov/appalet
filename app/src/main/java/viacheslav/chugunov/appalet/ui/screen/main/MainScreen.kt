package viacheslav.chugunov.appalet.ui.screen.main

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.animation.*
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import kotlinx.coroutines.launch
import viacheslav.chugunov.appalet.ui.theme.MaterialThemeTheme
import viacheslav.chugunov.core.util.Screen
import viacheslav.chugunov.appalet.extension.*
import viacheslav.chugunov.appalet.ui.animation.*
import viacheslav.chugunov.appalet.ui.screen.colors.ColorsScreen
import viacheslav.chugunov.appalet.ui.screen.preview.list.ListPreviewScreen
import viacheslav.chugunov.appalet.ui.screen.preview.input.InputPreviewScreen
import viacheslav.chugunov.appalet.ui.screen.preview.dialog.DialogPreviewScreen
import viacheslav.chugunov.appalet.ui.theme.LocalWindow
import viacheslav.chugunov.appalet.R
import viacheslav.chugunov.appalet.ui.screen.collection.CollectionScreen
import viacheslav.chugunov.appalet.ui.screen.settings.SettingsScreen
import viacheslav.chugunov.appalet.ui.view.*
import viacheslav.chugunov.core.model.Theme
import java.lang.IllegalStateException

@ExperimentalFoundationApi
@ExperimentalComposeUiApi
@ExperimentalAnimationApi
@Composable
fun MainScreen() {
    val viewModel: MainViewModel = hiltViewModel()
    val model = viewModel.modelFlow.collectAsState().value
    val navController = rememberAnimatedNavController()
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    
    LaunchedEffect(Unit) {
        viewModel.updateModel(closeAppOnBackPress = false)
    }
    
    BackHandler(enabled = !model.closeAppOnBackPress) {
        coroutineScope.launch {
            viewModel.updateModel(closeAppOnBackPress = true)
            snackbarHostState.showSnackbar("")
            viewModel.updateModel(closeAppOnBackPress = false)
            snackbarHostState.currentSnackbarData?.also { snackbarData ->
                snackbarData.dismiss()
            }
        }
    }

    DrawScreen(
        navHostController = navController,
        snackbarHostState = snackbarHostState,
        model = model,
        onChangeTypePerform = viewModel::updateThemeTypeChange,
        onChangeThemePerform = viewModel::changeThemeAsRandom,
        onApplyThemeIntent = viewModel::changeTheme,
        onModeDayPerform = viewModel::changeDayMode,
        onPreviousPerform = {
            navController.navigate(model.preview.previous)
            viewModel.updatePreviewToPrevious()
        },
        onNextPerform = {
            navController.navigate(model.preview.next)
            viewModel.updatePreviewToNext()
        },
        onColorsPerform = { navController.navigate(Screen.Colors) },
        onSettingsPerform = { navController.navigate(Screen.Settings) },
        onCollectionPerform = { navController.navigate(Screen.Collection) },
        onCurrentScreenChanged = { viewModel.updateModel(currentScreen = it) },
        onFavouritesChanged = viewModel::onThemeRemoved,
        addThemeToFavourites = viewModel::changeFavourites
    )
}

@ExperimentalFoundationApi
@ExperimentalComposeUiApi
@ExperimentalAnimationApi
@Composable
private fun DrawScreen(
    navHostController: NavHostController,
    snackbarHostState: SnackbarHostState,
    model: MainModel,
    onChangeTypePerform: () -> Unit,
    onChangeThemePerform: () -> Unit,
    onApplyThemeIntent: (Theme) -> Unit,
    onModeDayPerform: () -> Unit,
    onPreviousPerform: () -> Unit,
    onNextPerform: () -> Unit,
    onColorsPerform: () -> Unit,
    onSettingsPerform: () -> Unit,
    onCollectionPerform: () -> Unit,
    onCurrentScreenChanged: (Screen) -> Unit,
    onFavouritesChanged: (Theme) -> Unit,
    addThemeToFavourites: () -> Unit,
) = MaterialThemeTheme(model.theme) {

    LocalWindow.current?.apply {
        statusBarColor = model.theme.primaryDark.toArgb()
        navigationBarColor = model.theme.primaryDark.toArgb()
    }

    LocalContext.current.setLanguage(model.language)

    Scaffold(
        topBar = {
            TopAppBarView(
                visible = model.currentScreen.hasTitle,
                title = model.currentScreen.getTitle(LocalContext.current),
                actionIconId = if (model.inFavourites) R.drawable.ic_favourite else R.drawable.ic_not_favourite,
                onActionPerform = addThemeToFavourites,
                actionVisible = model.favouritesVisible
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
                    iconId = R.drawable.ic_settings,
                    onPerform = onSettingsPerform
                )
                ClickableIconView(
                    iconId = R.drawable.ic_colors,
                    onPerform = onColorsPerform
                )
                ClickableIconView(
                    iconId = R.drawable.ic_collection,
                    onPerform = onCollectionPerform
                )
            }
        },
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState,
                snackbar = {
                    Snackbar(
                        modifier = Modifier.padding(all = 8.dp),
                        backgroundColor = model.theme.secondaryDark,
                        contentColor = model.theme.secondaryOnDark,
                        shape = RoundedCornerShape(48.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            IconView(
                                id = R.drawable.ic_launcher,
                                modifier = Modifier.size(48.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            TextView(
                                text = R.string.warning_before_closing_app.stringRes,
                                color = model.theme.secondaryOnDark,
                                weight = FontWeight.SemiBold,
                                singleLine = false
                            )
                        }
                    }
                }
            )
        },
        floatingActionButton = {
            AnimatedVisibility(
                visible = model.currentScreen.showButtonBar,
                enter = slideInTop(),
                exit = slideOutBottom()
            ) {
                Row {
                    FloatingActionButtonView(
                        visible = model.currentScreen.showButtonBar,
                        text = model.changeTypeLetterRes.stringRes,
                        textSize = 24.sp,
                        onPerform = onChangeTypePerform,
                        modifier = Modifier.padding(end = 2.dp)
                    )
                    FloatingActionButtonView(
                        visible = model.currentScreen.showButtonBar,
                        iconId = R.drawable.ic_refresh,
                        onPerform = onChangeThemePerform,
                        modifier = Modifier.padding(start = 2.dp)
                    )
                }
            }
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
                route = Screen.Route.SETTINGS,
                enterTransition = {
                    when (val route = initialState.destination.route) {
                        Screen.Route.SETTINGS -> fadeIn()
                        Screen.Route.INPUT,
                        Screen.Route.LIST,
                        Screen.Route.DIALOG -> slideInTop()
                        else -> throw IllegalStateException("Navigated from $route")
                    }
                },
                popExitTransition = {
                    when (val route = targetState.destination.route) {
                        Screen.Route.SETTINGS -> fadeOut()
                        Screen.Route.INPUT,
                        Screen.Route.LIST,
                        Screen.Route.DIALOG -> slideOutBottom()
                        else -> throw IllegalStateException("Navigated from $route")
                    }
                }
            ) {
                onCurrentScreenChanged(Screen.Settings)
                SettingsScreen()
            }
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
                        Screen.Route.DIALOG -> slideOutBottom()
                        else -> throw IllegalStateException("Navigated from $route")
                    }
                }
            ) {
                onCurrentScreenChanged(Screen.Colors)
                ColorsScreen()
            }
            composable(
                route = Screen.Route.COLLECTION,
                enterTransition = {
                    when (val route = initialState.destination.route) {
                        Screen.Route.COLLECTION -> fadeIn()
                        Screen.Route.INPUT,
                        Screen.Route.LIST,
                        Screen.Route.DIALOG -> slideInTop()
                        else -> throw IllegalStateException("Navigated from $route")
                    }
                },
                popExitTransition = {
                    when (val route = targetState.destination.route) {
                        Screen.Route.COLLECTION -> fadeOut()
                        Screen.Route.INPUT,
                        Screen.Route.LIST,
                        Screen.Route.DIALOG -> slideOutBottom()
                        else -> throw IllegalStateException("Navigated from $route")
                    }
                }
            ) {
                onCurrentScreenChanged(Screen.Collection)
                CollectionScreen(
                    onThemeApplied = onApplyThemeIntent,
                    onThemeRemoved = onFavouritesChanged
                )
            }
            composable(
                route = Screen.Route.INPUT,
                enterTransition = {
                    when (val route = initialState.destination.route) {
                        Screen.Route.INPUT -> fadeIn()
                        Screen.Route.DIALOG -> slideToRight()
                        Screen.Route.LIST -> slideToLeft()
                        Screen.Route.SETTINGS,
                        Screen.Route.COLORS,
                        Screen.Route.COLLECTION -> slideInBottom()
                        else -> throw IllegalStateException("Navigated from $route")
                    }
                },
                exitTransition = {
                    when (val route = targetState.destination.route) {
                        Screen.Route.INPUT -> fadeOut()
                        Screen.Route.DIALOG -> slideFromRight()
                        Screen.Route.LIST -> slideFromLeft()
                        Screen.Route.SETTINGS,
                        Screen.Route.COLORS,
                        Screen.Route.COLLECTION -> slideOutTop()
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
                        Screen.Route.INPUT -> slideToRight()
                        Screen.Route.DIALOG -> slideToLeft()
                        Screen.Route.SETTINGS,
                        Screen.Route.COLORS,
                        Screen.Route.COLLECTION -> slideInBottom()
                        else -> throw IllegalStateException("Navigated from $route")
                    }
                },
                exitTransition = {
                    when (val route = targetState.destination.route) {
                        Screen.Route.LIST -> fadeOut()
                        Screen.Route.INPUT -> slideFromRight()
                        Screen.Route.DIALOG -> slideFromLeft()
                        Screen.Route.SETTINGS,
                        Screen.Route.COLORS,
                        Screen.Route.COLLECTION -> slideOutTop()
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
                        Screen.Route.LIST -> slideToRight()
                        Screen.Route.INPUT -> slideToLeft()
                        Screen.Route.SETTINGS,
                        Screen.Route.COLORS,
                        Screen.Route.COLLECTION -> slideInBottom()
                        else -> throw IllegalStateException("Navigated from $route")
                    }
                },
                exitTransition = {
                    when (val route = targetState.destination.route) {
                        Screen.Route.DIALOG -> fadeOut()
                        Screen.Route.LIST -> slideFromRight()
                        Screen.Route.INPUT -> slideFromLeft()
                        Screen.Route.SETTINGS,
                        Screen.Route.COLORS,
                        Screen.Route.COLLECTION -> slideOutTop()
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