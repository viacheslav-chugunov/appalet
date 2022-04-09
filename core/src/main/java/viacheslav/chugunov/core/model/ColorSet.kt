package viacheslav.chugunov.core.model

import android.util.Log

interface ColorSet {
    val regular: ColorDescription
    val onRegular: ColorDescription
    val light: ColorDescription
    val onLight: ColorDescription
    val dark: ColorDescription
    val onDark: ColorDescription



    open class Default(
        override val regular: ColorDescription,
        override val onRegular: ColorDescription,
        override val light: ColorDescription,
        override val onLight: ColorDescription,
        override val dark: ColorDescription,
        override val onDark: ColorDescription
    ) : ColorSet {

        override fun equals(other: Any?): Boolean =
            other is ColorSet &&
                    regular == other.regular &&
                    onRegular == other.onRegular &&
                    light == other.light &&
                    onLight == other.onLight &&
                    dark == other.dark &&
                    onDark == other.onDark

        override fun hashCode(): Int {
            var result = regular.hashCode()
            result = 31 * result + onRegular.hashCode()
            result = 31 * result + light.hashCode()
            result = 31 * result + onLight.hashCode()
            result = 31 * result + dark.hashCode()
            result = 31 * result + onDark.hashCode()
            return result
        }
    }



    object Empty : Default(
        regular = ColorDescription.White,
        onRegular = ColorDescription.Black,
        light = ColorDescription.White,
        onLight = ColorDescription.Black,
        dark = ColorDescription.White,
        onDark = ColorDescription.Black
    )
}