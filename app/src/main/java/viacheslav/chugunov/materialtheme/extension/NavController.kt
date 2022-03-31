package viacheslav.chugunov.materialtheme.extension

import androidx.navigation.NavController
import viacheslav.chugunov.core.util.Screen

fun NavController.navigate(screen: Screen) {
    navigate(screen.route)
}