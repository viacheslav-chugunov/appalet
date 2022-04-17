package viacheslav.chugunov.tests.util.factory

import viacheslav.chugunov.core.datasource.PreferenceDataSource
import viacheslav.chugunov.core.model.Language
import viacheslav.chugunov.core.model.Theme
import viacheslav.chugunov.core.usecase.*
import viacheslav.chugunov.storage.datastore.model.DataStorePreferredColors
import viacheslav.chugunov.storage.room.ThemeDao

class UseCaseFactory(private val repositoryFactory: RepositoryFactory) {

    fun newAddThemeToCollectionUseCase(
        dao: ThemeDao
    ): AddThemeToCollectionUseCase = AddThemeToCollectionUseCase.Default(
        preferredThemesRepository = repositoryFactory.newPreferredThemes(dao)
    )

    fun newAddThemeToCollectionUseCase(
        themes: List<Theme>
    ): AddThemeToCollectionUseCase = AddThemeToCollectionUseCase.Default(
        preferredThemesRepository = repositoryFactory.newPreferredThemes(themes)
    )

    fun newChangeBackgroundColorAsRandom(
        currentColors: DataStorePreferredColors?,
        defaultColors: DataStorePreferredColors
    ): ChangeBackgroundColorAsRandomUseCase = ChangeBackgroundColorAsRandomUseCase.Default(
        themeRepository = repositoryFactory.newTheme(),
        preferredColorsRepository = repositoryFactory.newPreferredColors(currentColors, defaultColors)
    )

    fun newChangeBackgroundColorAsRandom(
        preference: PreferenceDataSource<DataStorePreferredColors>,
        defaultColors: DataStorePreferredColors
    ): ChangeBackgroundColorAsRandomUseCase = ChangeBackgroundColorAsRandomUseCase.Default(
        themeRepository = repositoryFactory.newTheme(),
        preferredColorsRepository = repositoryFactory.newPreferredColors(preference, defaultColors)
    )

    fun newChangeLanguage(
        currentLanguage: Language?,
        defaultLanguage: Language
    ): ChangeLanguageUseCase = ChangeLanguageUseCase.Default(
        languageRepository = repositoryFactory.newLanguage(currentLanguage, defaultLanguage)
    )

    fun newChangeLanguage(
        preferences: PreferenceDataSource<Language>,
        defaultLanguage: Language
    ): ChangeLanguageUseCase = ChangeLanguageUseCase.Default(
        languageRepository = repositoryFactory.newLanguage(preferences, defaultLanguage)
    )

    fun newGetCollectedThemes(
        themes: List<Theme>
    ): GetCollectedThemesUseCase = GetCollectedThemesUseCase.Default(
        preferredThemesRepository = repositoryFactory.newPreferredThemes(themes)
    )

    fun newGetCollectedThemes(
        dao: ThemeDao
    ): GetCollectedThemesUseCase = GetCollectedThemesUseCase.Default(
        preferredThemesRepository = repositoryFactory.newPreferredThemes(dao)
    )

    fun newGetRandomThemeUseCase(
        currentColors: DataStorePreferredColors?,
        defaultColors: DataStorePreferredColors
    ): GetRandomThemeUseCase = GetRandomThemeUseCase.Default(
        preferredColorsRepository = repositoryFactory.newPreferredColors(currentColors, defaultColors),
        themeRepository = repositoryFactory.newTheme()
    )

    fun newGetRandomThemeUseCase(
        preference: PreferenceDataSource<DataStorePreferredColors>,
        defaultColors: DataStorePreferredColors
    ): GetRandomThemeUseCase = GetRandomThemeUseCase.Default(
        preferredColorsRepository = repositoryFactory.newPreferredColors(preference, defaultColors),
        themeRepository = repositoryFactory.newTheme()
    )

    fun newGetSettingsUseCase(
        currentLanguage: Language?,
        defaultLanguage: Language,
        currentColors: DataStorePreferredColors?,
        defaultColors: DataStorePreferredColors
    ): GetSettingsUseCase = GetSettingsUseCase.Default(
        languageRepository = repositoryFactory.newLanguage(currentLanguage, defaultLanguage),
        preferredColorsRepository = repositoryFactory.newPreferredColors(currentColors, defaultColors)
    )

    fun newGetSettingsUseCase(
        languagePreference: PreferenceDataSource<Language>,
        defaultLanguage: Language,
        colorsPreference: PreferenceDataSource<DataStorePreferredColors>,
        defaultColors: DataStorePreferredColors
    ): GetSettingsUseCase = GetSettingsUseCase.Default(
        languageRepository = repositoryFactory.newLanguage(languagePreference, defaultLanguage),
        preferredColorsRepository = repositoryFactory.newPreferredColors(colorsPreference, defaultColors)
    )

    fun newIsThemeInCollectionUseCase(
        themes: List<Theme>
    ): IsThemeInCollectionUseCase = IsThemeInCollectionUseCase.Default(
        preferredThemesRepository = repositoryFactory.newPreferredThemes(themes)
    )

    fun newIsThemeInCollectionUseCase(
        dao: ThemeDao
    ): IsThemeInCollectionUseCase = IsThemeInCollectionUseCase.Default(
        preferredThemesRepository = repositoryFactory.newPreferredThemes(dao)
    )

    fun newRemoveCollectedThemeUseCase(
        themes: List<Theme>
    ): RemoveCollectedThemeUseCase = RemoveCollectedThemeUseCase.Default(
        preferredThemesRepository = repositoryFactory.newPreferredThemes(themes)
    )

    fun newRemoveCollectedThemeUseCase(
        dao: ThemeDao
    ): RemoveCollectedThemeUseCase = RemoveCollectedThemeUseCase.Default(
        preferredThemesRepository = repositoryFactory.newPreferredThemes(dao)
    )

    fun newResetBackgroundColorUseCase(
        currentColors: DataStorePreferredColors?,
        defaultColors: DataStorePreferredColors
    ): ResetBackgroundColorUseCase = ResetBackgroundColorUseCase.Default(
        preferredColorsRepository = repositoryFactory.newPreferredColors(currentColors, defaultColors)
    )

    fun newResetBackgroundColorUseCase(
        preference: PreferenceDataSource<DataStorePreferredColors>,
        defaultColors: DataStorePreferredColors
    ): ResetBackgroundColorUseCase = ResetBackgroundColorUseCase.Default(
        preferredColorsRepository = repositoryFactory.newPreferredColors(preference, defaultColors)
    )
}