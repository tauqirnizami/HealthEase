package com.example.healthease.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.healthease.data.AppData
import java.text.SimpleDateFormat
import java.util.Date

@Composable
fun StreakCounter(
    modifier: Modifier = Modifier,
    streakCounterViewModel: CalculationsViewModel = viewModel(factory = AppViewModelProvider.Factory)

): String {
    val currentRetrievedDate = Date()
    val currentDate: String = SimpleDateFormat("dd-MM-yyyy").format(currentRetrievedDate)

    streakCounterViewModel.selectType("lastDate")
    val lastDateObjectList by streakCounterViewModel.appData.collectAsStateWithLifecycle()

    if (lastDateObjectList.size > 1) {
        LaunchedEffect(Unit) {
            for (i in 1 until lastDateObjectList.size) {
                streakCounterViewModel.deleteData(
                    id = lastDateObjectList[i].id,
                    type = lastDateObjectList[i].type,
                    content = lastDateObjectList[i].content
                )
            }
        }
    }

    val lastDateObject: AppData? = if (lastDateObjectList.isNotEmpty())
        lastDateObjectList[0]
    else null

    val lastDate = lastDateObject?.content ?: "00-00-0000"

    streakCounterViewModel.streakSelectType("currentStreak")
    val currentStreakObjectList by streakCounterViewModel.streakAppData.collectAsStateWithLifecycle()

    if (currentStreakObjectList.size > 1) {
        LaunchedEffect(Unit) {
            for (i in 1 until currentStreakObjectList.size) {
                streakCounterViewModel.deleteData(
                    id = currentStreakObjectList[i].id,
                    type = currentStreakObjectList[i].type,
                    content = currentStreakObjectList[i].content
                )
            }
        }
    }

    val currentStreakObject: AppData? = if (currentStreakObjectList.isNotEmpty())
        currentStreakObjectList[0]
    else null
    var currentStreak = currentStreakObject?.content ?: "0"

    var currentStreakInt = currentStreak.toInt()

    var lastDateDay = ""
    var lastDateMonth = ""
    var lastDateYear = ""

    for (i in 0..1) {
        lastDateDay += lastDate[i]
    }
    val lastDateDayInt: Int = lastDateDay.toInt()
    for (i in 3..4) {
        lastDateMonth += lastDate[i]
    }
    val lastDateMonthInt: Int = lastDateMonth.toInt()
    for (i in 6..9) {
        lastDateYear += lastDate[i]
    }
    val lastDateYearInt: Int = lastDateYear.toInt()

    var currentDateDay = ""
    var currentDateMonth = ""
    var currentDateYear = ""

    for (i in 0..1) {
        currentDateDay += currentDate[i]
    }
    val currentDateDayInt: Int = currentDateDay.toInt()
    for (i in 3..4) {
        currentDateMonth += currentDate[i]
    }
    val currentDateMonthInt: Int = currentDateMonth.toInt()
    for (i in 6..9) {
        currentDateYear += currentDate[i]
    }
    val currentDateYearInt: Int = currentDateYear.toInt()

    if (((((currentDateDayInt == lastDateDayInt + 1) && (lastDateMonthInt == currentDateMonthInt)) ||
                ((((lastDateMonthInt == 1) && (currentDateMonthInt == 2)) || ((lastDateMonthInt == 3) && (currentDateMonthInt == 4)) || ((lastDateMonthInt == 5) && (currentDateMonthInt == 6)) || ((lastDateMonthInt == 7) && (currentDateMonthInt == 8)) || ((lastDateMonthInt == 8) && (currentDateMonthInt == 9)) || ((lastDateMonthInt == 10) && (currentDateMonthInt == 11))) && ((lastDateDayInt == 31) && (currentDateDayInt == 1))) ||
                ((((lastDateMonthInt == 4) && (currentDateMonthInt == 5)) || ((lastDateMonthInt == 6) && (currentDateMonthInt == 7)) || ((lastDateMonthInt == 9) && (currentDateMonthInt == 10)) || ((lastDateMonthInt == 11) && (currentDateMonthInt == 12))) && ((lastDateDayInt == 30) && (currentDateDayInt == 1))) ||
                ((((lastDateMonthInt == 2) && (currentDateMonthInt == 3)) && ((lastDateDayInt == 28) && (currentDateDayInt == 1))) && (currentDateYearInt % 4 != 0)) ||
                ((((lastDateMonthInt == 2) && (currentDateMonthInt == 3)) && ((lastDateDayInt == 29) && (currentDateDayInt == 1))) && (currentDateYearInt % 4 == 0))
                && (currentDateYearInt == lastDateYearInt)) ||
                ((((lastDateMonthInt == 12) && (currentDateMonthInt == 1)) && ((lastDateDayInt == 31) && (currentDateDayInt == 1))) && (lastDateYearInt == currentDateYearInt - 1))
                ) && (((lastDateDayInt != 0) && (lastDateMonthInt != 0)) && (lastDateYearInt != 0))
    ) {
        currentStreakInt++
        currentStreak = currentStreakInt.toString()
        if (currentStreakObject != null) {
            LaunchedEffect(Unit) {

                streakCounterViewModel.updateData(
                    id = currentStreakObject.id,
                    type = currentStreakObject.type,
                    content = currentStreak
                )
            }
        } else {
            currentStreakInt++
            currentStreak = currentStreakInt.toString()

            LaunchedEffect(Unit) {
                streakCounterViewModel.saveData(
                    type = "currentStreak",
                    content = currentStreak
                )
            }
        }

        if (lastDateObject != null) {
            LaunchedEffect(Unit) {
                streakCounterViewModel.updateData(
                    id = lastDateObject.id,
                    type = lastDateObject.type,
                    content = currentDate
                )
            }
        } else {
            LaunchedEffect(Unit) {
                streakCounterViewModel.saveData(
                    type = "lastDate",
                    content = currentDate
                )
            }
        }
    }
//    else if (currentDate == lastDate) {
//
//    }
    else if (currentDate != lastDate) {
        currentStreakInt = 1
        currentStreak = "1"
        if (currentStreakObject != null) {
            LaunchedEffect(Unit) {

                streakCounterViewModel.updateData(
                    id = currentStreakObject.id,
                    type = currentStreakObject.type,
                    content = currentStreak
                )
            }
        } else {
            LaunchedEffect(Unit) {
                streakCounterViewModel.saveData(
                    type = "currentStreak",
                    content = currentStreak
                )
            }
        }

        if (lastDateObject != null) {
            LaunchedEffect(Unit) {
                streakCounterViewModel.updateData(
                    id = lastDateObject.id,
                    type = lastDateObject.type,
                    content = currentDate
                )
            }
        } else {
            LaunchedEffect(Unit) {
                streakCounterViewModel.saveData(
                    type = "lastDate",
                    content = currentDate
                )
            }
        }
    }
    return currentStreak
}