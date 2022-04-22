package viacheslav.chugunov.appalet.ui.screen.preview.input

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import viacheslav.chugunov.core.util.BaseViewModel
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@HiltViewModel
class InputPreviewViewModel @Inject constructor(
    model: InputPreviewModel,
    coroutineContext: CoroutineContext
) : BaseViewModel<InputPreviewModel>(model, coroutineContext) {

    fun updateModel(
        input1: String = model.input1,
        input2: String = model.input2,
        uiEnabled: Boolean = model.uiEnabled
    ) {
        model = InputPreviewModel(input1, input2, uiEnabled)
        modelMutableFlow.value = model
    }

    fun showCleanUpAnimation(delay: Long = 200L) {
        viewModelScope.launch(coroutineContext) {
            updateModel(uiEnabled = false)
            while (model.input1.isNotBlank() || model.input2.isNotBlank()) {
                var newInput1 = model.input1
                var newInput2 = model.input2
                if (newInput1.isNotBlank()) newInput1 = newInput1.substring(0, newInput1.length - 1)
                if (newInput2.isNotBlank()) newInput2 = newInput2.substring(0, newInput2.length - 1)
                updateModel(input1 = newInput1, input2 = newInput2)
                delay(delay)
            }
            updateModel(uiEnabled = true)
        }
    }
}