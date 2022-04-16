package viacheslav.chugunov.tests.repository

import android.util.Log
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import viacheslav.chugunov.core.datasource.PreferenceDataSource
import viacheslav.chugunov.core.model.Coloring
import viacheslav.chugunov.core.model.PreferredColors
import viacheslav.chugunov.core.repository.PreferredColorsRepository
import viacheslav.chugunov.storage.datastore.model.DataStorePreferredColors
import viacheslav.chugunov.storage.reposiotry.DefaultPreferredColorsRepository
import viacheslav.chugunov.tests.util.DataStorePreferredColorsPreferenceDataSource

@RunWith(AndroidJUnit4::class)
class PreferredColorsRepositoryTest {
    private val defaultColors: DataStorePreferredColors =
        DataStorePreferredColors(PreferredColors.Default(Coloring.White, Coloring.Gray900))
    private val defaultInverseColors: DataStorePreferredColors =
        DataStorePreferredColors(PreferredColors.Default(Coloring.Gray900, Coloring.White))

    @Test
    fun getDefaultColors() = runBlocking {
        val currentColors = null
        val repository = newRepository(currentColors, defaultColors)
        val colors = repository.getColors()
        Assert.assertTrue(defaultColors.equals(colors))
    }

    @Test
    fun getCurrentColors() = runBlocking {
        val currentColors = defaultInverseColors
        val repository = newRepository(currentColors, defaultColors)
        val colors = repository.getColors()
        Assert.assertTrue(currentColors.equals(colors))
    }

    @Test
    fun updateLightBackground() = runBlocking {
        val currentColoring = Coloring.White
        val currentColors = defaultInverseColors.copy(lightBackground = currentColoring)
        val preferences = DataStorePreferredColorsPreferenceDataSource(currentColors)
        val repository = newRepository(preferences, defaultColors)
        val newColoring = Coloring.Gray900
        repository.setLightBackground(newColoring)
        val lightColoring = preferences.colors!!.lightBackground
        Assert.assertTrue(newColoring == lightColoring)
    }

    @Test
    fun updateDarkBackground() = runBlocking {
        val currentColoring = Coloring.Gray900
        val currentColors = defaultInverseColors.copy(darkBackground = currentColoring)
        val preferences = DataStorePreferredColorsPreferenceDataSource(currentColors)
        val repository = newRepository(preferences, defaultColors)
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
        val preferences = DataStorePreferredColorsPreferenceDataSource(currentColors)
        val repository = newRepository(preferences, defaultColors)
        repository.resetLightBackground()
        val lightColoring = preferences.colors!!.lightBackground
        Assert.assertTrue(defaultColoring == lightColoring)
    }

    @Test
    fun resetDarkBackground() = runBlocking {
        val defaultColoring = defaultColors.darkBackground
        val currentColoring = Coloring.White
        val currentColors = defaultInverseColors.copy(darkBackground = currentColoring)
        val preferences = DataStorePreferredColorsPreferenceDataSource(currentColors)
        val repository = newRepository(preferences, defaultColors)
        repository.resetDarkBackground()
        val darkColoring = preferences.colors!!.darkBackground
        Assert.assertTrue(defaultColoring == darkColoring)
    }

    private fun newRepository(
        currentColors: DataStorePreferredColors?,
        defaultColors: DataStorePreferredColors
    ): PreferredColorsRepository = newRepository(
        preference = DataStorePreferredColorsPreferenceDataSource(currentColors),
        defaultColors = defaultColors
    )

    private fun newRepository(
        preference: PreferenceDataSource<DataStorePreferredColors>,
        defaultColors: DataStorePreferredColors
    ): PreferredColorsRepository = DefaultPreferredColorsRepository(
        preference = preference,
        defaultColors = defaultColors
    )
}