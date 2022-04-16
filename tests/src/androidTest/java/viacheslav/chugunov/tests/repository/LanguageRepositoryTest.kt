package viacheslav.chugunov.tests.repository

import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import viacheslav.chugunov.core.datasource.PreferenceDataSource
import viacheslav.chugunov.core.model.Language
import viacheslav.chugunov.core.repository.LanguageRepository
import viacheslav.chugunov.storage.reposiotry.DefaultLanguageRepository
import viacheslav.chugunov.tests.util.AndroidTest
import viacheslav.chugunov.tests.util.datasource.TestLanguagePreferenceDataSource

@RunWith(AndroidJUnit4::class)
class LanguageRepositoryTest : AndroidTest() {
    private val defaultLanguage: Language = Language.English

    @Test
    fun getDefaultLanguage() = runBlocking {
        val currentLanguage: Language? = null
        val repository = newRepository(currentLanguage, defaultLanguage)
        val language = repository.getLanguage()
        Assert.assertEquals(defaultLanguage, language)
    }

    @Test
    fun getCurrentLanguage() = runBlocking {
        val currentLanguage = Language.Russian
        val repository = newRepository( currentLanguage, defaultLanguage)
        val language = repository.getLanguage()
        Assert.assertEquals(currentLanguage, language)
    }

    @Test
    fun updateLanguage() = runBlocking {
        val currentLanguage = Language.Russian
        val preferences = TestLanguagePreferenceDataSource(currentLanguage)
        val repository = newRepository(preferences, defaultLanguage)
        val newLanguage = Language.Ukrainian
        repository.setLanguage(newLanguage)
        val language = preferences.language
        Assert.assertEquals(newLanguage, language)
    }

    private fun newRepository(
        currentLanguage: Language?,
        defaultLanguage: Language
    ): LanguageRepository = newRepository(
        preferences = TestLanguagePreferenceDataSource(currentLanguage),
        defaultLanguage = defaultLanguage
    )

    private fun newRepository(
        preferences: PreferenceDataSource<Language>,
        defaultLanguage: Language
    ): LanguageRepository = DefaultLanguageRepository(
        preferences = preferences,
        defaultLanguage = defaultLanguage
    )
}