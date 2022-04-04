package viacheslav.chugunov.appalet.ui.screen.colors

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import viacheslav.chugunov.core.model.ColorDescription
import viacheslav.chugunov.core.model.Theme
import viacheslav.chugunov.appalet.extension.*
import viacheslav.chugunov.appalet.ui.animation.slideInBottom
import viacheslav.chugunov.appalet.ui.animation.slideInRight
import viacheslav.chugunov.appalet.ui.animation.slideOutLeft
import viacheslav.chugunov.appalet.ui.animation.slideOutTop
import viacheslav.chugunov.appalet.ui.theme.LocalTheme
import viacheslav.chugunov.appalet.ui.view.TextView

@Composable
fun ColorsScreen() {
    val viewModel: ColorsViewModel = hiltViewModel()
    val model = viewModel.modelFlow.collectAsState().value
    val theme = LocalTheme.current

    DrawScreen(
        theme = theme,
        model = model
    )
}

@Composable
private fun DrawScreen(
    theme: Theme,
    model: ColorsModel
) {
    Column(modifier = Modifier.verticalScroll(ScrollState(0))) {
        DrawColor(
            background = theme.primaryLight,
            content = theme.primaryOnLight,
            description = theme.colorsPrimary.light,
            visible = model.visibleItemsCount >= 1
        )
        Spacer(modifier = Modifier.height(1.dp))
        DrawColor(
            background = theme.primaryRegular,
            content = theme.primaryOnRegular,
            description = theme.colorsPrimary.regular,
            visible = model.visibleItemsCount >= 2
        )
        Spacer(modifier = Modifier.height(1.dp))
        DrawColor(
            background = theme.primaryDark,
            content = theme.primaryOnDark,
            description = theme.colorsPrimary.dark,
            visible = model.visibleItemsCount >= 3
        )
        Spacer(modifier = Modifier.height(1.dp))
        DrawColor(
            background = theme.secondaryLight,
            content = theme.secondaryOnLight,
            description = theme.colorsSecondary.light,
            visible = model.visibleItemsCount >= 4
        )
        Spacer(modifier = Modifier.height(1.dp))
        DrawColor(
            background = theme.secondaryRegular,
            content = theme.secondaryOnRegular,
            description = theme.colorsSecondary.regular,
            visible = model.visibleItemsCount >= 5
        )
        Spacer(modifier = Modifier.height(1.dp))
        DrawColor(
            background = theme.secondaryDark,
            content = theme.secondaryOnDark,
            description = theme.colorsSecondary.dark,
            visible = model.visibleItemsCount >= 6
        )
        Spacer(modifier = Modifier.height(1.dp))
        DrawColor(
            background = theme.background,
            content = theme.onBackground,
            description = theme.colorBackground,
            visible = model.visibleItemsCount >= 7
        )
    }
}

@Composable
private fun DrawColor(
    background: Color,
    content: Color,
    description: ColorDescription,
    visible: Boolean,
) {
    AnimatedVisibility(
        visible = visible,
        enter = slideInBottom(),
        exit = slideOutTop()
    ) {
        Column(modifier = Modifier.background(background)) {
            TextView(
                text = "${description.nameRes.stringRes} ${description.alpha}",
                color = content,
                align = TextAlign.Center,
                weight = FontWeight.SemiBold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            )
            Row(
                modifier = Modifier
                    .padding(all = 16.dp)
                    .fillMaxWidth()
            ) {
                TextView(
                    text = "r:${description.red}",
                    color = content
                )
                Spacer(modifier = Modifier.width(8.dp))
                TextView(
                    text = "g:${description.green}",
                    color = content
                )
                Spacer(modifier = Modifier.width(8.dp))
                TextView(
                    text = "b:${description.blue}",
                    color = content
                )
                Spacer(modifier = Modifier.weight(1f))
                TextView(
                    text = "#${description.hex}",
                    color = content
                )
            }
        }
    }
}