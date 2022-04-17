package viacheslav.chugunov.tests.repository

import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import viacheslav.chugunov.core.model.Theme
import viacheslav.chugunov.tests.util.AndroidTest
import viacheslav.chugunov.tests.util.TestThemeDao

@RunWith(AndroidJUnit4::class)
class PreferredThemesRepositoryTest : AndroidTest() {
    private val defaultTheme: Theme = Theme.Empty

    @Test
    fun getThemes() = runBlocking {
        val initialThemes = listOf(defaultTheme)
        val repository = repositoryFactory.newPreferredThemes(initialThemes)
        val themes = repository.getThemes()
        val initialThemesArray = initialThemes.toTypedArray()
        val themesArray = themes.toTypedArray()
        Assert.assertArrayEquals(initialThemesArray, themesArray)
    }

    @Test
    fun addTheme() = runBlocking {
        val initialThemes = emptyList<Theme>()
        val dao = TestThemeDao(initialThemes)
        val repository = repositoryFactory.newPreferredThemes(dao)
        repository.addTheme(defaultTheme)
        val theme = dao.themes.first()
        Assert.assertEquals(defaultTheme, theme)
    }

    @Test
    fun removeTheme() = runBlocking {
        val initialThemes = listOf(defaultTheme)
        val dao = TestThemeDao(initialThemes)
        val repository = repositoryFactory.newPreferredThemes(dao)
        repository.removeTheme(defaultTheme)
        val themes = dao.themes
        Assert.assertTrue(themes.isEmpty())
    }

    @Test
    fun containsTheme() = runBlocking {
        val initialThemes = listOf(defaultTheme)
        val repository = repositoryFactory.newPreferredThemes(initialThemes)
        val contains = repository.isThemeAdded(defaultTheme)
        Assert.assertTrue(contains)
    }
}