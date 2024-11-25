package uk.ac.tees.mad.w9624019.cinimaapp.feature.signup.data

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import uk.ac.tees.mad.w9624019.cinimaapp.feature.signup.data.SignUpUIEvent
import uk.ac.tees.mad.w9624019.cinimaapp.utils.Validator
import uk.ac.tees.mad.w9624019.cinimaapp.utils.navigation.MoviesAppRouter
import uk.ac.tees.mad.w9624019.cinimaapp.utils.navigation.Screen

class SignUpViewModel : ViewModel() {

    var signUpUIState = mutableStateOf(SignUpUIState())
    private var allValidationPassed = mutableStateOf(false)
    var registerInProgress = mutableStateOf(false)

    fun onEvent(event: SignUpUIEvent) {

        when (event) {
            is SignUpUIEvent.FirstNameChanged -> {
                signUpUIState.value = signUpUIState.value.copy(
                    firstName = event.firstName
                )
                if (signUpUIState.value.firstName.isNotEmpty()) {
                    signUpUIState.value = signUpUIState.value.copy(
                        firstNameMsgError = ""
                    )
                }
            }

            is SignUpUIEvent.LastNameChanged -> {
                signUpUIState.value = signUpUIState.value.copy(
                    lastName = event.lastName
                )
                if (signUpUIState.value.lastName.isNotEmpty()) {
                    signUpUIState.value = signUpUIState.value.copy(
                        lastNameMsgError = ""
                    )
                }
            }

            is SignUpUIEvent.EmailChanged -> {
                signUpUIState.value = signUpUIState.value.copy(
                    email = event.email
                )
                if (signUpUIState.value.email.isNotEmpty()) {
                    signUpUIState.value = signUpUIState.value.copy(
                        emailMsgError = ""
                    )
                }
            }

            is SignUpUIEvent.PasswordChanged -> {
                signUpUIState.value = signUpUIState.value.copy(
                    password = event.password
                )
                if (signUpUIState.value.password.isNotEmpty()) {
                    signUpUIState.value = signUpUIState.value.copy(
                        passwordMsgError = ""
                    )
                }
            }

            is SignUpUIEvent.PrivacyPolicyCheckBoxClicked -> {
                signUpUIState.value = signUpUIState.value.copy(
                    privacyPolicyAccepted = event.status
                )
            }

            is SignUpUIEvent.SignUpButtonClicked -> {
                if (validateDateWithRules()) {
                    signUp()
                }

            }
        }


    }

    private fun signUp() {
        createUserFirebase(
            email = signUpUIState.value.email,
            password = signUpUIState.value.password
        )

    }

    private fun validateDateWithRules(): Boolean {
        val fNameResult = Validator.validateFirsName(
            fName = signUpUIState.value.firstName
        )
        val lNameResult = Validator.validateLastName(
            lName = signUpUIState.value.lastName
        )
        val emailResult = Validator.validateEmail(
            emailValue = signUpUIState.value.email
        )
        val passwordResult = Validator.validatePassword(
            passwordValue = signUpUIState.value.password
        )

        val privacyPolicyCheckResult = Validator.validateCheckBox(
            statusValue = signUpUIState.value.privacyPolicyAccepted
        )


        signUpUIState.value = signUpUIState.value.copy(
            firstNameError = fNameResult.status,
            lastNameError = lNameResult.status,
            emailError = emailResult.status,
            passwordError = passwordResult.status,
            privacyPolicyError = privacyPolicyCheckResult.status

        )

        errorMsgForm()
        allValidationPassed.value = fNameResult.status
                && lNameResult.status
                && emailResult.status
                && passwordResult.status
                && privacyPolicyCheckResult.status

        return allValidationPassed.value
    }

    private fun errorMsgForm(): Boolean {
        signUpUIState.value = signUpUIState.value.copy(
            firstNameMsgError = "",
            lastNameMsgError = "",
            emailMsgError = "",
            passwordMsgError = "",
            privacyPolicyAcceptedMsgError = ""
        )

        if (!signUpUIState.value.firstNameError) {

            signUpUIState.value = signUpUIState.value.copy(
                firstNameMsgError = "Please enter valid Name !"
            )
            return false
        }
        if (!signUpUIState.value.lastNameError) {
            signUpUIState.value = signUpUIState.value.copy(
                lastNameMsgError = "Please enter valid last name !"
            )
            return false
        }
        if (!signUpUIState.value.emailError) {

            signUpUIState.value = signUpUIState.value.copy(
                emailMsgError = "Please enter valid email !"

            )
            return false
        }
        if (!signUpUIState.value.passwordError) {

            signUpUIState.value = signUpUIState.value.copy(
                passwordMsgError = "Please enter valid password !"
            )
            return false
        }

        if (!signUpUIState.value.privacyPolicyError) {
            signUpUIState.value = signUpUIState.value.copy(
                privacyPolicyAcceptedMsgError = "Check Privacy Policy first !"
            )
            return false
        }
        return true
    }

    private fun createUserFirebase(email: String, password: String) {
        signUpUIState.value.errorMsg = ""
        registerInProgress.value = true
        FirebaseAuth
            .getInstance()
            .createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                registerInProgress.value = false
                if (it.isSuccessful) {
                    MoviesAppRouter.navigateTo(Screen.HomeScreen)
                }
            }
            .addOnFailureListener {
                registerInProgress.value = false
                signUpUIState.value.errorMsg = it.localizedMessage.toString()
            }
    }

}