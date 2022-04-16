package viacheslav.chugunov.tests.util

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import viacheslav.chugunov.core.datasource.PreferenceDataSource
import viacheslav.chugunov.core.model.Language

class TestLanguagePreferenceDataSource(
    var language: Language?
) : PreferenceDataSource<Language> {

    override fun getFlow(): Flow<Language?> {
        return flowOf(language)
    }

    override suspend fun set(data: Language) {
        language = data
    }

    override suspend fun clear() {
        language = null
    }
}