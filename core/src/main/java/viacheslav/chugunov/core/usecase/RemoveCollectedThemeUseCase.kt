package viacheslav.chugunov.core.usecase

import viacheslav.chugunov.core.model.Theme
import viacheslav.chugunov.core.repository.PreferredThemesRepository

interface RemoveCollectedThemeUseCase {
    suspend fun invoke(theme: Theme): Theme


    class Default(
        private val preferredThemesRepository: PreferredThemesRepository
    ) : RemoveCollectedThemeUseCase {
        override suspend fun invoke(theme: Theme): Theme {
            preferredThemesRepository.removeTheme(theme)
            return theme
        }
    }
}