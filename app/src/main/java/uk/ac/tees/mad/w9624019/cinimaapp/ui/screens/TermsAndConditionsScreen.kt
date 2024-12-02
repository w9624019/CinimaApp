package uk.ac.tees.mad.w9624019.cinimaapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import uk.ac.tees.mad.w9624019.cinimaapp.theme.componensts.HeadingTextComponent
import uk.ac.tees.mad.w9624019.cinimaapp.ui.utils.navigation.CinimaAppRouter
import uk.ac.tees.mad.w9624019.cinimaapp.ui.utils.navigation.Screen
import uk.ac.tees.mad.w9624019.cinimaapp.ui.utils.navigation.SystemBackButtonHandler

@Composable
fun TermsAndConditionsScreen() {
    Surface(modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(28.dp)) {
        HeadingTextComponent(value = "Terms And Condition")
    }
    SystemBackButtonHandler {
        CinimaAppRouter.navigateTo(Screen.SignUpScreen)
    }
}

@Preview
@Composable
fun DefaultPreviewOfTermsAndConditionsScreen(){
    TermsAndConditionsScreen()
}