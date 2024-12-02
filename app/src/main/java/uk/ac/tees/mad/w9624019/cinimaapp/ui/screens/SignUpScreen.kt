package uk.ac.tees.mad.w9624019.cinimaapp.ui.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import uk.ac.tees.mad.w9624019.cinimaapp.R
import uk.ac.tees.mad.w9624019.cinimaapp.ui.screens.data.SignUpUIEvent
import uk.ac.tees.mad.w9624019.cinimaapp.ui.screens.data.SignUpViewModel
import uk.ac.tees.mad.w9624019.cinimaapp.theme.componensts.ButtonComponent
import uk.ac.tees.mad.w9624019.cinimaapp.theme.componensts.CheckBoxComponent
import uk.ac.tees.mad.w9624019.cinimaapp.theme.componensts.ClickableLoginTextComponent
import uk.ac.tees.mad.w9624019.cinimaapp.theme.componensts.DividerTextComponent
import uk.ac.tees.mad.w9624019.cinimaapp.theme.componensts.HeadingTextComponent
import uk.ac.tees.mad.w9624019.cinimaapp.theme.componensts.NormalTextComponent
import uk.ac.tees.mad.w9624019.cinimaapp.theme.componensts.PasswordTextFieldComponent
import uk.ac.tees.mad.w9624019.cinimaapp.theme.componensts.TextFieldComponent
import uk.ac.tees.mad.w9624019.cinimaapp.ui.utils.navigation.CinimaAppRouter
import uk.ac.tees.mad.w9624019.cinimaapp.ui.utils.navigation.Screen
import uk.ac.tees.mad.w9624019.cinimaapp.ui.utils.navigation.SystemBackButtonHandler

@Composable
fun SignUpScreen(signUpViewModel: SignUpViewModel = viewModel()) {
    val context = LocalContext.current


    SystemBackButtonHandler {
        CinimaAppRouter.navigateTo(Screen.LoginScreen)
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(28.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
            ) {
                NormalTextComponent(value = stringResource(id = R.string.app_name))
                HeadingTextComponent(value = stringResource(id = R.string.create_account))
                Spacer(modifier = Modifier.height(20.dp))
                TextFieldComponent(
                    labelValue = stringResource(id = R.string.first_name),
                    painterResource = painterResource(id = R.drawable.ic_user),
                    keyboardType = KeyboardType.Text,
                    onTextSelected = {
                        signUpViewModel.onEvent(SignUpUIEvent.FirstNameChanged(it))
                    },
                    errorStatus = signUpViewModel.signUpUIState.value.firstNameError,
                    errorMsg = signUpViewModel.signUpUIState.value.firstNameMsgError
                )

                TextFieldComponent(
                    labelValue = stringResource(id = R.string.last_name),
                    painterResource = painterResource(id = R.drawable.ic_user),
                    keyboardType = KeyboardType.Text,
                    onTextSelected = {
                        signUpViewModel.onEvent(SignUpUIEvent.LastNameChanged(it))

                    },
                    errorStatus = signUpViewModel.signUpUIState.value.lastNameError,
                    errorMsg = signUpViewModel.signUpUIState.value.lastNameMsgError
                )

                TextFieldComponent(
                    labelValue = stringResource(id = R.string.email),
                    painterResource = painterResource(id = R.drawable.ic_email),
                    keyboardType = KeyboardType.Email,
                    onTextSelected = {
                        signUpViewModel.onEvent(SignUpUIEvent.EmailChanged(it))

                    },
                    errorStatus = signUpViewModel.signUpUIState.value.emailError,
                    errorMsg = signUpViewModel.signUpUIState.value.emailMsgError

                )

                PasswordTextFieldComponent(
                    labelValue = stringResource(id = R.string.password),
                    painterResource = painterResource(id = R.drawable.ic_lock),
                    onTextSelected = {
                        signUpViewModel.onEvent(SignUpUIEvent.PasswordChanged(it))
                    },
                    errorStatus = signUpViewModel.signUpUIState.value.passwordError,
                    errorMsg = signUpViewModel.signUpUIState.value.passwordMsgError

                )

                CheckBoxComponent(value = stringResource(id = R.string.trim_continuing),
                    onTextSelected = {
                        CinimaAppRouter.navigateTo(Screen.TermsAndConditionsScreen)
                    }, onCheckedChange = {
                        signUpViewModel.onEvent(SignUpUIEvent.PrivacyPolicyCheckBoxClicked(it))
                    }, errorMsg = signUpViewModel.signUpUIState.value.privacyPolicyAcceptedMsgError
                )

                Spacer(modifier = Modifier.height(40.dp))

                ButtonComponent(
                    value = stringResource(id = R.string.register), onButtonClicked = {
                        signUpViewModel.onEvent(SignUpUIEvent.SignUpButtonClicked)
                        if (signUpViewModel.signUpUIState.value.errorMsg != null) {
                            Toast.makeText(
                                context,
                                signUpViewModel.signUpUIState.value.errorMsg.toString(),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }, true
                )

                Spacer(modifier = Modifier.height(20.dp))

                DividerTextComponent()

                ClickableLoginTextComponent(
                    value = stringResource(id = R.string.already_login),
                    onTextSelected = {
                        CinimaAppRouter.navigateTo(Screen.LoginScreen)
                    })
            }
        }

        if (signUpViewModel.registerInProgress.value) {
            CircularProgressIndicator()
        }
    }


}

@Preview
@Composable
fun DefaultPreviewOfSignUpScreen() {
    SignUpScreen()
}
