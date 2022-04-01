package viacheslav.chugunov.materialtheme.extension

import androidx.navigation.NavController
import viacheslav.chugunov.core.util.Screen

fun NavController.navigate(screen: Screen) {
    val currentRoute = currentDestination?.route ?: ""
    navigate(screen.route) {
        if (screen.newTask) {
            popUpTo(currentRoute) {
                inclusive = true
            }
        }
    }
}