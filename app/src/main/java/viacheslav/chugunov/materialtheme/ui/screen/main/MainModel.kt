package viacheslav.chugunov.materialtheme.ui.screen.main

import viacheslav.chugunov.core.model.Theme

data class MainModel(
    val theme: Theme = Theme.Empty,
    val modeDay: Boolean = true
)