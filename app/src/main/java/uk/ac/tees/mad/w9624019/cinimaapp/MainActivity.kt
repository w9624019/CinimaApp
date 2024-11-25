package uk.ac.tees.mad.w9624019.cinimaapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.ExperimentalMaterial3Api
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
import uk.ac.tees.mad.w9624019.cinimaapp.app.MoviesApp
import uk.ac.tees.mad.w9624019.cinimaapp.ui.viewmodels.MovieViewModel
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val movieViewModel: MovieViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FirebaseApp.initializeApp(this)
            FirebaseAuth.getInstance().signOut()
            MoviesApp()
        }
    }
}


