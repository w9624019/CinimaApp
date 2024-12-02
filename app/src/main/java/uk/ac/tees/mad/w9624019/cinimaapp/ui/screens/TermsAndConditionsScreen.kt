package uk.ac.tees.mad.w9624019.cinimaapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
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
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = """
                    Welcome to CinemaApp! These Terms and Conditions ("Terms") govern your use of the CinemaApp mobile application ("App"). By downloading, installing, or using the App, you agree to be bound by these Terms. If you do not agree with these Terms, please do not use the App.
                    
                    1. General Information:
                    CinemaApp is a movie discovery application that displays a list of movies and related information fetched from The Movie Database (TMDb) API. CinemaApp is not affiliated with or endorsed by TMDb. The data displayed in the App is provided by TMDb and is subject to TMDb’s terms of use.

                    2. Use of the App:
                    - The App is for personal, non-commercial use only.
                    - You agree to use the App in compliance with applicable laws and regulations.
                    - You must not use the App for unlawful purposes, reverse-engineer its code, or use automated systems to access it.

                    3. Third-Party Content:
                    - The movie data is fetched from TMDb. CinemaApp does not guarantee its accuracy.
                    - By using the App, you agree to TMDb's Terms of Use.
                    - CinemaApp is not responsible for inaccuracies in TMDb data.

                    4. Privacy:
                    - CinemaApp does not collect or store personal information unless explicitly stated.
                    - The App may use analytics tools to improve performance.

                    5. Intellectual Property:
                    - All trademarks, logos, and movie data are the property of their respective owners.
                    - CinemaApp owns the design and functionality of the App.

                    6. Disclaimer of Warranties:
                    - CinemaApp is provided "as is" and does not guarantee error-free operation.
                    
                    7. Limitation of Liability:
                    - CinemaApp is not liable for damages caused by the use or inability to use the App.

                    8. Termination:
                    - We reserve the right to suspend or terminate your access to the App if you violate these Terms.

                    9. Modifications to Terms:
                    - CinemaApp may update these Terms periodically. Continued use of the App constitutes acceptance of any changes.

                    For questions, contact us at anusha@gmail.com, Teesside University.
                """.trimIndent(),
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Black,
            modifier = Modifier.verticalScroll(rememberScrollState())
        )


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