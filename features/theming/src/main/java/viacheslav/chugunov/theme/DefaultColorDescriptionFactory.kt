package viacheslav.chugunov.theme

import viacheslav.chugunov.core.model.ColorDescription
import viacheslav.chugunov.core.model.ColorSet
import viacheslav.chugunov.core.model.Coloring
import viacheslav.chugunov.core.util.ColorDescriptionFactory
import viacheslav.chugunov.core.R

class DefaultColorDescriptionFactory : ColorDescriptionFactory {

    override val materialColorSets: List<ColorSet>
        get() = listOf(
            red, pink, purple, deepPurple, indigo, blue, lightBlue, cyan, teal, green, lightGreen,
            lime, yellow, amber, orange, deepOrange, brown, gray, blueGray, redA, pinkA, purpleA,
            deepPurpleA, indigoA, blueA, lightBlueA, cyanA, tealA, greenA, lightGreenA, limeA, yellowA,
            amberA, orangeA, deepOrangeA
        ).flatten()

    override val materialColors: List<Coloring>
        get() = materialColorSets
            .map {
                listOf(
                    Coloring.Default(it.regular, it.onRegular),
                    Coloring.Default(it.light, it.onLight),
                    Coloring.Default(it.dark, it.onDark)
                )
            }
            .flatten()

