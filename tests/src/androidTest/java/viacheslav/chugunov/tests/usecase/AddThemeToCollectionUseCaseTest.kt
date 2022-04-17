package viacheslav.chugunov.tests.usecase

import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import viacheslav.chugunov.core.model.Theme
import viacheslav.chugunov.core.repository.PreferredThemesRepository
import viacheslav.chugunov.core.usecase.AddThemeToCollectionUseCase
import viacheslav.chugunov.tests.util.AndroidTest
import viacheslav.chugunov.tests.util.TestThemeDao

@RunWith(AndroidJUnit4::class)
class AddThemeToCollectionUseCaseTest : AndroidTest() {

    @Test
    fun addThemeToCollection() = runBlocking {
        val initialThemes = emptyList<Theme>()
        val dao = TestThemeDao(initialThemes)
        val useCase = useCaseFactory.newAddThemeToCollectionUseCase(dao)
        val newTheme = Theme.Empty
        useCase.invoke(newTheme)
        val theme = dao.themes.first()
        Assert.assertEquals(newTheme, theme)
    }

}