package viacheslav.chugunov.appalet.ui.screen.settings

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import viacheslav.chugunov.core.model.Language
import viacheslav.chugunov.core.repository.LanguageRepository
import viacheslav.chugunov.core.util.BaseViewModel
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@HiltViewModel
class SettingsViewModel(
    private val languageRepository: LanguageRepository,
    coroutineContext: CoroutineContext = Dispatchers.IO
) : BaseViewModel<Unit>(Unit, coroutineContext) {

    @Inject constructor(languageRepository: LanguageRepository): this(languageRepository, Dispatchers.IO)

    fun changeLanguage(language: Language) {
        viewModelScope.launch(coroutineContext) {
            languageRepository.setLanguage(language)
        }
    }
}