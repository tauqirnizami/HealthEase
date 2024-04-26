package com.example.healthease.ui.theme

import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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

@Composable
fun ExploreExercises(
    modifier: Modifier = Modifier,
    onSearchClick: () -> Unit
) {
    Column(
        modifier = modifier
            .padding(21.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(21.dp))

        OutlinedButton(onClick = { onSearchClick() }) {
            Text(text = "Search a particular Exercise")
        }

        Spacer(modifier = Modifier.height(51.dp))

        var atHome by remember {
            mutableStateOf(true)
        }
        HomeOrGym(text = "Only home exercises", open = atHome, onChanged = { atHome = !atHome })

        var chestExercises = mutableListOf<ChestExercises>()
        if (atHome) {
            homeChestExercises.shuffled().forEach() { item ->
                chestExercises.add(item)
            }
        } else {
            chestExercises.addAll(homeChestExercises)
            chestExercises.addAll(gymChestExercises)
            chestExercises = chestExercises.shuffled().toMutableList()
        }

        Spacer(modifier = Modifier.height(27.dp))

        DisplayExerciseList(title = "Chest Exercises", list = chestExercises)


        var shoulderExercises = mutableListOf<ShoulderExercises>()
        if (atHome) {
            homeShoulderExercises.shuffled().forEach() { item ->
                shoulderExercises.add(item)
            }
        } else {
            shoulderExercises.addAll(homeShoulderExercises)
            shoulderExercises.addAll(gymShoulderExercises)
            shoulderExercises = shoulderExercises.shuffled().toMutableList()
        }

        Spacer(modifier = Modifier.height(27.dp))

        DisplayExerciseList(title = "Shoulder Exercises", list = shoulderExercises)


        var tricepsExercises = mutableListOf<TricepsExercises>()
        if (atHome) {
            homeTricepsExercises.shuffled().forEach() { item ->
                tricepsExercises.add(item)
            }
        } else {
            tricepsExercises.addAll(homeTricepsExercises)
            tricepsExercises.addAll(gymTricepsExercises)
            tricepsExercises = tricepsExercises.shuffled().toMutableList()
        }

        Spacer(modifier = Modifier.height(27.dp))

        DisplayExerciseList(title = "Triceps Exercises", list = tricepsExercises)


        var bicepsExercises = mutableListOf<BicepsExercises>()
        if (atHome) {
            homeBicepsExercises.shuffled().forEach() { item ->
                bicepsExercises.add(item)
            }
        } else {
            bicepsExercises.addAll(homeBicepsExercises)
            bicepsExercises.addAll(gymBicepsExercises)
            bicepsExercises = bicepsExercises.shuffled().toMutableList()
        }

        Spacer(modifier = Modifier.height(27.dp))

        DisplayExerciseList(title = "Biceps Exercises", list = bicepsExercises)


        var backExercises = mutableListOf<BackExercises>()
        if (atHome) {
            homeBackExercises.shuffled().forEach() { item ->
                backExercises.add(item)
            }
        } else {
            backExercises.addAll(homeBackExercises)
            backExercises.addAll(gymBackExercises)
            backExercises = backExercises.shuffled().toMutableList()
        }

        Spacer(modifier = Modifier.height(27.dp))

        DisplayExerciseList(title = "Back Exercises", list = backExercises)


        var legsExercises = mutableListOf<LegExercises>()
        if (atHome) {
            homeLegExercises.shuffled().forEach() { item ->
                legsExercises.add(item)
            }
        } else {
            legsExercises.addAll(homeLegExercises)
            legsExercises.addAll(gymLegsExercises)
            legsExercises = legsExercises.shuffled().toMutableList()
        }

        Spacer(modifier = Modifier.height(27.dp))

        DisplayExerciseList(title = "Leg Exercises", list = legsExercises)


        var forearmsExercises = mutableListOf<ForearmsExercises>()
        if (atHome) {
            homeForearmExercises.shuffled().forEach() { item ->
                forearmsExercises.add(item)
            }
        } else {
            forearmsExercises.addAll(homeForearmExercises)
            forearmsExercises.addAll(gymForearmsExercises)
            forearmsExercises = forearmsExercises.shuffled().toMutableList()
        }

        Spacer(modifier = Modifier.height(27.dp))

        DisplayExerciseList(title = "Forearms Exercises", list = forearmsExercises)


        var absExercises = mutableListOf<AbsExercises>()
        if (atHome) {
            homeAbsExercises.shuffled().forEach() { item ->
                absExercises.add(item)
            }
        } else {
            absExercises.addAll(homeAbsExercises)
            absExercises.addAll(gymAbsExercises)
            absExercises = absExercises.shuffled().toMutableList()
        }

        Spacer(modifier = Modifier.height(27.dp))

        DisplayExerciseList(title = "Abs Exercises", list = absExercises)
    }
}


