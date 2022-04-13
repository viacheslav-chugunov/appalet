package viacheslav.chugunov.core.usecase

import viacheslav.chugunov.core.model.PreferredColors
import viacheslav.chugunov.core.model.Theme
import viacheslav.chugunov.core.repository.PreferredColorsRepository
import viacheslav.chugunov.core.repository.ThemeRepository

interface GetRandomThemeUseCase {
    suspend fun invoke(lightMode: Boolean, preferredColors: PreferredColors? = null): Theme


    class Default(
        private val preferredColorsRepository: PreferredColorsRepository,
        private val themeRepository: ThemeRepository
    ) : GetRandomThemeUseCase {
        override suspend fun invoke(lightMode: Boolean, preferredColors: PreferredColors?): Theme {
            val colors = preferredColors ?: preferredColorsRepository.getColors()
            return themeRepository.getRandomTheme(lightMode, colors)
        }
    }
}