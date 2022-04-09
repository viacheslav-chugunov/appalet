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
    @Ignore
    val serializer: ResourceSerializer = ResourceSerializer.ColorNameSerializer(),
) : ColorSet {

    private val original: ColorSet
        @Ignore
        get() = ColorSet.Default(
            regular = ColorDescription.Default(regularAlpha, regularValue, regularNameOrdinal, serializer),
            onRegular = ColorDescription.Default(onRegularAlpha, onRegularValue, onRegularNameOrdinal, serializer),
            light = ColorDescription.Default(lightAlpha, lightValue, lightNameOrdinal, serializer),
            onLight = ColorDescription.Default(onLightAlpha, onLightValue, onLightNameOrdinal, serializer),
            dark = ColorDescription.Default(darkAlpha, darkValue, darkNameOrdinal, serializer),
            onDark = ColorDescription.Default(onDarkAlpha, onDarkValue, onDarkNameOrdinal, serializer)
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

    constructor(
        set: ColorSet,
        serializer: ResourceSerializer = ResourceSerializer.ColorNameSerializer()
    ): this(
        regularNameOrdinal = serializer.toOrdinal(set.regular.nameRes),
        regularAlpha = set.regular.alpha,
        regularValue = set.regular.value,
        onRegularNameOrdinal = serializer.toOrdinal(set.onRegular.nameRes),
        onRegularAlpha = set.onRegular.alpha,
        onRegularValue = set.onRegular.value,
        lightNameOrdinal = serializer.toOrdinal(set.light.nameRes),
        lightAlpha = set.light.alpha,
        lightValue = set.light.value,
        onLightNameOrdinal = serializer.toOrdinal(set.onLight.nameRes),
        onLightAlpha = set.onLight.alpha,
        onLightValue = set.onLight.value,
        darkNameOrdinal = serializer.toOrdinal(set.dark.nameRes),
        darkAlpha = set.dark.alpha,
        darkValue = set.dark.value,
        onDarkNameOrdinal = serializer.toOrdinal(set.onDark.nameRes),
        onDarkAlpha = set.onDark.alpha,
        onDarkValue = set.onDark.value,
    )

    @Ignore
    override fun equals(other: Any?): Boolean = original == other

    @Ignore
    override fun hashCode(): Int = original.hashCode()
}