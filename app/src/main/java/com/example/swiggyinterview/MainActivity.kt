package com.example.swiggyinterview

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.swiggyinterview.presentation.general.MoviesList
import com.example.swiggyinterview.presentation.general.ReusableTextField
import com.example.swiggyinterview.presentation.viewmodel.MoviesViewModel
import com.example.swiggyinterview.ui.theme.SwiggyInterviewTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.serialization.Serializable

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    val viewModel: MoviesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SwiggyInterviewTheme {
                val navController = rememberNavController()

                viewModel.searchMovies("")
                val message = viewModel.statusMessage.collectAsState("")
                val context = LocalContext.current
                LaunchedEffect(message.value) {
                    if (message.value != "") {
                        Toast.makeText(context, message.value.toString(), Toast.LENGTH_SHORT).show()
                    }
                }
                NavHost(
                    navController = navController,
                    startDestination = ScreenA
                ) {
                    composable<ScreenA> {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.SpaceBetween,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {

                            val data = viewModel.movieListScreenState.collectAsState()

                            Log.d(TAG, "$data $message")

                            Column {
                                Spacer(modifier = Modifier.size(50.dp))
                                val text = remember { mutableStateOf("") }
                                ReusableTextField(value = text.value, onValueChange = { nextText ->
                                    text.value = nextText
                                    viewModel.searchMovies(text.value)
                                })

                                MoviesList(data.value.movies, onClick = { imdbId ->
                                    // navigate to screen 2
                                    viewModel.getMovieDetails(imdbId)
                                    navController.navigate(ScreenB(name = null, age = 25))
                                })

                                if(data.value.isLoading) {
                                    LoadingIndicator()
                                }


                            }

                        }
                    }
                    composable<ScreenB> {
                        val args = it.toRoute<ScreenB>()
                        val detailsData = viewModel.movieDetailsScreenState.collectAsState()
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {

                            Image(
                                painter = rememberImagePainter(data = detailsData.value.details?.Poster),
                                contentDescription = "SwiggyInterview Image",
                                modifier = Modifier.size(500.dp, 400.dp)
                            )

                            Text(text = "${detailsData.value.details}")
                            if(detailsData.value.isLoading) {
                                LoadingIndicator()
                            }
                        }
                    }
                }
            }
        }

    }

}

@Composable
fun LoadingIndicator() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Serializable
object ScreenA

@Serializable
data class ScreenB(
    val name: String?,
    val age: Int
)

@OptIn(ExperimentalCoilApi::class)
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Column {
        Text(
            text = "Hello $name!",
            modifier = modifier
        )


    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SwiggyInterviewTheme {
        Greeting("Android")
    }
}