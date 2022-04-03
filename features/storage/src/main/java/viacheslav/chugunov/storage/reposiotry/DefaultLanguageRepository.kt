package viacheslav.chugunov.storage.reposiotry

import kotlinx.coroutines.flow.Flow
import viacheslav.chugunov.core.datasource.PreferenceDataSource
import viacheslav.chugunov.core.model.Language
import viacheslav.chugunov.core.repository.LanguageRepository


class DefaultLanguageRepository(
    private val preferences: PreferenceDataSource.Language
) : LanguageRepository {

    override suspend fun getLanguage(): Language =
        preferences.get()

    override fun getLanguageFlow(): Flow<Language> =
        preferences.getFlow()

    override suspend fun setLanguage(language: Language) =
        preferences.set(language)
}