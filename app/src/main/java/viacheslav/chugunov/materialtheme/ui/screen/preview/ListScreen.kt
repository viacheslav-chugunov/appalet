package viacheslav.chugunov.materialtheme.ui.screen.preview

import android.graphics.fonts.FontStyle
import androidx.compose.animation.*
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.VisibilityThreshold
import androidx.compose.animation.core.spring
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.FabPosition
import androidx.compose.material.Scaffold
import androidx.compose.material.TabPosition
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import viacheslav.chugunov.core.model.domain.Theme
import viacheslav.chugunov.materialtheme.R
import viacheslav.chugunov.materialtheme.extension.*
import viacheslav.chugunov.materialtheme.ui.theme.LocalTheme
import viacheslav.chugunov.materialtheme.ui.view.FloatingActionButtonView
import viacheslav.chugunov.materialtheme.ui.view.TextView

@Composable
fun ListScreen() {
    val theme = LocalTheme.current
    var items by rememberSaveable { mutableStateOf(IntRange(0, 49).toList()) }
    var uiEnabled by rememberSaveable { mutableStateOf(true) }
    val showRefresh = items.size < 50

    val coroutineScope = rememberCoroutineScope()

    DrawScreen(
        theme = theme,
        items = items,
        showRefresh = showRefresh,
        uiEnabled = uiEnabled,
        onRemoveItemIntent = { items = items - it },
        onRefreshIntent = {
            coroutineScope.launch {
                items = listOf()
                delay(200)
                for (i in 49 downTo 0) {
                    items = items.toMutableList().apply { add(0, i) }
                    delay(50)
                }
                uiEnabled = true
            }
        },
        onUiEnabledChanged = { uiEnabled = it }
    )
}

@Composable
private fun DrawScreen(
    theme: Theme,
    items: List<Int>,
    showRefresh: Boolean,
    uiEnabled: Boolean,
    onRemoveItemIntent: (Int) -> Unit,
    onRefreshIntent: () -> Unit,
    onUiEnabledChanged: (Boolean) -> Unit,
) {
    Box {
        LazyColumn {
            items(items) { item ->
                val isEven = item % 2 == 0
                val position = item + 1
                Column(
                    modifier = Modifier
                        .background(if (isEven) theme.secondaryLight else theme.primaryLight)
                        .fillMaxWidth()
                        .clickable(enabled = uiEnabled) { onRemoveItemIntent(item) },
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
            }
        }


        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.TopEnd
        ) {
            Column(modifier = Modifier.padding(all = 12.dp)) {
                AnimatedVisibility(
                    visible = showRefresh && uiEnabled,
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
                        visible = showRefresh,
                        onPerform = {
                            onUiEnabledChanged(false)
                            onRefreshIntent()
                        }
                    )
                }
            }
        }
    }
}