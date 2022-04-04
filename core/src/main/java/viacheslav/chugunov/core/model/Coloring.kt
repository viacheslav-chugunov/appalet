package viacheslav.chugunov.core.model

interface Coloring {
    val color: ColorDescription
    val onColor: ColorDescription



    open class Default(
        override val color: ColorDescription = ColorDescription.White,
        override val onColor: ColorDescription = ColorDescription.Black
    ) : Coloring



    object White : Default(ColorDescription.White, ColorDescription.Black)
    object Gray900 : Default(ColorDescription.Gray900, ColorDescription.White)
}