package viacheslav.chugunov.theme

import viacheslav.chugunov.core.datasource.RandomThemeDataSource
import viacheslav.chugunov.core.model.ColorDescription
import viacheslav.chugunov.core.model.Coloring
import viacheslav.chugunov.core.model.PreferredColors
import viacheslav.chugunov.core.util.ColorDescriptionFactory
import viacheslav.chugunov.core.model.Theme

class DefaultRandomThemeDataSource(
    private val colorFactory: ColorDescriptionFactory
) : RandomThemeDataSource {

    override fun createTheme(isLight: Boolean, preferredColors: PreferredColors): Theme {
        val colors = colorFactory.materialColorSets
        assert(colors.size >= 2) { "Material colors count must be at least 2" }
        val shuffledColors = colors.shuffled()
        val primarySet = shuffledColors[0]
        val secondarySet = shuffledColors[1]
        return Theme.Adaptive(isLight, primarySet, secondarySet, preferredColors)
    }

    override fun createColor(): Coloring {
        val colors = colorFactory.materialColors
        assert(colors.isNotEmpty()) { "Material colors must not be empty" }
        val shuffledColors = colors.shuffled()
        return shuffledColors.first()
    }

}