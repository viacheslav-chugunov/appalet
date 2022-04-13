package viacheslav.chugunov.core.usecase

import viacheslav.chugunov.core.model.Theme
import viacheslav.chugunov.core.repository.PreferredThemesRepository

interface AddThemeToCollectionUseCase {
    suspend fun invoke(theme: Theme)


    class Default(
        private val preferredThemesRepository: PreferredThemesRepository
    ) : AddThemeToCollectionUseCase {
        override suspend fun invoke(theme: Theme) {
            preferredThemesRepository.addTheme(theme)
        }
    }
}