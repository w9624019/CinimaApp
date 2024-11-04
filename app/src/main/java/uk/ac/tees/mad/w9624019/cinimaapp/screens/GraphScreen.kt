package uk.ac.tees.mad.w9624019.cinimaapp.screens

import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import cis4034.starwarscensus.ui.theme.CIS4034StarWarsCensusTheme
import uk.ac.tees.mad.w9624019.cinimaapp.view_models.PlanetsViewModel

@Composable
fun GraphScreen(
    navHostController: NavHostController,
    planetsViewModel: PlanetsViewModel = viewModel(),
    modifier: Modifier = Modifier
) {
    val planetsData by planetsViewModel.planetsDataFlow.collectAsStateWithLifecycle()
    val localConfig = LocalConfiguration.current

    // Create a list of colours
    val colours by lazy {
        val totalPlanets = planetsData.size
        with(mutableListOf<Color>()) {
            val startCol = Color.Blue
            val endCol = Color.Red
            (0..totalPlanets).map {
                lerp(startCol, endCol, it / totalPlanets.toFloat())
            }.toList()
        }
    }

    Box(modifier = modifier) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            var xOffset = 0f
            planetsData.forEachIndexed { index, planet ->
                val pcOfLargest = (planet.diameter / 1000f) / (planetsViewModel.largestDiameter / 1000f)
                val height = pcOfLargest * localConfig.screenHeightDp

                Log.d("package:mine", "$xOffset -- $height")

                drawRect(
                    color = colours[index],
                    topLeft =
                        Offset(xOffset,
                            (planetsViewModel.largestDiameter / 1000f - height) + 600f),
                            size = Size(width = 50f, height = height))

                xOffset += 55f
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun GraphScreenPreview() {
    CIS4034StarWarsCensusTheme {
        val navController = rememberNavController()

        GraphScreen(navController)
    }
}