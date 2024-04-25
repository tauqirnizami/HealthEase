package com.example.healthease.ui.theme

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.healthease.DietData.BreakfastDiet
import com.example.healthease.DietData.FoodData
import com.example.healthease.DietData.PostWorkout
import com.example.healthease.DietData.breakfastDiet
import com.example.healthease.DietData.dinnerDiet
import com.example.healthease.DietData.lunchDiet
import com.example.healthease.DietData.notDietFood
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll


@Composable
fun SearchFoodItem(
    modifier: Modifier = Modifier,
    dietSearchViewModel: SearchViewModel = viewModel()
) {
    var search by rememberSaveable { mutableStateOf("") }

    var isSearching by remember { mutableStateOf(false) }
    var searchMatches by remember { mutableStateOf<MutableList<FoodData>>(mutableListOf()) }


    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .padding(31.dp)
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
        Spacer(modifier = Modifier.height(9.dp))

        Button(onClick = { isSearching = true }) {
            Text(text = "Search")
        }

        Spacer(modifier = Modifier.height(11.dp))

        if (isSearching && search != "") {
            LaunchedEffect(search) {
                searchMatches = dietSearchViewModel.searchItem(search)
            }
            searchMatches.forEach() { foodData ->
                var expanded by remember {
                    mutableStateOf(false)
                }
                Card(
                    modifier = modifier
                        .padding(5.dp)
                        .clickable(onClick = { expanded = !expanded })
                        .fillMaxWidth(1f),
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
                    DisplayItemScreen(item = foodData, onClick = { expanded = !expanded })
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun SearchFoodItemPreview() {
    HealthEaseTheme {
        SearchFoodItem()
    }
}