package com.example.healthease.ui.theme

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.healthease.data.AppData
import com.example.healthease.DisplayInstructions
import com.example.healthease.ExpandInfoButton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun WorkoutSearchScreen(
    workoutSearchViewModel: CalculationsViewModel = viewModel(factory = AppViewModelProvider.Factory),
    modifier: Modifier = Modifier
) {
    val workoutSearchUiState by workoutSearchViewModel.uiState.collectAsState()
    val intensity: Int = workoutSearchUiState.level
    var expandedInstructions by remember {
        mutableStateOf(false)
    }
    var deleteDialog by remember {
        mutableStateOf(false)
    }
    var atHome: Boolean by remember {
        mutableStateOf(true)
    }
    val weightLoss: Boolean =
        if (workoutSearchUiState.currentWeight > workoutSearchUiState.goalWeight) true else false

    if (workoutSearchUiState.workoutSearchScreen) {
        Column(
            modifier
                .verticalScroll(rememberScrollState())
                .padding(5.dp)
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioNoBouncy,
                        stiffness = Spring.StiffnessVeryLow
                    )
                )
        ) {
            Spacer(modifier = Modifier.height(63.dp))

            Button(
                onClick = { workoutSearchViewModel.workoutSearching() },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(41.dp)
            ) {
                Text(
                    text = "Get a Workout Plan",
                    fontSize = 23.sp,
                    lineHeight = 30.sp,
                    textAlign = TextAlign.Center
                )
            }

            HomeOrGym(
                text = "Home Workout Only",
                open = atHome,
                onChanged = { atHome = it },
                modifier = modifier.padding(start = 21.dp, end = 21.dp)
            )

            Spacer(modifier = Modifier.height(33.dp))

            val text = when (intensity) {
                0 -> "This level is for occasional training reduction while wanting to  maintaining you progress for a short while"
                1 -> "This level is for beginners or for people wanting to maintain their current progress for long."
                2 -> "This level is for those who have spent some time in exercising, and want to get better."
                3 -> "This level is for a bit experienced people."
                else -> "This level is for pro people!"
            }

            Text(
                text = text,
                modifier = modifier.padding(start = 31.dp, bottom = 11.dp, end = 37.dp)
            )

            Spacer(modifier = Modifier.height(33.dp))

            Card(
                modifier
                    .padding(47.dp)
            ) {
                Column(
                    modifier
                        .padding(15.dp)
                ) {
                    Row(
                        modifier = modifier,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Instructions",
                            fontWeight = FontWeight.ExtraBold,
                            modifier = Modifier
                                .weight(1f)
                        )
                        ExpandInfoButton(
                            contentDescription = "See more or less instructions regarding workout plan",
                            expanded = expandedInstructions,
                            onClick = { expandedInstructions = !expandedInstructions })
                    }
                    if (expandedInstructions) {

                        Column {
                            DisplayInstructions(
                                heading = if (!atHome) "\nWeight & Reps Selection:" else "\nReps Selection:",
                                body = if (!atHome) "Start from 35% of the weight you can do only 1 rep with, and increase it to 75%." +
                                        "You should reach RIR 2-4 within 9-12 reps" else "Do as many reps as you can until you reach an RIR of 2-4"
                            )

                            DisplayInstructions(
                                heading = "\nRIR:",
                                body = "This denotes the maximum (remaining) reps of the exercises you can do at the moment. If, " +
                                        "after 9 reps, now you can only do 3 more reps at most, this would mean that " +
                                        "you reached RIR 3. " +
                                        "If you just want to maintain your progress, you don't need to increase reps/sets/weight"
                            )

                            DisplayInstructions(
                                heading = "\nSets:", body =
                                if (weightLoss) "For weight cutting, reduce the given number of Sets and spend more " +
                                        "time in cardio. Slowly increase the number of sets every week"
                                else if (workoutSearchUiState.currentWeight == workoutSearchUiState.goalWeight)
                                    "If you only want to maintain you current progress, Intermediate I level " +
                                            "is sufficient. Follow the given number of sets in the program"
                                else "Start from the given number of sets and increase 1-2 sets every week " +
                                        "until you reach the next level."
                            )

                            DisplayInstructions(
                                heading = "\nWorkout Routine:",
                                body = "For beginners, 2-3 full-body workouts weekly suffice. Slightly experienced individuals benefit from muscle group splits like push/pull or upper/lower body splits. Customizing variations is ideal, such as chest/back/triceps/biceps one day, and legs/shoulders/other muscles another day. An example of a workout routine can be Mon/Fri = chest + back + triceps + biceps | Tue/Sat = legs + shoulders | other days = rest. Remember, complete the sets required per week, not per workout session."
                            )

                            DisplayInstructions(
                                heading = "\nRest:",
                                body = "Rest requirements vary based on your workout routine. For single muscle group targeting, 1 rest day per week suffices. For targeting multiple muscle groups, repeating the routine 2 times weekly may necessitate 2-3 or more rest days. Optimal rest aids recovery, crucial for gains and progress. Listen to your body first. Beginners can aim for 3 days of workouts with optimal rest. Slightly experienced individuals may opt for 2-3 days of rest within 3-5 days of workouts. Pro athletes may benefit from 5-6 days of workouts with 1-2 days of rest."
                            )

                            DisplayInstructions(
                                heading = "\nDeload Week:",
                                body = "Do a ~30% reduction in your training when you start feeling to much load. " +
                                        "You may even schedule a deload week after every 4-6 weeks."
                            )

                            DisplayInstructions(
                                heading = "\nExplore!",
                                body = "You can explore many different at home or at gym exercises in the \"Search Exercises\" section of the app!"
                            )

                            DisplayInstructions(
                                heading = "\nGot bored of exercises?",
                                body = "Explore many different sports and other fun physical activities you may enjoy!"
                            )
                        }

                    }
                }
            }
            Spacer(modifier = Modifier.height(27.dp))

            var dataList by remember { mutableStateOf(emptyList<AppData>()) }

            LaunchedEffect(workoutSearchViewModel) {
                workoutSearchViewModel.appDataRepository.getDataStream("workoutRoutine")
                    .collect { newDataList ->
                        dataList = newDataList as List<AppData>
                    }
            }

            // Display the data
            Column {
//                Text("Data List:")
                var i = 0
                dataList.forEach { data ->
                    i++
                    Text(
                        "\n\nWorkout Plan $i",
                        modifier = modifier.padding(5.dp)
                    )
                    Row {
                        Card(
                            modifier = modifier
                                .padding(start = 13.dp, bottom = 17.dp, end = 17.dp, top = 5.dp)
                        ) {

                            Text(data.content,
                                modifier = modifier.padding(11.dp))
                            IconButton(onClick =
                            { deleteDialog = true }
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Delete,
                                    contentDescription = "Delete",
                                    tint = Color(0xe5d11a21)
                                )
                            }
                        }
                    }
                    if (deleteDialog) {
                        DeleteConfirmationDialog(
                            text = "Are you sure you want to delete this Workout Plan?",
                            id = data.id,
                            type = data.type,
                            content = data.content,
                            viewModel = workoutSearchViewModel,
                            onClose = { deleteDialog = false }
                        )
                    }
                }
            }
        }
    } else {
        WorkoutPlanner(
            intensity = intensity,
            home = atHome,
            weightLoss = weightLoss,
            onClick = { workoutSearchViewModel.workoutSearching() }
        )
    }

}

