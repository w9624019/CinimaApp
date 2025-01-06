package uk.ac.tees.mad.w9624019.cinimaapp.feature.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import uk.ac.tees.mad.w9624019.cinimaapp.ui.navigation.BottomBarNavigation
import uk.ac.tees.mad.w9624019.cinimaapp.ui.navigation.BottomBarRoutes
import uk.ac.tees.mad.w9624019.cinimaapp.ui.navigation.rememberAppState
import uk.ac.tees.mad.w9624019.cinimaapp.ui.theme.CinimaAppTheme
import uk.ac.tees.mad.w9624019.cinimaapp.ui.viewmodels.MovieViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(movieViewModel: MovieViewModel = viewModel()) {
    val context = LocalContext.current
    CinimaAppTheme {
        val appState = rememberAppState()
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            val tabList = listOf(
                BottomBarRoutes.HOME,
                BottomBarRoutes.FAVORITE,
                BottomBarRoutes.LOGOUT
            )

            Scaffold(
                bottomBar = {
                    if (appState.shouldShowBottomBar)
                        NavigationBar(modifier =  Modifier.background(Color.Transparent)) {
                            val navStackBackEntry by appState.navHostController.currentBackStackEntryAsState()
                            val currentDestination = navStackBackEntry?.destination

                            tabList.forEach { tab ->
                                NavigationBarItem(
                                    selected = currentDestination?.hierarchy?.any { it.route == tab.routes } == true,
                                    onClick = { appState.navHostController.navigate(tab.routes) },
                                    icon ={ Icon(tab.icon, contentDescription = null) },
                                )
                            }
                        }
                }
            ) { innerPadding ->
                BottomBarNavigation(
                    navHostController = appState.navHostController,
                    padding = innerPadding,
                    movieViewModel = movieViewModel,
                )
            }
        }
    }

}


@Preview
@Composable
fun DefaultPreviewOfHomeScreen() {
    HomeScreen()
}