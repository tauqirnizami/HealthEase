package com.example.healthease.ui.theme

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.healthease.R

enum class ScreenNames {
    Beginning,
    Start,
    Workout,
    WorkoutPlanner,
    WorkoutProvider,
    ChoseIntermediateLvl,
    ExploreExercises,
    SearchExercise,
    PostureCorrection,
    Diet,
    DietPlanner,
    Calculations,
    ExploreFoodIems,
    SearchFoodItem,
    Others,
    StreakCounter,
    Sports,
    Motivation,
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameTopAppBar(
    modifier: Modifier = Modifier
        .fillMaxWidth()
) {
    TopAppBar(
        title = {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                var disclaimer: Boolean by remember {
                    mutableStateOf(false)
                }
                IconButton(onClick = { disclaimer = !disclaimer }) {
                    Icon(imageVector = Icons.Default.Info, contentDescription = "Disclaimer")
                }
                if (disclaimer){
                    VegInfoDialog(
                        text = "Disclaimer:\nAs much as the gathered info of the app belongs to reliable sources," +
                                " it is always recommended to seek professional help for personalized training" +
                                " and Diet Plans. Also, Follow the diet plan carefully, the diet part is still" +
                                "under development",
                        onClose = { disclaimer = !disclaimer })
                }
                Text(
                    text = "HealthEase",
                    textAlign = TextAlign.Center,
                    color = Color(android.graphics.Color.parseColor("#fba001")),
                    modifier = Modifier
                        .padding(9.dp),
                    fontFamily = FontFamily(Font(R.font.facon)),
                    fontSize = 21.sp
                )
            }
        },
        modifier = modifier
    )
}

@Composable
fun HealthEase(
    modifier: Modifier = Modifier,
    navigationViewModel: CalculationsViewModel = viewModel(factory = AppViewModelProvider.Factory),
    navController: NavHostController = rememberNavController(),
) {

    Scaffold(
        topBar = {
            GameTopAppBar(
                modifier = modifier
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = ScreenNames.Beginning.name,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            composable(ScreenNames.Beginning.name) {
                BeginningScreen(
                    onClick1 = { navController.navigate(ScreenNames.Start.name) },
                )
            }
            composable(ScreenNames.Start.name) {
                StartScreen(
                    onClick1 = { navController.navigate(ScreenNames.Diet.name) },
                    onClick2 = { navController.navigate(ScreenNames.Workout.name) },
                    onClick = { navController.navigate(ScreenNames.Others.name) }
                )
            }
            composable(ScreenNames.Diet.name) {
                DietButtonScreen(
                    onClick1 = { navController.navigate(ScreenNames.Calculations.name) },
                    onClick2 = { navController.navigate(ScreenNames.DietPlanner.name) },
                    onClick = { navController.navigate(ScreenNames.ExploreFoodIems.name) }
                )
            }
            composable(ScreenNames.Workout.name) {
                WorkoutButtonScreen(
                    onClick1 = { navController.navigate(ScreenNames.WorkoutPlanner.name) },
                    onClick2 = { navController.navigate(ScreenNames.ExploreExercises.name) },
                    onClick = { navController.navigate(ScreenNames.PostureCorrection.name) }
                )
            }
            composable(ScreenNames.Others.name) {
                OthersButtonScreen(
                    onClick1 = { navController.navigate(ScreenNames.Sports.name) },
                    onClick2 = { navController.navigate(ScreenNames.StreakCounter.name) },
                    onClick = { navController.navigate(ScreenNames.Motivation.name) }
                )
            }

            composable(ScreenNames.Calculations.name) {
                CalculationsScreen(
                    calculationsViewModel = navigationViewModel
                )
            }
            composable(ScreenNames.DietPlanner.name) {
                DietSearchScreen()
            }
            composable(ScreenNames.ExploreFoodIems.name) {
                ExploreDishes(
                    onSearchClick = { navController.navigate(ScreenNames.SearchFoodItem.name) }
                )
            }
            composable(ScreenNames.SearchFoodItem.name) {
                SearchFoodItem()
            }
            composable(ScreenNames.WorkoutPlanner.name) {
                WorkoutPlannerButtonScreen(
                    onClick1 = {
                        navigationViewModel.levelSet(0)
                        navController.navigate(ScreenNames.WorkoutProvider.name)
                    },
                    onClick2 = { navController.navigate(ScreenNames.ChoseIntermediateLvl.name) },
                    onClick = {
                        navigationViewModel.levelSet(4)
                        navController.navigate(ScreenNames.WorkoutProvider.name)
                    }
                )
            }
            composable(ScreenNames.ExploreExercises.name) {
                ExploreExercises(
                    onSearchClick = { navController.navigate(ScreenNames.SearchExercise.name) },
                )
            }
            composable(ScreenNames.PostureCorrection.name) {
                PostureScreen()
            }
            composable(ScreenNames.StreakCounter.name) {
                StreakCounterScreen()
            }
            composable(ScreenNames.Sports.name) {
                SportsScreenList()
            }
            composable(ScreenNames.Motivation.name) {
                MotivationScreen()
            }
            composable(ScreenNames.SearchExercise.name) {
                ExerciseSearchScreen()
            }
            composable(ScreenNames.ChoseIntermediateLvl.name) {
                MediumLevelButtonScreen(
                    onClick1 = {
                        navigationViewModel.levelSet(1)
                        navController.navigate(ScreenNames.WorkoutProvider.name)
                    },
                    onClick2 = {
                        navigationViewModel.levelSet(2)
                        navController.navigate(ScreenNames.WorkoutProvider.name)
                    },
                    onClick = {
                        navigationViewModel.levelSet(3)
                        navController.navigate(ScreenNames.WorkoutProvider.name)
                    },

                    )
            }
            composable(ScreenNames.WorkoutProvider.name) {
                WorkoutSearchScreen(
                    workoutSearchViewModel = navigationViewModel
                )
            }
        }
    }
}