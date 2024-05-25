package com.example.healthease.ui.theme

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.healthease.data.AppData

@Composable
fun StreakCounterScreen(
    modifier: Modifier = Modifier,
    streakCounterScreenViewModel: CalculationsViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    Column(
        modifier = modifier
            .padding(21.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        var isStreakScreen by remember {
            mutableStateOf(false)
        }
        var yesOrNo by rememberSaveable {
            mutableStateOf(true)
        }

        if (isStreakScreen) {
            val currentStreak: String
            if (yesOrNo) {
                currentStreak = actualStreakCounter(actualStreakCounterViewModel = streakCounterScreenViewModel)
                Text(
                    text = "Congratulations! Your have a",
                    modifier = modifier
                        .padding(11.dp),
                    textAlign = TextAlign.Center,
                    fontSize = 21.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = currentStreak,
                    modifier = modifier
                        .padding(7.dp),
                    textAlign = TextAlign.Center,
                    fontSize = 151.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "-day streak!",
                    modifier = modifier
                        .padding(11.dp),
                    textAlign = TextAlign.Center,
                    fontSize = 21.sp,
                    fontWeight = FontWeight.SemiBold
                )
            } else {
                currentStreak = "0"

                val pair = streakCounter01(streakCounterViewModel = streakCounterScreenViewModel)

                if (pair.first.size > 1) {
                    LaunchedEffect(Unit) {
                        for (i in 1 until pair.first.size) {
                            streakCounterScreenViewModel.deleteData(
                                id = pair.first[i].id,
                                type = pair.first[i].type,
                                content = pair.first[i].content
                            )
                        }
                    }
                }

                val dateData: AppData? = if (pair.first.isNotEmpty())
                    pair.first[0]
                else null

                if (pair.second.size > 1) {
                    LaunchedEffect(Unit) {
                        for (i in 1 until pair.second.size) {
                            streakCounterScreenViewModel.deleteData(
                                id = pair.second[i].id,
                                type = pair.second[i].type,
                                content = pair.second[i].content
                            )
                        }
                    }
                }

                val streakData: AppData? = if (pair.second.isNotEmpty())
                    pair.second[0]
                else null

                if ((dateData != null) && (streakData != null)) {
                    LaunchedEffect(Unit) {

                        streakCounterScreenViewModel.updateData(
                            id = dateData.id,
                            type = dateData.type,
                            content = "00-00-0000"
                        )
                    }
                    LaunchedEffect(Unit) {
                        streakCounterScreenViewModel.updateData(
                            id = streakData.id,
                            type = streakData.type,
                            content = "0"
                        )
                    }
                }
                Text(
                    text = "Your have a",
                    modifier = modifier
                        .padding(11.dp),
                    textAlign = TextAlign.Center,
                    fontSize = 21.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = currentStreak,
                    modifier = modifier
                        .padding(7.dp),
                    textAlign = TextAlign.Center,
                    fontSize = 151.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "days of streak!",
                    modifier = modifier
                        .padding(11.dp),
                    textAlign = TextAlign.Center,
                    fontSize = 21.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        } else {
            Spacer(modifier = Modifier.height(21.dp))

            Text(
                text = "Did You Do Your Workout And Followed A Healthy Diet Plan?\n" +
                        "You can change your choice as long as the app is open.\n" +
                        "If the date changes while you're using the app, you'll need to restart it to see your new streak.",
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(59.dp))

            Button(onClick = {
                yesOrNo = true
                isStreakScreen = true
            }) {
                Text(text = "Yes I did!")
            }

            Spacer(modifier = Modifier.height(23.dp))

            Button(onClick = {
                yesOrNo = false
                isStreakScreen = true
            }) {
                Text(text = "No, I didn't")
            }

            Spacer(modifier = Modifier.height(21.dp))

        }
    }
}


@Preview(showBackground = true)
@Composable
fun StreakCounterScreenPreview() {
    HealthEaseTheme {
        StreakCounterScreen()
    }
}