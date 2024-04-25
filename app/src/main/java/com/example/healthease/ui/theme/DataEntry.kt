package com.example.healthease.ui.theme

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.launch

@Composable
fun DataEntryBody(
    modifier: Modifier = Modifier,
    entryViewModel: CalculationsViewModel = viewModel(factory = AppViewModelProvider.Factory),
    type: String,
    content: String,
    text: String
) {
    val coroutineScope = rememberCoroutineScope()
    var enabled by remember {
        mutableStateOf(true)
    }
    Button(
        onClick = {
            coroutineScope.launch {
                entryViewModel.saveData(type = type, content = content)
                enabled = false
            }},
        enabled = enabled,
        modifier = modifier
            .padding(top = 47.dp, start = 47.dp, end = 47.dp, bottom = 23.dp)
            .fillMaxWidth()
    ) {
        Text(text = text)
    }
}