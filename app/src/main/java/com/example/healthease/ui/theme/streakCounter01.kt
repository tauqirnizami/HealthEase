package com.example.healthease.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.healthease.data.AppData
import kotlinx.coroutines.flow.toList
import java.text.SimpleDateFormat
import java.util.Date


@Composable
fun actualStreakCounter(
//    pair: Pair<List<AppData>, List<AppData>>,
    actualStreakCounterViewModel: CalculationsViewModel = viewModel(factory = AppViewModelProvider.Factory)
): String {
    val currentRetrievedDate = Date()
    val currentDate: String = SimpleDateFormat("dd-MM-yyyy").format(currentRetrievedDate)
    val pair = streakCounter01(streakCounterViewModel = actualStreakCounterViewModel)

    if (pair.first.size > 1) {
        LaunchedEffect(Unit) {
            for (i in 1 until pair.first.size) {
                actualStreakCounterViewModel.deleteData(
                    id = pair.first[i].id,
                    type = pair.first[i].type,
                    content = pair.first[i].content
                )
            }
        }
    }

    val lastDateObject: AppData? = pair.first.firstOrNull()/*if (pair.first.isNotEmpty())
        pair.first[0]
    else null*/

    val lastDate = lastDateObject?.content ?: "00-00-0000"



    if (pair.second.size > 1) {
        LaunchedEffect(Unit) {
            for (i in 1 until pair.second.size) {
                actualStreakCounterViewModel.deleteData(
                    id = pair.second[i].id,
                    type = pair.second[i].type,
                    content = pair.second[i].content
                )
            }
        }
    }

    val currentStreakObject: AppData? = pair.second.firstOrNull()/*if (pair.second.isNotEmpty())
        pair.second[0]
    else null*/

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
    /*These were the different conditions for 2 different dates to be of 2 consecutive days.
    * first one simply date increment by one in the same month
    * second one is the last day of a month with 31 days, & 1st of the next month
    * third is last day of month with 30 days, &  1st of next month
    * fourth is last day of february & 1st of march
    * Because all of these conditions need to be fulfilled within the same year, hence there is a common condition for it
    * fifth is the last day of december, and 1st of january of the next year
    * lastly, because date, month, and year values mustn't be 0 (which is the default value for them), hence a condition for it*/
    ) {
        currentStreakInt++
        currentStreak = currentStreakInt.toString()
        if (currentStreakObject != null) {
            LaunchedEffect(Unit) {

                actualStreakCounterViewModel.updateData(
                    id = currentStreakObject.id,
                    type = currentStreakObject.type,
                    content = currentStreak
                )
            }
        } /*else {
            currentStreakInt++
            currentStreak = currentStreakInt.toString()

            LaunchedEffect(Unit) {
                actualStreakCounterViewModel.saveData(
                    type = "currentStreak",
                    content = currentStreak
                )
            }
        }*/

        if (lastDateObject != null) {
            LaunchedEffect(Unit) {
                actualStreakCounterViewModel.updateData(
                    id = lastDateObject.id,
                    type = lastDateObject.type,
                    content = currentDate
                )
            }
        } /*else {
            LaunchedEffect(Unit) {
                actualStreakCounterViewModel.saveData(
                    type = "lastDate",
                    content = currentDate
                )
            }
        }*/
    }
//    else if (currentDate == lastDate) {
//
//    }
//    else {
    else if (currentDate != lastDate){
    /*This block is for when the streak needs to be set to 1 such as when user logged in after more than 1 day,
    * or when the user logged in for the first time
    * or when the user said he didn't do workout, then same day or some other day came back and logged in
    * in the last 2 points, the day, month, year, and streak values are set to 0
    * this is the reason why when date values are 0, I don't allowed for the if block to be executed
    * All these conditions can automatically be fulfilled if the first if block's conditions are not met
    * and the user didn't logged in on the same date*/
        currentStreakInt = 1
        currentStreak = "1"
        if (currentStreakObject != null) {
            LaunchedEffect(Unit) {

                actualStreakCounterViewModel.updateData(
                    id = currentStreakObject.id,
                    type = currentStreakObject.type,
                    content = currentStreak
                )
            }
        } else {
            LaunchedEffect(Unit) {
                actualStreakCounterViewModel.saveData(
                    type = "currentStreak",
                    content = currentStreak
                )
            }
        }

        if (lastDateObject != null) {
            LaunchedEffect(Unit) {
                actualStreakCounterViewModel.updateData(
                    id = lastDateObject.id,
                    type = lastDateObject.type,
                    content = currentDate
                )
            }
        } else {
            LaunchedEffect(Unit) {
                actualStreakCounterViewModel.saveData(
                    type = "lastDate",
                    content = currentDate
                )
            }
        }
    }
    /*There is no block for same day login bcz nothing would need change in that situation*/
    return currentStreak

}

@Composable
fun streakCounter01(
//    modifier: Modifier = Modifier,
    streakCounterViewModel: CalculationsViewModel = viewModel(factory = AppViewModelProvider.Factory)

): Pair<List<AppData>, List<AppData>> {
//    streakCounterViewModel.selectType("lastDate")
//    val lastDateObjectList by streakCounterViewModel.appData.collectAsStateWithLifecycle()
//
//    streakCounterViewModel.streakSelectType("currentStreak")
//    val currentStreakObjectList by streakCounterViewModel.streakAppData.collectAsStateWithLifecycle()
//
//    val lastDateObjectList = streakCounterViewModel.lastDate
//    val currentStreakObjectList = streakCounterViewModel.currentStreak

    val lastDateObjectList = streakCounterViewModel.lastDate
    val currentStreakObjectList = streakCounterViewModel.currentStreak

    return Pair(lastDateObjectList, currentStreakObjectList)
}