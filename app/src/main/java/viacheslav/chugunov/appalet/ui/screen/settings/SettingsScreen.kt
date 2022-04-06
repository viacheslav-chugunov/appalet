package viacheslav.chugunov.appalet.ui.screen.settings

import android.content.Context
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import viacheslav.chugunov.appalet.R
import viacheslav.chugunov.appalet.extension.*
import viacheslav.chugunov.appalet.ui.theme.LocalTheme
import viacheslav.chugunov.appalet.ui.view.ColoredButtonView
import viacheslav.chugunov.appalet.ui.view.TextView
import viacheslav.chugunov.core.model.ColorDescription
import viacheslav.chugunov.core.model.Coloring
import viacheslav.chugunov.core.model.Language
import viacheslav.chugunov.core.model.PreferredColors

@Composable
fun SettingsScreen() {
    val viewModel: SettingsViewModel = hiltViewModel()
    val model = viewModel.modelFlow.collectAsState().value

    DrawScreen(
        model = model,
        onLanguageChangeIntent = viewModel::changeLanguage,
        onLightBackgroundColorChangeIntent = viewModel::changeLightBackgroundColor,
        onLightBackgroundColorResetIntent = viewModel::resetLightBackgroundColor,
        onDarkBackgroundColorChangeIntent = viewModel::changeDarkBackgroundColor,
        onDarkBackgroundColorResetIntent = viewModel::resetDarkBackgroundColor
    )
}

@Composable
private fun DrawScreen(
    model: SettingsModel,
    onLanguageChangeIntent: (Language) -> Unit,
    onLightBackgroundColorChangeIntent: () -> Unit,
    onDarkBackgroundColorChangeIntent: () -> Unit,
    onLightBackgroundColorResetIntent: () -> Unit,
    onDarkBackgroundColorResetIntent: () -> Unit
) {
    LocalContext.current.setLanguage(model.language)

    Column(modifier = Modifier.fillMaxSize()) {
        DrawTitle(
            titleId = R.string.language,
            language = model.language
        )
        DrawLanguageButton(
            language = Language.English,
            selected = model.language.isEnglish,
            onPerform = { onLanguageChangeIntent(Language.English) }
        )
        Spacer(modifier = Modifier.height(8.dp))
        DrawLanguageButton(
            language = Language.Russian,
            selected = model.language.isRussian,
            onPerform = { onLanguageChangeIntent(Language.Russian) }
        )
        Spacer(modifier = Modifier.height(8.dp))
        DrawLanguageButton(
            language = Language.Ukrainian,
            selected = model.language.isUkrainian,
            onPerform = { onLanguageChangeIntent(Language.Ukrainian) }
        )
        Spacer(modifier = Modifier.height(8.dp))
        DrawTitle(
            titleId = R.string.background_colors,
            language = model.language
        )
        DrawBackgroundColorButton(
            labelId = R.string.light_theme,
            coloring = model.preferredColors.lightBackground,
            onChangeColorIntent = onLightBackgroundColorChangeIntent,
            onResetColorIntent = onLightBackgroundColorResetIntent
        )
        Spacer(modifier = Modifier.height(8.dp))
        DrawBackgroundColorButton(
            labelId = R.string.dark_theme,
            coloring = model.preferredColors.darkBackground,
            onChangeColorIntent = onDarkBackgroundColorChangeIntent,
            onResetColorIntent = onDarkBackgroundColorResetIntent
        )
        Spacer(modifier = Modifier.height(8.dp))
    }
}

@Composable
private fun DrawTitle(
    titleId: Int,
    language: Language
) {
    LocalContext.current.setLanguage(language)

    TextView(
        text = titleId.stringRes,
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 16.dp),
        align = TextAlign.Center,
        size = 18.sp,
        weight = FontWeight.SemiBold
    )
}

@Composable
private fun DrawLanguageButton(
    language: Language,
    selected: Boolean,
    onPerform: () -> Unit
) {
    val theme = LocalTheme.current

    ColoredButtonView(
        text = language.nameId.stringRes,
        leftIconId = language.flagIconId,
        iconsOnEdge = true,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        onClick = onPerform,
        backgroundColor = if (selected) theme.secondaryRegular else theme.primaryRegular,
        contentColor = if (selected) theme.secondaryOnRegular else theme.primaryOnRegular,
        iconsColor = Color.Unspecified
    )
}

@Composable
private fun DrawBackgroundColorButton(
    labelId: Int,
    coloring: Coloring,
    onChangeColorIntent: () -> Unit,
    onResetColorIntent: () -> Unit
) {
    val theme = LocalTheme.current

    Row(modifier = Modifier.padding(horizontal = 16.dp)) {
        ColoredButtonView(
            text =  labelId.stringRes,
            leftIconId = R.drawable.ic_painter,
            iconsOnEdge = true,
            onClick = onChangeColorIntent,
            modifier = Modifier.weight(1f),
            backgroundColor = theme.primaryRegular,
            contentColor = theme.primaryOnRegular,
            iconsColor = Color(coloring.color.value)
        )
        Spacer(modifier = Modifier.width(8.dp))
        ColoredButtonView(
            leftIconId = R.drawable.ic_reset,
            onClick = onResetColorIntent,
            backgroundColor = theme.primaryRegular,
            contentColor = theme.primaryOnRegular
        )
    }
}