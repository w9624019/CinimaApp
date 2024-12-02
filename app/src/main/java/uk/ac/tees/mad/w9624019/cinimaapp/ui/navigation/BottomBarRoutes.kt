package uk.ac.tees.mad.w9624019.cinimaapp.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector

enum class BottomBarRoutes(
    val id: Int,
    val title: String,
    val routes: String,
    val icon: ImageVector
) {

    HOME(1, "Home", "/home", Icons.Default.Home),
    FAVORITE(2, "Favorites", "/favorite", Icons.Default.Star),
    LOGOUT(2, "Logout", "/logout", Icons.Default.ExitToApp)
}