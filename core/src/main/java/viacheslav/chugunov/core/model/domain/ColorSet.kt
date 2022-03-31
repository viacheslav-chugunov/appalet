package viacheslav.chugunov.core.model.domain

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
    ) : ColorSet



    object Empty : Default(
        regular = ColorDescription.White,
        onRegular = ColorDescription.Black,
        light = ColorDescription.White,
        onLight = ColorDescription.Black,
        dark = ColorDescription.White,
        onDark = ColorDescription.Black
    )
}