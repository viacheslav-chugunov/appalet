package viacheslav.chugunov.appalet.ui.screen.preview.list

import androidx.compose.animation.*
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.VisibilityThreshold
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.*
import viacheslav.chugunov.core.model.Theme
import viacheslav.chugunov.appalet.R
import viacheslav.chugunov.appalet.extension.*
import viacheslav.chugunov.appalet.ui.theme.LocalTheme
import viacheslav.chugunov.appalet.ui.view.FloatingActionButtonView
import viacheslav.chugunov.appalet.ui.view.TextView

@Composable
fun ListPreviewScreen() {
    val viewModel: ListPreviewViewModel = hiltViewModel()
    val model = viewModel.modelFlow.collectAsState().value

    DrawScreen(
        model = model,
        onRemoveItemIntent = viewModel::removeItem,
        onRefreshIntent = viewModel::showRefreshItemsAnimation,
    )
}

@Composable
private fun DrawScreen(
    model: ListPreviewModel,
    onRemoveItemIntent: (Int) -> Unit,
    onRefreshIntent: () -> Unit,
    theme: Theme = LocalTheme.current,
) {
    Box {
        LazyColumn {
            items(model.items) { item ->
                val isEven = item % 2 == 0
                val position = item + 1
                Column(
                    modifier = Modifier
                        .background(if (isEven) theme.secondaryLight else theme.primaryLight)
                        .fillMaxWidth()
                        .clickable(enabled = model.uiEnabled) { onRemoveItemIntent(item) },
                ) {
                    TextView(
                        text = "${R.string.title.stringRes} $position",
                        color = if (isEven) theme.secondaryOnLight else theme.primaryOnLight,
                        modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 4.dp, top = 16.dp)
                    )
                    TextView(
                        text = "${R.string.subtitle.stringRes} $position",
                        size = 14.sp,
                        color = if (isEven) theme.secondaryOnLight else theme.primaryOnLight,
                        modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
                    )
                }
                if (item != model.items.last()) {
                    Spacer(
                        modifier = Modifier
                            .height(1.dp)
                            .background(theme.background)
                    )
                }
            }
        }


        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.TopEnd
        ) {
            Column(modifier = Modifier.padding(all = 12.dp)) {
                AnimatedVisibility(
                    visible = model.showRefresh,
                    enter = slideInHorizontally(
                        spring(
                            stiffness = Spring.StiffnessLow,
                            visibilityThreshold = IntOffset.VisibilityThreshold
                        )
                    ) { it + 200 } + fadeIn(
                        spring(stiffness = Spring.StiffnessLow)
                    ),
                    exit = slideOutHorizontally(
                        spring(
                            stiffness = Spring.StiffnessLow,
                            visibilityThreshold = IntOffset.VisibilityThreshold
                        )
                    ) { it + 200 } + fadeOut(
                        spring(stiffness = Spring.StiffnessLow)
                    )
                ) {
                    FloatingActionButtonView(
                        iconId = R.drawable.ic_update,
                        visible = model.showRefresh,
                        loading = !model.uiEnabled,
                        onPerform = {
                            onRefreshIntent()
                        }
                    )
                }
            }
        }
    }
}