    val red: List<ColorSet> = createColorSet(
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

    val pink: List<ColorSet> = createColorSet(
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

    val purple: List<ColorSet> = createColorSet(
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

    val deepPurple: List<ColorSet> = createColorSet(
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

    val indigo: List<ColorSet> = createColorSet(
        regularNameRes = R.string.indigo,
        lightNameRes = R.string.indigo_light,
        darkNameRes = R.string.indigo_dark,
        regularColors = listOf(
            0xffe8eaf6, 0xffc5cae9, 0xff9fa8da, 0xff7986cb, 0xff5c6bc0,
            0xff3f51b5, 0xff3949ab, 0xff303f9f, 0xff283593, 0xff1a237e),
        lightColors = listOf(
            0xffffffff, 0xfff8fdff, 0xffd1d9ff, 0xffaab6fe, 0xff8e99f3,
            0xff757de8, 0xff6f74dd, 0xff666ad1, 0xff5f5fc4, 0xff534bae),
        darkColors = listOf(
            0xffb6b8c3, 0xff9499b7, 0xff6f79a8, 0xff49599a, 0xff26418f,
            0xff002984,0xff00227b, 0xff001970, 0xff001064, 0xff000051),
            whiteAfterRegular = 3,
            whiteAfterLight = 6,
            whiteAfterDark = 2
        )

    val blue: List<ColorSet> = createColorSet(
        regularNameRes = R.string.blue,
        lightNameRes = R.string.blue_light,
        darkNameRes = R.string.blue_dark,
        regularColors = listOf(
            0xffe3f2fd, 0xffbbdefb, 0xff90caf9, 0xff64b5f6, 0xff42a5f5,
            0xff2196f3, 0xff1e88e5, 0xff1976d2, 0xff1565c0, 0xff0d47a1),
        lightColors = listOf(
            0xffffffff, 0xffeeffff, 0xffc3fdff, 0xff9be7ff, 0xff80d6ff,
            0xff6ec6ff, 0xff6ab7ff, 0xff63a4ff, 0xff5e92f3, 0xff5472d3),
        darkColors = listOf(
            0xffb1bfca, 0xff8aacc8, 0xff5d99c6, 0xff2286c3, 0xff0077c2,
            0xff0069c0, 0xff005cb2, 0xff004ba0, 0xff003c8f, 0xff002171
        ),
        whiteAfterRegular = 6,
        whiteAfterLight = 9,
        whiteAfterDark = 3
    )

    val lightBlue: List<ColorSet> = createColorSet(
        regularNameRes = R.string.light_blue,
        lightNameRes = R.string.light_blue_light,
        darkNameRes = R.string.light_blue_dark,
        regularColors = listOf(
            0xffe1f5fe, 0xffb3e5fc, 0xff81d4fa, 0xff4fc3f7, 0xff29b6f6,
            0xff03a9f4, 0xff039be5, 0xff0288d1, 0xff0277bd, 0xff01579b),
        lightColors = listOf(
            0xffffffff, 0xffe6ffff, 0xffb6ffff, 0xff8bf6ff, 0xff73e8ff,
            0xff67daff, 0xff63ccff, 0xff5eb8ff, 0xff58a5f0, 0xff4f83cc),
        darkColors = listOf(
            0xffafc2cb, 0xff82b3c9, 0xff4ba3c7, 0xff0093c4, 0xff0086c3,
            0xff007ac1, 0xff006db3, 0xff005b9f, 0xff004c8c, 0xff002f6c),
        whiteAfterRegular = 7,
        whiteAfterLight = 9,
        whiteAfterDark = 4
    )

    val cyan: List<ColorSet> = createColorSet(
        regularNameRes = R.string.cyan,
        lightNameRes = R.string.cyan_light,
        darkNameRes = R.string.cyan_dark,
        regularColors = listOf(
            0xffe0f7fa, 0xffb2ebf2, 0xff80deea, 0xff4dd0e1, 0xff26c6da,
            0xff00bcd4, 0xff00acc1, 0xff0097a7, 0xff00838f, 0xff006064),
        lightColors = listOf(
            0xffffffff, 0xffe5ffff, 0xffb4ffff, 0xff88ffff, 0xff6ff9ff,
            0xff62efff, 0xff5ddef4, 0xff56c8d8, 0xff4fb3bf, 0xff428e92),
        darkColors = listOf(
            0xffaec4c7, 0xff81b9bf, 0xff4bacb8, 0xff009faf, 0xff0095a8,
            0xff008ba3, 0xff007c91, 0xff006978, 0xff005662, 0xff00363a),
        whiteAfterRegular = 7,
        whiteAfterLight = 9,
        whiteAfterDark = 5
    )

    val teal: List<ColorSet> = createColorSet(
        regularNameRes = R.string.teal,
        lightNameRes = R.string.teal_light,
        darkNameRes = R.string.teal_dark,
        regularColors = listOf(
            0xffe0f2f1, 0xffb2dfdb, 0xff80cbc4, 0xff4db6ac, 0xff26a69a,
            0xff009688, 0xff00897b, 0xff00796b, 0xff00695c, 0xff004d40),
        lightColors = listOf(
            0xffffffff, 0xffe5ffff, 0xffb2fef7, 0xff82e9de, 0xff64d8cb,
            0xff52c7b8, 0xff4ebaaa, 0xff48a999, 0xff439889, 0xff39796b),
        darkColors = listOf(
            0xffaebfbe, 0xff82ada9, 0xff4f9a94, 0xff00867d, 0xff00766c,
            0xff00675b, 0xff005b4f, 0xff004c40, 0xff003d33, 0xff00251a),
        whiteAfterRegular = 6,
        whiteAfterLight = 8,
        whiteAfterDark = 3
    )

    val green: List<ColorSet> = createColorSet(
        regularNameRes = R.string.green,
        lightNameRes = R.string.green_light,
        darkNameRes = R.string.green_dark,
        regularColors = listOf(
            0xffe8f5e9, 0xffc8e6c9, 0xffa5d6a7, 0xff81c784, 0xff66bb6a,
            0xff4caf50, 0xff43a047, 0xff388e3c, 0xff2e7d32, 0xff1b5e20),
        lightColors = listOf(
            0xffffffff, 0xfffbfffc, 0xffd7ffd9, 0xffb2fab4, 0xff98ee99,
            0xff80e27e, 0xff76d275, 0xff6abf69, 0xff60ad5e, 0xff4c8c4a),
        darkColors = listOf(
            0xffb6c2b7, 0xff97b498, 0xff75a478, 0xff519657, 0xff338a3e,
            0xff087f23, 0xff00701a, 0xff00600f, 0xff005005, 0xff003300),
        whiteAfterRegular = 7,
        whiteAfterLight = 9,
        whiteAfterDark = 4
    )

    val lightGreen: List<ColorSet> = createColorSet(
        regularNameRes = R.string.light_green,
        lightNameRes = R.string.light_green_light,
        darkNameRes = R.string.light_green_dark,
        regularColors = listOf(
            0xfff1f8e9, 0xffdcedc8, 0xffc5e1a5, 0xffaed581, 0xff9ccc65,
            0xff8bc34a, 0xff7cb342, 0xff689f38, 0xff558b2f, 0xff33691e),
        lightColors = listOf(
            0xffffffff, 0xfffffffb, 0xfff8ffd7, 0xffe1ffb1, 0xffcfff95,
            0xffbef67a, 0xffaee571, 0xff99d066, 0xff85bb5c, 0xff629749),
        darkColors = listOf(
            0xffbec5b7, 0xffaabb97, 0xff94af76, 0xff7da453, 0xff6b9b37,
            0xff5a9216, 0xff4b830d, 0xff387002, 0xff255d00, 0xff003d00),
        whiteAfterRegular = 8,
        whiteAfterLight = 9,
        whiteAfterDark = 5
    )

    val lime: List<ColorSet> = createColorSet(
        regularNameRes = R.string.lime,
        lightNameRes = R.string.lime_light,
        darkNameRes = R.string.lime_dark,
        regularColors = listOf(
            0xfff9fbe7, 0xfff0f4c3, 0xffe6ee9c, 0xffdce775, 0xffd4e157,
            0xffcddc39, 0xffc0ca33, 0xffafb42b, 0xff9e9d24, 0xff827717),
        lightColors = listOf(
            0xffffffff, 0xfffffff6, 0xffffffce, 0xffffffa6, 0xffffff89,
            0xffffff6e, 0xfff5fd67, 0xffe4e65e, 0xffd2ce56, 0xffb4a647),
        darkColors = listOf(
            0xffc6c8b5, 0xffbdc192, 0xffb3bc6d, 0xffa8b545, 0xffa0af22,
            0xff99aa00, 0xff8c9900, 0xff7c8500, 0xff6c6f00, 0xff524c00),
        whiteAfterRegular = 8,
        whiteAfterLight = 9,
        whiteAfterDark = 7
    )

    val yellow: List<ColorSet> = createColorSet(
        regularNameRes = R.string.yellow,
        lightNameRes = R.string.yellow_light,
        darkNameRes = R.string.yellow_dark,
        regularColors = listOf(
            0xfffffde7, 0xfffff9c4, 0xfffff59d, 0xfffff176, 0xffffee58,
            0xffffeb3b, 0xfffdd835, 0xfffbc02d, 0xfff9a825, 0xfff57f17),
        lightColors = listOf(
            0xffffffff, 0xfffffff7, 0xffffffcf, 0xffffffa8, 0xffffff8b,
            0xffffff72, 0xffffff6b, 0xfffff263, 0xffffd95a, 0xffffb04c),
        darkColors = listOf(
            0xffcccab5, 0xffcbc693, 0xffcbc26d, 0xffcabf45, 0xffc9bc1f,
            0xffc8b900, 0xffc6a700, 0xffc49000, 0xffc17900, 0xffbc5100),
        whiteAfterRegular = 9,
        whiteAfterLight = 9,
        whiteAfterDark = 8
    )

    val amber: List<ColorSet> = createColorSet(
        regularNameRes = R.string.amber,
        lightNameRes = R.string.amber_light,
        darkNameRes = R.string.amber_dark,
        regularColors = listOf(
            0xfffff8e1, 0xffffecb3, 0xffffe082, 0xffffd54f, 0xffffca28,
            0xffffc107, 0xffffb300, 0xffffa000, 0xffff8f00, 0xffff6f00),
        lightColors = listOf(
            0xffffffff, 0xffffffe5, 0xffffffb3, 0xffffff81, 0xfffffd61,
            0xfffff350, 0xffffe54c, 0xffffd149, 0xffffc046, 0xffffa040),
        darkColors = listOf(
            0xffccc5af, 0xffcbba83, 0xffcaae53, 0xffc8a415, 0xffc79a00,
            0xffc79100, 0xffc68400, 0xffc67100, 0xffc56000, 0xffc43e00),
        whiteAfterRegular = 9,
        whiteAfterLight = 9,
        whiteAfterDark = 8
    )

    val orange: List<ColorSet> = createColorSet(
        regularNameRes = R.string.orange,
        lightNameRes = R.string.orange_light,
        darkNameRes = R.string.orange_dark,
        regularColors = listOf(
            0xfffff3e0, 0xffffe0b2, 0xffffcc80, 0xffffb74d, 0xffffa726,
            0xffff9800, 0xfffb8c00, 0xfff57c00, 0xffef6c00, 0xffe65100),
        lightColors = listOf(
            0xffffffff, 0xffffffe4, 0xffffffb0, 0xffffe97d, 0xffffd95b,
            0xffffc947, 0xffffbd45, 0xffffad42, 0xffff9d3f, 0xffff833a),
        darkColors = listOf(
            0xffccc0ae, 0xffcbae82, 0xffca9b52, 0xffc88719, 0xffc77800,
            0xffc66900, 0xffc25e00, 0xffbb4d00, 0xffb53d00, 0xffac1900),
        whiteAfterRegular = 9,
        whiteAfterLight = 9,
        whiteAfterDark = 6
    )

    val deepOrange: List<ColorSet> = createColorSet(
        regularNameRes = R.string.deep_orange,
        lightNameRes = R.string.deep_orange_light,
        darkNameRes = R.string.deep_orange_dark,
        regularColors = listOf(
            0xfffbe9e7, 0xffffccbc, 0xffffab91, 0xffff8a65, 0xffff7043,
            0xffff5722, 0xfff4511e, 0xffe64a19, 0xffd84315, 0xffbf360c),
        lightColors = listOf(
            0xffffffff, 0xffffffee, 0xffffddc1, 0xffffbb93, 0xffffa270,
            0xffff8a50, 0xffff844c, 0xffff7d47, 0xffff7543, 0xfff9683a),
        darkColors = listOf(
            0xffc8b7b5, 0xffcb9b8c, 0xffc97b63, 0xffc75b39, 0xffc63f17,
            0xffc41c00, 0xffb91400, 0xffac0800, 0xff9f0000, 0xff870000),
        whiteAfterRegular = 8,
        whiteAfterLight = 9,
        whiteAfterDark = 3
    )

    val brown: List<ColorSet> = createColorSet(
        regularNameRes = R.string.brown,
        lightNameRes = R.string.brown_light,
        darkNameRes = R.string.brown_dark,
        regularColors = listOf(
            0xffefebe9, 0xffd7ccc8, 0xffbcaaa4, 0xffa1887f, 0xff8d6e63,
            0xff795548, 0xff6d4c41, 0xff5d4037, 0xff4e342e, 0xff3e2723),
        lightColors = listOf(
            0xffffffff, 0xfffffffb, 0xffefdcd5, 0xffd3b8ae, 0xffbe9c91,
            0xffa98274, 0xff9c786c, 0xff8b6b61, 0xff7b5e57, 0xff6a4f4b),
        darkColors = listOf(
            0xffbdb9b7, 0xffa69b97, 0xff8c7b75, 0xff725b53, 0xff5f4339,
            0xff4b2c20, 0xff40241a, 0xff321911, 0xff260e04, 0xff1b0000),
        whiteAfterRegular = 3,
        whiteAfterLight = 6,
        whiteAfterDark = 2
    )

    val gray: List<ColorSet> = createColorSet(
        regularNameRes = R.string.gray,
        lightNameRes = R.string.gray_light,
        darkNameRes = R.string.gray_dark,
        regularColors = listOf(
            0xfffafafa, 0xfff5f5f5, 0xffeeeeee, 0xffe0e0e0, 0xffbdbdbd,
            0xff9e9e9e, 0xff757575, 0xff616161, 0xff424242, 0xff212121),
        lightColors = listOf(
            0xffffffff, 0xffffffff, 0xffffffff, 0xffffffff, 0xffefefef,
            0xffcfcfcf, 0xffa4a4a4, 0xff8e8e8e, 0xff6d6d6d, 0xff484848),
        darkColors = listOf(
            0xffc7c7c7, 0xffc2c2c2, 0xffbcbcbc, 0xffaeaeae, 0xff8d8d8d,
            0xff707070, 0xff494949, 0xff373737, 0xff1b1b1b, 0xff000000),
        whiteAfterRegular = 5,
        whiteAfterLight = 7,
        whiteAfterDark = 4
    )

    val blueGray: List<ColorSet> = createColorSet(
        regularNameRes = R.string.blue_gray,
        lightNameRes = R.string.blue_gray_light,
        darkNameRes = R.string.blue_gray_dark,
        regularColors = listOf(
            0xffeceff1, 0xffcfd8dc, 0xffb0bec5, 0xff90a4ae, 0xff78909c,
            0xff607d8b, 0xff546e7a, 0xff455a64, 0xff37474f, 0xff263238),
        lightColors = listOf(
            0xffffffff, 0xffffffff, 0xffe2f1f8, 0xffc1d5e0, 0xffa7c0cd,
            0xff8eacbb, 0xff819ca9, 0xff718792, 0xff62727b, 0xff4f5b62),
        darkColors = listOf(
            0xffbabdbe, 0xff9ea7aa, 0xff808e95, 0xff62757f, 0xff4b636e,
            0xff34515e, 0xff29434e, 0xff1c313a, 0xff102027, 0xff000a12),
        whiteAfterRegular = 5,
        whiteAfterLight = 7,
        whiteAfterDark = 2
    )

    val redA: List<ColorSet> = createAlternativeColorSet(
        regularNameRes = R.string.red,
        lightNameRes = R.string.red_light,
        darkNameRes = R.string.red_dark,
        regularColors = listOf(0xffff8a80, 0xffff5252, 0xffff1744, 0xffd50000),
        lightColors = listOf(0xffffbcaf, 0xffff867f, 0xffff616f, 0xffff5131),
        darkColors = listOf(0xffc85a54, 0xffc50e29, 0xffc4001d, 0xff9b0000),
        whiteAfterRegular = 2,
        whiteAfterLight = 3,
        whiteAfterDark = 0
    )

    val pinkA: List<ColorSet> = createAlternativeColorSet(
        regularNameRes = R.string.pink,
        lightNameRes = R.string.pink_light,
        darkNameRes = R.string.pink_dark,
        regularColors = listOf(0xffff80ab, 0xffff4081, 0xfff50057, 0xffc51162),
        lightColors = listOf(0xffffb2dd, 0xffff79b0, 0xffff5983, 0xfffd558f),
        darkColors = listOf(0xffc94f7c, 0xffc60055, 0xffbb002f, 0xff8e0038),
        whiteAfterRegular = 2,
        whiteAfterLight = 3,
        whiteAfterDark = 0
    )

    val purpleA: List<ColorSet> = createAlternativeColorSet(
        regularNameRes = R.string.purple,
        lightNameRes = R.string.purple_light,
        darkNameRes = R.string.purple_dark,
        regularColors = listOf(0xffea80fc, 0xffe040fb, 0xffd500f9, 0xffaa00ff),
        lightColors = listOf(0xffffb2ff, 0xffff79ff, 0xffff5bff, 0xffe254ff),
        darkColors = listOf(0xffb64fc8, 0xffaa00c7, 0xff9e00c5, 0xff7200ca),
        whiteAfterRegular = 2,
        whiteAfterLight = 3,
        whiteAfterDark = 0
    )

    val deepPurpleA: List<ColorSet> = createAlternativeColorSet(
        regularNameRes = R.string.deep_purple,
        lightNameRes = R.string.deep_purple_light,
        darkNameRes = R.string.deep_purple_dark,
        regularColors = listOf(0xffb388ff, 0xff7c4dff, 0xff651fff, 0xff6200ea),
        lightColors = listOf(0xffe7b9ff, 0xffb47cff, 0xffa255ff, 0xff9d46ff),
        darkColors = listOf(0xff805acb, 0xff3f1dcb, 0xff0100ca, 0xff0a00b6),
        whiteAfterRegular = 0,
        whiteAfterLight = 3,
        whiteAfterDark = -1
    )

    val indigoA: List<ColorSet> = createAlternativeColorSet(
        regularNameRes = R.string.indigo,
        lightNameRes = R.string.indigo_light,
        darkNameRes = R.string.indigo_dark,
        regularColors = listOf(0xff8c9eff, 0xff536dfe, 0xff3d5afe, 0xff304ffe),
        lightColors = listOf(0xffc0cfff, 0xff8f9bff, 0xff8187ff, 0xff7a7cff),
        darkColors = listOf(0xff5870cb, 0xff0043ca, 0xff0031ca, 0xff0026ca),
        whiteAfterRegular = 1,
        whiteAfterLight = 3,
        whiteAfterDark = -1
    )

    val blueA: List<ColorSet> = createAlternativeColorSet(
        regularNameRes = R.string.blue,
        lightNameRes = R.string.blue_light,
        darkNameRes = R.string.blue_dark,
        regularColors = listOf(0xff82b1ff, 0xff448aff, 0xff2979ff, 0xff2962ff),
        lightColors = listOf(0xffb6e3ff, 0xff83b9ff, 0xff75a7ff, 0xff768fff),
        darkColors = listOf(0xff4d82cb, 0xff005ecb, 0xff004ecb, 0xff0039cb),
        whiteAfterRegular = 2,
        whiteAfterLight = 3,
        whiteAfterDark = 0
    )

    val lightBlueA: List<ColorSet> = createAlternativeColorSet(
        regularNameRes = R.string.light_blue,
        lightNameRes = R.string.light_blue_light,
        darkNameRes = R.string.light_blue_dark,
        regularColors = listOf(0xff80d8ff, 0xff40c4ff, 0xff00b0ff, 0xff0091ea),
        lightColors = listOf(0xffb5ffff, 0xff82f7ff, 0xff69e2ff, 0xff64c1ff),
        darkColors = listOf(0xff49a7cc, 0xff0094cc, 0xff0081cb, 0xff0064b7),
        whiteAfterRegular = 3,
        whiteAfterLight = 3,
        whiteAfterDark = 2
    )

    val cyanA: List<ColorSet> = createAlternativeColorSet(
        regularNameRes = R.string.cyan,
        lightNameRes = R.string.cyan_light,
        darkNameRes = R.string.cyan_dark,
        regularColors = listOf(0xff84ffff, 0xff18ffff, 0xff00e5ff, 0xff00b8d4),
        lightColors = listOf(0xffbaffff, 0xff76ffff, 0xff6effff, 0xff62ebff),
        darkColors = listOf(0xff4bcbcc, 0xff00cbcc, 0xff00b2cc, 0xff0088a3),
        whiteAfterRegular = 3,
        whiteAfterLight = 3,
        whiteAfterDark = 3
    )

    val tealA: List<ColorSet> = createAlternativeColorSet(
        regularNameRes = R.string.teal,
        lightNameRes = R.string.teal_light,
        darkNameRes = R.string.teal_dark,
        regularColors = listOf(0xffa7ffeb, 0xff64ffda, 0xff1de9b6, 0xff00bfa5),
        lightColors = listOf(0xffdbffff, 0xff9effff, 0xff6effe8, 0xff5df2d6),
        darkColors = listOf(0xff75ccb9, 0xff14cba8, 0xff00b686, 0xff008e76),
        whiteAfterRegular = 3,
        whiteAfterLight = 3,
        whiteAfterDark = 3
    )

    val greenA: List<ColorSet> = createAlternativeColorSet(
        regularNameRes = R.string.green,
        lightNameRes = R.string.green_light,
        darkNameRes = R.string.green_dark,
        regularColors = listOf(0xffb9f6ca, 0xff69f0ae, 0xff00e676, 0xff00c853),
        lightColors = listOf(0xffecfffd, 0xff9fffe0, 0xff66ffa6, 0xff5efc82),
        darkColors = listOf(0xff88c399, 0xff2bbd7e, 0xff00b248, 0xff009624),
        whiteAfterRegular = 3,
        whiteAfterLight = 3,
        whiteAfterDark = 3
    )

    val lightGreenA: List<ColorSet> = createAlternativeColorSet(
        regularNameRes = R.string.light_green,
        lightNameRes = R.string.light_green_light,
        darkNameRes = R.string.light_green_dark,
        regularColors = listOf(0xffccff90, 0xffb2ff59, 0xff76ff03, 0xff64dd17),
        lightColors = listOf(0xffffffc2, 0xffe7ff8c, 0xffb0ff57, 0xff9cff57),
        darkColors = listOf(0xff99cc60, 0xff7ecb20, 0xff32cb00, 0xff1faa00),
        whiteAfterRegular = 3,
        whiteAfterLight = 3,
        whiteAfterDark = 3
    )

    val limeA: List<ColorSet> = createAlternativeColorSet(
        regularNameRes = R.string.lime,
        lightNameRes = R.string.lime_light,
        darkNameRes = R.string.lime_dark,
        regularColors = listOf(0xfff4ff81, 0xffeeff41, 0xffc6ff00, 0xffaeea00),
        lightColors = listOf(0xffffffb3, 0xffffff78, 0xfffdff58, 0xffe4ff54),
        darkColors = listOf(0xffbfcc50, 0xffb8cc00, 0xff90cc00, 0xff79b700),
        whiteAfterRegular = 3,
        whiteAfterLight = 3,
        whiteAfterDark = 3
    )

    val yellowA: List<ColorSet> = createAlternativeColorSet(
        regularNameRes = R.string.yellow,
        lightNameRes = R.string.yellow_light,
        darkNameRes = R.string.yellow_dark,
        regularColors = listOf(0xffffff8d, 0xffffff00, 0xffffea00, 0xffffd600),
        lightColors = listOf(0xffffffbf, 0xffffff5a, 0xffffff56, 0xffffff52),
        darkColors = listOf(0xffcacc5d, 0xffc7cc00, 0xffc7b800, 0xffc7a500),
        whiteAfterRegular = 3,
        whiteAfterLight = 3,
        whiteAfterDark = 3
    )

    val amberA: List<ColorSet> = createAlternativeColorSet(
        regularNameRes = R.string.amber,
        lightNameRes = R.string.amber_light,
        darkNameRes = R.string.amber_dark,
        regularColors = listOf(0xffffe57f, 0xffffd740, 0xffffc400, 0xffffab00),
        lightColors = listOf(0xffffffb0, 0xffffff74, 0xfffff64f, 0xffffdd4b),
        darkColors = listOf(0xffcab350, 0xffc8a600, 0xffc79400, 0xffc67c00),
        whiteAfterRegular = 3,
        whiteAfterLight = 3,
        whiteAfterDark = 3
    )

    val orangeA: List<ColorSet> = createAlternativeColorSet(
        regularNameRes = R.string.orange,
        lightNameRes = R.string.orange_light,
        darkNameRes = R.string.orange_dark,
        regularColors = listOf(0xffffd180, 0xffffab40, 0xffff9100, 0xffff6d00),
        lightColors = listOf(0xffffffb1, 0xffffdd71, 0xffffc246, 0xffff9e40),
        darkColors = listOf(0xffcaa052, 0xffc77c02, 0xffc56200, 0xffc43c00),
        whiteAfterRegular = 3,
        whiteAfterLight = 3,
        whiteAfterDark = 2
    )

    val deepOrangeA: List<ColorSet> = createAlternativeColorSet(
        regularNameRes = R.string.deep_orange,
        lightNameRes = R.string.deep_orange_light,
        darkNameRes = R.string.deep_orange_dark,
        regularColors = listOf(0xffff9e80, 0xffff6e40, 0xffff3d00, 0xffdd2c00),
        lightColors = listOf(0xffffd0b0, 0xffffa06d, 0xffff7539, 0xffff6434),
        darkColors = listOf(0xffc96f53, 0xffc53d13, 0xffc30000, 0xffa30000),
        whiteAfterRegular = 2,
        whiteAfterLight = 3,
        whiteAfterDark = 0
    )

    companion object {
        /**
         * Creates color set with 10 material colors
         * */
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
            val alphas = listOf("50", "100", "200", "300", "400", "500", "600", "700", "800", "900")

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

        /**
         * Creates color set with 4 material alternative colors
         * */
        private fun createAlternativeColorSet(
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
            assert(regularColors.size == 4) { "Regular colors size must be equal 4, but provided ${regularColors.size}" }
            assert(lightColors.size == 4) { "Light colors size must be equal 4, but provided ${regularColors.size}" }
            assert(darkColors.size == 4) { "Dark colors size must be equal 4, but provided ${regularColors.size}" }

            val colorSets = mutableListOf<ColorSet>()
            val alphas = listOf("100A", "200A", "400A", "700A")

            for (i in 0..3) {
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