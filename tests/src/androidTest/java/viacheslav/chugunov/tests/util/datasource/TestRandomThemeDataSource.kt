package viacheslav.chugunov.tests.util.datasource

import viacheslav.chugunov.core.datasource.RandomThemeDataSource
import viacheslav.chugunov.core.model.Coloring
import viacheslav.chugunov.core.model.PreferredColors
import viacheslav.chugunov.core.model.Theme

class TestRandomThemeDataSource(
    
) : RandomThemeDataSource {

    override fun createTheme(isLight: Boolean, preferredColors: PreferredColors): Theme {
        TODO("Not yet implemented")
    }

    override fun createColor(): Coloring {
        TODO("Not yet implemented")
    }
}