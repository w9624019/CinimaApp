package uk.ac.tees.mad.w9624019.cinimaapp.ui.screens

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
import uk.ac.tees.mad.w9624019.cinimaapp.theme.componensts.ButtonComponent
import uk.ac.tees.mad.w9624019.cinimaapp.theme.componensts.ClickableRegisterTextComponent
import uk.ac.tees.mad.w9624019.cinimaapp.theme.componensts.DividerTextComponent
import uk.ac.tees.mad.w9624019.cinimaapp.theme.componensts.HeadingTextComponent
import uk.ac.tees.mad.w9624019.cinimaapp.theme.componensts.NormalTextComponent
import uk.ac.tees.mad.w9624019.cinimaapp.theme.componensts.PasswordTextFieldComponent
import uk.ac.tees.mad.w9624019.cinimaapp.theme.componensts.TextFieldComponent
import uk.ac.tees.mad.w9624019.cinimaapp.ui.screens.data.LoginUIEvent
import uk.ac.tees.mad.w9624019.cinimaapp.ui.screens.data.LoginViewModel
import uk.ac.tees.mad.w9624019.cinimaapp.ui.utils.navigation.CinimaAppRouter
import uk.ac.tees.mad.w9624019.cinimaapp.ui.utils.navigation.Screen

@Composable
fun LoginScreen(loginViewModel: LoginViewModel = viewModel()) {
    val context = LocalContext.current

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
                Spacer(modifier = Modifier.height(20.dp))
                NormalTextComponent(value = stringResource(id = R.string.app_name))
                HeadingTextComponent(value = stringResource(id = R.string.welcome_back))
                Spacer(modifier = Modifier.height(20.dp))
                TextFieldComponent(
                    labelValue = stringResource(id = R.string.email),
                    painterResource = painterResource(id = R.drawable.ic_email),
                    keyboardType = KeyboardType.Email,
                    onTextSelected = {
                        loginViewModel.onEvent(LoginUIEvent.EmailChanged(it))
                    },
                    errorStatus = loginViewModel.loginUIState.value.emailError,
                    errorMsg = loginViewModel.loginUIState.value.emailMsgError

                )

                PasswordTextFieldComponent(
                    labelValue = stringResource(id = R.string.password),
                    painterResource = painterResource(id = R.drawable.ic_lock),
                    onTextSelected = {
                        loginViewModel.onEvent(LoginUIEvent.PasswordChanged(it))

                    },
                    errorStatus = loginViewModel.loginUIState.value.passwordError,
                    errorMsg = loginViewModel.loginUIState.value.passwordMsgError

                )

                Spacer(modifier = Modifier.height(200.dp))

                ButtonComponent(
                    value = stringResource(id = R.string.login),
                    onButtonClicked = {
                        loginViewModel.onEvent(LoginUIEvent.LogInButtonClicked)
                        loginViewModel.errorMsgServer(context)
                    }, true
                )

                Spacer(modifier = Modifier.height(10.dp))

                DividerTextComponent()

                ClickableRegisterTextComponent(
                    value = stringResource(id = R.string.no_account),
                    onTextSelected = {
                        CinimaAppRouter.navigateTo(Screen.SignUpScreen)
                    })
            }
        }

        if (loginViewModel.loginInProgress.value) {
            CircularProgressIndicator()
        }
    }

}


@Preview
@Composable
fun DefaultPreviewOfLoginScreen() {
    LoginScreen()
}