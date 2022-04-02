package viacheslav.chugunov.appalet.ui.screen.main

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import viacheslav.chugunov.core.model.Theme
import viacheslav.chugunov.core.repository.ThemeRepository
import viacheslav.chugunov.core.util.BaseViewModel
import viacheslav.chugunov.core.util.Screen
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@HiltViewModel
class MainViewModel(
    private val themeRepository: ThemeRepository,
    model: MainModel,
    coroutineContext: CoroutineContext = Dispatchers.IO
) : BaseViewModel<MainModel>(model, coroutineContext) {

    @Inject constructor(themeRepository: ThemeRepository): this(themeRepository, MainModel())

    init { changeTheme() }

    fun updateModel(
        theme: Theme = model.theme,
        modeDay: Boolean = model.modeDay,
        preview: Screen.Preview = model.preview,
        currentScreen: Screen = model.currentScreen
    ) {
        model = MainModel(theme, modeDay, preview, currentScreen)
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