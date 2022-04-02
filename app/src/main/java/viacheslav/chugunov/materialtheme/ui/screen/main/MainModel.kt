package viacheslav.chugunov.materialtheme.ui.screen.main

import viacheslav.chugunov.core.model.Theme
import viacheslav.chugunov.core.util.Screen

data class MainModel(
    val theme: Theme = Theme.Empty,
    val modeDay: Boolean = true,
    val preview: Screen.Preview = Screen.Input,
    val currentScreen: Screen = Screen.Input,
)