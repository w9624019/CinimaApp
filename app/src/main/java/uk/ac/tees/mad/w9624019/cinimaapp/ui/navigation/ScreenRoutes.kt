package uk.ac.tees.mad.w9624019.cinimaapp.ui.navigation

sealed class ScreenRoutes(val route: String) {
    object BottomBar : ScreenRoutes("/bottombar")
    object Detail : ScreenRoutes("/detail/{movieId}")

}