package viacheslav.chugunov.materialtheme.ui.screen.preview

import androidx.compose.animation.*
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.VisibilityThreshold
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import viacheslav.chugunov.materialtheme.R
import viacheslav.chugunov.materialtheme.extension.stringRes
import viacheslav.chugunov.materialtheme.ui.view.ColoredButtonView
import viacheslav.chugunov.materialtheme.ui.view.OutlinedTextFieldView

@Composable
fun InputScreen() {
    var input1 by rememberSaveable { mutableStateOf("") }
    var input2 by rememberSaveable { mutableStateOf("") }
    var uiEnabled by rememberSaveable { mutableStateOf(true) }

    val coroutineContext = rememberCoroutineScope()

    DrawScreen(
        input1 = input1,
        input2 = input2,
        uiEnabled = uiEnabled,
        onInput1Changed = { input1 = it },
        onInput2Changed = { input2 = it },
        onUiEnabledChanged = { uiEnabled = it },
        onButtonClicked = {
            coroutineContext.launch {
                while (input1.isNotBlank() || input2.isNotBlank()) {
                    if (input1.isNotBlank()) input1 = input1.substring(0, input1.length - 1)
                    if (input2.isNotBlank()) input2 = input2.substring(0, input2.length - 1)
                    delay(200)
                }
                uiEnabled = true
            }
        }
    )
}

@Composable
private fun DrawScreen(
    input1: String,
    input2: String,
    uiEnabled: Boolean,
    onInput1Changed: (String) -> Unit,
    onInput2Changed: (String) -> Unit,
    onUiEnabledChanged: (Boolean) -> Unit,
    onButtonClicked: () -> Unit
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(modifier = Modifier.padding(horizontal = 32.dp)) {
            OutlinedTextFieldView(
                value = input1,
                labelText = "${R.string.input.stringRes} 1",
                onValueChanged = onInput1Changed,
                enabled = uiEnabled,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextFieldView(
                value = input2,
                labelText = "${R.string.input.stringRes} 2",
                onValueChanged = onInput2Changed,
                enabled = uiEnabled,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(24.dp))
            AnimatedVisibility(
                visible = uiEnabled && (input1.isNotBlank() || input2.isNotBlank()),
                enter = slideInVertically(
                    spring(
                        stiffness = Spring.StiffnessVeryLow,
                        visibilityThreshold = IntOffset.VisibilityThreshold
                    )) { it + 500 } + fadeIn(spring(stiffness = Spring.StiffnessVeryLow)),
                exit = slideOutVertically(
                    spring(
                        stiffness = Spring.StiffnessVeryLow,
                        visibilityThreshold = IntOffset.VisibilityThreshold
                    )) { it + 500 } + fadeOut(spring(stiffness = Spring.StiffnessVeryLow))
            ) {
                ColoredButtonView(
                    text = R.string.button.stringRes,
                    rightIconId = R.drawable.ic_next,
                    enabled = uiEnabled,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    onUiEnabledChanged(false)
                    onButtonClicked()
                }
            }
        }
    }
}