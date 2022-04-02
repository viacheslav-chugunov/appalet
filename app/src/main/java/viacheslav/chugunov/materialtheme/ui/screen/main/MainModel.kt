package viacheslav.chugunov.materialtheme.ui.screen.main

import viacheslav.chugunov.core.model.domain.Theme
import viacheslav.chugunov.core.model.ui.MainState
import viacheslav.chugunov.core.util.Screen

data class MainModel(
    override val theme: Theme,
    override val modeDay: Boolean,
    override val preview: Screen.Preview,
    override val currentScreen: Screen,
) : MainState