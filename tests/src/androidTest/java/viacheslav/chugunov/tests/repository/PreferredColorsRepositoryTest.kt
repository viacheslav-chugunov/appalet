package viacheslav.chugunov.tests.repository

import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import viacheslav.chugunov.core.model.Coloring
import viacheslav.chugunov.core.model.PreferredColors
import viacheslav.chugunov.storage.datastore.model.DataStorePreferredColors
import viacheslav.chugunov.tests.util.AndroidTest
import viacheslav.chugunov.tests.util.datasource.TestDataStorePreferredColorsPreferenceDataSource

@RunWith(AndroidJUnit4::class)
class PreferredColorsRepositoryTest : AndroidTest() {
    private val defaultColors: DataStorePreferredColors =
        DataStorePreferredColors(PreferredColors.Default(Coloring.White, Coloring.Gray900))
    private val defaultInverseColors: DataStorePreferredColors =
        DataStorePreferredColors(PreferredColors.Default(Coloring.Gray900, Coloring.White))

    @Test
    fun getDefaultColors() = runBlocking {
        val currentColors = null
        val repository = repositoryFactory.newPreferredColors(currentColors, defaultColors)
        val colors = repository.getColors()
        Assert.assertTrue(defaultColors.equals(colors))
    }

    @Test
    fun getCurrentColors() = runBlocking {
        val currentColors = defaultInverseColors
        val repository = repositoryFactory.newPreferredColors(currentColors, defaultColors)
        val colors = repository.getColors()
        Assert.assertTrue(currentColors.equals(colors))
    }

    @Test
    fun updateLightBackground() = runBlocking {
        val currentColoring = Coloring.White
        val currentColors = defaultInverseColors.copy(lightBackground = currentColoring)
        val preferences = TestDataStorePreferredColorsPreferenceDataSource(currentColors)
        val repository = repositoryFactory.newPreferredColors(preferences, defaultColors)
        val newColoring = Coloring.Gray900
        repository.setLightBackground(newColoring)
        val lightColoring = preferences.colors!!.lightBackground
        Assert.assertTrue(newColoring == lightColoring)
    }

    @Test
    fun updateDarkBackground() = runBlocking {
        val currentColoring = Coloring.Gray900
        val currentColors = defaultInverseColors.copy(darkBackground = currentColoring)
        val preferences = TestDataStorePreferredColorsPreferenceDataSource(currentColors)
        val repository = repositoryFactory.newPreferredColors(preferences, defaultColors)
        val newColoring = Coloring.White
        repository.setDarkBackground(newColoring)
        val darkColoring = preferences.colors!!.darkBackground
        Assert.assertTrue(newColoring == darkColoring)
    }

    @Test
    fun resetLightBackground() = runBlocking {
        val defaultColoring = defaultColors.lightBackground
        val currentColoring = Coloring.Gray900
        val currentColors = defaultInverseColors.copy(lightBackground = currentColoring)
        val preferences = TestDataStorePreferredColorsPreferenceDataSource(currentColors)
        val repository = repositoryFactory.newPreferredColors(preferences, defaultColors)
        repository.resetLightBackground()
        val lightColoring = preferences.colors!!.lightBackground
        Assert.assertTrue(defaultColoring == lightColoring)
    }

    @Test
    fun resetDarkBackground() = runBlocking {
        val defaultColoring = defaultColors.darkBackground
        val currentColoring = Coloring.White
        val currentColors = defaultInverseColors.copy(darkBackground = currentColoring)
        val preferences = TestDataStorePreferredColorsPreferenceDataSource(currentColors)
        val repository = repositoryFactory.newPreferredColors(preferences, defaultColors)
        repository.resetDarkBackground()
        val darkColoring = preferences.colors!!.darkBackground
        Assert.assertTrue(defaultColoring == darkColoring)
    }
}