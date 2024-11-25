package uk.ac.tees.mad.w9624019.cinimaapp.ui.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Logout
import androidx.compose.ui.graphics.vector.ImageVector

enum class BottomBarRoutes(
    val id: Int,
    val title: String,
    val routes: String,
    val icon: ImageVector
) {

    HOME(1, "Home", "/home", Icons.Default.Home),
    FAVORITE(2, "Favorites", "/favorite", Icons.Default.Favorite),
    LOGOUT(2, "Logout", "/logout", Icons.Default.Logout)
}