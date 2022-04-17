package viacheslav.chugunov.tests.usecase

import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import viacheslav.chugunov.core.model.Theme
import viacheslav.chugunov.tests.util.AndroidTest
import viacheslav.chugunov.tests.util.TestThemeDao

@RunWith(AndroidJUnit4::class)
class GetCollectedThemesUseCaseTest : AndroidTest() {

    @Test
    fun getCollectedThemesUseCase() = runBlocking {
        val initialTheme = Theme.Empty
        val initialThemes = listOf(initialTheme)
        val dao = TestThemeDao(initialThemes)
        val useCase = useCaseFactory.newGetCollectedThemes(dao)
        val theme = useCase.invoke().first().first()
        Assert.assertEquals(initialTheme, theme)
    }

}