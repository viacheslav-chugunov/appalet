package viacheslav.chugunov.appalet.ui.screen.preview.list

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import viacheslav.chugunov.core.util.BaseViewModel
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@HiltViewModel
class ListPreviewViewModel @Inject constructor(
    model: ListPreviewModel,
    coroutineContext: CoroutineContext
) : BaseViewModel<ListPreviewModel>(model, coroutineContext) {

    fun updateModel(
        items: List<Int> = model.items,
        uiEnabled: Boolean = model.uiEnabled,
    ) {
        model = ListPreviewModel(items, uiEnabled)
        modelMutableFlow.value = model
    }

    fun removeItem(item: Int) {
        val newItems = model.items - item
        updateModel(items = newItems)
    }

    fun showRefreshItemsAnimation(delay: Long = 75L) {
        viewModelScope.launch(coroutineContext) {
            updateModel(uiEnabled = false)
            updateModel(items = emptyList())
            delay(delay)
            for (item in ListPreviewModel.ITEMS_LIMIT -1 downTo 0) {
                val newItems = model.items.toMutableList().apply { add(0, item) }
                updateModel(items = newItems)
                delay(delay)
            }
            updateModel(uiEnabled = true)
        }
    }


}