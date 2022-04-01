package viacheslav.chugunov.materialtheme.ui.screen.main

import androidx.compose.animation.*
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
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
import viacheslav.chugunov.materialtheme.ui.screen.preview.ListScreen
import viacheslav.chugunov.materialtheme.ui.screen.preview.InputScreen
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
        preview = model.preview,
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
    preview: Screen.Preview,
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
        topBar = {
            TopAppBar(
                backgroundColor = theme.primaryRegular,
                contentColor = theme.primaryOnRegular,
            ) {
                Text(
                    text = "${preview.number}/${Screen.Preview.COUNT}",
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    fontSize = 16.sp
                )
            }
        },
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
                enterTransition = { fadeIn(spring(stiffness = Spring.StiffnessVeryLow)) },
                exitTransition = { fadeOut(spring(stiffness = Spring.StiffnessVeryLow)) }
            ) {
                InputScreen()
            }
            composable(
                route = Screen.Route.LIST,
                enterTransition = { fadeIn(spring(stiffness = Spring.StiffnessVeryLow)) },
                exitTransition = { fadeOut(spring(stiffness = Spring.StiffnessVeryLow)) }
            ) {
                ListScreen()
            }
        }
    }
}