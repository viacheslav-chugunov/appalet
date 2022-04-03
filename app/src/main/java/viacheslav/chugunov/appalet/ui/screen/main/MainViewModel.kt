package viacheslav.chugunov.appalet.ui.screen.main

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import viacheslav.chugunov.core.model.Language
import viacheslav.chugunov.core.model.Theme
import viacheslav.chugunov.core.repository.LanguageRepository
import viacheslav.chugunov.core.repository.ThemeRepository
import viacheslav.chugunov.core.util.BaseViewModel
import viacheslav.chugunov.core.util.Screen
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@HiltViewModel
class MainViewModel(
    private val themeRepository: ThemeRepository,
    languageRepository: LanguageRepository,
    model: MainModel,
    coroutineContext: CoroutineContext = Dispatchers.IO
) : BaseViewModel<MainModel>(model, coroutineContext) {

    @Inject constructor(
        themeRepository: ThemeRepository,
        languageRepository: LanguageRepository,
    ): this(themeRepository, languageRepository, MainModel())

    init {
        changeTheme()
        viewModelScope.launch(coroutineContext) {
            languageRepository.getLanguageFlow().collect { language ->
                updateModel(language = language)
            }
        }
    }

    fun updateModel(
        theme: Theme = model.theme,
        language: Language = model.language,
        modeDay: Boolean = model.modeDay,
        preview: Screen.Preview = model.preview,
        currentScreen: Screen = model.currentScreen
    ) {
        model = MainModel(theme, language, modeDay, preview, currentScreen)
        modelMutableFlow.value = model
    }

    fun changeTheme() {
        val isLight = model.modeDay
        val theme = themeRepository.getRandom(isLight)
        updateModel(theme = theme)
    }

    fun changeDayMode() {
        val isLight = model.modeDay
        val theme = if (isLight) Theme.Dark(model.theme) else Theme.Light(model.theme)
        updateModel(theme = theme, modeDay = !isLight)
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