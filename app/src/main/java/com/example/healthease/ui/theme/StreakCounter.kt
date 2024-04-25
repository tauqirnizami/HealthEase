package com.example.healthease.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.healthease.data.AppData
import java.text.SimpleDateFormat
import java.util.Date

@Composable
fun StreakCounter(
    modifier: Modifier = Modifier,
    streakCounterViewModel: CalculationsViewModel = viewModel(factory = AppViewModelProvider.Factory)

): String {
    val currentRetrievedDate: Date = Date()
    val currentDate: String = SimpleDateFormat("dd-MM-yyyy").format(currentRetrievedDate)

    var lastDateObject: AppData? = null
//    var lastDateId: Int = 0
    LaunchedEffect(Unit) {
        lastDateObject = streakCounterViewModel.streakDataHandler()
    }
    var lastDate: String = "00-00-0000"
    if (lastDateObject != null) {
//        LaunchedEffect(Unit) {
//            streakCounterViewModel.saveData(type = "lastDate", content = currentDate)
//            lastDate = currentDate
//        }
        lastDate = lastDateObject!!.content
    }
//    LaunchedEffect(Unit) {
//        lastDateId = streakCounterViewModel.streakDataHandler()!!.id
//    }


    var currentStreakObject: AppData? = null
    LaunchedEffect(Unit) {
        currentStreakObject = streakCounterViewModel.streakDataHandler("currentStreak")
    }
    var currentStreak: String = "0"
//    var streakId: Int = 0
    if (currentStreakObject != null) {
//        LaunchedEffect(Unit) {
//            streakCounterViewModel.saveData(type = "currentStreak", content = "0")
//        }
        currentStreak = currentStreakObject!!.content
//    } else {
    }
//    LaunchedEffect(Unit) {
//        streakId = streakCounterViewModel.streakDataHandler("currentStreak")!!.id
//    }

    var currentStreakInt = currentStreak.toInt()

//    val lastDateDay =
//        lastDate.substring(0, 1) // Extracting 4th and 5th digits (index starts from 0)
//    val lastDateDayInt = lastDateDay.toInt()
//    val lastDateMonth =
//        lastDate.substring(3, 4) // Extracting 4th and 5th digits (index starts from 0)
//    val lastDateMonthInt = lastDateMonth.toInt()
//    val lastDateYear =
//        lastDate.substring(6, 9) // Extracting 4th and 5th digits (index starts from 0)
//    val lastDateYearInt = lastDateYear.toInt()
    var lastDateDay: String = "" // Extracting 4th and 5th digits (index starts from 0)
    var lastDateDayInt: Int
    var lastDateMonth: String = "" // Extracting 4th and 5th digits (index starts from 0)
    var lastDateMonthInt: Int
    var lastDateYear: String = "" // Extracting 4th and 5th digits (index starts from 0)
    var lastDateYearInt: Int

    for (i in 0 .. 1){
        lastDateDay += lastDate[i]
    }
    lastDateDayInt = lastDateDay.toInt()
    for (i in 3 .. 4){
        lastDateMonth += lastDate[i]
    }
    lastDateMonthInt = lastDateMonth.toInt()
    for (i in 6 .. 9){
        lastDateYear += lastDate[i]
    }
    lastDateYearInt = lastDateYear.toInt()

//    val currentDateDay =
//        currentDate.substring(0, 1) // Extracting 4th and 5th digits (index starts from 0)
//    val currentDateDayInt = currentDateDay.toInt()
//    val currentDateMonth =
//        currentDate.substring(3, 4) // Extracting 4th and 5th digits (index starts from 0)
//    val currentDateMonthInt = currentDateMonth.toInt()
//    val currentDateYear =
//        currentDate.substring(6, 9) // Extracting 4th and 5th digits (index starts from 0)
//    val currentDateYearInt = currentDateYear.toInt()

    var currentDateDay: String = "" // Extracting 4th and 5th digits (index starts from 0)
    var currentDateDayInt: Int
    var currentDateMonth: String = "" // Extracting 4th and 5th digits (index starts from 0)
    var currentDateMonthInt: Int
    var currentDateYear: String = "" // Extracting 4th and 5th digits (index starts from 0)
    var currentDateYearInt: Int

    for (i in 0 .. 1){
        currentDateDay += currentDate[i]
    }
    currentDateDayInt = currentDateDay.toInt()
    for (i in 3 .. 4){
        currentDateMonth += currentDate[i]
    }
    currentDateMonthInt = currentDateMonth.toInt()
    for (i in 6 .. 9){
        currentDateYear += currentDate[i]
    }
    currentDateYearInt = currentDateYear.toInt()

    if ((((currentDateDayInt == lastDateDayInt + 1) ||
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
                    id = currentStreakObject!!.id,
                    type = currentStreakObject!!.type,
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
                    id = lastDateObject!!.id,
                    type = lastDateObject!!.type,
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
    } else if (currentDate == lastDate) {

    } else {
        currentStreakInt = 1
        currentStreak = "1"
        if (currentStreakObject != null) {
            LaunchedEffect(Unit) {

                streakCounterViewModel.updateData(
                    id = currentStreakObject!!.id,
                    type = currentStreakObject!!.type,
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

    }
    return currentStreak
}