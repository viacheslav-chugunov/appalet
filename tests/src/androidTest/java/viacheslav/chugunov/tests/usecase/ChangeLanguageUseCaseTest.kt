package viacheslav.chugunov.tests.usecase

import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import viacheslav.chugunov.core.model.Language
import viacheslav.chugunov.tests.util.AndroidTest
import viacheslav.chugunov.tests.util.datasource.TestLanguagePreferenceDataSource

@RunWith(AndroidJUnit4::class)
class ChangeLanguageUseCaseTest : AndroidTest() {

    @Test
    fun changeLanguage() = runBlocking {
        val defaultLanguage = Language.English
        val preference = TestLanguagePreferenceDataSource(defaultLanguage)
        val useCase = useCaseFactory.newChangeLanguage(preference, defaultLanguage)
        val newLanguage = Language.Russian
        useCase.invoke(newLanguage)
        val language = preference.language
        Assert.assertEquals(newLanguage, language)
    }

}