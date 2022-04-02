package viacheslav.chugunov.materialtheme.ui.screen.preview.input

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import viacheslav.chugunov.core.util.BaseViewModel
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@HiltViewModel
class InputPreviewViewModel(
    model: InputPreviewModel,
    coroutineContext: CoroutineContext = Dispatchers.IO
) : BaseViewModel<InputPreviewModel>(model, coroutineContext) {

    @Inject constructor(): this(InputPreviewModel())

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
                if (model.input1.isNotBlank()) newInput1 = model.input1.substring(0, model.input1.length - 1)
                if (model.input2.isNotBlank()) newInput2 = model.input2.substring(0, model.input2.length - 1)
                updateModel(input1 = newInput1, input2 = newInput2)
                delay(delay)
            }
            updateModel(uiEnabled = true)
        }
    }
}