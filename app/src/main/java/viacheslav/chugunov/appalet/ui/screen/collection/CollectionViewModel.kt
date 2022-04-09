package viacheslav.chugunov.appalet.ui.screen.collection

import android.util.Log
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import viacheslav.chugunov.core.model.Theme
import viacheslav.chugunov.core.repository.PreferredThemesRepository
import viacheslav.chugunov.core.util.BaseViewModel
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@HiltViewModel
class CollectionViewModel(
    private val themesRepository: PreferredThemesRepository,
    model: CollectionModel,
    coroutineContext: CoroutineContext = Dispatchers.IO
) : BaseViewModel<CollectionModel>(model, coroutineContext) {

    @Inject constructor(
        themesRepository: PreferredThemesRepository
    ): this(themesRepository, CollectionModel())

    init {
        subscribeOnObservable()
    }

    fun updateModel(
        themes: List<Theme> = model.themes
    ) {
        model = CollectionModel(themes)
        modelMutableFlow.value = model
    }

    fun subscribeOnObservable() {
        viewModelScope.launch(coroutineContext) {
            themesRepository.getThemesFlow().collect { themes ->
                updateModel(themes = themes)
            }
        }
    }

    suspend fun removeTheme(theme: Theme) {
        themesRepository.removeTheme(theme)
    }

}