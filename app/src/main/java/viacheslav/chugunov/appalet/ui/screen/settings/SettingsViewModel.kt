package viacheslav.chugunov.appalet.ui.screen.settings

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import viacheslav.chugunov.core.model.Language
import viacheslav.chugunov.core.model.PreferredColors
import viacheslav.chugunov.core.repository.LanguageRepository
import viacheslav.chugunov.core.repository.PreferredColorsRepository
import viacheslav.chugunov.core.repository.ThemeRepository
import viacheslav.chugunov.core.util.BaseViewModel
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@HiltViewModel
class SettingsViewModel(
    private val languageRepository: LanguageRepository,
    private val preferredColorsRepository: PreferredColorsRepository,
    private val themeRepository: ThemeRepository,
    model: SettingsModel,
    coroutineContext: CoroutineContext
) : BaseViewModel<SettingsModel>(model, coroutineContext) {

    @Inject constructor(
        languageRepository: LanguageRepository,
        preferredColorsRepository: PreferredColorsRepository,
        themeRepository: ThemeRepository,
        coroutineContext: CoroutineContext
    ): this(languageRepository, preferredColorsRepository, themeRepository, SettingsModel(), coroutineContext)

    fun updateModel(
        preferredColors: PreferredColors = model.preferredColors
    ) {
        model = SettingsModel(preferredColors)
        modelMutableFlow.value = model
    }

    init {
        viewModelScope.launch {
            preferredColorsRepository.getColorsFlow().collect { colors ->
                updateModel(preferredColors = colors)
            }
        }
    }

    fun changeLanguage(language: Language) {
        viewModelScope.launch(coroutineContext) {
            languageRepository.setLanguage(language)
        }
    }

    fun changeLightBackgroundColor() {
        viewModelScope.launch(coroutineContext) {
            val color = themeRepository.getRandomColor()
            preferredColorsRepository.setLightBackground(color)
        }
    }

    fun resetLightBackgroundColor() {
        viewModelScope.launch(coroutineContext) {
            preferredColorsRepository.resetLightBackground()
        }
    }

    fun changeDarkBackgroundColor() {
        viewModelScope.launch(coroutineContext) {
            val color = themeRepository.getRandomColor()
            preferredColorsRepository.setDarkBackground(color)
        }
    }

    fun resetDarkBackgroundColor() {
        viewModelScope.launch(coroutineContext) {
            preferredColorsRepository.resetDarkBackground()
        }
    }
}