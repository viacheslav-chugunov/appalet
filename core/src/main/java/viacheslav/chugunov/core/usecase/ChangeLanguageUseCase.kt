package viacheslav.chugunov.core.usecase

import viacheslav.chugunov.core.model.Language
import viacheslav.chugunov.core.repository.LanguageRepository

interface ChangeLanguageUseCase {
    suspend fun invoke(language: Language)


    class Default(
        private val languageRepository: LanguageRepository
    ) : ChangeLanguageUseCase {
        override suspend fun invoke(language: Language) {
            languageRepository.setLanguage(language)
        }
    }
}