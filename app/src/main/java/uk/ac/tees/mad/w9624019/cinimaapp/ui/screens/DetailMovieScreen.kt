package uk.ac.tees.mad.w9624019.cinimaapp.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import uk.ac.tees.mad.w9624019.cinimaapp.ui.components.MovieDetailContent
import uk.ac.tees.mad.w9624019.cinimaapp.ui.viewmodels.MovieViewModel

@Composable
fun DetailMovieScreen(movieId: Int, viewModel: MovieViewModel) {
    val movie by remember {viewModel.selectedMovie}.observeAsState(null)
    val movieInDAO by remember{viewModel.movieExistsInFav}.observeAsState(null)

    LaunchedEffect(key1 = true) {
        viewModel.getMovieDetail(movieId)
        viewModel.isMovieInDao(movieId)

        Log.e("MOVIEDAO",movieInDAO.toString())
    }
    Log.e("MOVIEDAO 1",movieInDAO.toString())
    if (movie != null) {
        MovieDetailContent(movie = movie!!,posterUrl = viewModel.getPosterUrl(movie!!.posterUrl).toString(), viewModel = viewModel, movieInDAO = movieInDAO)
    } else {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Loading..",
                textAlign = TextAlign.Center
            )
        }
    }
}