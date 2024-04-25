package com.example.healthease.ui.theme

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.healthease.DietData.FoodData
import com.example.healthease.data.Exercises

@Composable
fun ExerciseSearchScreen(
    modifier: Modifier = Modifier,
    exerciseSearchViewModel: ExerciseSearchViewModel = viewModel()
) {
    var search by rememberSaveable { mutableStateOf("") }

    var isSearching by remember { mutableStateOf(false) }
    var searchMatches by remember { mutableStateOf<MutableList<Exercises>>(mutableListOf()) }


    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .padding(11.dp)
        ,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = search,
            onValueChange = { search = it
                isSearching = false},
            label = { Text(text = "name") },
        )

        Button(onClick = { isSearching = true }) {
            Text(text = "Search")
        }

        if (isSearching && search != "") {
            LaunchedEffect(search) {
                searchMatches = exerciseSearchViewModel.searchItem(search)
            }
            searchMatches.forEach() { foodData ->
                var expanded by remember {
                    mutableStateOf(false)
                }
                Card(
                    modifier = modifier
                        .padding(5.dp)
                        .clickable(onClick = { expanded = !expanded }),
                    shape = CutCornerShape(topEnd = 7.dp, bottomStart = 7.dp, topStart = 2.dp, bottomEnd = 2.dp)
                ) {
                    Text(
                        text = foodData.name1,
                        modifier = modifier
                            .padding(5.dp),
                        textAlign = TextAlign.Center
                    )
                }
                if (expanded) {
                    DisplayExerciseItemScreen(item = foodData, onClick = { expanded = !expanded })
                }
            }
        }
    }
}

@Preview
@Composable
fun ExerciseSearchScreenPreview(){
    HealthEaseTheme {
        ExerciseSearchScreen()
    }
}