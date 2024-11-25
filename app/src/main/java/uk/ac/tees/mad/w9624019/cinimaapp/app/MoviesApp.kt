package uk.ac.tees.mad.w9624019.cinimaapp.app

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import uk.ac.tees.mad.w9624019.cinimaapp.feature.home.HomeScreen
import uk.ac.tees.mad.w9624019.cinimaapp.feature.home.data.HomeViewModel
import uk.ac.tees.mad.w9624019.cinimaapp.feature.login.LoginScreen
import uk.ac.tees.mad.w9624019.cinimaapp.feature.signup.SignUpScreen
import uk.ac.tees.mad.w9624019.cinimaapp.feature.termsconditions.TermsAndConditionsScreen
import uk.ac.tees.mad.w9624019.cinimaapp.utils.navigation.MoviesAppRouter
import uk.ac.tees.mad.w9624019.cinimaapp.utils.navigation.Screen

@Composable
fun MoviesApp(homeViewModel: HomeViewModel = viewModel()) {

    homeViewModel.checkForActiveSession()
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {

        if (homeViewModel.isUserLoggedIn.value == true) {
            MoviesAppRouter.navigateTo(Screen.HomeScreen)
        }

        Crossfade(targetState = MoviesAppRouter.currentScreen, label = "") { currentState->
            when(currentState.value){
                is Screen.LoginScreen->{
                    LoginScreen()
                }
                is Screen.SignUpScreen->{
                    SignUpScreen()
                }
                is Screen.TermsAndConditionsScreen ->{
                    TermsAndConditionsScreen()
                }
                is Screen.HomeScreen->{
                    HomeScreen()
                }
            }
        }

    }

}