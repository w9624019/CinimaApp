package uk.ac.tees.mad.w9624019.cinimaapp.utils.navigation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.google.firebase.auth.FirebaseAuth

sealed class Screen(){

    object LoginScreen : Screen()
    object SignUpScreen : Screen()
    object TermsAndConditionsScreen : Screen()
    object HomeScreen : Screen()

}

object MoviesAppRouter{

    var currentScreen: MutableState<Screen> = mutableStateOf(Screen.LoginScreen)
    fun navigateTo(destination : Screen){
        if(destination.equals(Screen.LoginScreen)){
            FirebaseAuth.getInstance().signOut()
        }
        if(destination.equals(Screen.HomeScreen)){
            if(FirebaseAuth.getInstance().currentUser==null){
                currentScreen.value = Screen.LoginScreen
            }
        }
        currentScreen.value = destination
    }
}