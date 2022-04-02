package viacheslav.chugunov.core.util

import android.content.Context
import viacheslav.chugunov.core.R
import java.lang.IllegalStateException

sealed class Screen(
    val route: String,
    val showButtonBar: Boolean,
    val title: String? = null,
    val titleId: Int? = null,
    val newTask: Boolean = false
) {

    val hasTitle: Boolean get() = title != null || titleId != null

    fun getTitle(context: Context): String = when {
        titleId != null -> context.getString(titleId)
        title != null -> title
        else -> throw IllegalStateException("titleId and title were null")
    }

    object Colors : Screen(
        route = Route.COLORS,
        showButtonBar = false,
        titleId = R.string.colors,
        newTask = false
    )

    /**
     * Screens associated as previewers of material theme must be inherited from Preview class. User
     * can moving between previews on UI.
     *
     * @property previous previous preview shown, when user invokes "Back" button on UI.
     * @property next next preview shown, when user invokes "Next" button on UI.
     * */
    abstract class Preview(route: String, title: String) : Screen(
        route = route,
        showButtonBar = true,
        title = title,
        newTask = true
    ) {
        abstract val previous: Preview
        abstract val next: Preview
    }



    object Input : Preview(Route.INPUT, title = "1 / 3") {
        override val previous: Preview = Dialog
        override val next: Preview = List
    }

    object List : Preview(Route.LIST, title = "2 / 3") {
        override val previous: Preview = Input
        override val next: Preview = Dialog
    }

    object Dialog : Preview(Route.DIALOG, title = "3 / 3") {
        override val previous: Preview = List
        override val next: Preview = Input
    }



    object Route {
        const val COLORS = "colors"
        const val INPUT = "input"
        const val LIST = "list"
        const val DIALOG = "dialog"
    }
}