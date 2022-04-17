package viacheslav.chugunov.tests.usecase

import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import viacheslav.chugunov.core.model.ColorDescription
import viacheslav.chugunov.core.model.Coloring
import viacheslav.chugunov.storage.datastore.model.DataStorePreferredColors
import viacheslav.chugunov.tests.util.AndroidTest
import viacheslav.chugunov.tests.util.datasource.TestDataStorePreferredColorsPreferenceDataSource

@RunWith(AndroidJUnit4::class)
class ChangeBackgroundColorAsRandomUseCaseTest : AndroidTest() {

    @Test
    fun changeLightBackgroundColorAsRandom() = runBlocking {
        val defaultColoring = Coloring.White
        val defaultColors = DataStorePreferredColors().copy(defaultColoring)
        val preference = TestDataStorePreferredColorsPreferenceDataSource(defaultColors)
        val useCase = useCaseFactory.newChangeBackgroundColorAsRandom(preference, defaultColors)
        val isLight = true
        useCase.invoke(isLight)
        val coloring = preference.colors!!.lightBackground
        // Color must not be white or black in any case.
        Assert.assertNotEquals(defaultColoring, coloring)
    }

    @Test
    fun changeDarkBackgroundColorAsRandom() = runBlocking {
        val defaultColoring = Coloring.Default(ColorDescription.Black, ColorDescription.White)
        val defaultColors = DataStorePreferredColors().copy(defaultColoring)
        val preference = TestDataStorePreferredColorsPreferenceDataSource(defaultColors)
        val useCase = useCaseFactory.newChangeBackgroundColorAsRandom(preference, defaultColors)
        val isLight = false
        useCase.invoke(isLight)
        val coloring = preference.colors!!.darkBackground
        // Color must not be white or black in any case.
        Assert.assertNotEquals(defaultColoring, coloring)
    }

}