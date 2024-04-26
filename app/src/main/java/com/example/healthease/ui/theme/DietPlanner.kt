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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.healthease.DietData.FoodData
import com.example.healthease.DietData.PostWorkout
import com.example.healthease.ExpandInfoButton

@Composable
fun DietPlanner(
    dietPlannerViewModel: CalculationsViewModel = viewModel(factory = AppViewModelProvider.Factory),
    modifier: Modifier = Modifier,
    isVeg: Boolean = false,
    onClick: () -> Unit
) {
    val diet = dietPlannerViewModel.dietSelection(isVeg = isVeg)

    Column(
        modifier
            .padding(21.dp)
            .verticalScroll(rememberScrollState())
    ) {

        for (i in 1..12) {
            Column(modifier = modifier) {

                when (i) {
                    1 -> {Text(text = "\n\n",
                        fontWeight = FontWeight.Bold,
                        fontSize = 21.sp,
                        modifier = modifier.padding(0.dp))
                        Card(modifier = modifier
                            .fillMaxWidth()
                            .padding(end = 61.dp)) {
                        Text(
                            text = "Breakfast:",
                            fontWeight = FontWeight.Bold,
                            fontSize = 21.sp,
                            modifier = modifier.padding(top = 5.dp, bottom = 5.dp, start = 9.dp, end = 9.dp)
                        )
                    }
                        Text(text = "\n",
                            fontWeight = FontWeight.Bold,
                            fontSize = 21.sp,
                            modifier = modifier.padding(0.dp))
                    }

                    4

                    ->{
                        Text(text = "\n\n",
                            fontWeight = FontWeight.Bold,
                            fontSize = 21.sp,
                            modifier = modifier.padding(0.dp))
                        Card(modifier = modifier
                            .fillMaxWidth()
                            .padding(end = 61.dp)) {
                        Text(
                            text = "Lunch:",
                            fontWeight = FontWeight.Bold,
                            fontSize = 21.sp,
                            modifier = modifier.padding(top = 5.dp, bottom = 5.dp, start = 9.dp, end = 9.dp)
                        )
                    }
                        Text(text = "\n")
                    }

                    7 -> {
                        Text(text = "\n\n",
                            fontWeight = FontWeight.Bold,
                            fontSize = 21.sp,
                            modifier = modifier.padding(0.dp))
                        Card(modifier = modifier
                            .fillMaxWidth()
                            .padding(end = 61.dp)) {
                        Text(
                            text = "Post Workout Meal:",
                            fontWeight = FontWeight.Bold,
                            fontSize = 21.sp,
                            modifier = modifier.padding(top = 5.dp, bottom = 5.dp, start = 9.dp, end = 9.dp)
                        )
                    }
                        Text(text = "\n",
                            fontWeight = FontWeight.Bold,
                            fontSize = 21.sp,
                            modifier = modifier.padding(0.dp))
                    }

                    10 -> { Text(text = "\n\n",
                        fontWeight = FontWeight.Bold,
                        fontSize = 21.sp,
                        modifier = modifier.padding(0.dp))
                        Card(modifier = modifier
                            .fillMaxWidth()
                            .padding(end = 61.dp)) {
                            Text(
                                text = "Dinner:",
                                fontWeight = FontWeight.Bold,
                                fontSize = 21.sp,
                                modifier = modifier.padding(top = 5.dp, bottom = 5.dp, start = 9.dp, end = 9.dp)
                            )
                        }
                        Text(text = "\n",
                            fontWeight = FontWeight.Bold,
                            fontSize = 21.sp,
                            modifier = modifier.padding(0.dp))
                    }
                    else -> {}
                }

                val optionText = when (i) {
                    in 1..3 -> "Option $i:"
                    in 4..6 -> "Option ${(i - 3)}:"
                    in 7..9 -> "Option ${(i - 6)}"
                    else -> "Option ${(i - 9)}"
                }

                Text(
                    text = optionText,
                    modifier = modifier.padding(start = 27.dp, end = 11.dp)
                )

                DietPrint(
                    diet[i - 1],
                    modifier = modifier
                        .padding(start = 37.dp, end = 17.dp)
                )
            }
        }

        var dietPlan: String = ""

        dietPlan = dietPlan + "*BREAKFAST*"
        for (i in 0..2) {
            dietPlan = dietPlan + "\nOption ${i + 1}:" + diet[i].name1
        }

        dietPlan = dietPlan + "\n\n*LUNCH*"
        for (i in 3..5) {
            dietPlan = dietPlan + "\nOption ${i - 2}:" + diet[i].name1
        }

        dietPlan = dietPlan + "\n\n*POST-WORKOUT*"
        for (i in 6..8) {
            dietPlan = dietPlan + "\nOption ${i + 5}:" + diet[i].name1
        }

        dietPlan = dietPlan + "\n\n*DINNER*"
        for (i in 9..11) {
            dietPlan = dietPlan + "\nOption ${i - 8}:" + diet[i].name1
        }

        DataEntryBody(type = "dietPlan", content = dietPlan, text = "Save this diet plan")

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
fun DietPrint(
    item: FoodData,
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
        Row(modifier) {
            Text(
                text = item.name1,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .weight(1f)
                    .padding(top = 0.dp, bottom = 0.dp)
            )

            ExpandInfoButton(
                contentDescription = "See more or less information about this dish",
                expanded = expanded,
                onClick = { expanded = !expanded })
        }
        if (expanded) {
            Column(modifier) {
                Text(
                    text = "Calories per 100 gram: ${item.caloriesPer100g1} Kcal\n" +
                            "Protein per 100 gram: ${item.proteinPer100g1}\n" +
                            "Carbs per 100 gram: ${item.caloriesPer100g1}\n" +
                            "Fat per 100 gram: ${item.fatPer100g1}\n" +
                            "Other nutrients: ${item.otherNutrients1}"
                )
                Text(text = "Instructions: ${item.instructions1}")
                if (item is PostWorkout) {
                    Text(text = "\nMinimum time to wait after workout before having meal: ${item.timeToEatAfterWorkout0}")
                }
            }
        }
    }

    Spacer(modifier = Modifier.height(11.dp))
}


@Preview(showBackground = true)
@Composable
fun DietPlannerPreview() {
    HealthEaseTheme {
        DietPlanner(onClick = {})
    }
}