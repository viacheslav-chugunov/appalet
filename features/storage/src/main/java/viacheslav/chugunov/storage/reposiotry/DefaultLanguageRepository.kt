package viacheslav.chugunov.storage.reposiotry

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import viacheslav.chugunov.core.datasource.PreferenceDataSource
import viacheslav.chugunov.core.model.Language
import viacheslav.chugunov.core.repository.LanguageRepository


class DefaultLanguageRepository(
    private val preferences: PreferenceDataSource<Language>,
    private val defaultLanguage: Language = Language.English
) : LanguageRepository {

    override suspend fun getLanguage(): Language =
        preferences.get() ?: defaultLanguage

    override fun getLanguageFlow(): Flow<Language> =
        preferences.getFlow().map { it ?: defaultLanguage }

    override suspend fun setLanguage(language: Language) =
        preferences.set(language)
}