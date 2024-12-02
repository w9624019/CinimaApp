package uk.ac.tees.mad.w9624019.cinimaapp.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.google.firebase.auth.FirebaseAuth
import uk.ac.tees.mad.w9624019.cinimaapp.ui.screens.LoginScreen
import uk.ac.tees.mad.w9624019.cinimaapp.ui.screens.DetailMovieScreen
import uk.ac.tees.mad.w9624019.cinimaapp.ui.screens.FavoriteMovieScreen
import uk.ac.tees.mad.w9624019.cinimaapp.ui.screens.HomeScreen
import uk.ac.tees.mad.w9624019.cinimaapp.ui.viewmodels.MovieViewModel

@Composable
fun BottomBarNavigation(
    navHostController: NavHostController,
    padding: PaddingValues,
    movieViewModel: MovieViewModel
) {
    NavHost(
        navController = navHostController,
        startDestination = ScreenRoutes.BottomBar.route,
        modifier = Modifier.padding(padding)
    ) {
        navigation(
            route = ScreenRoutes.BottomBar.route,
            startDestination = BottomBarRoutes.HOME.routes
        ) {
            composable(BottomBarRoutes.HOME.routes) {
                HomeScreen(navController = navHostController, viewModel = movieViewModel)
            }
            composable(BottomBarRoutes.FAVORITE.routes) {
                FavoriteMovieScreen(navController = navHostController, viewModel = movieViewModel)
            }
            composable(BottomBarRoutes.LOGOUT.routes) {
                FirebaseAuth.getInstance().signOut()
                LoginScreen()
            }
        }
        composable(ScreenRoutes.Detail.route) { backStackEntry ->
            val movieId = backStackEntry.arguments?.getString("movieId")?.toIntOrNull()
            if (movieId != null) {
                DetailMovieScreen(movieId,movieViewModel)
            } else {
                // Handle invalid movieId
            }
        }
    }
}
