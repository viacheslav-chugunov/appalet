package viacheslav.chugunov.core.model

interface SettingsConfiguration {
    val language: Language
    val preferredColors: PreferredColors


    class Default(
        override val language: Language,
        override val preferredColors: PreferredColors
    ) : SettingsConfiguration
}