package viacheslav.chugunov.core.model

import android.content.Context
import viacheslav.chugunov.core.R
import viacheslav.chugunov.core.util.ResourceSerializer

interface ColorDescription {
    val nameRes: Int
    val alpha: String
    val value: Long
    val hex: String get() = Integer.toHexString(value.toInt())

    val red: Int get() = (value and 0xFF0000 shr 16).toInt()
    val green: Int get() = (value and 0xFF00 shr 8).toInt()
    val blue: Int get() = (value and 0xFF).toInt()

    fun getName(context: Context): String =
        "${context.getString(nameRes)} $alpha"



    open class Default(
        override val nameRes: Int,
        override val alpha: String,
        override val value: Long
    ) : ColorDescription {

        constructor(alpha: String, value: Long, ordinal: Int, serializer: ResourceSerializer): this(
            nameRes = serializer.fromOrdinal(ordinal),
            alpha = alpha,
            value = value
        )

        override fun toString(): String =
            """ColorDescription {
                |   nameRes = $nameRes
                |   alpha = $alpha
                |   value = $value
                |   hex = $hex
                |}
            """.trimMargin()

        override fun equals(other: Any?): Boolean =
            other is ColorDescription &&
                    ResourceSerializer.ColorNameSerializer()
                        .run { toOrdinal(nameRes) == toOrdinal(other.nameRes) } &&
                    alpha == other.alpha &&
                    value == other.value

        override fun hashCode(): Int {
            var result = nameRes
            result = 31 * result + alpha.hashCode()
            result = 31 * result + value.hashCode()
            return result
        }
    }



    object White : Default(R.string.white, "1000", 0xffffffff)
    object Black : Default(R.string.black, "1000", 0xff000000)
    object Gray900 : Default(R.string.gray, "900", 0xff212121)
}