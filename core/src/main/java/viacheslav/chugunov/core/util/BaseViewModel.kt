package viacheslav.chugunov.core.util

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlin.coroutines.CoroutineContext

open class BaseViewModel<Model>(
    protected var model: Model,
    protected var coroutineContext: CoroutineContext
) : ViewModel() {
    protected val modelMutableFlow = MutableStateFlow(model)
    val modelFlow = modelMutableFlow.asStateFlow()
}