package uk.ac.tees.mad.w9624019.cinimaapp.screens


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import uk.ac.tees.mad.w9624019.cinimaapp.view_models.PlanetsViewModel

@Composable
fun LandingScreen(
    navHostController: NavHostController,
    planetsViewModel: PlanetsViewModel = viewModel(),
    modifier: Modifier = Modifier
) {
    val planetsData by planetsViewModel.planetsDataFlow.collectAsStateWithLifecycle()

    Column {
        LazyColumn {
            items(planetsData, key = {it.name}) {
                Text(text = "${it.name} -- ${it.diameter}")
            }
        }

        Text(text = "Smallest Diameter: ${planetsViewModel.smallestDiameter}")
        Text(text = "Smallest Diameter: ${planetsViewModel.largestDiameter}")

        Button(
            onClick = {
                navHostController.navigate("graph")
            }
        ) {
            Text(text = "Display Bar Graph")
        }

    }
}