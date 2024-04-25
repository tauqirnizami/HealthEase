package com.example.healthease.ui.theme

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
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
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.healthease.DietData.BreakfastDiet
import com.example.healthease.DietData.DinnerDiet
import com.example.healthease.DietData.FoodData
import com.example.healthease.DietData.LunchDiet
import com.example.healthease.DietData.PostWorkout
import com.example.healthease.DietData.breakfastDiet
import com.example.healthease.DietData.dinnerDiet
import com.example.healthease.DietData.lunchDiet
import com.example.healthease.DietData.postWorkoutDiet

@Composable
fun ExploreDishes(
    modifier: Modifier = Modifier,
    onSearchClick: ()->Unit
) {
    Column(
        modifier = modifier
            .padding(21.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(21.dp))
        var dialogue by remember {
            mutableStateOf(false)
        }
        Row(
            modifier = modifier,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            IconButton(onClick = { dialogue = !dialogue }) {
                Icon(
                    imageVector = Icons.Default.Info,
                    contentDescription = "Information Regarding Search Algorithm"
                )
            }

            OutlinedButton(onClick = { onSearchClick() }) {
                Text(text = "Search a particular Dish")
            }

            if (dialogue) {
                VegInfoDialog(
                    text = "1. You may even search some casual or unhealthy dishes here for calorie tracking purpose." +
                            "\n" +
                            "2. Top results prioritize exact name matches, while lower results may include secondary name matches or matches based on ingredients.",
                    onClose = { dialogue = !dialogue })
            }
        }

        Spacer(modifier = Modifier.height(51.dp))

        var vegOrNot by remember {
            mutableStateOf(false)
        }
        HomeOrGym(text = "Only Veg", open = vegOrNot, onChanged = { vegOrNot = !vegOrNot })

        val breakfastList = mutableListOf<BreakfastDiet>()
//        val breakfastList by remember { mutableStateOf<MutableList<BreakfastDiet>>(mutableListOf()) }
        if (vegOrNot) {
            breakfastDiet.shuffled().forEach() { item ->
                if (item.isVeg)
                    breakfastList.add(item)
            }
        } else {
            breakfastList.addAll(breakfastDiet.shuffled())
        }

        Spacer(modifier = Modifier.height(27.dp))

        DisplayList(title = "Diet Breakfast Dishes", list = breakfastList)

        val lunchList = mutableListOf<LunchDiet>()
        if (vegOrNot) {
            lunchDiet.shuffled().forEach() { item ->
                if (item.isVeg)
                    lunchList.add(item)
            }
        } else {
            lunchList.addAll(lunchDiet.shuffled())
        }

        Spacer(modifier = Modifier.height(51.dp))

        DisplayList(title = "Diet Lunch Dishes", list = lunchList)

        val postWorkoutList = mutableListOf<PostWorkout>()
        if (vegOrNot) {
            postWorkoutDiet.shuffled().forEach() { item ->
                if (item.isVeg)
                    postWorkoutList.add(item)
            }
        } else {
            postWorkoutList.addAll(postWorkoutDiet.shuffled())
        }

        Spacer(modifier = Modifier.height(51.dp))

        DisplayList(title = "Diet Post Workout Meals", list = postWorkoutList)

        val dinnerList = mutableListOf<DinnerDiet>()
        if (vegOrNot) {
            dinnerDiet.shuffled().forEach() { item ->
                if (item.isVeg)
                    dinnerList.add(item)
            }
        } else {
            dinnerList.addAll(dinnerDiet.shuffled())
        }

        Spacer(modifier = Modifier.height(51.dp))

        DisplayList(title = "Diet Dinner Dishes", list = dinnerList)

    }
}

@Composable
fun DisplayList(
    modifier: Modifier = Modifier,
    title: String,
    list: List<FoodData>
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
            DisplayItemScreen(item = dietItem,
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
                                DisplayItem(text = "Total Calories per 100g = ${list[i].caloriesPer100g1}")

                                DisplayItem(text = "Carbs per 100g = ${list[i].carbsPer100g1}")

                                DisplayItem(text = "Protein per 100g = ${list[i].proteinPer100g1}")

                                DisplayItem(text = "Fat per 100g = ${list[i].fatPer100g1}")


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
//                                }
                            }
                        }
                    }
                    Card(
                        modifier = Modifier
                            .padding(vertical = 8.dp, horizontal = 16.dp)
                            .clickable(onClick = { completeList = !completeList }),
                        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp) // Set desired elevation
                    ) {
                        Text(text = "\n.\n.\n.\n",
                            modifier = modifier
                                .padding(start = 11.dp, end = 11.dp, top = 2.dp, bottom = 2.dp))
                    }
//                    OutlinedButton(
//                        onClick = { completeList = !completeList },
//                        modifier = modifier.padding(0.dp)
//                    ) {
//                        Text(
//                            text = "\n.\n.\n.\n",
//                            modifier = modifier.padding(0.dp)
//                        )
//                    }
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
                                    DisplayItem(text = "Total Calories per 100g = ${item.caloriesPer100g1}")

                                    DisplayItem(text = "Carbs per 100g = ${item.carbsPer100g1}")

                                    DisplayItem(text = "Protein per 100g = ${item.proteinPer100g1}")

                                    DisplayItem(text = "Fat per 100g = ${item.fatPer100g1}")

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
fun DisplayItem(
    modifier: Modifier = Modifier,
    text: String
) {
    Text(
        text = text,
        modifier = modifier
            .padding(
                top = 1.dp,
                start = 9.dp,
                end = 9.dp,
                bottom = 2.dp
            ),
        fontSize = 14.sp
//                                    textAlign = TextAlign.Center
    )
}

@Composable
fun DisplayItemScreen(
    modifier: Modifier = Modifier,
    item: FoodData,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier
            .padding(31.dp)
//            .verticalScroll(rememberScrollState())
        ,
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
            text = "Total Calories per 100 gram = ${item.caloriesPer100g1}",
            modifier = modifier.padding(7.dp),
            textAlign = TextAlign.Justify
        )
        Text(
            text = "Total Protein per 100 gram = ${item.proteinPer100g1}",
            modifier = modifier.padding(7.dp),
            textAlign = TextAlign.Justify
        )
        Text(
            text = "Total Carbs per 100 gram = ${item.carbsPer100g1}",
            modifier = modifier.padding(7.dp),
            textAlign = TextAlign.Justify
        )
        Text(
            text = "Total Fat per 100 gram = ${item.fatPer100g1}",
            modifier = modifier.padding(7.dp),
            textAlign = TextAlign.Justify
        )
        Text(
            text = "Other Nutrients = ${item.otherNutrients1}",
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
fun ExploreDishesPreview() {
    HealthEaseTheme {
        ExploreDishes(onSearchClick = {})
    }
}