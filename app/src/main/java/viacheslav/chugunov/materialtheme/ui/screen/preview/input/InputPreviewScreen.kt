package viacheslav.chugunov.materialtheme.ui.screen.preview.input

import androidx.compose.animation.*
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.VisibilityThreshold
import androidx.compose.animation.core.spring
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import viacheslav.chugunov.materialtheme.R
import viacheslav.chugunov.materialtheme.extension.stringRes
import viacheslav.chugunov.materialtheme.ui.animation.slideInLeft
import viacheslav.chugunov.materialtheme.ui.animation.slideInTop
import viacheslav.chugunov.materialtheme.ui.animation.slideOutLeft
import viacheslav.chugunov.materialtheme.ui.animation.slideOutTop
import viacheslav.chugunov.materialtheme.ui.view.ColoredButtonView
import viacheslav.chugunov.materialtheme.ui.view.OutlinedTextFieldView

@ExperimentalComposeUiApi
@Composable
fun InputPreviewScreen() {
    val viewModel: InputPreviewViewModel = hiltViewModel()
    val model = viewModel.modelFlow.collectAsState().value
    val keyboardController = LocalSoftwareKeyboardController.current

    DrawScreen(
        model = model,
        keyboardController = keyboardController,
        onInput1Changed = { viewModel.updateModel(input1 = it) },
        onInput2Changed = { viewModel.updateModel(input2 = it) },
        onButtonClicked = {
            keyboardController?.hide()
            viewModel.showCleanUpAnimation()
        }
    )
}

@ExperimentalComposeUiApi
@Composable
private fun DrawScreen(
    model: InputPreviewModel,
    onInput1Changed: (String) -> Unit,
    onInput2Changed: (String) -> Unit,
    onButtonClicked: () -> Unit,
    keyboardController: SoftwareKeyboardController? = LocalSoftwareKeyboardController.current
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .clickable(
                interactionSource = MutableInteractionSource(),
                indication = null,
                onClick = { keyboardController?.hide() }
            )
    ) {
        Column(modifier = Modifier.padding(horizontal = 32.dp)) {
            OutlinedTextFieldView(
                value = model.input1,
                labelText = "${R.string.input.stringRes} 1",
                onValueChanged = onInput1Changed,
                enabled = model.uiEnabled,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextFieldView(
                value = model.input2,
                labelText = "${R.string.input.stringRes} 2",
                onValueChanged = onInput2Changed,
                enabled = model.uiEnabled,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(24.dp))
            AnimatedVisibility(
                visible = model.buttonVisible,
                enter = slideInTop(),
                exit = slideOutTop()
            ) {
                ColoredButtonView(
                    text = R.string.button.stringRes,
                    rightIconId = R.drawable.ic_next,
                    enabled = model.uiEnabled,
                    modifier = Modifier.fillMaxWidth(),
                    onClick = onButtonClicked
                )
            }
        }
    }
}