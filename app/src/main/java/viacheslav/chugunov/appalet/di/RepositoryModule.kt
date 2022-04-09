package viacheslav.chugunov.appalet.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import viacheslav.chugunov.core.repository.LanguageRepository
import viacheslav.chugunov.core.repository.PreferredColorsRepository
import viacheslav.chugunov.core.repository.PreferredThemesRepository
import viacheslav.chugunov.core.repository.ThemeRepository
import viacheslav.chugunov.core.util.Serializer
import viacheslav.chugunov.storage.datastore.LanguagePreferenceDataSource
import viacheslav.chugunov.storage.datastore.PreferredColorsPreferenceDataSource
import viacheslav.chugunov.storage.reposiotry.DefaultLanguageRepository
import viacheslav.chugunov.storage.reposiotry.DefaultPreferredColorsRepository
import viacheslav.chugunov.storage.reposiotry.DefaultPreferredThemesRepository
import viacheslav.chugunov.storage.room.AppRoomDatabase
import viacheslav.chugunov.storage.room.ThemeDatabaseDataSource
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
        @ApplicationContext context: Context
    ): LanguageRepository {
        val preference = LanguagePreferenceDataSource(context, Serializer.Default())
        return DefaultLanguageRepository(preference)
    }

    @Provides
    fun preferredColorsRepository(
        @ApplicationContext context: Context
    ): PreferredColorsRepository {
        val preference = PreferredColorsPreferenceDataSource(context, Serializer.Default())
        return DefaultPreferredColorsRepository(preference)
    }

    @Provides
    fun preferredThemesRepository(
        @ApplicationContext context: Context
    ): PreferredThemesRepository {
        val database = AppRoomDatabase.getInstance(context)
        val dao = database.themeDao
        val dataSource = ThemeDatabaseDataSource(dao)
        return DefaultPreferredThemesRepository(dataSource)
    }

}