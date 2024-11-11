package uk.ac.tees.mad.w9624019.cinimaapp.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import uk.ac.tees.mad.w9624019.cinimaapp.AuthViewModel
import uk.ac.tees.mad.w9624019.cinimaapp.Movie
import uk.ac.tees.mad.w9624019.cinimaapp.R

@Composable
fun HomePage(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    authViewModel: AuthViewModel // Ensure this is the correct type
) {
    val scrollState = rememberScrollState()
    val username = authViewModel.getUsername() // Get the username
    val movieList = remember {
        mutableStateListOf(
            Movie("Example Movie", 99.99,"Description 1", R.drawable.ic_launcher_background),
        )
    }

    Column(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Home Page", fontSize = 32.sp)
        Text(text = "Welcome, $username!", fontSize = 24.sp) // Display the username

        TextButton(onClick = {
            authViewModel.logout()
            navController.navigate("login")
        }) {
            Text(text = "Logout", fontSize = 16.sp)
        }
        LazyColumn (modifier = Modifier.fillMaxSize()) {
            items(movieList) { product ->
                ProductCard(movie = product, navController = navController)
            }
        }
    }
}

@Composable
fun ProductCard(movie: Movie, navController: NavHostController,) {
    Card(
        modifier = Modifier.fillMaxSize().padding(
            horizontal = 16.dp,
            vertical = 8.dp,

        ),
        onClick = {
            // Handle movie click
            // For example, you can navigate to the movie details page

            navController.navigate("singleProduct/${movie.name}/${movie.price}/${movie.image}/${movie.description}")

        }

    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = movie.name)
            Text(text = movie.price.toString())
            Image(painter = painterResource(id = movie.image), contentDescription = null, contentScale = ContentScale.Fit, modifier = Modifier.fillMaxSize())
        }
    }

}
