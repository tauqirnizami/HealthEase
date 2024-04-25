package com.example.healthease.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.healthease.R

enum class ScreenNames {
    Beginning,
    Start,
    Workout,
//    Choselvl1,
//    Choselvl2,
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
//    currentScreen: ScreenNames,
//    canNavigateBack: Boolean,
//    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
        .fillMaxWidth()
) {
//    if (currentScreen != ScreenNames.Start) {
        TopAppBar(
            title = {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
//                    if (canNavigateBack) {
//                        IconButton(
//                            onClick = navigateUp,
//                        ) {
//                            Icon(
//                                imageVector = Icons.Filled.ArrowBack,
//                                contentDescription = stringResource(R.string.back_button)
//                            )
//                        }
//                    }
//                    Row() {
//                        Image(
//                            painter = painterResource(id = R.drawable.croppedjpgicon2__1___1_),
//                            contentDescription = null,
//                            modifier = Modifier
//                                .size(50.dp)
//                        )
                        Text(
                            text = "HealthEase",
                            textAlign = TextAlign.Center,
                            color = Color(android.graphics.Color.parseColor("#fba001")),
                            modifier = Modifier
                                .padding(9.dp),
                            fontFamily = FontFamily(Font(R.font.facon)),
                            fontSize = 21.sp
                            )
//                    }

//                    Spacer(modifier = Modifier.width(55.dp))
                }
            },
            modifier = modifier
        )
//    }
}

@Composable
fun HealthEase(
    navigationViewModel: CalculationsViewModel = viewModel(factory = AppViewModelProvider.Factory),
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
) {
    // Get current back stack entry
//    val backStackEntry by navController.currentBackStackEntryAsState()
    // Get the name of the current screen
//    val currentScreen = ScreenNames.valueOf(
//        backStackEntry?.destination?.route ?: ScreenNames.Start.name
//    )

    Scaffold(
        topBar = {
            GameTopAppBar(
//                currentScreen = currentScreen,
//                canNavigateBack = navController.previousBackStackEntry != null,
//                navigateUp = { navController.navigateUp() }
                modifier = modifier
            )
        }
    ) { innerPadding ->
//        val uiState by gameViewModel.uiState.collectAsState()
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
                // Your composable content for the Start screen
            }
            composable(ScreenNames.Start.name) {
                StartScreen(
                    onClick1 = { navController.navigate(ScreenNames.Diet.name) },
                    onClick2 = { navController.navigate(ScreenNames.Workout.name) },
                    onClick = {navController.navigate(ScreenNames.Others.name)}
                )
                // Your composable content for the Rules screen
            }
            composable(ScreenNames.Diet.name) {
                DietButtonScreen(
                    onClick1 = { navController.navigate(ScreenNames.Calculations.name) },
                    onClick2 = { navController.navigate(ScreenNames.DietPlanner.name) },
                    onClick = {navController.navigate(ScreenNames.ExploreFoodIems.name)}
                )
                // Your composable content for the Rules screen
            }
            composable(ScreenNames.Workout.name) {
                WorkoutButtonScreen(
                    onClick1 = { navController.navigate(ScreenNames.WorkoutPlanner.name) },
                    onClick2 = { navController.navigate(ScreenNames.ExploreExercises.name) },
                    onClick = {navController.navigate(ScreenNames.PostureCorrection.name)}
                )
                // Your composable content for the Rules screen
            }
            composable(ScreenNames.Others.name) {
                OthersButtonScreen(
                    onClick1 = { navController.navigate(ScreenNames.Sports.name) },
                    onClick2 = { navController.navigate(ScreenNames.StreakCounter.name) },
                    onClick = {navController.navigate(ScreenNames.Motivation.name)}
                )
                // Your composable content for the Rules screen
            }
            /***
             * Second Layer
             */
            composable(ScreenNames.Calculations.name) {
                CalculationsScreen(
                    calculationsViewModel = navigationViewModel
                )
                // Your composable content for the Rules screen
            }
            composable(ScreenNames.DietPlanner.name) {
                DietSearchScreen()
                // Your composable content for the Rules screen
            }
            composable(ScreenNames.ExploreFoodIems.name) {
                ExploreDishes(
                    onSearchClick = { navController.navigate(ScreenNames.SearchFoodItem.name) }
                )
                // Your composable content for the Rules screen
            }
            composable(ScreenNames.SearchFoodItem.name) {
                SearchFoodItem()
                // Your composable content for the Rules screen
            }
            composable(ScreenNames.WorkoutPlanner.name) {
                WorkoutPlannerButtonScreen(
                    onClick1 = {navigationViewModel.levelSet(0)
                        navController.navigate(ScreenNames.WorkoutProvider.name) },
                    onClick2 = { navController.navigate(ScreenNames.ChoseIntermediateLvl.name) },
                    onClick = {navigationViewModel.levelSet(4)
                        navController.navigate(ScreenNames.WorkoutProvider.name)}
                )
                // Your composable content for the Rules screen
            }
            composable(ScreenNames.ExploreExercises.name) {
                ExploreExercises(
                    onSearchClick = { navController.navigate(ScreenNames.SearchExercise.name) },
                )
                // Your composable content for the Rules screen
            }
            composable(ScreenNames.PostureCorrection.name) {
                PostureScreen()
                // Your composable content for the Rules screen
            }
            composable(ScreenNames.StreakCounter.name) {
                StreakCounterScreen()
                // Your composable content for the Rules screen
            }
            composable(ScreenNames.Sports.name) {
                SportsScreenList()
                // Your composable content for the Rules screen
            }
            composable(ScreenNames.Motivation.name) {
                MotivationScreen()
                // Your composable content for the Rules screen
            }
            composable(ScreenNames.SearchExercise.name) {
                ExerciseSearchScreen()
                // Your composable content for the Rules screen
            }
            composable(ScreenNames.ChoseIntermediateLvl.name) {
                MediumLevelButtonScreen(
                    onClick1 = {navigationViewModel.levelSet(1)
                        navController.navigate(ScreenNames.WorkoutProvider.name) },
                    onClick2 = {navigationViewModel.levelSet(2)
                        navController.navigate(ScreenNames.WorkoutProvider.name) },
                    onClick = {navigationViewModel.levelSet(3)
                        navController.navigate(ScreenNames.WorkoutProvider.name) },

                    )
                // Your composable content for the Rules screen
            }
            composable(ScreenNames.WorkoutProvider.name) {
                WorkoutSearchScreen(
                    workoutSearchViewModel = navigationViewModel
                )
                // Your composable content for the Rules screen
            }
//            composable(ScreenNames.Game.name) {
//                GameScreen(
////                gotoStart = {navController.navigate(ScreenNames.Start.name)},
//                )
//                // Your composable content for the Game screen
//            }
        }
    }
}