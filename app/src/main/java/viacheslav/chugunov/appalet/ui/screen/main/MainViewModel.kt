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
    model: MainModel,
    coroutineContext: CoroutineContext
) : BaseViewModel<MainModel>(model, coroutineContext) {

    @Inject constructor(
        themeRepository: ThemeRepository,
        languageRepository: LanguageRepository,
        preferredColorsRepository: PreferredColorsRepository,
        coroutineContext: CoroutineContext
    ): this(themeRepository, languageRepository, preferredColorsRepository, MainModel(), coroutineContext)

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
    ) {
        model = MainModel(theme, language, preferredColors, modeDay, preview, currentScreen, closeAppOnBackPress)
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
                updateModel(language = language, preferredColors = preferredColors, theme = theme)
            }
        }
    }

    fun changeTheme() {
        val isLight = model.modeDay
        val preferredColors = model.preferredColors
        val theme = themeRepository.getRandomTheme(isLight, preferredColors)
        updateModel(theme = theme)
    }

    fun changeDayMode() {
        val isLight = model.modeDay
        val theme = model.theme
        val preferredColors = model.preferredColors
        val newTheme = if (isLight) Theme.Dark(theme, preferredColors) else Theme.Light(theme, preferredColors)
        updateModel(theme = newTheme, modeDay = !isLight)
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