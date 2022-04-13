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

    }
}