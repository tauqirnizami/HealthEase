package com.example.healthease.ui.theme

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FormatQuote
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.healthease.othersData.motivationDataList

@Composable
fun MotivationScreen(
    motivationViewModel: CalculationsViewModel = viewModel(factory = AppViewModelProvider.Factory),
    modifier: Modifier = Modifier
) {
    val shuffledIndexes: List<Int> = remember { listOf(0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16).shuffled() }
    val motivationUiState by motivationViewModel.uiState.collectAsState()

    val shuffledListIndex = motivationUiState.currentIndex

    val index: Int = shuffledIndexes[shuffledListIndex]

    val currentObject = motivationDataList[index]
    Column(
        modifier = modifier
            .padding(25.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

//        Spacer(modifier = Modifier.height(57.dp))

        Card(modifier = modifier) {

            Row(
                modifier = modifier,
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = currentObject.quote,
                    Modifier
                        .weight(4f)
                        .padding(11.dp)
                )
                Icon(
                    imageVector = Icons.Default.FormatQuote,
                    contentDescription = "Motivational Quote",
                    Modifier
                        .weight(1f)
                        .size(50.dp)
                )
            }
        }
        Spacer(modifier = Modifier.height(41.dp))

        Text(
            text = currentObject.shortStory,
            modifier = modifier
                .padding(17.dp),
            textAlign = TextAlign.Justify
        )

        Spacer(modifier = Modifier.height(61.dp))

//        val newIndex =
        Button(onClick = {motivationViewModel.currentIndexing()}) {
            Text(text = "Get a New Quote")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MotivationScreenPreview() {
    HealthEaseTheme {
        MotivationScreen()
    }
}