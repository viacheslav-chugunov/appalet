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
     * @property number an individual position in all previews
     * @property COUNT all previews count
     * */
    abstract class Preview(route: String) : Screen(route, showButtonBar = true, newTask = true) {
        abstract val previous: Preview
        abstract val next: Preview
        abstract val number: Int

        companion object {
            const val COUNT = 2
        }
    }



    object Input : Preview(Route.INPUT) {
        override val previous: Preview = List
        override val next: Preview = List
        override val number: Int = 1
    }

    object List : Preview(Route.LIST) {
        override val previous: Preview = Input
        override val next: Preview = Input
        override val number: Int = 2
    }



    object Route {
        const val INPUT = "input"
        const val LIST = "list"
    }
}