package viacheslav.chugunov.core.usecase

import viacheslav.chugunov.core.model.Theme
import viacheslav.chugunov.core.repository.PreferredThemesRepository

interface IsThemeInCollectionUseCase {
    suspend fun invoke(theme: Theme): Boolean


    class Default(
        private val preferredThemesRepository: PreferredThemesRepository
    ) : IsThemeInCollectionUseCase {
        override suspend fun invoke(theme: Theme): Boolean {
            return preferredThemesRepository.isThemeAdded(theme)
        }
    }
}