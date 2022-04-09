package viacheslav.chugunov.core.util

import viacheslav.chugunov.core.R

interface ResourceSerializer {
    fun toOrdinal(nameRes: Int): Int
    fun fromOrdinal(ordinal: Int): Int



    class ColorNameSerializer(
        private val default: Color = Color.WHITE
    ) : ResourceSerializer {
        override fun toOrdinal(nameRes: Int): Int =
            Color.all.find { nameRes == it.res }?.ordinal ?: default.ordinal

        override fun fromOrdinal(ordinal: Int): Int =
            Color.all.find { ordinal == it.ordinal }?.res ?: default.res

        enum class Color(val res: Int) {
            RED(R.string.red), RED_LIGHT(R.string.red_light), RED_DARK(R.string.red_dark),
            PINK(R.string.pink), PINK_LIGHT(R.string.pink_light), PINK_DARK(R.string.pink_dark),
            PURPLE(R.string.purple), PURPLE_LIGHT(R.string.purple_light), PURPLE_DARK(R.string.purple_dark),
            DEEP_PURPLE(R.string.deep_purple), DEEP_PURPLE_LIGHT(R.string.deep_purple_light), DEEP_PURPLE_DARK(R.string.deep_purple_dark),
            INDIGO(R.string.indigo), INDIGO_LIGHT(R.string.indigo_light), INDIGO_DARK(R.string.indigo_dark),
            BLUE(R.string.blue), BLUE_LIGHT(R.string.blue_light), BLUE_DARK(R.string.blue_dark),
            LIGHT_BLUE(R.string.light_blue), LIGHT_BLUE_LIGHT(R.string.light_blue_light), LIGHT_BLUE_DARK(R.string.light_blue_dark),
            CYAN(R.string.cyan), CYAN_LIGHT(R.string.cyan_light), CYAN_DARK(R.string.cyan_dark),
            TEAL(R.string.teal), TEAL_LIGHT(R.string.teal_light), TEAL_DARK(R.string.teal_dark),
            GREEN(R.string.green), GREEN_LIGHT(R.string.green_light), GREEN_DARK(R.string.green_dark),
            LIGHT_GREEN(R.string.light_green), LIGHT_GREEN_LIGHT(R.string.light_green_light), LIGHT_GREEN_DARK(R.string.light_green_dark),
            LIME(R.string.lime), LIME_LIGHT(R.string.lime_light), LIME_DARK(R.string.lime_dark),
            YELLOW(R.string.yellow), YELLOW_LIGHT(R.string.yellow_light), YELLOW_DARK(R.string.yellow_dark),
            AMBER(R.string.amber), AMBER_LIGHT(R.string.amber_light), AMBER_DARK(R.string.amber_dark),
            ORANGE(R.string.orange), ORANGE_LIGHT(R.string.orange_light), ORANGE_DARK(R.string.orange_dark),
            DEEP_ORANGE(R.string.deep_orange), DEEP_ORANGE_LIGHT(R.string.deep_orange_light), DEEP_ORANGE_DARK(R.string.deep_orange_dark),
            BROWN(R.string.brown), BROWN_LIGHT(R.string.brown_light), BROWN_DARK(R.string.brown_dark),
            GRAY(R.string.gray), GRAY_LIGHT(R.string.gray_light), GRAY_DARK(R.string.gray_dark),
            BLUE_GRAY(R.string.blue_gray), BLUE_GRAY_LIGHT(R.string.blue_gray_light), BLUE_GRAY_DARK(R.string.blue_gray_dark),
            WHITE(R.string.white), BLACK(R.string.black);

            companion object {
                val all: Set<Color> get() = setOf(
                    RED, RED_LIGHT, RED_DARK, PINK, PINK_LIGHT, PINK_DARK, PURPLE, PURPLE_LIGHT,
                    PURPLE_DARK, DEEP_PURPLE, DEEP_PURPLE_LIGHT, DEEP_PURPLE_DARK, INDIGO, INDIGO_LIGHT,
                    INDIGO_DARK, BLUE, BLUE_LIGHT, BLUE_DARK, LIGHT_BLUE, LIGHT_BLUE_LIGHT, LIGHT_BLUE_DARK,
                    CYAN, CYAN_LIGHT, CYAN_DARK, TEAL, TEAL_LIGHT, TEAL_DARK, GREEN, GREEN_LIGHT, GREEN_DARK,
                    LIGHT_GREEN, LIGHT_GREEN_LIGHT, LIGHT_GREEN_DARK, LIME, LIME_LIGHT, LIME_DARK, YELLOW,
                    YELLOW_LIGHT, YELLOW_DARK, AMBER, AMBER_LIGHT, AMBER_DARK, ORANGE, ORANGE_LIGHT,
                    ORANGE_DARK, DEEP_ORANGE, DEEP_ORANGE_LIGHT, DEEP_ORANGE_DARK, BROWN, BROWN_LIGHT,
                    BROWN_DARK, GRAY, GRAY_LIGHT, GRAY_DARK, BLUE_GRAY, BLUE_GRAY_LIGHT, BLUE_GRAY_DARK)
            }
        }
    }
}