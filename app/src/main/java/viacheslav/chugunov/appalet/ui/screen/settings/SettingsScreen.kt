package viacheslav.chugunov.appalet.ui.screen.settings

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
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
import viacheslav.chugunov.core.model.Language

@Composable
fun SettingsScreen(language: Language) {
    val viewModel: SettingsViewModel = hiltViewModel()

    DrawScreen(
        language = language,
        onLanguageChanged = viewModel::changeLanguage
    )
}

@Composable
private fun DrawScreen(
    language: Language,
    onLanguageChanged: (Language) -> Unit
) {

    LocalContext.current.setLanguage(language)

    Column(modifier = Modifier.fillMaxSize()) {
        TextView(
            text = R.string.language.stringRes,
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 16.dp),
            align = TextAlign.Center,
            size = 18.sp,
            weight = FontWeight.SemiBold
        )
        DrawLanguageButton(
            language = Language.English,
            selected = language.isEnglish,
            onPerform = { onLanguageChanged(Language.English) }
        )
        Spacer(modifier = Modifier.height(8.dp))
        DrawLanguageButton(
            language = Language.Russian,
            selected = language.isRussian,
            onPerform = { onLanguageChanged(Language.Russian) }
        )
        Spacer(modifier = Modifier.height(8.dp))
        DrawLanguageButton(
            language = Language.Ukrainian,
            selected = language.isUkrainian,
            onPerform = { onLanguageChanged(Language.Ukrainian) }
        )
        Spacer(modifier = Modifier.height(8.dp))
    }
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