package viacheslav.chugunov.tests.repository

import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import viacheslav.chugunov.core.model.Coloring
import viacheslav.chugunov.core.model.PreferredColors
import viacheslav.chugunov.tests.util.AndroidTest

@RunWith(AndroidJUnit4::class)
class ThemeRepositoryTest : AndroidTest() {

    @Test
    fun getThemeWithDifferentColorSets() {
        val repository = repositoryFactory.newTheme()
        val isLight = true
        val preferredColors = PreferredColors.Default()
        // You don’t need to do it longer - it will take too much time.
        for (i in 1..100_000) {
            val theme = repository.getRandomTheme(isLight, preferredColors)
            val primaryColors = theme.colorsPrimary
            val secondaryColors = theme.colorsSecondary
            Assert.assertNotEquals(primaryColors, secondaryColors)
        }
    }

    @Test
    fun getLightRandomTheme() {
        val repository = repositoryFactory.newTheme()
        val isLight = true
        val lightColoring = Coloring.White
        val preferredColors = PreferredColors.Default(lightBackground = lightColoring)
        val theme = repository.getRandomTheme(isLight, preferredColors)
        val backgroundColor = theme.colorBackground
        val backgroundOnColor = theme.colorOnBackground
        val backgroundColoring = Coloring.Default(backgroundColor, backgroundOnColor)
        Assert.assertEquals(lightColoring, backgroundColoring)
    }

    @Test
    fun getDarkRandomTheme() {
        val repository = repositoryFactory.newTheme()
        val isLight = false
        val darkColoring = Coloring.Gray900
        val preferredColors = PreferredColors.Default(darkBackground = darkColoring)
        val theme = repository.getRandomTheme(isLight, preferredColors)
        val backgroundColor = theme.colorBackground
        val backgroundOnColor = theme.colorOnBackground
        val backgroundColoring = Coloring.Default(backgroundColor, backgroundOnColor)
        Assert.assertEquals(darkColoring, backgroundColoring)
    }
}