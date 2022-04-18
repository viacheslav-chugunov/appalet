package viacheslav.chugunov.appalet.ui.screen.colors

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import viacheslav.chugunov.core.util.BaseViewModel
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@HiltViewModel
class ColorsViewModel(
    model: ColorsModel,
    coroutineContext: CoroutineContext
) : BaseViewModel<ColorsModel>(model, coroutineContext) {

    @Inject
    constructor(coroutineContext: CoroutineContext) : this(ColorsModel(), coroutineContext)

    init {
        showInitialAnimation()
    }

    fun updateModel(visibleItemsCount: Int = model.visibleItemsCount) {
        model = ColorsModel(visibleItemsCount)
        modelMutableFlow.value = model
    }

    fun showInitialAnimation(delay: Long = 200L) {
        viewModelScope.launch(coroutineContext) {
            for (i in 1..7) {
                delay(delay)
                updateModel(visibleItemsCount = i)
            }
        }
    }

}