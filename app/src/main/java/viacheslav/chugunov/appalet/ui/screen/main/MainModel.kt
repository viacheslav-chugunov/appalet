package viacheslav.chugunov.appalet.ui.screen.main

import viacheslav.chugunov.core.model.Language
import viacheslav.chugunov.core.model.Theme
import viacheslav.chugunov.core.util.Screen

data class MainModel(
    val theme: Theme = Theme.Empty,
    val language: Language = Language.English,
    val modeDay: Boolean = true,
    val preview: Screen.Preview = Screen.Input,
    val currentScreen: Screen = Screen.Input,
)