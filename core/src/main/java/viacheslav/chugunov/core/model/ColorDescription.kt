package viacheslav.chugunov.core.model

import android.content.Context
import viacheslav.chugunov.core.R

interface ColorDescription {
    val nameRes: Int
    val alpha: String
    val value: Long
    val hex: String

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
        override val hex: String get() = Integer.toHexString(value.toInt())
    }



    object White : Default(R.string.white, "1000", 0xffffffff)
    object Black : Default(R.string.black, "1000", 0xff000000)
    object Gray900 : Default(R.string.gray, "900", 0xff212121)
}