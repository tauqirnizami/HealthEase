package com.example.healthease.ui.theme

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.healthease.data.AbsExercises
import com.example.healthease.data.BackExercises
import com.example.healthease.data.BicepsExercises
import com.example.healthease.data.ChestExercises
import com.example.healthease.data.Exercises
import com.example.healthease.data.ForearmsExercises
import com.example.healthease.data.LegExercises
import com.example.healthease.data.ShoulderExercises
import com.example.healthease.data.TricepsExercises
import com.example.healthease.data.gymAbsExercises
import com.example.healthease.data.gymBackExercises
import com.example.healthease.data.gymBicepsExercises
import com.example.healthease.data.gymChestExercises
import com.example.healthease.data.gymForearmsExercises
import com.example.healthease.data.gymLegsExercises
import com.example.healthease.data.gymShoulderExercises
import com.example.healthease.data.gymTricepsExercises
import com.example.healthease.data.homeAbsExercises
import com.example.healthease.data.homeBackExercises
import com.example.healthease.data.homeBicepsExercises
import com.example.healthease.data.homeChestExercises
import com.example.healthease.data.homeForearmExercises
import com.example.healthease.data.homeLegExercises
import com.example.healthease.data.homeShoulderExercises
import com.example.healthease.data.homeTricepsExercises
import com.example.healthease.ExpandInfoButton
import com.example.healthease.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@Composable
fun WorkoutPlanner(
    modifier: Modifier = Modifier,
    workoutPlannerViewModel: CalculationsViewModel = viewModel(factory = AppViewModelProvider.Factory),
    intensity: Int = 1,
    home: Boolean = true,
    weightLoss: Boolean = false,
    onClick: () -> Unit
) {

    var chestWorkout by remember { mutableStateOf<List<ChestExercises>>(emptyList()) }
    var backWorkout by remember { mutableStateOf<List<BackExercises>>(emptyList()) }
    var tricepsWorkout by remember { mutableStateOf<List<TricepsExercises>>(emptyList()) }
    var shoulderWorkout by remember { mutableStateOf<List<ShoulderExercises>>(emptyList()) }
    var bicepsWorkout by remember { mutableStateOf<List<BicepsExercises>>(emptyList()) }
    var absWorkout by remember { mutableStateOf<List<AbsExercises>>(emptyList()) }
    var forearmsWorkout by remember { mutableStateOf<List<ForearmsExercises>>(emptyList()) }
    var legsWorkout by remember { mutableStateOf<List<LegExercises>>(emptyList()) }

//    Text(text = "Not Started")
    /***
     * 1 = Abs
     * 2 = Back
     * 3 = Biceps
     * 4 = Triceps
     * 5 = Legs
     * 6 = Shoulder
     * 7 = Forearms
     * 8/else = Chest
     */

    val maxAbsSets = workoutPlannerViewModel.maxSetsCounter(1, intensity)
    val maxBackSets = workoutPlannerViewModel.maxSetsCounter(2, intensity)
    val maxBicepsSets = workoutPlannerViewModel.maxSetsCounter(3, intensity)
    val maxTricepsSets = workoutPlannerViewModel.maxSetsCounter(4, intensity)
    val maxLegsSets = workoutPlannerViewModel.maxSetsCounter(5, intensity)
    val maxShoulderSets = workoutPlannerViewModel.maxSetsCounter(6, intensity)
    val maxForearmsSets = workoutPlannerViewModel.maxSetsCounter(7, intensity)
    val maxChestSets = workoutPlannerViewModel.maxSetsCounter(8, intensity)

    LaunchedEffect(Unit) {
        CoroutineScope(Dispatchers.Default).launch {
            // Call your suspend functions here
            chestWorkout = workoutPlannerViewModel.selectChestExercises(
                homeOrGym = if (home) homeChestExercises else gymChestExercises,
//                intensity,
                setsMap = maxChestSets
            )
            backWorkout = workoutPlannerViewModel.selectBackExercises(
                homeOrGym = if (home) homeBackExercises else gymBackExercises,
//                intensity,
                setsMap = maxBackSets
            )
            tricepsWorkout = workoutPlannerViewModel.selectTricepsExercises(
                homeOrGym = if (home) homeTricepsExercises else gymTricepsExercises,
//                intensity,
                setsMap = maxTricepsSets

            )
            shoulderWorkout = workoutPlannerViewModel.selectShoulderExercises(
                homeOrGym = if (home) homeShoulderExercises else gymShoulderExercises,
//                intensity,
                setsMap = maxShoulderSets
            )
            bicepsWorkout = workoutPlannerViewModel.selectBicepsExercises(
                homeOrGym = if (home) homeBicepsExercises else gymBicepsExercises,
//                intensity,
                setsMap = maxBicepsSets
            )
            absWorkout = workoutPlannerViewModel.selectAbsExercises(
                homeOrGym = if (home) homeAbsExercises else gymAbsExercises,
//                intensity,
                setsMap = maxAbsSets
            )
            forearmsWorkout = workoutPlannerViewModel.selectForearmsExercises(
                homeOrGym = if (home) homeForearmExercises else gymForearmsExercises,
//                intensity,
                setsMap = maxForearmsSets
            )
            legsWorkout = workoutPlannerViewModel.selectLegExercises(
                homeOrGym = if (home) homeLegExercises else gymLegsExercises,
//                intensity,
                setsMap = maxLegsSets
            )
            // Add more suspend function calls as needed
        }
    }

    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .padding(start = 5.dp, end = 5.dp)
    ) {
        if (weightLoss) {
            Card(
                modifier
                    .padding(top = 11.dp, bottom = 31.dp, start = 5.dp, end = 5.dp)
            ) {
                Column(modifier = modifier
                    .padding(top = 11.dp, bottom = 11.dp, start = 5.dp, end = 5.dp)) {
                    Text(text = "For weight Loss, reduce the given number of sets, and do more cardio.")
                    Text(text = "Cardio includes exercises such as:")
                    Text(text = "Jumping Jacks, Burpees, Jumping Lunges")
                    Text(text = "Cycling, Jogging, Running")
                    Text(text = "Stair Climber, Treadmill Running, Stationary Bike, etc.")
                }
            }
        }

        Text(
            text = "Warmup:",
            fontWeight = FontWeight.Bold,
            modifier = modifier
                .padding(top = 31.dp, start = 30.dp)
        )
        Card(
            modifier
                .padding(top = 11.dp, bottom = 31.dp, start = 5.dp, end = 5.dp)
        ) {
            Text(
                text = "Warmup can be the same exercises with a lot lesser weight, just enough to warm you body.\n" +
                        "But a general Warmup can be 2-3 sets of push ups, pull ups, and around 7 minutes of cycling/jogging",
                modifier = modifier.padding(11.dp)
            )
        }

        val advancePlus: String = if (intensity == 4) "+" else ""

        Row {

            Column(Modifier.weight(1f)) {
                Text(
                    text = "Legs:",
                    fontWeight = FontWeight.Bold,
                    modifier = modifier
                        .padding(top = 31.dp, start = 30.dp)
                )
                Text(
                    text = stringResource(R.string.sets) +
                            "| Quads = ${maxLegsSets.getValue("maxQuadCount")}$advancePlus | " +
                            "Hamstrings = ${maxLegsSets.getValue("maxHamstringCount")}$advancePlus | " +
                            "Calves = ${maxLegsSets.getValue("maxCalfCount")}$advancePlus | " +
                            "Glutes = ${maxLegsSets.getValue("maxGlutesCount")}$advancePlus | " +
                            "Adductors & Abductors = ${
                                maxLegsSets.getValue("maxAdductorsCount") + maxLegsSets.getValue(
                                    "maxAbductorCount"
                                )
                            }$advancePlus |",
                    fontWeight = FontWeight.Medium,
                    modifier = modifier.padding(15.dp)
                )
                Card(
                    modifier
                        .padding(top = 11.dp, bottom = 31.dp, start = 5.dp, end = 5.dp)
                ) {
                    legsWorkout.forEach { item ->
                        WorkoutPrint(item = item)
                    }
                }
            }

            Column(Modifier.weight(1f)) {
                Text(
                    text = "Back:",
                    fontWeight = FontWeight.Bold,
                    modifier = modifier
                        .padding(top = 31.dp, start = 30.dp)
                )
                Text(
                    text = stringResource(R.string.sets) +
                            "| Lats = ${maxBackSets.getValue("maxLatsCount")}$advancePlus | " +
                            "Traps = ${maxBackSets.getValue("maxTrapsCount")}$advancePlus | " +
                            "Rhomboids = ${maxBackSets.getValue("maxRhomboidsCount")}$advancePlus | " +
                            "Erector Spinae = ${maxBackSets.getValue("maxErectorCount")}$advancePlus | " +
                            "Rotator Cuff = ${maxBackSets.getValue("maxRotatorCount")}$advancePlus |",
                    fontWeight = FontWeight.Medium,
                    modifier = modifier.padding(15.dp)
                )
                Card(
                    modifier
                        .padding(top = 11.dp, bottom = 31.dp, start = 5.dp, end = 5.dp)
                ) {
                    backWorkout.forEach { item ->
                        WorkoutPrint(item = item)
                    }
                }
            }
        }

        Row {
            Column(Modifier.weight(1f)) {
                Text(
                    text = "Triceps:",
                    fontWeight = FontWeight.Bold,
                    modifier = modifier
                        .padding(start = 30.dp)
                )
                Text(
                    text = stringResource(R.string.sets) +
                            "| Long Head = ${maxTricepsSets.getValue("maxLongCount")}$advancePlus | " +
                            "Medial Head = ${maxTricepsSets.getValue("maxMedialCount")}$advancePlus | " +
                            "Lateral head = ${maxTricepsSets.getValue("maxMedialCount")}$advancePlus |",
                    fontWeight = FontWeight.Medium,
                    modifier = modifier.padding(15.dp)
                )
                Card(
                    modifier
                        .padding(top = 11.dp, bottom = 31.dp, start = 5.dp, end = 5.dp)
                ) {
                    tricepsWorkout.forEach { item ->
                        WorkoutPrint(item = item)
                    }
                }
            }

            Column(Modifier.weight(1f)) {
                Text(
                    text = "Biceps:",
                    fontWeight = FontWeight.Bold,
                    modifier = modifier
                        .padding(start = 30.dp)
                )
                Text(
                    text = stringResource(R.string.sets) +
                            "| Long Head = ${maxBicepsSets.getValue("maxLongCount")}$advancePlus | " +
                            "Long Head = ${maxBicepsSets.getValue("maxShortCount")}$advancePlus |",
                    fontWeight = FontWeight.Medium,
                    modifier = modifier.padding(15.dp)
                )
                Card(
                    modifier
                        .padding(top = 11.dp, bottom = 31.dp, start = 5.dp, end = 5.dp)
                ) {
                    bicepsWorkout.forEach { item ->
                        WorkoutPrint(item = item)
                    }
                }
            }
        }

        if (intensity != 0 && intensity != 1) {
            Row {
                Column(Modifier.weight(1f)) {
                    Text(
                        text = "Forearms:",
                        fontWeight = FontWeight.Bold,
                        modifier = modifier
                            .padding(start = 30.dp)
                    )
                    Text(
                        text = stringResource(R.string.sets) +
                                "| Flexors = ${maxForearmsSets.getValue("maxFlexorsCount")}$advancePlus | " +
                                "Extensors = ${maxForearmsSets.getValue("maxExtensorsCount")}$advancePlus |",
                        fontWeight = FontWeight.Medium,
                        modifier = modifier.padding(15.dp)
                    )
                    Card(
                        modifier
                            .padding(top = 11.dp, bottom = 31.dp, start = 5.dp, end = 5.dp)
                    ) {
                        forearmsWorkout.forEach { item ->
                            WorkoutPrint(item = item)
                        }
                    }
                }


                Column(Modifier.weight(1f)) {
                    Text(
                        text = "Abs:",
                        fontWeight = FontWeight.Bold,
                        modifier = modifier
                            .padding(start = 30.dp)
                    )
                    Text(
                        text = stringResource(R.string.sets) +
                                "| Lower Abs = ${maxAbsSets.getValue("maxLowerAbsCount")}$advancePlus | " +
                                "Middle Abs = ${maxAbsSets.getValue("maxMiddleAbsCount")}$advancePlus | " +
                                "Transverse = ${maxAbsSets.getValue("maxTransverseCount")}$advancePlus | " +
                                "Obliques = ${maxAbsSets.getValue("maxObliquesCount")}$advancePlus | " +
                                "Upper Abs = ${maxAbsSets.getValue("maxUpperAbsCount")}$advancePlus |",
                        fontWeight = FontWeight.Medium,
                        modifier = modifier.padding(15.dp)
                    )
                    Card(
                        modifier
                            .padding(top = 11.dp, bottom = 31.dp, start = 5.dp, end = 5.dp)
                    ) {
                        absWorkout.forEach { item ->
                            WorkoutPrint(item = item)
                        }
                    }
                }
            }
        }

        Row {
            if (intensity != 0) {
                Column(Modifier.weight(1f)) {
                    Text(
                        text = "Shoulder:",
                        fontWeight = FontWeight.Bold,
                        modifier = modifier
                            .padding(start = 30.dp)
                    )
                    Text(
                        text = stringResource(R.string.sets) +
                                "| Anterior Delts = ${maxShoulderSets.getValue("maxAntDeltsCount")}$advancePlus | " +
                                "Posterior Delts = ${maxShoulderSets.getValue("maxPostDeltsCount")}$advancePlus | " +
                                "Medial Delts = ${maxShoulderSets.getValue("maxMedDeltsCount")}$advancePlus |",
                        fontWeight = FontWeight.Medium,
                        modifier = modifier.padding(15.dp)
                    )
                    Card(
                        modifier
                            .padding(top = 11.dp, bottom = 51.dp, start = 5.dp, end = 5.dp)
                    ) {
                        shoulderWorkout.forEach { item ->
                            WorkoutPrint(item = item)
                        }
                    }
                }
            }

            Column(Modifier.weight(1f)) {
                Text(
                    text = "Chest:",
                    fontWeight = FontWeight.Bold,
                    modifier = modifier
                        .padding(start = 30.dp)

                )
                Text(
                    text = stringResource(R.string.sets) +
                            "| Upper Chest = ${maxChestSets.getValue("maxUpperChestCount")}$advancePlus | " +
                            "Middle Chest = ${maxChestSets.getValue("maxMiddleChestCount")}$advancePlus | " +
                            "Lower Chest = ${maxChestSets.getValue("maxLowerChestCount")}$advancePlus |",
                    fontWeight = FontWeight.Medium,
                    modifier = modifier.padding(15.dp)
                )
                Card(
                    modifier
                        .padding(top = 11.dp, bottom = 51.dp, start = 5.dp, end = 5.dp)
                ) {
                    chestWorkout.forEach { item ->
                        WorkoutPrint(item = item)
                    }
                }
            }
        }


        if (intensity == 0) {
            Card(
                modifier
                    .padding(top = 11.dp, bottom = 31.dp, start = 5.dp, end = 5.dp)
            ) {
                Text(
                    text = "Exercises for shoulder, forearms, and abs will start in the upcoming levels",
                    fontWeight = FontWeight.Bold
                )
            }

        } else if (intensity == 1) {
            Card(
                modifier
                    .padding(top = 11.dp, bottom = 31.dp, start = 5.dp, end = 5.dp)
            ) {
                Text(
                    text = "Exercises for forearms, and abs will start in the upcoming levels",
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = modifier
                        .padding(5.dp)
                )
            }
        }

        var contentStr: String = ""

        /***
         * Chest
         */
        contentStr =
            contentStr + "\n\n*CHEST*\n" + "Sets Per Week: Upper Chest = ${maxChestSets.getValue("maxUpperChestCount")}$advancePlus|" +
                    "Middle Chest = ${maxChestSets.getValue("maxMiddleChestCount")}$advancePlus|" +
                    "Lower Chest = ${maxChestSets.getValue("maxLowerChestCount")}$advancePlus|"
        for (item in chestWorkout) {
            contentStr = contentStr + "\n" + item.name0 + "(${item.muscleSubGroup0})"
        }

        /***
         * Triceps
         */
        contentStr =
            contentStr + "\n\n*TRICEPS*\n" + "Sets Per Week: Long Head = ${maxTricepsSets.getValue("maxLongCount")}$advancePlus|" +
                    "Medial Head = ${maxTricepsSets.getValue("maxMedialCount")}$advancePlus|" +
                    "Lateral head = ${maxTricepsSets.getValue("maxMedialCount")}$advancePlus"
        for (item in tricepsWorkout) {
            contentStr = contentStr + "\n" + item.name0 + "(${item.muscleSubGroup0})"
        }

        /***
         * Biceps
         */
        contentStr =
            contentStr + "\n\n*BICEPS*\n" + "Sets Per Week: Long Head = ${maxBicepsSets.getValue("maxLongCount")}$advancePlus|" +
                    "Long Head = ${maxBicepsSets.getValue("maxShortCount")}$advancePlus"
        for (item in bicepsWorkout) {
            contentStr = contentStr + "\n" + item.name0 + "(${item.muscleSubGroup0})"
        }

        /***
         * Back
         */
        contentStr =
            contentStr + "\n\n*BACK*\n" + "Sets Per Week: Lats = ${maxBackSets.getValue("maxLatsCount")}$advancePlus|" +
                    "Traps = ${maxBackSets.getValue("maxTrapsCount")}$advancePlus|" +
                    "Rhomboids = ${maxBackSets.getValue("maxRhomboidsCount")}$advancePlus|" +
                    "Erector Spinae = ${maxBackSets.getValue("maxErectorCount")}$advancePlus|" +
                    "Rotator Cuff = ${maxBackSets.getValue("maxRotatorCount")}$advancePlus"

        for (item in backWorkout) {
            contentStr = contentStr + "\n" + item.name0 + "(${item.muscleSubGroup0})"
        }

        /***
         * Legs
         */
        contentStr =
            contentStr + "\n\n*LEGS*\n" + "Sets Per Week: Quads = ${maxLegsSets.getValue("maxQuadCount")}$advancePlus|" +
                    "Hamstrings = ${maxLegsSets.getValue("maxHamstringCount")}$advancePlus|" +
                    "Calves = ${maxLegsSets.getValue("maxCalfCount")}$advancePlus|" +
                    "Glutes = ${maxLegsSets.getValue("maxGlutesCount")}$advancePlus|" +
                    "Adductors & Abductors = ${
                        maxLegsSets.getValue("maxAdductorsCount") + maxLegsSets.getValue(
                            "maxAbductorCount"
                        )
                    }$advancePlus"
        for (item in legsWorkout) {
            contentStr = contentStr + "\n" + item.name0 + "(${item.muscleSubGroup0})"
        }

        /***
         * Shoulder
         */
        contentStr = contentStr + "\n\n*SHOULDER*\n" + "Sets Per Week: Anterior Delts = ${
            maxShoulderSets.getValue("maxAntDeltsCount")
        }$advancePlus|" +
                "Posterior Delts = ${maxShoulderSets.getValue("maxPostDeltsCount")}$advancePlus|" +
                "Medial Delts = ${maxShoulderSets.getValue("maxMedDeltsCount")}$advancePlus"
        for (item in shoulderWorkout) {
            contentStr = contentStr + "\n" + item.name0 + "(${item.muscleSubGroup0})"
        }

        /***
         * Forearms
         */
        contentStr =
            contentStr + "\n\n*FOREARAMS*\n" + "Sets Per Week: Flexors = ${maxForearmsSets.getValue("maxFlexorsCount")}$advancePlus|" +
                    "Extensors = ${maxForearmsSets.getValue("maxExtensorsCount")}$advancePlus"
        for (item in forearmsWorkout) {
            contentStr = contentStr + "\n" + item.name0 + "(${item.muscleSubGroup0})"
        }

        /***
         * Abs
         */
        contentStr =
            contentStr + "\n\n*ABS*\n" + "Sets Per Week: Lower Abs = ${maxAbsSets.getValue("maxLowerAbsCount")}$advancePlus|" +
                    "Middle Abs = ${maxAbsSets.getValue("maxMiddleAbsCount")}$advancePlus|" +
                    "Transverse = ${maxAbsSets.getValue("maxTransverseCount")}$advancePlus|" +
                    "Obliques = ${maxAbsSets.getValue("maxObliquesCount")}$advancePlus|" +
                    "Upper Abs = ${maxAbsSets.getValue("maxUpperAbsCount")}$advancePlus"
        for (item in absWorkout) {
            contentStr = contentStr + "\n" + item.name0 + "(${item.muscleSubGroup0})"
        }

        DataEntryBody(type = "workoutRoutine", content = contentStr, text = "Save this workout plan")
        Button(
            onClick = onClick,
            modifier = modifier
                .padding(start = 47.dp, end = 47.dp, bottom = 47.dp)
                .fillMaxWidth()
        ) {
            Text(text = "Get another workout plan")
        }
    }
}


@Composable
fun WorkoutPrint(
    item: Exercises,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = Modifier
            .padding(5.dp)
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioLowBouncy,
                    stiffness = Spring.StiffnessMedium
                )
            )
    ) {
        var expanded by remember {
            mutableStateOf(false)
        }
        Row(
            modifier = modifier,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = item.name1,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .weight(1f)
                    .padding(top = 0.dp, bottom = 0.dp)
            )

            ExpandInfoButton(
                contentDescription = "See more or less information about this exercise",
                expanded = expanded,
                onClick = { expanded = !expanded })
        }
        if (expanded) {
            Column(modifier) {
                Text(text = item.muscleSubGroup1)
                Text(text = "Instructions: ${item.instructions1}")
            }
        }
    }

    Spacer(modifier = Modifier.height(11.dp))
}

@Preview(showBackground = true)
@Composable
fun WorkoutPlannerPreview() {
    HealthEaseTheme {
//        WorkoutPlanner(intensity = 1)
    }
}