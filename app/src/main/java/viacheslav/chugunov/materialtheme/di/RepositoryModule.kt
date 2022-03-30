package viacheslav.chugunov.materialtheme.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import viacheslav.chugunov.core.repository.ThemeRepository
import viacheslav.chugunov.theme.DefaultColorDescriptionFactory
import viacheslav.chugunov.theme.DefaultRandomThemeDataSource
import viacheslav.chugunov.theme.DefaultThemeRepository

@Module
@InstallIn(ViewModelComponent::class)
class RepositoryModule {

    val themeRepository: ThemeRepository
        @Provides get() {
            val factory = DefaultColorDescriptionFactory()
            val dataSource = DefaultRandomThemeDataSource(factory)
            return DefaultThemeRepository(dataSource)
        }

}