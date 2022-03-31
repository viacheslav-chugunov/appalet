package viacheslav.chugunov.core.util

sealed class Screen(
    val route: String,
    val showButtonBar: Boolean,
    val newTask: Boolean = false
) {

    /**
     * Screens associated as previewers of material theme must be inherited from Preview class. User
     * can moving between previews on UI.
     *
     * @property previous previous preview shown, when user invokes "Back" button on UI.
     * @property next next preview shown, when user invokes "Next" button on UI.
     * */
    abstract class Preview(route: String) : Screen(route, showButtonBar = true) {
        abstract val previous: Preview
        abstract val next: Preview
    }



    object Login : Preview(Route.LOGIN) {
        override val previous: Preview = List
        override val next: Preview = List
    }

    object List : Preview(Route.LIST) {
        override val previous: Preview = Login
        override val next: Preview = Login
    }



    object Route {
        const val LOGIN = "login"
        const val LIST = "list"
    }
}