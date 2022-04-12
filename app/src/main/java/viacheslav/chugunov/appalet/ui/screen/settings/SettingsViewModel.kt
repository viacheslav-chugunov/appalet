package viacheslav.chugunov.appalet.ui.screen.settings

import android.util.Log
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.combine
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
        preferredColors: PreferredColors = model.preferredColors,
        language: Language = model.language
    ) {
        model = SettingsModel(preferredColors, language)
        modelMutableFlow.value = model
    }

    init {
        viewModelScope.launch {
            val colorsFlow = preferredColorsRepository.getColorsFlow()
            val languageFlow = languageRepository.getLanguageFlow()
            colorsFlow.combine(languageFlow) { colors, language ->
                Pair(colors, language)
            }.collect { (colors, language) ->
                updateModel(preferredColors = colors, language = language)
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