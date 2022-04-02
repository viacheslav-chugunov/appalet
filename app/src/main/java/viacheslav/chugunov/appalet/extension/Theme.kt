package viacheslav.chugunov.appalet.extension

import androidx.compose.ui.graphics.Color
import viacheslav.chugunov.core.model.Theme

val Theme.primaryRegular: Color get() = Color(colorsPrimary.regular.value)
val Theme.primaryOnRegular: Color get() = Color(colorsPrimary.onRegular.value)
val Theme.primaryLight: Color get() = Color(colorsPrimary.light.value)
val Theme.primaryOnLight: Color get() = Color(colorsPrimary.onLight.value)
val Theme.primaryDark: Color get() = Color(colorsPrimary.dark.value)
val Theme.primaryOnDark: Color get() = Color(colorsPrimary.onDark.value)
val Theme.secondaryRegular: Color get() = Color(colorsSecondary.regular.value)
val Theme.secondaryOnRegular: Color get() = Color(colorsSecondary.onRegular.value)
val Theme.secondaryLight: Color get() = Color(colorsSecondary.light.value)
val Theme.secondaryOnLight: Color get() = Color(colorsSecondary.onLight.value)
val Theme.secondaryDark: Color get() = Color(colorsSecondary.dark.value)
val Theme.secondaryOnDark: Color get() = Color(colorsSecondary.onDark.value)
val Theme.background: Color get() = Color(colorBackground.value)
val Theme.onBackground: Color get() = Color(colorOnBackground.value)