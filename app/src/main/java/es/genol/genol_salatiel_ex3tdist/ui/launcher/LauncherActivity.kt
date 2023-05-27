package es.genol.genol_salatiel_ex3tdist.ui.launcher

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import es.genol.genol_salatiel_ex3tdist.R
import es.genol.genol_salatiel_ex3tdist.ui.clients.ui.ClientViewModel
import es.genol.genol_salatiel_ex3tdist.ui.clients.ui.ManageScreen
import es.genol.genol_salatiel_ex3tdist.ui.clients.ui.RegisterScreen
import es.genol.genol_salatiel_ex3tdist.ui.theme.Genol_salatiel_ex3tDistTheme
import kotlin.random.Random

/*
Éste ejercicio pertenece al examen de tercera evaluación de PMDM, realizado en fecha 26/05/2023,
por Salatiel Genol.
*/

class LauncherActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val clientViewModel: ClientViewModel = viewModel()


            Genol_salatiel_ex3tDistTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LauncherNavController(clientViewModel)
                }
            }
        }
    }
}


@Composable
fun LauncherNavController(clientViewModel: ClientViewModel) {
    val navController = rememberNavController()
    NavHost(navController, startDestination = LauncherModel.ExerciseList.path) {
        composable(route = LauncherModel.ExerciseList.path) { ExerciseList(navController) }
        composable(route = LauncherModel.ExerciseOne.path) { RegisterScreen(clientViewModel) }
        composable(route = LauncherModel.ExerciseTwo.path) { ManageScreen(clientViewModel) }
    }
}

private val newColor = Array(2) { ramdomColor() }

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExerciseList(navController: NavController) {

    Scaffold(topBar = {
        TopAppBar(
            title = {
                Text(text = stringResource(R.string.launcher_top_bar))
            }
        )
    }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            LauncherButton(
                label = stringResource(R.string.launcher_button_one),
                { navController.navigate(route = LauncherModel.ExerciseOne.path) },
                color = newColor[0]
            )
            LauncherButton(
                label = stringResource(R.string.launcher_button_two),
                { navController.navigate(route = LauncherModel.ExerciseTwo.path) },
                color = newColor[1]
            )
        }

    }
}

private fun ramdomColor(): Color {
    return Color(
        red = Random.nextFloat() / 2f + 0.25f,
        green = Random.nextFloat() / 2f + 0.25f,
        blue = Random.nextFloat() / 2f + 0.25f
    )
}