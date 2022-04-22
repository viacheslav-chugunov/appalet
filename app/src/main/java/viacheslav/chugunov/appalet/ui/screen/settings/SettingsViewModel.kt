package viacheslav.chugunov.appalet.ui.screen.settings

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import viacheslav.chugunov.core.model.Language
import viacheslav.chugunov.core.model.PreferredColors
import viacheslav.chugunov.core.usecase.ChangeBackgroundColorAsRandomUseCase
import viacheslav.chugunov.core.usecase.ChangeLanguageUseCase
import viacheslav.chugunov.core.usecase.GetSettingsUseCase
import viacheslav.chugunov.core.usecase.ResetBackgroundColorUseCase
import viacheslav.chugunov.core.util.BaseViewModel
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val getSettingsUseCase: GetSettingsUseCase,
    private val changeLanguageUseCase: ChangeLanguageUseCase,
    private val changeBackgroundColorAsRandomUseCase: ChangeBackgroundColorAsRandomUseCase,
    private val resetBackgroundColorUseCase: ResetBackgroundColorUseCase,
    model: SettingsModel,
    coroutineContext: CoroutineContext
) : BaseViewModel<SettingsModel>(model, coroutineContext) {

    fun updateModel(
        preferredColors: PreferredColors = model.preferredColors,
        language: Language = model.language
    ) {
        model = SettingsModel(preferredColors, language)
        modelMutableFlow.value = model
    }

    init {
        subscribeOnObservable()
    }

    fun subscribeOnObservable(): Job =
        viewModelScope.launch {
            getSettingsUseCase.invoke().collect { settingsConfig ->
                val language = settingsConfig.language
                val preferredColors = settingsConfig.preferredColors
                updateModel(preferredColors = preferredColors, language = language)
            }
        }

    fun changeLanguage(language: Language): Job =
        viewModelScope.launch(coroutineContext) {
            changeLanguageUseCase.invoke(language)
        }

    fun changeLightBackgroundColor(): Job =
        viewModelScope.launch(coroutineContext) {
            changeBackgroundColorAsRandomUseCase.invoke(isLight = true)
        }

    fun resetLightBackgroundColor(): Job =
        viewModelScope.launch(coroutineContext) {
            resetBackgroundColorUseCase.invoke(isLight = true)
        }

    fun changeDarkBackgroundColor(): Job =
        viewModelScope.launch(coroutineContext) {
            changeBackgroundColorAsRandomUseCase.invoke(isLight = false)
        }

    fun resetDarkBackgroundColor(): Job =
        viewModelScope.launch(coroutineContext) {
            resetBackgroundColorUseCase.invoke(isLight = false)
        }
}