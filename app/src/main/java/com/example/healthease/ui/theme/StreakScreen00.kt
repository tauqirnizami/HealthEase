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
                currentStreak = StreakCounter()
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
                    text = "days of streak!",
                    modifier = modifier
                        .padding(11.dp),
                    textAlign = TextAlign.Center,
                    fontSize = 21.sp,
                    fontWeight = FontWeight.SemiBold
                )
            } else {
                currentStreak = "0"

//                var dateData: AppData? = null
//                LaunchedEffect(Unit) {
                var dateDataList by remember { mutableStateOf(emptyList<AppData>()) }

                LaunchedEffect(streakCounterScreenViewModel) {
                    streakCounterScreenViewModel.appDataRepository.getDataStream("lastDate")
                        .collect { newDataList ->
                            dateDataList = newDataList as List<AppData>
                        }
                }

                var dateData: AppData?/* = dateDataList[0] ?: null*/
                dateData = if (dateDataList.isNotEmpty())
                    dateDataList[0]
                else null

//                    dateData = streakCounterScreenViewModel.streakDataHandler()
//                }

                var streakDataList by remember { mutableStateOf(emptyList<AppData>()) }

                LaunchedEffect(streakCounterScreenViewModel) {
                    streakCounterScreenViewModel.appDataRepository.getDataStream("currentStreak")
                        .collect { newDataList ->
                            streakDataList = newDataList as List<AppData>
                        }
                }

                var streakData: AppData?/* = streakDataList[0] ?: null*/
                streakData = if (streakDataList.isNotEmpty())
                    streakDataList[0]
                else null
//                var streakData: AppData? = null
//                LaunchedEffect(Unit) {
//                    streakData = streakCounterScreenViewModel.streakDataHandler("currentStreak")
//                }

                if ((dateData != null) && (streakData != null)) {
                    LaunchedEffect(Unit) {

                        streakCounterScreenViewModel.updateData(
                            id = dateData!!.id,
                            type = dateData!!.type,
                            content = "00-00-0000"
                        )
                    }
                    LaunchedEffect(Unit) {
                        streakCounterScreenViewModel.updateData(
                            id = streakData!!.id,
                            type = streakData!!.type,
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
                text = "Did You Do Your Workout And Followed A Healthy Diet Plan?",
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