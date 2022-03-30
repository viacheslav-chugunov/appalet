package viacheslav.chugunov.materialtheme.ui.screen.main

import android.util.Log
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import viacheslav.chugunov.core.model.Theme
import viacheslav.chugunov.core.repository.ThemeRepository
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val themeRepository: ThemeRepository
) : ViewModel() {
    private var model = MainModel()
    private val _modelFlow = MutableStateFlow(model)
    val modelFlow = _modelFlow.asStateFlow()

    init { changeTheme() }

    fun updateModel(
        theme: Theme = model.theme,
        modeDay: Boolean = model.modeDay
    ) {
        model = MainModel(theme, modeDay)
        _modelFlow.value = model
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
        Log.d("MyTest", theme.toString())
    }
}