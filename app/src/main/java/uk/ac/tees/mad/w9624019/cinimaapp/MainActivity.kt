package uk.ac.tees.mad.w9624019.cinimaapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import uk.ac.tees.mad.w9624019.cinimaapp.screens.GraphScreen
import uk.ac.tees.mad.w9624019.cinimaapp.screens.LandingScreen
import uk.ac.tees.mad.w9624019.cinimaapp.screens.LoginScreen
import cis4034.starwarscensus.ui.theme.CIS4034StarWarsCensusTheme
import uk.ac.tees.mad.w9624019.cinimaapp.ui.theme.CinimaAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CIS4034StarWarsCensusTheme {
                Scaffold( modifier = Modifier.fillMaxSize() ) { innerPadding ->
                    App(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun App(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        val navController = rememberNavController()
        NavHost(navController, startDestination = "login") {
            composable(route = "login") {
                LoginScreen(
                    navController,
                    viewModel()
                )
            }
            composable(route = "landing") { navStackEntry ->
                LandingScreen(navController, viewModel())
            }
            composable(route = "graph")  { navStackEntry ->
                GraphScreen(navController, viewModel())
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CinimaAppTheme() {
        App()
    }
}