package viacheslav.chugunov.appalet.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import viacheslav.chugunov.core.repository.LanguageRepository
import viacheslav.chugunov.core.repository.PreferredColorsRepository
import viacheslav.chugunov.core.repository.PreferredThemesRepository
import viacheslav.chugunov.core.repository.ThemeRepository
import viacheslav.chugunov.core.usecase.*

@Module
@InstallIn(ViewModelComponent::class)
class UseCaseModule {

    @Provides
    fun addThemeToCollectionUseCase(
        preferredThemesRepository: PreferredThemesRepository
    ): AddThemeToCollectionUseCase =
        AddThemeToCollectionUseCase.Default(preferredThemesRepository)

    @Provides
    fun getRandomThemeUseCase(
        preferredColorsRepository: PreferredColorsRepository,
        themeRepository: ThemeRepository
    ): GetRandomThemeUseCase =
        GetRandomThemeUseCase.Default(preferredColorsRepository, themeRepository)

    @Provides
    fun getSettingsUseCase(
        languageRepository: LanguageRepository,
        preferredColorsRepository: PreferredColorsRepository
    ): GetSettingsUseCase =
        GetSettingsUseCase.Default(languageRepository, preferredColorsRepository)

    @Provides
    fun isThemeInCollectionUseCase(
        preferredThemesRepository: PreferredThemesRepository
    ): IsThemeInCollectionUseCase =
        IsThemeInCollectionUseCase.Default(preferredThemesRepository)

    @Provides
    fun getCollectedThemesUseCase(
        preferredThemesRepository: PreferredThemesRepository
    ): GetCollectedThemesUseCase =
        GetCollectedThemesUseCase.Default(preferredThemesRepository)

    @Provides
    fun removeCollectedThemeUseCase(
        preferredThemesRepository: PreferredThemesRepository
    ): RemoveCollectedThemeUseCase =
        RemoveCollectedThemeUseCase.Default(preferredThemesRepository)

    @Provides
    fun changeLanguageUseCase(
        languageRepository: LanguageRepository
    ): ChangeLanguageUseCase =
        ChangeLanguageUseCase.Default(languageRepository)

    @Provides
    fun changeBackgroundColorAsRandomUseCase(
        themeRepository: ThemeRepository,
        preferredColorsRepository: PreferredColorsRepository
    ): ChangeBackgroundColorAsRandomUseCase =
        ChangeBackgroundColorAsRandomUseCase.Default(themeRepository, preferredColorsRepository)

    @Provides
    fun resetBackgroundColorUseCase(
        preferredColorsRepository: PreferredColorsRepository
    ): ResetBackgroundColorUseCase =
        ResetBackgroundColorUseCase.Default(preferredColorsRepository)
}