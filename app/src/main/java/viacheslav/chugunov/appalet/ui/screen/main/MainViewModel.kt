package viacheslav.chugunov.appalet.ui.screen.main

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import viacheslav.chugunov.core.model.Language
import viacheslav.chugunov.core.model.PreferredColors
import viacheslav.chugunov.core.model.Theme
import viacheslav.chugunov.core.usecase.AddThemeToCollectionUseCase
import viacheslav.chugunov.core.usecase.GetRandomThemeUseCase
import viacheslav.chugunov.core.usecase.GetSettingsUseCase
import viacheslav.chugunov.core.usecase.IsThemeInCollectionUseCase
import viacheslav.chugunov.core.util.BaseViewModel
import viacheslav.chugunov.core.util.Screen
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@HiltViewModel
class MainViewModel(
    private val getRandomThemeUseCase: GetRandomThemeUseCase,
    private val getSettingsUseCase: GetSettingsUseCase,
    private val isThemeInCollectionUseCase: IsThemeInCollectionUseCase,
    private val addThemeToCollectionUseCase: AddThemeToCollectionUseCase,
    model: MainModel,
    coroutineContext: CoroutineContext
) : BaseViewModel<MainModel>(model, coroutineContext) {

    @Inject
    constructor(
        getRandomThemeUseCase: GetRandomThemeUseCase,
        getSettingsUseCase: GetSettingsUseCase,
        isThemeInCollectionUseCase: IsThemeInCollectionUseCase,
        addThemeToCollectionUseCase: AddThemeToCollectionUseCase,
        coroutineContext: CoroutineContext
    ) : this(
        getRandomThemeUseCase,
        getSettingsUseCase,
        isThemeInCollectionUseCase,
        addThemeToCollectionUseCase,
        MainModel(),
        coroutineContext
    )

    init {
        subscribeOnObservable()
    }

    fun updateModel(
        theme: Theme = model.theme,
        language: Language = model.language,
        preferredColors: PreferredColors = model.preferredColors,
        modeDay: Boolean = model.modeDay,
        preview: Screen.Preview = model.preview,
        currentScreen: Screen = model.currentScreen,
        closeAppOnBackPress: Boolean = model.closeAppOnBackPress,
        inFavourites: Boolean = model.inFavourites,
        loading: Boolean = model.loading
    ) {
        model = MainModel(
            theme,
            language,
            preferredColors,
            modeDay,
            preview,
            currentScreen,
            closeAppOnBackPress,
            inFavourites,
            loading
        )
        modelMutableFlow.value = model
    }

    fun subscribeOnObservable(): Job =
        viewModelScope.launch(coroutineContext) {
            getSettingsUseCase.invoke().collect { settingsConfig ->
                val language = settingsConfig.language
                val preferredColors = settingsConfig.preferredColors
                val modeLight = model.modeDay
                val currentTheme = model.theme
                val newTheme = Theme.Adaptive(modeLight, currentTheme, preferredColors)
                val inFavourites = isThemeInCollectionUseCase.invoke(newTheme)
                val isLoading = model.loading
                if (isLoading) changeThemeAsRandom()
                updateModel(
                    language = language,
                    preferredColors = preferredColors,
                    theme = newTheme,
                    inFavourites = inFavourites,
                    loading = false
                )
            }
        }

    fun changeThemeAsRandom(): Job =
        viewModelScope.launch(coroutineContext) {
            val isLight = model.modeDay
            val preferredColors = model.preferredColors
            val theme = getRandomThemeUseCase.invoke(isLight, preferredColors)
            val inFavourites = isThemeInCollectionUseCase.invoke(theme)
            updateModel(theme = theme, inFavourites = inFavourites)
        }

    fun changeTheme(theme: Theme): Job =
        viewModelScope.launch(coroutineContext) {
            val inFavourites = isThemeInCollectionUseCase.invoke(theme)
            updateModel(theme = theme, inFavourites = inFavourites)
        }

    fun changeDayMode(): Job =
        viewModelScope.launch(coroutineContext) {
            val isLight = model.modeDay
            val theme = model.theme
            val preferredColors = model.preferredColors
            val newTheme = Theme.Adaptive(!isLight, theme, preferredColors)
            val inFavourites = isThemeInCollectionUseCase.invoke(newTheme)
            updateModel(
                theme = newTheme,
                modeDay = !isLight,
                inFavourites = inFavourites
            )
        }

    fun updateFavourites(): Job =
        viewModelScope.launch(coroutineContext) {
            val theme = model.theme
            val inFavourites = isThemeInCollectionUseCase.invoke(theme)
            updateModel(inFavourites = inFavourites)
        }

    fun addThemeToCollection(): Job =
        viewModelScope.launch(coroutineContext) {
            val theme = model.theme
            addThemeToCollectionUseCase.invoke(theme)
            val inFavourites = isThemeInCollectionUseCase.invoke(theme)
            updateModel(inFavourites = inFavourites)
        }

    fun updatePreviewToPrevious() {
        val preview = model.preview.previous
        updateModel(preview = preview)
    }

    fun updatePreviewToNext() {
        val preview = model.preview.next
        updateModel(preview = preview)
    }
}