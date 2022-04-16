package viacheslav.chugunov.core.model

interface PreferredColors {
    val lightBackground: Coloring
    val darkBackground: Coloring


    open class Default(
        override val lightBackground: Coloring = Coloring.White,
        override val darkBackground: Coloring = Coloring.Gray900
    ) : PreferredColors {

        override fun toString(): String =
            """PreferredColors {
                |    lightBackground = $lightBackground
                |    darkBackground = $darkBackground
                |}
            """.trimMargin()

        override fun equals(other: Any?): Boolean =
            other is PreferredColors &&
                    lightBackground == other.lightBackground &&
                    darkBackground == other.darkBackground

        override fun hashCode(): Int {
            var result = lightBackground.hashCode()
            result = 31 * result + darkBackground.hashCode()
            return result
        }

    }
}