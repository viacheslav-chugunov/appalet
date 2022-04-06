package viacheslav.chugunov.appalet.ui.screen.settings

import viacheslav.chugunov.core.model.Language
import viacheslav.chugunov.core.model.PreferredColors

data class SettingsModel(
    val preferredColors: PreferredColors = PreferredColors.Default(),
    val language: Language = Language.English
)
