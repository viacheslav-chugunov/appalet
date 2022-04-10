package viacheslav.chugunov.storage.datastore.model

import viacheslav.chugunov.core.model.ColorDescription
import viacheslav.chugunov.core.model.Coloring
import viacheslav.chugunov.core.model.PreferredColors

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
) : PreferredColors {

    override val lightBackground: Coloring
        get() = Coloring.Default(
            ColorDescription.Default(lightColorNameOrdinal, lightColorAlpha, lightColorValue),
            ColorDescription.Default(lightOnColorNameOrdinal, lightOnColorAlpha, lightOnColorValue)
        )

    override val darkBackground: Coloring
        get() = Coloring.Default(
            ColorDescription.Default(darkColorNameOrdinal, darkColorAlpha, darkColorValue),
            ColorDescription.Default(darkOnColorNameOrdinal, darkOnColorAlpha, darkOnColorValue)
        )
}