package com.example.healthease.ui.theme

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.healthease.data.Sports
import com.example.healthease.data.SportsDatabase

@Composable
fun SportsScreenList(sports: List<Sports> = SportsDatabase.sportsList) {

    LazyColumn {
        items(sports) { item ->
            SportsScreen(
                item,
                modifier = Modifier
                    .padding(
                        start = 16.dp,
                        top = 8.dp,
                        end = 16.dp
                    )
                    .animateContentSize(
                        animationSpec = spring(
                            dampingRatio = Spring.DampingRatioLowBouncy,
                            stiffness = Spring.StiffnessMedium
                        )
                    )
            )


        }
    }
}


@Composable
fun SportsDescription(sportsDescription: String) {
    Box(modifier = Modifier.padding(16.dp)) {
        Text(
            text = sportsDescription
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SportsScreen(sports: Sports, modifier: Modifier) {
    var expanded by remember { mutableStateOf(false) }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape = CutCornerShape(topEnd = 16.dp, bottomStart = 16.dp))
            .then(modifier),
        onClick = { expanded = !expanded }
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)


        ) {
            Text(
                text = sports.sportsName
            )

            Spacer(modifier = Modifier.padding(4.dp))

            if (expanded) {
                SportsDescription(sportsDescription = sports.sportsDescription)
            }
        }
    }

}

@Preview
@Composable
fun PreviewExerciseScreen() {
    HealthEaseTheme() {
        SportsScreenList(sports = SportsDatabase.sportsList)
    }
}