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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import com.example.healthease.DisplayInstructions
import com.example.healthease.ExpandInfoButton
import com.example.healthease.data.AppData

@Composable
fun DietSearchScreen(
    dietSearchViewModel: CalculationsViewModel = viewModel(factory = AppViewModelProvider.Factory),
    modifier: Modifier = Modifier
) {
    val dietSearchUiState by dietSearchViewModel.uiState.collectAsState()
    var expandedInstructions by remember {
        mutableStateOf(false)
    }
    var deleteDialog by remember {
        mutableStateOf(false)
    }
    var vegInstructions by remember {
        mutableStateOf(false)
    }
    var isVeg: Boolean by remember {
        mutableStateOf(true)
    }
    val weightLoss: Boolean =
        if (dietSearchUiState.currentWeight > dietSearchUiState.goalWeight) true else false

    if (dietSearchUiState.dietSearchScreen) {
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
                onClick = { dietSearchViewModel.dietSearching() },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(41.dp)
            ) {
                Text(
                    text = "Get a Diet Plan",
                    fontSize = 23.sp,
                    lineHeight = 30.sp,
                    textAlign = TextAlign.Center
                )
            }

            Row(
                modifier = modifier
                    .padding(start = 31.dp, end = 31.dp)
            ) {
                IconButton(onClick = { vegInstructions = !vegInstructions }) {
                    Icon(Icons.Default.Info, contentDescription = "Add something")
                }
                if (vegInstructions) {
                    VegInfoDialog(text = "Veg diet includes diet for Lacto-Veg people. I.e., it includes vegetables," +
                            "dairy items, etc. but excludes things such as meat, poultry items, eggs, etc.",
                        onClose = { vegInstructions = !vegInstructions })
                }
                HomeOrGym(
                    text = " Veg? (or Non Veg?)",
                    open = isVeg,
                    onChanged = { isVeg = it },
                    modifier =
                    modifier.weight(1f)
                )
            }

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
                            val calculations: String =
                                "You can easily see your current calorie need in the \"Calculations\" section of the app."
                            DisplayInstructions(
                                heading = "\nIntake Amount:",
                                body = if (weightLoss) "Amounts should be such that you would be reducing 300-500 Kcal per day. $calculations"
                                else "For weight gain, increase  your calorie intake by 300-500 Kcal per day," +
                                        " and for maintaining your weight, keep your calorie intake closer to you current calorie need. $calculations"
                            )

                            DisplayInstructions(
                                heading = "\nCalorie Division",
                                body = "Even though there is no hard & fast rule for this, but a general division for calorie" +
                                        " intake throughout the day can be:\n" +
                                        "Breakfast = 30-40%\n" +
                                        "Lunch: 30-40%\n" +
                                        "Post Workout: 10-20%\n" +
                                        "Dinner: 20-30%\n" +
                                        "Your daily fluid (water) need can be calculated in the \"Calculations\" section of the app."
                            )

                            DisplayInstructions(heading = "\nCalorific Division", body = "Recommended Ranges for macro nutrients would depend on various factors, some general rules would be:\n" +
                                    "Protein Intake: should be between 20-35%. There should a minimum of 0.8 grams protein per kg of body weight\n" +
                                    "Carbs Intake: should be between 45-65%\n" +
                                    "Healthy Fats: should be between 20-35%, although 0.5-1 gram per kg of body weight is a sufficient range")

                            DisplayInstructions(
                                heading = "\nCalorie Tracking Made Easy:",
                                body = "Whenever you eat not in your diet plan, you can easily see its calorie count & basic nutritional info in the \"Search Dish\" section of ths app!"
                            )

                            DisplayInstructions(
                                heading = "\nGot bored of Dieting?",
                                body = "Explore many Different Healthy dishes in the \"Search Dish\" section of the app!"
                            )
                        }

                    }
                }
            }
            Spacer(modifier = Modifier.height(27.dp))

            var dataList by remember { mutableStateOf(emptyList<AppData>()) }

            LaunchedEffect(dietSearchViewModel) {
                dietSearchViewModel.appDataRepository.getDataStream("dietPlan")
                    .collect { newDataList ->
                        dataList = newDataList as List<AppData>
                    }
            }

            Column {
                var i = 0
                dataList.forEach { data ->
                    i++
                    Text(
                        "\n\nDiet Plan $i",
                        modifier = modifier.padding(5.dp)
                    )
                    Row {
                        Card(
                            modifier = modifier
                                .padding(start = 13.dp, bottom = 17.dp, end = 17.dp, top = 5.dp)
                        ) {

                            Text(
                                data.content,
                                modifier = modifier.padding(11.dp)
                            )
                            IconButton(onClick = {
                                deleteDialog = true
                            }) {
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
                            text = "Are you sure you want to delete this Diet Plan?",
                            id = data.id,
                            type = data.type,
                            content = data.content,
                            viewModel = dietSearchViewModel,
                            onClose = { deleteDialog = false }
                        )
                    }
                }
            }
        }
    } else {
        DietPlanner(
            onClick = { dietSearchViewModel.dietSearching() },
            isVeg = isVeg
        )
    }

}


@Composable
fun VegInfoDialog(
    text: String,
    onClose: () -> Unit,
    modifier: Modifier = Modifier
) {

    AlertDialog(
        onDismissRequest = {
            onClose()
        },
        title = { Text(text = text,
            textAlign = TextAlign.Justify,
            fontSize = 17.sp)},
        modifier = modifier,
        confirmButton = {
            TextButton(onClick = {
                onClose()
            }) {
                Text(text = "Close")
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun DietSearchScreenPreview() {
    HealthEaseTheme {
        DietSearchScreen()
    }
}