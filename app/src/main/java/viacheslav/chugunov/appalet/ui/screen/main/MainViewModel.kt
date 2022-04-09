package viacheslav.chugunov.appalet.ui.screen.main

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import viacheslav.chugunov.core.model.Language
import viacheslav.chugunov.core.model.PreferredColors
import viacheslav.chugunov.core.model.Theme
import viacheslav.chugunov.core.repository.LanguageRepository
import viacheslav.chugunov.core.repository.PreferredColorsRepository
import viacheslav.chugunov.core.repository.PreferredThemesRepository
import viacheslav.chugunov.core.repository.ThemeRepository
import viacheslav.chugunov.core.util.BaseViewModel
import viacheslav.chugunov.core.util.Screen
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@HiltViewModel
class MainViewModel(
    private val themeRepository: ThemeRepository,
    private val languageRepository: LanguageRepository,
    private val preferredColorsRepository: PreferredColorsRepository,
    private val preferredThemesRepository: PreferredThemesRepository,
    model: MainModel,
    coroutineContext: CoroutineContext
) : BaseViewModel<MainModel>(model, coroutineContext) {

    @Inject constructor(
        themeRepository: ThemeRepository,
        languageRepository: LanguageRepository,
        preferredColorsRepository: PreferredColorsRepository,
        preferredThemesRepository: PreferredThemesRepository,
        coroutineContext: CoroutineContext
    ): this(themeRepository, languageRepository, preferredColorsRepository, preferredThemesRepository,
        MainModel(), coroutineContext)

    init {
        changeTheme()
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
        model = MainModel(theme, language, preferredColors, modeDay, preview, currentScreen,
            closeAppOnBackPress, inFavourites, loading)
        modelMutableFlow.value = model
    }

    fun subscribeOnObservable() {
        viewModelScope.launch(coroutineContext) {
            val languageFlow = languageRepository.getLanguageFlow()
            val preferredColorsFlow = preferredColorsRepository.getColorsFlow()
            languageFlow.combine(preferredColorsFlow) { language, preferredColors ->
                Pair(language, preferredColors)
            }.collect { (language, preferredColors) ->
                val theme = if (model.modeDay)
                    Theme.Light(model.theme, preferredColors)
                else
                    Theme.Dark(model.theme, preferredColors)
                val inFavourites = preferredThemesRepository.isThemeAdded(theme)
                updateModel(language = language, preferredColors = preferredColors, theme = theme,
                    inFavourites = inFavourites)
            }
        }
    }

    fun changeTheme() {
        if (model.loading) return
        updateModel(loading = true)
        viewModelScope.launch(coroutineContext) {
            val isLight = model.modeDay
            val preferredColors = model.preferredColors
            val theme = themeRepository.getRandomTheme(isLight, preferredColors)
            val inFavourites = preferredThemesRepository.isThemeAdded(theme)
            updateModel(theme = theme, inFavourites = inFavourites, loading = false)
        }
    }

    fun changeDayMode() {
        if (model.loading) return
        updateModel(loading = true)
        viewModelScope.launch(coroutineContext) {
            val isLight = model.modeDay
            val theme = model.theme
            val preferredColors = model.preferredColors
            val newTheme = if (isLight) Theme.Dark(theme, preferredColors) else Theme.Light(theme, preferredColors)
            val inFavourites = preferredThemesRepository.isThemeAdded(newTheme)
            updateModel(theme = newTheme, modeDay = !isLight, inFavourites = inFavourites, loading = false)
        }
    }

    fun updateFavourites(addCurrentTheme: Boolean) {
        if (model.loading) return
        updateModel(loading = true)
        viewModelScope.launch(coroutineContext) {
            if (addCurrentTheme) preferredThemesRepository.addTheme(model.theme)
            val inFavourites = preferredThemesRepository.isThemeAdded(model.theme)
            updateModel(inFavourites = inFavourites, loading = false)
        }
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