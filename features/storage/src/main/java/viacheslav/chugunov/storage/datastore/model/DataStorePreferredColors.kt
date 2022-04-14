package viacheslav.chugunov.storage.datastore.model

import viacheslav.chugunov.core.model.ColorDescription
import viacheslav.chugunov.core.model.Coloring
import viacheslav.chugunov.core.model.PreferredColors

/**
 * Gson-friendly preferredColors implementation
 * */
class DataStorePreferredColors(
    var lightColorNameOrdinal: Int = ColorDescription.White.name.ordinal,
    var lightColorAlpha: String = ColorDescription.White.alpha,
    var lightColorValue: Long = ColorDescription.White.value,
    var lightOnColorNameOrdinal: Int = ColorDescription.Black.name.ordinal,
    var lightOnColorAlpha: String = ColorDescription.Black.alpha,
    var lightOnColorValue: Long = ColorDescription.Black.value,
    var darkColorNameOrdinal: Int = ColorDescription.Gray900.name.ordinal,
    var darkColorAlpha: String = ColorDescription.Gray900.alpha,
    var darkColorValue: Long = ColorDescription.Gray900.value,
    var darkOnColorNameOrdinal: Int = ColorDescription.White.name.ordinal,
    var darkOnColorAlpha: String = ColorDescription.White.alpha,
    var darkOnColorValue: Long = ColorDescription.White.value,
) {

    constructor(preferredColors: PreferredColors) : this(
        lightColorNameOrdinal = preferredColors.lightBackground.color.name.ordinal,
        lightColorAlpha = preferredColors.lightBackground.color.alpha,
        lightColorValue = preferredColors.lightBackground.color.value,
        lightOnColorNameOrdinal = preferredColors.lightBackground.onColor.name.ordinal,
        lightOnColorAlpha = preferredColors.lightBackground.onColor.alpha,
        lightOnColorValue = preferredColors.lightBackground.onColor.value,
        darkColorNameOrdinal = preferredColors.darkBackground.color.name.ordinal,
        darkColorAlpha = preferredColors.darkBackground.color.alpha,
        darkColorValue = preferredColors.darkBackground.color.value,
        darkOnColorNameOrdinal = preferredColors.darkBackground.onColor.name.ordinal,
        darkOnColorAlpha = preferredColors.darkBackground.onColor.alpha,
        darkOnColorValue = preferredColors.darkBackground.onColor.value
    )

    val lightBackground: Coloring
        get() = Coloring.Default(
            ColorDescription.Default(lightColorNameOrdinal, lightColorAlpha, lightColorValue),
            ColorDescription.Default(lightOnColorNameOrdinal, lightOnColorAlpha, lightOnColorValue)
        )

    val darkBackground: Coloring
        get() = Coloring.Default(
            ColorDescription.Default(darkColorNameOrdinal, darkColorAlpha, darkColorValue),
            ColorDescription.Default(darkOnColorNameOrdinal, darkOnColorAlpha, darkOnColorValue)
        )

    fun asPreferredColors(): PreferredColors =
        PreferredColors.Default(lightBackground, darkBackground)
}