package viacheslav.chugunov.materialtheme.ui.screen.main

import android.util.Log
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import viacheslav.chugunov.core.model.domain.Theme
import viacheslav.chugunov.core.model.ui.MainState
import viacheslav.chugunov.core.repository.ThemeRepository
import viacheslav.chugunov.core.util.Screen
import javax.inject.Inject

@HiltViewModel
class MainViewModel(
    private val themeRepository: ThemeRepository,
    state: MainState
) : ViewModel() {
    private var model = state
    private val modelMutableFlow = MutableStateFlow(model)
    val modelFlow = modelMutableFlow.asStateFlow()

    @Inject constructor(
        themeRepository: ThemeRepository
    ): this(themeRepository, MainState.Empty)

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