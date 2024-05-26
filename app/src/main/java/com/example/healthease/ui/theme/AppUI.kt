package com.example.healthease.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.healthease.R

@Composable
fun ThreeButtonScreen(
    button1: String,
    onClick1: () -> Unit,

    button2: String,
    onClick2: () -> Unit,

    button3: String,
    onClick3: () -> Unit,

    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        Image(
            painter = painterResource(R.drawable.new_project__1__webp),
            contentDescription = null,
            modifier
                .fillMaxSize()
                .align(Alignment.TopCenter)
        )
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(45.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {
            Button(
                onClick = { onClick1() },
                modifier = modifier.fillMaxWidth(),
                shape = CutCornerShape(
                    bottomStart = 13.dp,
                    topEnd = 13.dp,
                    topStart = 5.dp,
                    bottomEnd = 5.dp
                ),
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 10.dp,
                    pressedElevation = 5.dp,
                )
            ) {
                Text(
                    text = button1,
                    fontSize = 25.sp,
                    lineHeight = 27.sp,
                    fontFamily = FontFamily(Font(R.font.facon)),
                    textAlign = TextAlign.Center
                )
            }
            Spacer(modifier = Modifier.height(91.dp))

            Button(
                onClick = { onClick2() },
                modifier = modifier.fillMaxWidth(),
                shape = CutCornerShape(
                    bottomStart = 13.dp,
                    topEnd = 13.dp,
                    topStart = 5.dp,
                    bottomEnd = 5.dp
                ),
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 10.dp,
                    pressedElevation = 5.dp,
                )
            ) {
                Text(
                    text = button2,
                    fontSize = 25.sp,
                    lineHeight = 27.sp,
                    fontFamily = FontFamily(Font(R.font.facon)),
                    textAlign = TextAlign.Center
                )
            }
            Spacer(modifier = Modifier.height(91.dp))

            Button(
                onClick = { onClick3() },
                modifier = modifier.fillMaxWidth(),
                shape = CutCornerShape(
                    bottomStart = 13.dp,
                    topEnd = 13.dp,
                    topStart = 5.dp,
                    bottomEnd = 5.dp
                ),
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 10.dp,
                    pressedElevation = 5.dp,
                )
            ) {
                Text(
                    text = button3,
                    fontSize = 25.sp,
                    lineHeight = 27.sp,
                    fontFamily = FontFamily(Font(R.font.facon)),
                    textAlign = TextAlign.Center
                )
            }
            Spacer(modifier = Modifier.height(41.dp))
        }
    }
}

@Composable
fun StartScreen(
    modifier: Modifier = Modifier,
    onClick1: () -> Unit,
    onClick2: () -> Unit,
    onClick: () -> Unit
) {
    ThreeButtonScreen(
        onClick1 = onClick1,
        onClick2 = onClick2,
        onClick3 = onClick,
        modifier = modifier,
        button1 = "Diet & Calculations",
        button2 = "Workout",
        button3 = "Others"
    )
}

@Composable
fun DietButtonScreen(
    modifier: Modifier = Modifier,
    onClick1: () -> Unit,
    onClick2: () -> Unit,
    onClick: () -> Unit
) {
    ThreeButtonScreen(
        onClick1 = onClick1,
        onClick2 = onClick2,
        onClick3 = onClick,
        modifier = modifier,
        button1 = "Calculations",
        button2 = "Diet Plan",
        button3 = "Explore Food items"
    )
}

@Composable
fun WorkoutButtonScreen(
    modifier: Modifier = Modifier,
    onClick1: () -> Unit,
    onClick2: () -> Unit,
    onClick: () -> Unit
) {
    ThreeButtonScreen(
        onClick1 = onClick1, onClick2 = onClick2, onClick3 = onClick, modifier = modifier,
        button1 = "Workout Planner",
        button2 = "Explore Exercises",
        button3 = "Posture Correction"
    )
}

@Composable
fun OthersButtonScreen(
    modifier: Modifier = Modifier,
    onClick1: () -> Unit,
    onClick2: () -> Unit,
    onClick: () -> Unit
) {
    ThreeButtonScreen(
        onClick1 = onClick1, onClick2 = onClick2, onClick3 = onClick, modifier = modifier,
        button1 = "Sports & Activities",
        button2 = "Streak Counter",
        button3 = "Motivational Quotes"
    )
}
/*@Composable
fun StreakInstructionsScreen(
    onClick1: () -> Unit,
    onNavBack: () -> Unit,
    modifier: Modifier = Modifier
){
    Box(modifier = modifier) {
        Image(
            painter = painterResource(R.drawable.new_project__1__webp),
            contentDescription = null,
            modifier
                .fillMaxSize()
                .align(Alignment.TopCenter)
        )
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(45.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {
            Button(
                onClick = { onNavBack() },
                modifier = modifier.fillMaxWidth(),
                shape = CutCornerShape(
                    bottomStart = 13.dp,
                    topEnd = 13.dp,
                    topStart = 5.dp,
                    bottomEnd = 5.dp
                ),
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 10.dp,
                    pressedElevation = 5.dp,
                )
            ) {
                Text(
                    text = "Back",
                    fontSize = 25.sp,
                    lineHeight = 27.sp,
                    fontFamily = FontFamily(Font(R.font.facon)),
                    textAlign = TextAlign.Center
                )
            }
            Spacer(modifier = Modifier.height(71.dp))

            Text(
                text = "Instructions",
                fontSize = 41.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(31.dp))

            Text(
                text = "1) Select yes only when you've completed proper workout of the day as well as followed" +
                        " a proper healthy diet throughout the day! Although you may chose yes if you've taken" +
                        " a routined rest day break from workout.\n",
                modifier = modifier.padding(7.dp),
                textAlign = TextAlign.Justify,
                fontSize = 21.sp
            )

//            Spacer(modifier = Modifier.height(91.dp))

            Text(
                text = "2) Select no if either you didn't do proper workout or" +
                        " if you didn't follow a proper diet plan today.\n",
                modifier = modifier.padding(7.dp),
                textAlign = TextAlign.Justify,
                fontSize = 21.sp
            )

//            Spacer(modifier = Modifier.height(91.dp))

            Text(
                text = "3) You may change the option you chose as long as you're in the app's \"others\" section." +
                        " Once you leave this section, the streak would be get saved accordingly" +
                        " and you won't be able to change it later.",
                modifier = modifier.padding(7.dp),
                textAlign = TextAlign.Justify,
                fontSize = 21.sp
            )

            Spacer(modifier = Modifier.height(71.dp))

            Button(
                onClick = { onClick1() },
                modifier = modifier.fillMaxWidth(),
                shape = CutCornerShape(
                    bottomStart = 13.dp,
                    topEnd = 13.dp,
                    topStart = 5.dp,
                    bottomEnd = 5.dp
                ),
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 10.dp,
                    pressedElevation = 5.dp,
                )
            ) {
                Text(
                    text = "Got It!",
                    fontSize = 25.sp,
                    lineHeight = 27.sp,
                    fontFamily = FontFamily(Font(R.font.facon)),
                    textAlign = TextAlign.Center
                )
            }
            Spacer(modifier = Modifier.height(41.dp))
        }
    }
}*/


