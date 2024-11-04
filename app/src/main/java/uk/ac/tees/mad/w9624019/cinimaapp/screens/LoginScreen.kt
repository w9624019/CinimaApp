package uk.ac.tees.mad.w9624019.cinimaapp.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import uk.ac.tees.mad.w9624019.cinimaapp.view_models.UserViewModel
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(
    navHostController: NavHostController,
    userViewModel: UserViewModel,
    modifier: Modifier = Modifier
) {
    val coroutineScope = rememberCoroutineScope()

    Column(modifier = modifier) {
        if (!userViewModel.isLoggedIn) {
            Text(text = "You Need To Login")
            Button(
                onClick = {
                    coroutineScope.launch {
                        userViewModel.doLogin()

                        if (userViewModel.isLoggedIn) {
                            navHostController.navigate("landing")
                        }
                    }
                }
            ) {
                Text(text = "Login")
            }
        }
    }
}
