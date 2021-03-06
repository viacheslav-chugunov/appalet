package viacheslav.chugunov.appalet.ui.screen.main

import viacheslav.chugunov.appalet.R
import viacheslav.chugunov.core.model.Language
import viacheslav.chugunov.core.model.PreferredColors
import viacheslav.chugunov.core.model.Theme
import viacheslav.chugunov.core.util.Screen

data class MainModel(
    val theme: Theme = Theme.Empty,
    val language: Language = Language.English,
    val preferredColors: PreferredColors = PreferredColors.Default(),
    val modeDay: Boolean = true,
    val preview: Screen.Preview = Screen.Input,
    val currentScreen: Screen = Screen.Input,
    val closeAppOnBackPress: Boolean = false,
    val inFavourites: Boolean = false,
    val loading: Boolean = true,
    val favouritesVisible: Boolean = true,
    val changeTypeLetterRes: Int = R.string.theme_change_type_all
)