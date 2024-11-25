package uk.ac.tees.mad.w9624019.cinimaapp.feature.login.data

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import uk.ac.tees.mad.w9624019.cinimaapp.utils.Validator
import uk.ac.tees.mad.w9624019.cinimaapp.utils.navigation.MoviesAppRouter
import uk.ac.tees.mad.w9624019.cinimaapp.utils.navigation.Screen

class LoginViewModel : ViewModel() {

    var loginUIState = mutableStateOf(LoginUIState())
    var allValidationPassed = mutableStateOf(false)
    var loginInProgress = mutableStateOf(false)

    fun onEvent(event: LoginUIEvent) {
        when (event) {
            is LoginUIEvent.EmailChanged -> {
                loginUIState.value = loginUIState.value.copy(
                    email = event.email
                )

                if (loginUIState.value.email.isNotEmpty()) {
                    loginUIState.value = loginUIState.value.copy(
                        emailMsgError = ""
                    )
                }
            }

            is LoginUIEvent.PasswordChanged -> {
                loginUIState.value = loginUIState.value.copy(
                    password = event.password
                )

                if (loginUIState.value.email.isNotEmpty()) {
                    loginUIState.value = loginUIState.value.copy(
                        passwordMsgError = ""
                    )
                }
            }

            is LoginUIEvent.LogInButtonClicked -> {
                FirebaseAuth.getInstance().signOut()
                if (validateDateWithRules()) {
                    login()
                }
            }
        }
    }

    private fun validateDateWithRules(): Boolean {
        val emailResult = Validator.validateEmail(
            emailValue = loginUIState.value.email
        )
        val passwordResult = Validator.validatePassword(
            passwordValue = loginUIState.value.password
        )
        //todo set data
        loginUIState.value = loginUIState.value.copy(
            emailError = emailResult.status,
            passwordError = passwordResult.status
        )
        errorMsgForm()
        //todo kondisi
        allValidationPassed.value = emailResult.status && passwordResult.status

        return allValidationPassed.value
    }

    private fun errorMsgForm(): Boolean {

        loginUIState.value = loginUIState.value.copy(
            emailMsgError = "",
            passwordMsgError = ""
        )

        if (!loginUIState.value.emailError) {
            loginUIState.value = loginUIState.value.copy(
                emailMsgError = "Please enter valid last email !"
            )
            return false
        }
        if (!loginUIState.value.passwordError) {
            loginUIState.value = loginUIState.value.copy(
                passwordMsgError = "Please enter valid last password !"

            )
            return false
        }

        return true
    }

    private fun login() {
        FirebaseAuth.getInstance().signOut()
        loginInProgress.value = true
        loginUIState.value.msgError = ""
        FirebaseAuth
            .getInstance()
            .signInWithEmailAndPassword(loginUIState.value.email, loginUIState.value.password)
            .addOnCompleteListener {

                if (it.isSuccessful) {
                    loginInProgress.value = false
                    MoviesAppRouter.navigateTo(Screen.HomeScreen)
                } else {
                    loginInProgress.value = false
                }
            }
            .addOnFailureListener {
                loginInProgress.value = false
                loginUIState.value.msgError = it.localizedMessage.toString()
            }
    }

    fun errorMsgServer(context : Context){
        if (loginUIState.value.msgError != ""){
            Toast.makeText(
                context,
                loginUIState.value.msgError,
                Toast.LENGTH_SHORT
            ).show()
        }

    }
}