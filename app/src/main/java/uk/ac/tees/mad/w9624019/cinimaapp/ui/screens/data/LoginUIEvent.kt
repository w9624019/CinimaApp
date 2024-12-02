package uk.ac.tees.mad.w9624019.cinimaapp.ui.screens.data

sealed class LoginUIEvent {

    data class EmailChanged(val email: String) : LoginUIEvent()
    data class PasswordChanged(val password: String) : LoginUIEvent()
    object  LogInButtonClicked : LoginUIEvent()

}
