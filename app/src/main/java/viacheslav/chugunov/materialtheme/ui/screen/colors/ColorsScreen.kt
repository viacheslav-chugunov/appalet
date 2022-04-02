package viacheslav.chugunov.materialtheme.ui.screen.colors

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import viacheslav.chugunov.core.model.domain.ColorDescription
import viacheslav.chugunov.core.model.domain.Theme
import viacheslav.chugunov.materialtheme.extension.*
import viacheslav.chugunov.materialtheme.ui.theme.LocalTheme
import viacheslav.chugunov.materialtheme.ui.view.TextView

@Composable
fun ColorsScreen() {
    val theme = LocalTheme.current

    DrawScreen(
        theme = theme
    )
}

@Composable
private fun DrawScreen(
    theme: Theme
) {
    Column(modifier = Modifier.verticalScroll(ScrollState(0))) {
        DrawColor(
            background = theme.primaryLight,
            content = theme.primaryOnLight,
            description = theme.colorsPrimary.light
        )
        Spacer(modifier = Modifier.height(1.dp))
        DrawColor(
            background = theme.primaryRegular,
            content = theme.primaryOnRegular,
            description = theme.colorsPrimary.regular
        )
        Spacer(modifier = Modifier.height(1.dp))
        DrawColor(
            background = theme.primaryDark,
            content = theme.primaryOnDark,
            description = theme.colorsPrimary.dark
        )
        Spacer(modifier = Modifier.height(1.dp))
        DrawColor(
            background = theme.secondaryLight,
            content = theme.secondaryOnLight,
            description = theme.colorsSecondary.light
        )
        Spacer(modifier = Modifier.height(1.dp))
        DrawColor(
            background = theme.secondaryRegular,
            content = theme.secondaryOnRegular,
            description = theme.colorsSecondary.regular
        )
        Spacer(modifier = Modifier.height(1.dp))
        DrawColor(
            background = theme.secondaryDark,
            content = theme.secondaryOnDark,
            description = theme.colorsSecondary.dark
        )
        Spacer(modifier = Modifier.height(1.dp))
        DrawColor(
            background = theme.background,
            content = theme.onBackground,
            description = theme.colorBackground
        )
    }
}

@Composable
private fun DrawColor(
    background: Color,
    content: Color,
    description: ColorDescription,
) {
    Column(modifier = Modifier.background(background)) {
        TextView(
            text = "${description.nameRes.stringRes} ${description.alpha}",
            color = content,
            align = TextAlign.Center,
            weight = FontWeight.SemiBold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        )
        Row(
            modifier = Modifier
                .padding(all = 16.dp)
                .fillMaxWidth()
        ) {
            TextView(
                text = "r: ${description.red}",
                color = content
            )
            Spacer(modifier = Modifier.width(16.dp))
            TextView(
                text = "g: ${description.green}",
                color = content
            )
            Spacer(modifier = Modifier.width(16.dp))
            TextView(
                text = "b: ${description.blue}",
                color = content
            )
            Spacer(modifier = Modifier.weight(1f))
            TextView(
                text = "#${description.hex}",
                color = content
            )
        }
    }
}