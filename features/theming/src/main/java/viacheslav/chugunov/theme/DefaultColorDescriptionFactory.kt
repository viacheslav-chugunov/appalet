package viacheslav.chugunov.theme

import viacheslav.chugunov.core.model.ColorDescription
import viacheslav.chugunov.core.model.ColorSet
import viacheslav.chugunov.core.util.ColorDescriptionFactory

class DefaultColorDescriptionFactory : ColorDescriptionFactory {
    override val materialColors: List<ColorSet>
        get() = listOf(redColors, pinkColors, purpleColors, deepPurpleColors).flatten()

    val redColors: List<ColorSet> = createColorSet(
        regularNameRes = R.string.red,
        lightNameRes = R.string.red_light,
        darkNameRes = R.string.red_dark,
        regularColors = listOf(
            0xffffebee, 0xffffcdd2, 0xffef9a9a, 0xffe57373, 0xffef5350,
            0xfff44336, 0xffe53935, 0xffd32f2f, 0xffc62828, 0xffb71c1c),
        lightColors = listOf(
            0xffffffff, 0xffffffff, 0xffffcccb, 0xffffa4a2, 0xffff867c,
            0xffff7961, 0xffff6f60, 0xffff6659, 0xffff5f52, 0xfff05545),
        darkColors = listOf(
            0xffccb9bc, 0xffcb9ca1, 0xffba6b6c, 0xffaf4448, 0xffb61827,
            0xffba000d, 0xffab000d, 0xff9a0007, 0xff8e0000, 0xffb71c1c),
        whiteAfterRegular = 6,
        whiteAfterLight = 9,
        whiteAfterDark = 2
    )

    val pinkColors: List<ColorSet> = createColorSet(
        regularNameRes = R.string.pink,
        lightNameRes = R.string.pink_light,
        darkNameRes = R.string.pink_dark,
        regularColors = listOf(
            0xfffce4ec, 0xfff8bbd0, 0xfff48fb1, 0xfff06292, 0xffec407a,
            0xffe91e63, 0xffd81b60, 0xffc2185b, 0xffad1457, 0xff880e4f),
        lightColors = listOf(
            0xffffffff, 0xffffeeff, 0xffffc1e3, 0xffff94c2, 0xffff77a9,
            0xffff6090, 0xffff5c8d, 0xfffa5788, 0xffe35183, 0xffbc477b),
        darkColors = listOf(
            0xffc9b2ba, 0xffc48b9f, 0xffbf5f82, 0xffba2d65, 0xffb4004e,
            0xffb0003a, 0xffa00037, 0xff8c0032, 0xff78002e, 0xff560027),
        whiteAfterRegular = 5,
        whiteAfterLight = 8,
        whiteAfterDark = 2
    )

    val purpleColors: List<ColorSet> = createColorSet(
        regularNameRes = R.string.purple,
        lightNameRes = R.string.purple_light,
        darkNameRes = R.string.purple_dark,
        regularColors = listOf(
            0xfff3e5f5, 0xffe1bee7, 0xffce93d8, 0xffba68c8, 0xffab47bc,
            0xff9c27b0, 0xff8e24aa, 0xff7b1fa2, 0xff6a1b9a, 0xff4a148c),
        lightColors = listOf(
            0xffffffff, 0xfffff1ff, 0xffffc4ff, 0xffee98fb, 0xffdf78ef,
            0xffd05ce3, 0xffc158dc, 0xffae52d4, 0xff9c4dcc, 0xff7c43bd),
        darkColors = listOf(
            0xffc0b3c2, 0xffaf8eb5, 0xff9c64a6, 0xff883997, 0xff790e8b,
            0xff6a0080, 0xff5c007a, 0xff4a0072, 0xff38006b, 0xff12005e),
        whiteAfterRegular = 3,
        whiteAfterLight = 7,
        whiteAfterDark = 2
    )

    val deepPurpleColors: List<ColorSet> = createColorSet(
        regularNameRes = R.string.deep_purple,
        lightNameRes = R.string.deep_purple_light,
        darkNameRes = R.string.deep_purple_dark,
        regularColors = listOf(
            0xffede7f6, 0xffd1c4e9, 0xffb39ddb, 0xff9575cd, 0xff7e57c2,
            0xff673ab7, 0xff5e35b1, 0xff512da8, 0xff4527a0, 0xff311b92),
        lightColors = listOf(
            0xffffffff, 0xfffff7ff, 0xffe6ceff, 0xffc7a4ff, 0xffb085f5,
            0xff9a67ea, 0xff9162e4, 0xff8559da, 0xff7953d2, 0xff6746c3),
        darkColors = listOf(
            0xffbbb5c3, 0xffa094b7, 0xff836fa9, 0xff65499c, 0xff4d2c91,
            0xff320b86, 0xff280680, 0xff140078, 0xff000070, 0xff000063),
        whiteAfterRegular = 3,
        whiteAfterLight = 6,
        whiteAfterDark = 2
    )



    companion object {
        private fun createColorSet(
            regularNameRes: Int,
            lightNameRes: Int,
            darkNameRes: Int,
            regularColors: List<Long>,
            lightColors: List<Long>,
            darkColors: List<Long>,
            whiteAfterRegular: Int,
            whiteAfterLight: Int,
            whiteAfterDark: Int
        ) : List<ColorSet> {
            assert(regularColors.size == 10) { "Regular colors size must be equal 10, but provided ${regularColors.size}" }
            assert(lightColors.size == 10) { "Light colors size must be equal 10, but provided ${regularColors.size}" }
            assert(darkColors.size == 10) { "Dark colors size must be equal 10, but provided ${regularColors.size}" }

            val colorSets = mutableListOf<ColorSet>()
            val alphas = listOf(50, 100, 200, 300, 400, 500, 600, 700, 800, 900)

            for (i in 0..9) {
                colorSets += ColorSet.Default(
                    regular = ColorDescription.Default(regularNameRes, alphas[i], regularColors[i]),
                    onRegular = if (i > whiteAfterRegular) ColorDescription.White else ColorDescription.Black,
                    light = ColorDescription.Default(lightNameRes, alphas[i], lightColors[i]),
                    onLight = if (i > whiteAfterLight) ColorDescription.White else ColorDescription.Black,
                    dark = ColorDescription.Default(darkNameRes, alphas[i], darkColors[i]),
                    onDark = if (i > whiteAfterDark) ColorDescription.White else ColorDescription.Black
                )
            }

            return colorSets.toList()
        }
    }
}