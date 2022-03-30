package viacheslav.chugunov.materialtheme

sealed interface PreviewScreen {
    val previous: PreviewScreen
    val next: PreviewScreen

    object Login : PreviewScreen {
        override val previous: PreviewScreen = List
        override val next: PreviewScreen = List
    }

    object List : PreviewScreen {
        override val previous: PreviewScreen = Login
        override val next: PreviewScreen = Login
    }
}