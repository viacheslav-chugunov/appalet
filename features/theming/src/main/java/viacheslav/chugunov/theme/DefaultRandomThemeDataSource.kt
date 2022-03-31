package viacheslav.chugunov.theme

import viacheslav.chugunov.core.datasource.RandomThemeDataSource
import viacheslav.chugunov.core.util.ColorDescriptionFactory
import viacheslav.chugunov.core.model.domain.Theme

class DefaultRandomThemeDataSource(
    private val colorFactory: ColorDescriptionFactory
) : RandomThemeDataSource {

    override fun create(isLight: Boolean): Theme {
        val colors = colorFactory.materialColors
        assert(colors.size >= 2) { "Material colors count must be at least 2" }
        val shuffledColors = colors.shuffled()
        val primarySet = shuffledColors[0]
        val secondarySet = shuffledColors[1]
        return if (isLight) Theme.Light(primarySet, secondarySet) else Theme.Dark(primarySet, secondarySet)
    }
}