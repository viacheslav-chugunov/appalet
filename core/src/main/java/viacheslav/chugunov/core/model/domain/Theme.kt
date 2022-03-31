package viacheslav.chugunov.core.model.domain

interface Theme : SelectedTheme {
    override val colorsPrimary: ColorSet
    override val colorsSecondary: ColorSet
    val colorBackground: ColorDescription
    val colorOnBackground: ColorDescription



    abstract class Default : Theme {
        override fun toString(): String =
            """Theme {
              |    primaryRegular = ${colorsPrimary.regular.hex}
              |    primaryOnRegular = ${colorsPrimary.onRegular.hex}
              |    primaryLight = ${colorsPrimary.light.hex}
              |    primaryOnLight = ${colorsPrimary.onLight.hex}
              |    primaryDark = ${colorsPrimary.dark.hex}
              |    primaryOnDark = ${colorsPrimary.onDark.hex}
              |    secondaryRegular = ${colorsSecondary.regular.hex}
              |    secondaryOnRegular = ${colorsSecondary.onRegular.hex}
              |    secondaryLight = ${colorsSecondary.light.hex}
              |    secondaryOnLight = ${colorsSecondary.onLight.hex}
              |    secondaryDark = ${colorsSecondary.dark.hex}
              |    secondaryOnDark = ${colorsSecondary.onDark.hex}
              |    background = ${colorBackground.hex}
              |    onBackground = ${colorOnBackground.hex}
              |}
            """.trimMargin()
    }



    object Empty : Default() {
        override val colorsPrimary: ColorSet = ColorSet.Empty
        override val colorsSecondary: ColorSet = ColorSet.Empty
        override val colorBackground: ColorDescription = ColorDescription.White
        override val colorOnBackground: ColorDescription = ColorDescription.Black
    }



    class Light(
        override val colorsPrimary: ColorSet,
        override val colorsSecondary: ColorSet
    ) : Default() {
        override val colorBackground: ColorDescription = ColorDescription.White
        override val colorOnBackground: ColorDescription = ColorDescription.Black

        constructor(theme: SelectedTheme): this(theme.colorsPrimary, theme.colorsSecondary)
    }



    class Dark(
        override val colorsPrimary: ColorSet,
        override val colorsSecondary: ColorSet
    ) : Default() {
        override val colorBackground: ColorDescription = ColorDescription.Gray900
        override val colorOnBackground: ColorDescription = ColorDescription.White

        constructor(theme: SelectedTheme): this(theme.colorsPrimary, theme.colorsSecondary)
    }
}