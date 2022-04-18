package viacheslav.chugunov.appalet.ui.screen.collection

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
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

    fun updateModel(
        themes: List<Theme> = model.themes,
        visibleItemsCount: Int = model.visibleItemsCount
    ) {
        model = CollectionModel(themes, visibleItemsCount)
        modelMutableFlow.value = model
    }

    fun subscribeOnObservable(): Job =
        viewModelScope.launch(coroutineContext) {
            getCollectedThemesUseCase.invoke().collect { themes ->
                updateModel(themes = themes)
                if (themes.isNotEmpty() && model.visibleItemsCount == 0)
                    showInitialAnimation()
            }
        }

    suspend fun removeTheme(theme: Theme): Theme =
        removeCollectedThemeUseCase.invoke(theme)

    fun showInitialAnimation(delay: Long = 200L): Job =
        viewModelScope.launch(coroutineContext) {
            for (i in 1..model.themes.size) {
                delay(delay)
                if (i < model.themes.size) {
                    updateModel(visibleItemsCount = i)
                }
            }
            updateModel(visibleItemsCount = Int.MAX_VALUE)
        }

}