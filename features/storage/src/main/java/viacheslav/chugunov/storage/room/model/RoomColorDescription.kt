package viacheslav.chugunov.storage.room.model

import androidx.room.ColumnInfo
import androidx.room.Ignore
import viacheslav.chugunov.core.model.ColorDescription
import viacheslav.chugunov.core.util.ResourceSerializer

class RoomColorDescription(
    @ColumnInfo(name = "NAME_ORDINAL")
    var nameOrdinal: Int = -1,
    @ColumnInfo(name = "COLOR_ALPHA")
    var colorAlpha: String = "",
    @ColumnInfo(name = "COLOR_VALUE")
    var colorValue: Long = -1L,
    @Ignore
    val serializer: ResourceSerializer = ResourceSerializer.ColorNameSerializer()
) : ColorDescription {

    private val original: ColorDescription
        @Ignore
        get() =  ColorDescription.Default(colorAlpha, colorValue, nameOrdinal, serializer)

    override val nameRes: Int
        @Ignore
        get() = original.nameRes

    override val alpha: String
        @Ignore
        get() = original.alpha

    override val value: Long
        @Ignore
        get() = original.value

    constructor(
        color: ColorDescription,
        serializer: ResourceSerializer = ResourceSerializer.ColorNameSerializer()
    ): this(
        nameOrdinal = serializer.toOrdinal(color.nameRes),
        colorAlpha = color.alpha,
        colorValue = color.value
    )

    @Ignore
    override fun equals(other: Any?): Boolean = original == other

    @Ignore
    override fun hashCode(): Int = original.hashCode()
}