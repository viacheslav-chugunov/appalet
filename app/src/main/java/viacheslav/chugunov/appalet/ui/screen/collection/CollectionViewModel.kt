package viacheslav.chugunov.appalet.ui.screen.collection

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import viacheslav.chugunov.core.model.Theme
import viacheslav.chugunov.core.usecase.GetCollectedThemesUseCase
import viacheslav.chugunov.core.usecase.RemoveCollectedThemeUseCase
import viacheslav.chugunov.core.util.BaseViewModel
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@HiltViewModel
class CollectionViewModel(
    private val getCollectedThemesUseCase: GetCollectedThemesUseCase,
    private val removeCollectedThemeUseCase: RemoveCollectedThemeUseCase,
    model: CollectionModel,
    coroutineContext: CoroutineContext = Dispatchers.IO
) : BaseViewModel<CollectionModel>(model, coroutineContext) {

    @Inject
    constructor(
        getCollectedThemesUseCase: GetCollectedThemesUseCase,
        removeCollectedThemeUseCase: RemoveCollectedThemeUseCase
    ) : this(getCollectedThemesUseCase, removeCollectedThemeUseCase, CollectionModel())

    init {
        subscribeOnObservable()
    }

    fun updateModel(themes: List<Theme> = model.themes) {
        model = CollectionModel(themes)
        modelMutableFlow.value = model
    }

    fun subscribeOnObservable(): Job =
        viewModelScope.launch(coroutineContext) {
            getCollectedThemesUseCase.invoke().collect { themes ->
                updateModel(themes = themes)
            }
        }

    suspend fun removeTheme(theme: Theme) =
        removeCollectedThemeUseCase.invoke(theme)
}