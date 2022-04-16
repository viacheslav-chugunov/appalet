package viacheslav.chugunov.tests.repository

import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import viacheslav.chugunov.core.datasource.DatabaseDataSource
import viacheslav.chugunov.core.model.ColorSet
import viacheslav.chugunov.core.model.Theme
import viacheslav.chugunov.core.repository.PreferredThemesRepository
import viacheslav.chugunov.storage.reposiotry.DefaultPreferredThemesRepository
import viacheslav.chugunov.storage.room.ThemeDao
import viacheslav.chugunov.storage.room.ThemeDatabaseDataSource
import viacheslav.chugunov.storage.room.ThemeEntity
import viacheslav.chugunov.tests.util.TestThemeDao

@RunWith(AndroidJUnit4::class)
class PreferredThemesRepositoryTest {
    private val defaultTheme: Theme = Theme.Empty

    @Test
    fun getThemes() = runBlocking {
        val initialThemes = listOf(defaultTheme)
        val repository = newRepository(initialThemes)
        val themes = repository.getThemes()
        val initialThemesArray = initialThemes.toTypedArray()
        val themesArray = themes.toTypedArray()
        Assert.assertArrayEquals(initialThemesArray, themesArray)
    }

    @Test
    fun addTheme() = runBlocking {
        val initialThemes = emptyList<Theme>()
        val dao = TestThemeDao(initialThemes)
        val repository = newRepository(dao)
        repository.addTheme(defaultTheme)
        val theme = dao.themes.first()
        Assert.assertEquals(defaultTheme, theme)
    }

    @Test
    fun removeTheme() = runBlocking {
        val initialThemes = listOf(defaultTheme)
        val dao = TestThemeDao(initialThemes)
        val repository = newRepository(dao)
        repository.removeTheme(defaultTheme)
        val themes = dao.themes
        Assert.assertTrue(themes.isEmpty())
    }

    @Test
    fun containsTheme() = runBlocking {
        val initialThemes = listOf(defaultTheme)
        val repository = newRepository(initialThemes)
        val contains = repository.isThemeAdded(defaultTheme)
        Assert.assertTrue(contains)
    }

    private fun newRepository(themes: List<Theme>): PreferredThemesRepository =
        newRepository(TestThemeDao(themes))

    private fun newRepository(dao: ThemeDao): PreferredThemesRepository =
        DefaultPreferredThemesRepository(ThemeDatabaseDataSource(dao))
}