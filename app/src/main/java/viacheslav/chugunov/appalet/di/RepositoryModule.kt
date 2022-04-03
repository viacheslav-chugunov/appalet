package viacheslav.chugunov.appalet.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import viacheslav.chugunov.core.datasource.PreferenceDataSource
import viacheslav.chugunov.core.repository.LanguageRepository
import viacheslav.chugunov.core.repository.ThemeRepository
import viacheslav.chugunov.core.util.Serializer
import viacheslav.chugunov.storage.datastore.LanguagePreferenceDataSource
import viacheslav.chugunov.storage.reposiotry.DefaultLanguageRepository
import viacheslav.chugunov.theme.DefaultColorDescriptionFactory
import viacheslav.chugunov.theme.DefaultRandomThemeDataSource
import viacheslav.chugunov.theme.DefaultThemeRepository

@Module
@InstallIn(ViewModelComponent::class)
class RepositoryModule {

    @Provides
    fun themeRepository(): ThemeRepository {
        val factory = DefaultColorDescriptionFactory()
        val dataSource = DefaultRandomThemeDataSource(factory)
        return DefaultThemeRepository(dataSource)
    }

    @Provides
    fun languageRepository(
        @ApplicationContext context: Context,
        serializer: Serializer
    ): LanguageRepository {
        val preferences = LanguagePreferenceDataSource(context, serializer)
        return DefaultLanguageRepository(preferences)
    }

}