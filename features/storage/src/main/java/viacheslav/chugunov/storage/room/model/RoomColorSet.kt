package viacheslav.chugunov.storage.room.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Ignore
import viacheslav.chugunov.core.R
import viacheslav.chugunov.core.model.ColorDescription
import viacheslav.chugunov.core.model.ColorSet
import viacheslav.chugunov.core.util.ResourceSerializer

class RoomColorSet(
    @ColumnInfo(name = "REGULAR_NAME_ORDINAL")
    var regularNameOrdinal: Int = -1,
    @ColumnInfo(name = "REGULAR_ALPHA")
    var regularAlpha: String = "",
    @ColumnInfo(name = "REGULAR_VALUE")
    var regularValue: Long = -1L,
    @ColumnInfo(name = "ON_REGULAR_NAME_ORDINAL")
    var onRegularNameOrdinal: Int = -1,
    @ColumnInfo(name = "ON_REGULAR_ALPHA")
    var onRegularAlpha: String = "",
    @ColumnInfo(name = "ON_REGULAR_VALUE")
    var onRegularValue: Long = -1L,
    @ColumnInfo(name = "LIGHT_NAME_ORDINAL")
    var lightNameOrdinal: Int = -1,
    @ColumnInfo(name = "LIGHT_ALPHA")
    var lightAlpha: String = "",
    @ColumnInfo(name = "LIGHT_VALUE")
    var lightValue: Long = -1L,
    @ColumnInfo(name = "ON_LIGHT_NAME_ORDINAL")
    var onLightNameOrdinal: Int = -1,
    @ColumnInfo(name = "ON_LIGHT_ALPHA")
    var onLightAlpha: String = "",
    @ColumnInfo(name = "ON_LIGHT_VALUE")
    var onLightValue: Long = -1L,
    @ColumnInfo(name = "DARK_NAME_ORDINAL")
    var darkNameOrdinal: Int = -1,
    @ColumnInfo(name = "DARK_ALPHA")
    var darkAlpha: String = "",
    @ColumnInfo(name = "DARK_VALUE")
    var darkValue: Long = -1L,
    @ColumnInfo(name = "ON_DARK_NAME_ORDINAL")
    var onDarkNameOrdinal: Int = -1,
    @ColumnInfo(name = "ON_DARK_ALPHA")
    var onDarkAlpha: String = "",
    @ColumnInfo(name = "ON_DARK_VALUE")
    var onDarkValue: Long = -1L,
) : ColorSet {

    private val original: ColorSet
        @Ignore
        get() = ColorSet.Default(
            regular = ColorDescription.Default(regularNameOrdinal, regularAlpha, regularValue),
            onRegular = ColorDescription.Default(onRegularNameOrdinal, onRegularAlpha, onRegularValue),
            light = ColorDescription.Default(lightNameOrdinal, lightAlpha, lightValue),
            onLight = ColorDescription.Default(onLightNameOrdinal, onLightAlpha, onLightValue),
            dark = ColorDescription.Default(darkNameOrdinal, darkAlpha, darkValue),
            onDark = ColorDescription.Default(onDarkNameOrdinal, onDarkAlpha, onDarkValue)
        )

    override val regular: ColorDescription
        @Ignore
        get() = original.regular

    override val onRegular: ColorDescription
        @Ignore
        get() = original.onRegular

    override val light: ColorDescription
        @Ignore
        get() = original.light

    override val onLight: ColorDescription
        @Ignore
        get() = original.onLight

    override val dark: ColorDescription
        @Ignore
        get() = original.dark

    override val onDark: ColorDescription
        @Ignore
        get() = original.onDark

    constructor(set: ColorSet): this(
        regularNameOrdinal = set.regular.name.ordinal,
        regularAlpha = set.regular.alpha,
        regularValue = set.regular.value,
        onRegularNameOrdinal = set.onRegular.name.ordinal,
        onRegularAlpha = set.onRegular.alpha,
        onRegularValue = set.onRegular.value,
        lightNameOrdinal = set.light.name.ordinal,
        lightAlpha = set.light.alpha,
        lightValue = set.light.value,
        onLightNameOrdinal = set.onLight.name.ordinal,
        onLightAlpha = set.onLight.alpha,
        onLightValue = set.onLight.value,
        darkNameOrdinal = set.dark.name.ordinal,
        darkAlpha = set.dark.alpha,
        darkValue = set.dark.value,
        onDarkNameOrdinal = set.onDark.name.ordinal,
        onDarkAlpha = set.onDark.alpha,
        onDarkValue = set.onDark.value,
    )

    @Ignore
    override fun equals(other: Any?): Boolean = original == other

    @Ignore
    override fun hashCode(): Int = original.hashCode()
}