@Composable
fun DisplayExerciseList(
    modifier: Modifier = Modifier,
    title: String,
    list: List<Exercises>
) {
    Column(
        modifier = modifier
            .padding(0.dp)
            .fillMaxWidth(1f),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = title,
            modifier = modifier,
            fontSize = 25.sp,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Start
        )
        Spacer(modifier = Modifier.height(31.dp))

        var completeList by remember {
            mutableStateOf(false)
        }
        var expanded by rememberSaveable {
            mutableStateOf(false)
        }


        var dietItem by remember {
            mutableStateOf(list[0])
        }


        if (expanded) {
            DisplayExerciseItemScreen(item = dietItem,
                onClick = { expanded = !expanded })
        } else {
            if (!completeList) {
                Row(
                    modifier = modifier
                        .padding(5.dp)
                        .horizontalScroll(rememberScrollState()),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    for (i in 0..2) {
                        Card(
                            modifier = modifier
                                .padding(7.dp)
                        ) {
                            Column(
                                modifier = modifier
                                    .padding(start = 9.dp, end = 9.dp),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.Start
                            ) {
                                Text(
                                    text = list[i].name1,
                                    modifier = modifier
                                        .padding(
                                            top = 11.dp,
                                            start = 9.dp,
                                            end = 9.dp,
                                            bottom = 9.dp
                                        ),
                                    fontSize = 19.sp,
                                    fontWeight = FontWeight.SemiBold
                                )
                                DisplayItem(text = "Muscle Group: ${list[i].muscle1}")

                                DisplayItem(text = "Muscle Sub Group: ${list[i].muscleSubGroup1}")

                                OutlinedButton(onClick = {
                                    expanded = !expanded
                                    dietItem = list[i]
                                }) {
                                    Row {

                                        Text(
                                            text = "See more detail",
                                            fontSize = 15.sp
                                        )
                                        Icon(
                                            imageVector = if (expanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                                            contentDescription = "See/Hide Information"
                                        )
                                    }
                                }
                            }
                        }
                    }
                    Card(
                        modifier = Modifier
                            .padding(vertical = 8.dp, horizontal = 16.dp)
                            .clickable(onClick = { completeList = !completeList }),
                        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp) // Set desired elevation
                    ) {
                        Text(
                            text = "\n.\n.\n.\n",
                            modifier = modifier
                                .padding(start = 11.dp, end = 11.dp, top = 2.dp, bottom = 2.dp)
                        )
                    }
                }
            } else if (completeList) {
                Column(
                    modifier = modifier
                        .padding(5.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    list.forEach() { item ->
                        Card(
                            modifier = modifier
                                .padding(7.dp)
                        ) {
                            Column(
                                modifier = modifier
                                    .padding(start = 9.dp, end = 9.dp),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = item.name1,
                                    modifier = modifier
                                        .padding(
                                            top = 11.dp,
                                            start = 9.dp,
                                            end = 9.dp,
                                            bottom = 9.dp
                                        ),
                                    fontSize = 19.sp,
                                    fontWeight = FontWeight.SemiBold
                                )

                                Row(
                                    modifier = modifier
                                        .padding(3.dp)
                                        .horizontalScroll(rememberScrollState())
                                ) {
                                    DisplayItem(text = "Muscle Group: ${item.muscle1}")

                                    DisplayItem(text = "Muscle Sub Group: ${item.muscleSubGroup1}")

                                    Spacer(modifier = Modifier.height(7.dp))
                                }
                                OutlinedButton(onClick = {
                                    expanded = !expanded
                                    dietItem = item
                                }) {
                                    Row {

                                        Text(
                                            text = "See more detail",
                                            fontSize = 15.sp
                                        )
                                        Icon(
                                            imageVector = if (expanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                                            contentDescription = "See/Hide Information"
                                        )
                                    }
                                }
                            }
                        }
                    }
                    Button(onClick = { completeList = !completeList }) {
                        Text(
                            text = "See Less",
                            fontSize = 14.sp
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun DisplayExerciseItemScreen(
    modifier: Modifier = Modifier,
    item: Exercises,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier
            .padding(31.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(13.dp))

        Text(
            text = item.name1,
            fontSize = 31.sp,
            fontWeight = FontWeight.Bold,
            lineHeight = 33.sp,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(43.dp))

        Text(
            text = "Calorific  values:",
            fontWeight = FontWeight.SemiBold,
            fontSize = 21.sp
        )
        Text(
            text = "Muscle Groups targeted = ${item.muscle1}",
            modifier = modifier.padding(7.dp),
            textAlign = TextAlign.Justify
        )
        Text(
            text = "Muscle Sub Group = ${item.muscleSubGroup1}",
            modifier = modifier.padding(7.dp),
            textAlign = TextAlign.Justify
        )

        Spacer(modifier = Modifier.height(33.dp))

        Text(
            text = "Concise Instructions:",
            modifier = modifier.padding(7.dp),
            textAlign = TextAlign.Justify
        )
        Text(
            text = item.instructions1,
            fontWeight = FontWeight.SemiBold,
            fontSize = 21.sp
        )

        Spacer(modifier = Modifier.height(47.dp))

        Button(onClick = { onClick() }) {
            Text(text = "Go Back")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ExploreExercisesScreenPreview() {
    HealthEaseTheme {
        ExploreExercises(onSearchClick = {})
    }
}