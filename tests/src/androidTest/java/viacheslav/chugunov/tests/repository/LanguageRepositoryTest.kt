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
import viacheslav.chugunov.tests.util.TestLanguagePreferenceDataSource

@RunWith(AndroidJUnit4::class)
class LanguageRepositoryTest : AndroidTest() {

    @Test
    fun getDefaultLanguage() = runBlocking {
        val currentLanguage: Language? = null
        val defaultLanguage = Language.English
        val repository = newRepository(currentLanguage, defaultLanguage)
        val language = repository.getLanguage()
        Assert.assertEquals(defaultLanguage, language)
    }

    @Test
    fun getCurrentLanguage() = runBlocking {
        val currentLanguage = Language.Russian
        val defaultLanguage = Language.English
        val repository = newRepository( currentLanguage, defaultLanguage)
        val language = repository.getLanguage()
        Assert.assertEquals(currentLanguage, language)
    }

    @Test
    fun updateLanguage() = runBlocking {
        val currentLanguage = Language.Russian
        val defaultLanguage = Language.English
        val dataSource = TestLanguagePreferenceDataSource(currentLanguage)
        val repository = newRepository(dataSource, defaultLanguage)
        val newLanguage = Language.Ukrainian
        repository.setLanguage(newLanguage)
        val language = dataSource.language
        Assert.assertEquals(newLanguage, language)
    }

    private fun newRepository(
        currentLanguage: Language?,
        defaultLanguage: Language
    ): LanguageRepository = DefaultLanguageRepository(
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