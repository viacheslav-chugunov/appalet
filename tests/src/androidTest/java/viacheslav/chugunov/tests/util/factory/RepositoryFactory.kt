package viacheslav.chugunov.tests.util.factory

import viacheslav.chugunov.core.datasource.PreferenceDataSource
import viacheslav.chugunov.core.model.Language
import viacheslav.chugunov.core.model.Theme
import viacheslav.chugunov.core.repository.LanguageRepository
import viacheslav.chugunov.core.repository.PreferredColorsRepository
import viacheslav.chugunov.core.repository.PreferredThemesRepository
import viacheslav.chugunov.core.repository.ThemeRepository
import viacheslav.chugunov.storage.datastore.model.DataStorePreferredColors
import viacheslav.chugunov.storage.reposiotry.DefaultLanguageRepository
import viacheslav.chugunov.storage.reposiotry.DefaultPreferredColorsRepository
import viacheslav.chugunov.storage.reposiotry.DefaultPreferredThemesRepository
import viacheslav.chugunov.storage.room.ThemeDao
import viacheslav.chugunov.storage.room.ThemeDatabaseDataSource
import viacheslav.chugunov.tests.util.TestThemeDao
import viacheslav.chugunov.tests.util.datasource.TestDataStorePreferredColorsPreferenceDataSource
import viacheslav.chugunov.tests.util.datasource.TestLanguagePreferenceDataSource
import viacheslav.chugunov.theme.DefaultColorDescriptionFactory
import viacheslav.chugunov.theme.DefaultRandomThemeDataSource
import viacheslav.chugunov.theme.DefaultThemeRepository

class RepositoryFactory {

    fun newLanguage(
        currentLanguage: Language?,
        defaultLanguage: Language
    ): LanguageRepository = newLanguage(
        preferences = TestLanguagePreferenceDataSource(currentLanguage),
        defaultLanguage = defaultLanguage
    )

    fun newLanguage(
        preferences: PreferenceDataSource<Language>,
        defaultLanguage: Language
    ): LanguageRepository = DefaultLanguageRepository(
        preferences = preferences,
        defaultLanguage = defaultLanguage
    )

    fun newPreferredColors(
        currentColors: DataStorePreferredColors?,
        defaultColors: DataStorePreferredColors
    ): PreferredColorsRepository = newPreferredColors(
        preference = TestDataStorePreferredColorsPreferenceDataSource(currentColors),
        defaultColors = defaultColors
    )

    fun newPreferredColors(
        preference: PreferenceDataSource<DataStorePreferredColors>,
        defaultColors: DataStorePreferredColors
    ): PreferredColorsRepository = DefaultPreferredColorsRepository(
        preference = preference,
        defaultColors = defaultColors
    )

    fun newPreferredThemes(themes: List<Theme>): PreferredThemesRepository =
        newPreferredThemes(TestThemeDao(themes))

    fun newPreferredThemes(dao: ThemeDao): PreferredThemesRepository =
        DefaultPreferredThemesRepository(ThemeDatabaseDataSource(dao))

    fun newTheme(): ThemeRepository =
        DefaultThemeRepository(DefaultRandomThemeDataSource(DefaultColorDescriptionFactory()))
}