@Composable
fun WorkoutPlannerButtonScreen(
    modifier: Modifier = Modifier,
    onClick1: () -> Unit,
    onClick2: () -> Unit,
    onClick: () -> Unit,
//    workoutPlannerViewModel: CalculationsViewModel = viewModel(factory = AppViewModelProvider.Factory),

    ) {
    ThreeButtonScreen(
        onClick1 = {
            onClick1()},
        onClick2 = onClick2,
        onClick3 = {
            onClick()}, modifier = modifier,
        button1 = "On Vacation Routine",
        button2 = "Medium Level",
        button3 = "Advanced"
    )
}

@Composable
fun MediumLevelButtonScreen(
    modifier: Modifier = Modifier,
    onClick1: () -> Unit,
    onClick2: () -> Unit,
    onClick: () -> Unit,
//    MediumLevelSelectionViewModel: CalculationsViewModel = viewModel(factory = AppViewModelProvider.Factory),

    ) {
    ThreeButtonScreen(
        onClick1 = {
            onClick1()},
        onClick2 = {
            onClick2()},
        onClick3 = {
            onClick()}, modifier = modifier,
        button1 = "Intermediate I",
        button2 = "Intermediate II",
        button3 = "Intermediate III"
    )
}

@Composable
fun BeginningScreen(
    modifier: Modifier = Modifier,
    onClick1: () -> Unit
) {
    Column(
        modifier = modifier
            .padding(51.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(31.dp))

        Image(
            painter = painterResource(id = R.drawable.logo01webp), contentDescription = "Logo",
            modifier = modifier.size(300.dp)
        )

        Spacer(modifier = Modifier.height(11.dp))

        Text(
            text = "Healthy habits Made Easy",
            fontSize = 21.sp,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center
        )
        Text(
            text = "HealthEase",
            fontSize = 21.sp,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Center,
            fontFamily = FontFamily(Font(R.font.facon)),
        )

        Spacer(modifier = Modifier.height(83.dp))

        Button(
            onClick = { onClick1() },
            modifier = modifier.fillMaxWidth(),
            shape = CutCornerShape(
                bottomStart = 13.dp,
                topEnd = 13.dp,
                topStart = 5.dp,
                bottomEnd = 5.dp
            ),
            elevation = ButtonDefaults.buttonElevation(
                defaultElevation = 10.dp,
                pressedElevation = 5.dp,
            )
        ) {
            Text(
                text = "Start",
                fontSize = 35.sp,
                lineHeight = 27.sp,
                fontFamily = FontFamily(Font(R.font.facon)),
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BeginningScreenPreview() {
    HealthEaseTheme {
        BeginningScreen(onClick1 = {})
    }
}


@Preview(showBackground = true)
@Composable
fun StartScreenPreview() {
    HealthEaseTheme {
        StartScreen(onClick1 = {},
            onClick2 = {},
            onClick = {})
    }
}

@Preview(showBackground = true)
@Composable
fun DietButtonScreenPreview() {
    HealthEaseTheme {
        DietButtonScreen(onClick1 = {},
            onClick2 = {},
            onClick = {})
    }
}

@Preview(showBackground = true)
@Composable
fun WorkoutButtonScreenPreview() {
    HealthEaseTheme {
        WorkoutButtonScreen(onClick1 = {},
            onClick2 = {},
            onClick = {})
    }
}

@Preview(showBackground = true)
@Composable
fun OthersScreenPreview() {
    HealthEaseTheme {
        OthersButtonScreen(onClick1 = {},
            onClick2 = {},
            onClick = {})
    }
}


@Preview(showBackground = true)
@Composable
fun WorkoutPlannerScreenPreview() {
    HealthEaseTheme {
        WorkoutPlannerButtonScreen(onClick1 = {},
            onClick2 = {},
            onClick = {})
    }
}

@Preview(showBackground = true)
@Composable
fun MediumLevelScreenPreview() {
    HealthEaseTheme {
        MediumLevelButtonScreen(onClick1 = {},
            onClick2 = {},
            onClick = {})
    }
}