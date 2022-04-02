package viacheslav.chugunov.appalet.ui.screen.preview.dialog

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import viacheslav.chugunov.core.util.BaseViewModel
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@HiltViewModel
class DialogPreviewViewModel(
    model: DialogPreviewModel,
    coroutineContext: CoroutineContext = Dispatchers.IO
) : BaseViewModel<DialogPreviewModel>(model, coroutineContext) {

    @Inject constructor(): this(DialogPreviewModel())

    fun updateModel(
        showDialog: Boolean = model.showDialog
    ) {
        model = DialogPreviewModel(showDialog)
        modelMutableFlow.value = model
    }
}