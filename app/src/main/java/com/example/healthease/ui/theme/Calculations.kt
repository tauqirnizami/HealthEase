package com.example.healthease.ui.theme

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlin.math.ceil

@Composable
fun CalculationsScreen(
    modifier: Modifier = Modifier,
    calculationsViewModel: CalculationsViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val calculationsUiState by calculationsViewModel.uiState.collectAsState()

    val height = calculationsViewModel.height//in meters
    val age = calculationsViewModel.age
    val gender = calculationsViewModel.gender

    val goalWeight = calculationsViewModel.goalWeight

    val bmi = calculationsUiState.bmi
    val minWeight = calculationsViewModel.minWeight(bmi)
    val maxWeight = calculationsViewModel.maxWeight(bmi)

    Column(
        modifier = modifier
            .statusBarsPadding()
            .safeDrawingPadding()
            .verticalScroll(rememberScrollState())
            .padding(top = 15.dp, bottom = 15.dp, start = 21.dp, end = 21.dp)
    ) {

        Card(modifier = modifier) {
            Column(
                modifier = modifier
                    .padding(top = 11.dp, bottom = 11.dp, start = 13.dp, end = 13.dp)
            ) {


                Text(text = "Current BMI Calculation:")

                Spacer(modifier = Modifier.height(10.dp))

                EditField(label = "Weight",
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next
                    ), value = calculationsViewModel.weight,
                    onValueChanged = { calculationsViewModel.weightSet(it) })

                EditField(label = "Height",
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Done
                    ), value = calculationsViewModel.height,
                    onValueChanged = { calculationsViewModel.heightSet(it) })

                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    modifier = modifier
                        .padding(start = 27.dp, end = 11.dp)
                ) {
                    Text(
                        text = "BMI =",
                        modifier = modifier
                    )
                    OutlinedCard(
                        modifier = modifier
                            .padding(top = 1.dp, bottom = 1.dp, start = 11.dp, end = 11.dp),
                        shape = RoundedCornerShape(31)
                    ) {
                        Text(
                            text = "${calculationsUiState.bmi}",
                            modifier = modifier
                                .padding(top = 3.dp, bottom = 3.dp, start = 9.dp, end = 9.dp)
                        )
                    }
                }


                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "Different BMI Ranges:\n" +
                            "Underweight: BMI less than 18.5\n" +
                            "Normal Weight: BMI between 18.5 and 24.9\n" +
                            "Overweight: BMI between 25 and 29.9\n" +
                            "Obese: BMI of 30 or higher",
                    modifier = modifier
                        .padding(start = 21.dp, end = 15.dp)
                )

                Spacer(modifier = Modifier.height(31.dp))

                Column(
                    modifier = modifier
                        .padding(start = 11.dp, end = 11.dp)
                ) {
                    Text(
                        text = "Normal Healthy Weight Range for your Height:",
                        modifier = modifier
                    )
                    OutlinedCard(
                        modifier = modifier
                            .padding(top = 1.dp, bottom = 1.dp, start = 11.dp, end = 7.dp),
                        shape = RoundedCornerShape(31)
                    ) {
                        Text(
                            text = "${ceil(minWeight * 100) / 100} kg. to ${ceil(maxWeight * 100) / 100} kg.",
                            modifier = modifier
                                .padding(top = 3.dp, bottom = 3.dp, start = 9.dp, end = 9.dp)
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(51.dp))

        Card(modifier = modifier) {
            Column(
                modifier = modifier
                    .padding(top = 11.dp, bottom = 11.dp, start = 13.dp, end = 13.dp)
            ) {
                val actLvlList: List<String> = listOf(
                    "Little or no exercise",
                    "Light exercise",
                    "Moderate exercise",
                    "Very active",
                    "Extremely active"
                )
                Text(text = "Calories Counter:")
                CaloriesCounter(
                    actLvlList = actLvlList,
                    weightVal = calculationsViewModel.weight,
                    heightVal = calculationsViewModel.height,
                    ageVal = calculationsViewModel.age,
                    onWeightChange = { calculationsViewModel.weightSet(it) },
                    onHeightChange = { calculationsViewModel.heightSet(it) },
                    onAgeChange = { calculationsViewModel.ageSet(it) },
                    onGenderSelectionChange = { calculationsViewModel.setGender1(it) },
                    onActLvlSelectionChange = { calculationsViewModel.setActLvl1(it) },
//                goalWeight = calculationsViewModel.goalWeight
                )

                Spacer(modifier = Modifier.height(10.dp))

//        val calories = calcounter(weight, height, age, gender, actLvlFloat)
                Column(
                    modifier = modifier
                        .padding(start = 27.dp, end = 11.dp)
                ) {
                    Text(
                        text = "Minimum Calories Needed:",
                        modifier = modifier
                    )
                    OutlinedCard(
                        modifier = modifier
                            .padding(top = 1.dp, bottom = 1.dp, start = 11.dp, end = 7.dp),
                        shape = RoundedCornerShape(31)
                    ) {
                        Text(
                            text = "${ceil(calculationsUiState.currentCaloryNeed * 100) / 100} Kcal",
                            modifier = modifier
                                .padding(top = 3.dp, bottom = 3.dp, start = 9.dp, end = 9.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(51.dp))

                Text(text = "Goal:")
                EditField(label = "Weight",
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Done
                    ),
                    value = calculationsViewModel.goalWeight,
                    onValueChanged = { calculationsViewModel.goalWeightSet(it) }
                )

                SelectOptionScreen(options = actLvlList,
                    onSelectionChanged = { calculationsViewModel.goalActLvlSet(it) })

                Spacer(modifier = Modifier.height(10.dp))

//        val goalCalories = calcounter(goalWeight, height, age, gender, actLvlFloat)
                Column(
                    modifier = modifier
                        .padding(start = 27.dp, end = 11.dp)
                ) {
                    Text(
                        text = "Minimum calories needed for a ${if (gender == "") "___" else gender} person " +
                                "of weight ${if (goalWeight == "") "___" else goalWeight} kg." +
                                "and height ${if (height == "") "___" else height} meters " +
                                "at the age of ${if (age == "") "___" else age} years " +
                                "for the chosen activity level is:",
                        modifier = modifier
                    )
                    OutlinedCard(
                        modifier = modifier
                            .padding(top = 1.dp, bottom = 1.dp, start = 11.dp, end = 7.dp),
                        shape = RoundedCornerShape(31)
                    ) {
                        Text(
                            text = "${ceil(calculationsUiState.goalCaloryNeed * 100) / 100} Kcal",
                            modifier = modifier
                                .padding(top = 3.dp, bottom = 3.dp, start = 9.dp, end = 9.dp)
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(51.dp))

        Card(modifier = modifier) {
            Column(
                modifier = modifier
                    .padding(top = 11.dp, bottom = 11.dp, start = 13.dp, end = 13.dp)
            ) {
//        val ageFloat = age.toFloatOrNull() ?: 0.0f
//                val waterNeeded = calculationsViewModel.waterNeeded(age, calories)
                Text(text = "Fluid (water) required per day for a person who spends ${calculationsUiState.currentCaloryNeed} Kcal per day would be:")
                OutlinedCard(
                    modifier = modifier
                        .padding(top = 1.dp, bottom = 1.dp, start = 11.dp, end = 11.dp),
                    shape = RoundedCornerShape(31)
                ) {
                    Text(
                        text = "${ceil(calculationsUiState.currentFluidNeed * 10) / 10} liters",
                        modifier = modifier
                            .padding(top = 3.dp, bottom = 3.dp, start = 5.dp, end = 5.dp)
                    )
                }

                Spacer(modifier = Modifier.height(31.dp))
//                val goalWaterNeeded = calculationsViewModel.waterNeeded(age, goalCalories)
                Text(text = "Fluid (water) that would be required at age ${calculationsViewModel.age} if you spend ${calculationsUiState.goalCaloryNeed} Kcal per day:")
                OutlinedCard(
                    modifier = modifier
                        .padding(top = 1.dp, bottom = 1.dp, start = 11.dp, end = 11.dp),
                    shape = RoundedCornerShape(31)
                ) {
                    Text(
                        text = "${ceil(calculationsUiState.goalFluidNeed * 10) / 10} liters",
                        modifier = modifier
                            .padding(top = 3.dp, bottom = 3.dp, start = 5.dp, end = 5.dp)
                    )
                }
            }
        }
    }
}


@Composable
fun EditField(
    label: String,
//    @DrawableRes leadingIcon: Int,
    keyboardOptions: KeyboardOptions,
    value: String,
    onValueChanged: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = value,
        singleLine = true,
//        leadingIcon = { Icon(painter = painterResource(id = leadingIcon), null) },
        modifier = modifier
            .padding(start = 31.dp, end = 21.dp),
        onValueChange = onValueChanged,
        label = { Text(text = label) },
        keyboardOptions = keyboardOptions
    )
}

@Composable
fun SelectOptionScreen(
    options: List<String>,
    modifier: Modifier = Modifier,
    onSelectionChanged: (String) -> Unit = {}
) {
    var selectedValue by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = modifier
            .padding(start = 51.dp, end = 21.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(modifier = Modifier/*.padding(0.dp)*/) {
            options.forEach { item ->
                Row(
                    modifier = Modifier.selectable(
                        selected = selectedValue == item,
                        onClick = {
                            selectedValue = item
                            onSelectionChanged(item)
                        }
                    ),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = selectedValue == item,
                        onClick = {
                            selectedValue = item
                            onSelectionChanged(item)
                        }
                    )
                    Text(item)
                }
            }
            Divider(
                thickness = 10.dp,
                modifier = Modifier.padding(bottom = 10.dp)
            )
        }

    }

}

@Composable
fun CaloriesCounter(
    actLvlList: List<String>,
    weightVal: String,
    heightVal: String,
    ageVal: String,
    onWeightChange: (String) -> Unit,
    onHeightChange: (String) -> Unit,
    onAgeChange: (String) -> Unit,
    onGenderSelectionChange: (String) -> Unit,
    onActLvlSelectionChange: (String) -> Unit,
) {
    Column(
        modifier = Modifier
    ) {
        var infoDialog by remember {
            mutableStateOf(false)
        }
        Spacer(modifier = Modifier.height(15.dp))

        EditField(
            label = "Weight",
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            value = weightVal,
            onValueChanged = onWeightChange
        )
        EditField(
            label = "height",
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            value = heightVal,
            onValueChanged = onHeightChange
        )

        EditField(
            label = "Age", keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ), value = ageVal,
            onValueChanged = onAgeChange
        )

        Spacer(modifier = Modifier.height(25.dp))

        val genderList: List<String> = listOf("Male", "Female")
        Text(
            text = "Gender:",
            modifier = Modifier
                .padding(start = 25.dp)
        )
        SelectOptionScreen(
            options = genderList,
            onSelectionChanged = onGenderSelectionChange,
        )

        Spacer(modifier = Modifier.height(31.dp))

        Row(modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically) {

            Text(
                text = "Activity Level:",
                modifier = Modifier
                    .padding(start = 31.dp)
            )
            IconButton(onClick = { infoDialog = !infoDialog }) {
                Icon(
                    imageVector = Icons.Default.Info,
                    contentDescription = "Reminder About Your Exercise Routine"
                )
            }
        }
        if (infoDialog) {
            VegInfoDialog(
                text = "Keep in mind the Exercise You'll Be Doing From Now Onwards",
                onClose = { infoDialog = !infoDialog })
        }
        SelectOptionScreen(
            options = actLvlList,
            onSelectionChanged = onActLvlSelectionChange,
        )
    }
}


@Preview(showBackground = true)
@Composable
fun CalculationsScreenPreview() {
    HealthEaseTheme {
        CalculationsScreen()
    }
}