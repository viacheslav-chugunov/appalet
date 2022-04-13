package viacheslav.chugunov.core.model

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

        override fun equals(other: Any?): Boolean =
            other is Theme &&
                    colorsPrimary == other.colorsPrimary &&
                    colorsSecondary == other.colorsSecondary &&
                    colorBackground == other.colorBackground &&
                    colorOnBackground == other.colorOnBackground

        override fun hashCode(): Int {
            var result = colorsPrimary.hashCode()
            result = 31 * result + colorsSecondary.hashCode()
            result = 31 * result + colorBackground.hashCode()
            result = 31 * result + colorOnBackground.hashCode()
            return result
        }
    }


    object Empty : Default() {
        override val colorsPrimary: ColorSet = ColorSet.Empty
        override val colorsSecondary: ColorSet = ColorSet.Empty
        override val colorBackground: ColorDescription = ColorDescription.White
        override val colorOnBackground: ColorDescription = ColorDescription.Black
    }


    class Light private constructor(
        override val colorsPrimary: ColorSet,
        override val colorsSecondary: ColorSet,
        preferredColors: PreferredColors
    ) : Default() {
        override val colorBackground: ColorDescription = preferredColors.lightBackground.color
        override val colorOnBackground: ColorDescription = preferredColors.lightBackground.onColor

        constructor(theme: SelectedTheme, preferredColors: PreferredColors) : this(
            colorsPrimary = theme.colorsPrimary,
            colorsSecondary = theme.colorsSecondary,
            preferredColors = preferredColors
        )
    }


    class Dark(
        override val colorsPrimary: ColorSet,
        override val colorsSecondary: ColorSet,
        preferredColors: PreferredColors
    ) : Default() {
        override val colorBackground: ColorDescription = preferredColors.darkBackground.color
        override val colorOnBackground: ColorDescription = preferredColors.darkBackground.onColor

        constructor(theme: SelectedTheme, preferredColors: PreferredColors) : this(
            colorsPrimary = theme.colorsPrimary,
            colorsSecondary = theme.colorsSecondary,
            preferredColors = preferredColors
        )
    }


    class Adaptive(
        modeLight: Boolean,
        override val colorsPrimary: ColorSet,
        override val colorsSecondary: ColorSet,
        preferredColors: PreferredColors
    ) : Default() {
        override val colorBackground: ColorDescription =
            preferredColors.run { if (modeLight) lightBackground else darkBackground }.color
        override val colorOnBackground: ColorDescription =
            preferredColors.run { if (modeLight) lightBackground else darkBackground }.onColor

        constructor(
            modeLight: Boolean,
            theme: SelectedTheme,
            preferredColors: PreferredColors
        ) : this(
            modeLight = modeLight,
            colorsPrimary = theme.colorsPrimary,
            colorsSecondary = theme.colorsSecondary,
            preferredColors = preferredColors
        )
    }
}