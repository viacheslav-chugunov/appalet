package viacheslav.chugunov.appalet.ui.screen.collection

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import viacheslav.chugunov.appalet.extension.*
import viacheslav.chugunov.appalet.ui.view.ClickableIconView
import viacheslav.chugunov.appalet.R
import viacheslav.chugunov.appalet.ui.animation.slideInBottom
import viacheslav.chugunov.appalet.ui.animation.slideInBottomFast
import viacheslav.chugunov.appalet.ui.animation.slideOutTop
import viacheslav.chugunov.appalet.ui.animation.slideOutTopFast
import viacheslav.chugunov.appalet.ui.theme.LocalTheme
import viacheslav.chugunov.appalet.ui.theme.LocalVerticalMetrics
import viacheslav.chugunov.appalet.ui.view.TextView
import viacheslav.chugunov.core.model.Theme

@Composable
fun CollectionScreen(
    onThemeApplied: (Theme) -> Unit,
    onThemeRemoved: (Theme) -> Unit
) {
    val viewModel: CollectionViewModel = hiltViewModel()
    val model = viewModel.modelFlow.collectAsState().value
    val coroutineScope = rememberCoroutineScope { Dispatchers.IO }

    DrawScreen(
        model = model,
        onApplyThemeIntent = onThemeApplied,
        onRemoveThemeIntent = {
            coroutineScope.launch {
                val theme = viewModel.removeTheme(it)
                onThemeRemoved(theme)
            }
        }
    )
}

@Composable
private fun DrawScreen(
    model: CollectionModel,
    onApplyThemeIntent: (Theme) -> Unit,
    onRemoveThemeIntent: (Theme) -> Unit
) {
    val padding = (LocalVerticalMetrics.current.size - 256.dp) / 2
    AnimatedVisibility(
        visible = model.themes.isEmpty(),
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        Box(
            modifier = Modifier
                .padding(top = padding)
                .fillMaxSize(),
            contentAlignment = Alignment.TopCenter
        ) {
            TextView(
                text = R.string.empty_collection_message.stringRes,
                align = TextAlign.Center,
                singleLine = false,
                modifier = Modifier.padding(all = 16.dp),
                weight = FontWeight.SemiBold
            )
        }
    }

    AnimatedVisibility(
        visible = model.themes.isNotEmpty(),
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            itemsIndexed(model.themes) { index, theme ->
                AnimatedVisibility(
                    visible = model.visibleItemsCount >= index + 1,
                    enter = slideInBottomFast(),
                    exit = slideOutTopFast()
                ) {
                    Column {
                        Row(modifier = Modifier.clickable { onApplyThemeIntent(theme) }) {
                            DrawColor(theme.primaryLight)
                            DrawColor(theme.primaryRegular)
                            DrawColor(theme.primaryDark)
                            DrawColor(theme.secondaryLight)
                            DrawColor(theme.secondaryRegular)
                            DrawColor(theme.secondaryDark)
                            DrawColor(theme.background, theme.onBackground) { onRemoveThemeIntent(theme) }
                        }
                        if (index + 1 < model.themes.size) {
                            Spacer(modifier = Modifier.height(1.dp))
                        }
                    }
                }
            }
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