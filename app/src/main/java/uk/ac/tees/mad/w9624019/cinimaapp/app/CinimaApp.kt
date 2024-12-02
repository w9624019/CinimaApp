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
import uk.ac.tees.mad.w9624019.cinimaapp.ui.screens.LoginScreen
import uk.ac.tees.mad.w9624019.cinimaapp.ui.screens.SignUpScreen
import uk.ac.tees.mad.w9624019.cinimaapp.ui.screens.TermsAndConditionsScreen
import uk.ac.tees.mad.w9624019.cinimaapp.ui.utils.navigation.CinimaAppRouter
import uk.ac.tees.mad.w9624019.cinimaapp.ui.utils.navigation.Screen

@Composable
fun CinimaApp(homeViewModel: HomeViewModel = viewModel()) {

    homeViewModel.checkForActiveSession()
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {

        if (homeViewModel.isUserLoggedIn.value == true) {
            CinimaAppRouter.navigateTo(Screen.HomeScreen)
        }

        Crossfade(targetState = CinimaAppRouter.currentScreen, label = "") { currentState->
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

