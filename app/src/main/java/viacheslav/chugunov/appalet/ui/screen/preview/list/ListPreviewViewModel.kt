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
class ListPreviewViewModel(
    model: ListPreviewModel,
    coroutineContext: CoroutineContext = Dispatchers.IO
) : BaseViewModel<ListPreviewModel>(model, coroutineContext) {

    @Inject constructor(): this(ListPreviewModel())

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

    fun showRefreshItemsAnimation(
        delay: Long = 50L,
        onItemRefreshed: suspend (Int) -> Unit
    ) {
        viewModelScope.launch(coroutineContext) {
            updateModel(uiEnabled = false)
            for (i in 0 until ListPreviewModel.ITEMS_LIMIT) {
                if (i !in model.items) {
                    val newItems = (model.items + i).sorted()
                    updateModel(items = newItems)
                    onItemRefreshed(i)
                }
                delay(delay)
            }
            updateModel(uiEnabled = true)
            onItemRefreshed(0)
        }
    }


}