@Composable
fun DeleteConfirmationDialog(
    text: String,
    id: Int,
    type: String,
    content: String,
    viewModel: CalculationsViewModel,
    onClose: () -> Unit,
    modifier: Modifier = Modifier
) {

    AlertDialog(
        onDismissRequest = {
            onClose()
        },
        title = { Text(text = text) },
        modifier = modifier,
        dismissButton = {
            TextButton(
                onClick = {
                    onClose()
                }
            ) {
                Text(text = "Cancel")
            }
        },
        confirmButton = {
            TextButton(onClick = {
                    CoroutineScope(Dispatchers.IO).launch {
                        deleteData(
                            id,
                            type,
                            content,
                            viewModel
                        )
                        onClose()
                    }
                }) {
                Text(text = "Delete")
            }
        }
    )
}

suspend fun deleteData(id: Int, type: String, content: String, viewModel: CalculationsViewModel) {
    viewModel.deleteData(id, type, content)
}


@Composable
fun HomeOrGym(
    text: String,
    open: Boolean,
    onChanged: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
//            .fillMaxWidth()
//            .size(48.dp)
            ,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(text = text,
//            Modifier.weight(2f)
            )
        
        Spacer(modifier = Modifier.width(90.dp))
        
        Switch(
            modifier = Modifier
//                .fillMaxWidth()
                .wrapContentWidth(Alignment.End)
//                .weight(1f)
            ,
            checked = open,
            onCheckedChange = onChanged,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun WorkoutSearchScreenPreview() {
    HealthEaseTheme {
        WorkoutSearchScreen()
    }
}