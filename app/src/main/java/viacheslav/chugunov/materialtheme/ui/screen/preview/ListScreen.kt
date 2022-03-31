package viacheslav.chugunov.materialtheme.ui.screen.preview

import android.graphics.fonts.FontStyle
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import viacheslav.chugunov.core.model.domain.Theme
import viacheslav.chugunov.materialtheme.R
import viacheslav.chugunov.materialtheme.extension.primaryLight
import viacheslav.chugunov.materialtheme.extension.primaryOnLight
import viacheslav.chugunov.materialtheme.extension.secondaryLight
import viacheslav.chugunov.materialtheme.extension.secondaryOnLight
import viacheslav.chugunov.materialtheme.ui.theme.LocalTheme
import viacheslav.chugunov.materialtheme.ui.view.FloatingActionButtonView

@Composable
fun ListScreen() {
    val theme = LocalTheme.current
    var items by rememberSaveable { mutableStateOf(IntRange(0, 49).toList()) }
    val showRefresh = items.size < 50

    DrawScreen(
        theme = theme,
        items = items,
        showRefresh = showRefresh,
        onRemoveItemIntent = { items = items - it },
        onRefreshIntent = { items = IntRange(0, 49).toList() }
    )
}

@Composable
private fun DrawScreen(
    theme: Theme,
    items: List<Int>,
    showRefresh: Boolean,
    onRemoveItemIntent: (Int) -> Unit,
    onRefreshIntent: () -> Unit,
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
                        .clickable { onRemoveItemIntent(item) },
                ) {
                    Text(
                        text = "Title $position",
                        fontSize = 16.sp,
                        color = if (isEven) theme.secondaryOnLight else theme.primaryOnLight,
                        modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 4.dp, top = 16.dp)
                    )
                    Text(
                        text = "Subtitle $position",
                        fontSize = 14.sp,
                        color = if (isEven) theme.secondaryOnLight else theme.primaryOnLight,
                        modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
                    )
                }
            }
        }


        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(all = 12.dp),
            contentAlignment = Alignment.TopEnd
        ) {
            FloatingActionButtonView(
                iconId = R.drawable.ic_update,
                visible = showRefresh,
                onPerform = onRefreshIntent
            )
        }
    }
}