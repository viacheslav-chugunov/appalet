package viacheslav.chugunov.core.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import viacheslav.chugunov.core.model.PreferredColors
import viacheslav.chugunov.core.model.SettingsConfiguration
import viacheslav.chugunov.core.repository.LanguageRepository
import viacheslav.chugunov.core.repository.PreferredColorsRepository

interface GetSettingsUseCase {
    fun invoke(): Flow<SettingsConfiguration>


    class Default(
        private val languageRepository: LanguageRepository,
        private val preferredColorsRepository: PreferredColorsRepository
    ) : GetSettingsUseCase {
        override fun invoke(): Flow<SettingsConfiguration> {
            val languageFlow = languageRepository.getLanguageFlow()
            val colorsFlow = preferredColorsRepository.getColorsFlow()
            return languageFlow.combine(colorsFlow) { language, colors ->
                SettingsConfiguration.Default(language, colors)
            }
        }
    }
}