package viacheslav.chugunov.appalet.ui.screen.collection

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import viacheslav.chugunov.appalet.extension.*
import viacheslav.chugunov.appalet.ui.view.ClickableIconView
import viacheslav.chugunov.appalet.R
import viacheslav.chugunov.appalet.ui.theme.LocalTheme
import viacheslav.chugunov.core.model.Theme

@Composable
fun CollectionScreen(
    onThemeRemoved: () -> Unit
) {
    val viewModel: CollectionViewModel = hiltViewModel()
    val model = viewModel.modelFlow.collectAsState().value
    val coroutineScope = rememberCoroutineScope { Dispatchers.IO }

    DrawScreen(
        model = model,
        onRemoveThemeIntent = {
            coroutineScope.launch {
                viewModel.removeTheme(it)
                onThemeRemoved()
            }
        }
    )
}

@Composable
private fun DrawScreen(
    model: CollectionModel,
    onRemoveThemeIntent: (Theme) -> Unit
) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(model.themes) { theme ->
            Row {
                DrawColor(theme.primaryLight)
                DrawColor(theme.primaryRegular)
                DrawColor(theme.primaryDark)
                DrawColor(theme.secondaryLight)
                DrawColor(theme.secondaryRegular)
                DrawColor(theme.secondaryDark)
                DrawColor(theme.background, theme.onBackground) { onRemoveThemeIntent(theme) }
            }
            Spacer(modifier = Modifier.height(0.5.dp))
        }
    }
}

@Composable
private fun RowScope.DrawColor(
    color: Color,
    removeIconTint: Color = LocalTheme.current.onBackground,
    onRemoveIconPerform: (() -> Unit)? = null
) {
    Box(
        modifier = Modifier
            .height(50.dp)
            .weight(1f)
            .background(Color(color.value)),
        contentAlignment = Alignment.Center
    ) {
        onRemoveIconPerform?.let {
            ClickableIconView(
                iconId = R.drawable.ic_delete,
                onPerform = onRemoveIconPerform,
                tint = removeIconTint
            )
        }
    }
}