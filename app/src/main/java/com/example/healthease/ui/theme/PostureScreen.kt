package com.example.healthease.ui.theme

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.healthease.R


@Composable
fun PostureScreen(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(11.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Posture",
            fontSize = 41.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(43.dp))

        Text(
            text = "Posture is crucial for daily activities, defining how we hold our bodies against gravity while standing, sitting, or moving. Weak muscles can lead to postural issues like kyphosis or lordosis, impacting efficiency. Understanding correct and incorrect posture is vital, along with exercises to improve it.",
            modifier = modifier.padding(7.dp),
            textAlign = TextAlign.Justify
        )

        Spacer(modifier = Modifier.height(43.dp))

        Text(
            text = "Correct Standing Posture:",
            fontWeight = FontWeight.SemiBold,
            fontSize = 21.sp
        )
        Text(
            text = "Stand with heels together and toes slightly apart, keeping the body straight, knees straight, chin in, chest out, and abdomen in. Distribute weight evenly on both feet, ensuring balance. Maintain alignment from ear to ankle, i.e., the line of centre of gravity must pass through the ear, shoulder, hip, knee and ankle, relieving stress on muscles and ligaments.",
            modifier = modifier.padding(7.dp),
            textAlign = TextAlign.Justify
        )
        Spacer(modifier = Modifier.height(17.dp))
        Image(
            painter = painterResource(id = R.drawable.standingposture),
            contentDescription = "Standing Postures"
        )
        Text(
            text = "Image by freepik",
            fontSize = 11.sp,
            modifier = Modifier.alpha(0.2f)
        )

        Spacer(modifier = Modifier.height(51.dp))

        Text(
            text = "Correct Sitting Posture:",
            fontWeight = FontWeight.SemiBold,
            fontSize = 21.sp
        )
        Text(
            text = "When sitting, ensure hips are far back in the chair with head, spine, shoulders, and hips aligned. Keep feet flat on the ground, thighs horizontal. Position the book on the table at least 30 cm from the eyes to prevent eye strain. For writing, use a slightly inclined table.",
            modifier = modifier.padding(7.dp),
            textAlign = TextAlign.Justify
        )

        Spacer(modifier = Modifier.height(17.dp))

        Image(
            painter = painterResource(id = R.drawable.sittingposture),
            contentDescription = "Sitting Postures"
        )
        Text(
            text = "Image by freepik",
            fontSize = 11.sp,
            modifier = Modifier.alpha(0.2f)
        )

        Spacer(modifier = Modifier.height(63.dp))

        Text(
            text = "Common Postural Deformities:",
            fontWeight = FontWeight.SemiBold,
            fontSize = 21.sp
        )

        Spacer(modifier = Modifier.height(31.dp))

        DisplayPostures(
            heading = "Kyphosis:",
            body = "Kyphosis is a condition characterized by excessive curvature of the" +
                    " upper back, leading to a rounded or hunched posture. Causes include poor" +
                    " posture, osteoporosis, and spinal conditions. Remedies may involve exercises" +
                    " to strengthen back muscles, posture correction, and medical intervention" +
                    " in severe cases.",
            correctiveMeasure = "1. Lie on your back with knees bent and feet flat on the ground. Extend arms sideways, palms up, then raise them overhead. Hold, return arms to sides, and repeat 10 times.\n" +
                    "\n" +
                    "2. Lie face down with hands on hips. Lift head and chest a few inches off the ground, keeping chin tucked. Hold, then lower. Repeat 10 times.\n" +
                    "\n" +
                    "3. Sit upright holding a stick horizontally over the head, hands wide apart. Lower the stick, then raise it behind the head and shoulders while keeping head and trunk straight. Repeat 10-12 times."
        )

        Spacer(modifier = Modifier.height(31.dp))

        DisplayPostures(
            heading = "Lordosis:",
            body = "Lordosis is an excessive inward curvature of the spine, typically in the lower back, causing a swayback appearance. Causes include poor posture, obesity, and certain medical conditions. Remedies may include exercises to strengthen core muscles, postural correction, and medical treatment for underlying conditions.",
            correctiveMeasure = "1. Lie prone with hands under abdomen, press hands on abdomen to lift lower back while keeping hips and shoulders down.\n" +
                    "\n" +
                    "2. Squat by bending knees forward, keeping back straight and knees aligned with feet. Descend until thighs are parallel to floor, then extend knees and hips back to starting position.\n" +
                    "\n" +
                    "3. Lunge forward with one knee on mat, foot placed beyond knee. Place hands on knee and push hips forward to stretch rear leg's hip. Repeat on opposite side.\n" +
                    "\n" +
                    "4. Sit on chair with feet wide apart, shoulders between knees. Reach floor under chair back and hold.\n" +
                    "\n" +
                    "5. Lie prone, palms on floor shoulder-width apart. Push torso up while keeping pelvis down. Hold.\n" +
                    "\n" +
                    "6. Sit with knees extended, feet together, hands at sides. Bend forward, touching toes. Hold, then return to start. Repeat."
        )

        Spacer(modifier = Modifier.height(31.dp))

        DisplayPostures(
            heading = "Scoliosis:",
            body = "Scoliosis is an abnormal sideways curvature of the spine. It can be congenital or develop during growth spurts. Causes include genetics, neuromuscular conditions, and unknown factors. Treatments may involve bracing, physical therapy, or surgery in severe cases.",
            correctiveMeasure = "1. Lie prone with right arm extended overhead and left arm at side. Slide left hip up while pressing down with left hand.\n" +
                    "\n" +
                    "2. Stand upright with feet apart. Lift left heel and hip, extending right arm overhead to the left. Press left hand against left ribs.\n" +
                    "\n" +
                    "3. Stand upright with feet apart. Place left fingertips on left shoulder and bend upper body to the right (or left if there's only a 'C' curve). Repeat for the opposite curve."
        )

        Spacer(modifier = Modifier.height(31.dp))

        DisplayPostures(
            heading = "Flat Foot:",
            body = "Flat foot, or pes planus, is a condition where the arches of the feet collapse, causing the entire sole of the foot to touch the ground. It can be caused by genetics, injury, or weakening of the foot muscles and tendons. Remedies may include orthotic devices, supportive footwear, physical therapy, and in severe cases, surgical intervention.",
            correctiveMeasure = "1. Jumping on toes for some time.\n" +
                    "\n" +
                    "2. Rope skipping.\n" +
                    "\n" +
                    "3. Stand up and down on the heels.\n" +
                    "\n" +
                    "4. Walk on the toes.\n" +
                    "\n" +
                    "5. Sit down properly. Try to grip small wads of paper with your toes. These pieces of paper should be picked up by gripping forcefully using toes."
        )

        Spacer(modifier = Modifier.height(31.dp))

        DisplayPostures(
            heading = "Knock Knees:",
            body = "Knock knees, or genu valgum, is a condition where the knees angle inward, causing the legs to appear bowed. It can result from factors like genetics, injury, or underlying conditions such as rickets or osteoarthritis. Treatment may involve exercises to strengthen muscles around the knees, orthotics, or surgery in severe cases.",
            correctiveMeasure = "1. Horse-riding is one of the best exercises for correction of knock-knees.\n" +
                    "\n" +
                    "2. Keep a pillow between the knees and stand straight for some time. Both the feet should touch each other.\n" +
                    "\n" +
                    "3. Use of walking calipers may be beneficial."
        )

        Spacer(modifier = Modifier.height(31.dp))

        DisplayPostures(
            heading = "Bow Legs:",
            body = "Bow legs refer to a condition where the knees stay wide apart when a person stands with their feet and ankles together. Causes can include genetic factors, rickets, or bone diseases. Treatment may involve exercises, braces, or surgery in severe cases.",
            correctiveMeasure = "1. Stand straight with feet together. Place a cloth strip around both knees, secured by a partner. Squat as low as comfortable, holding briefly. Return to standing and repeat 4 to 6 times.\n" +
                    "\n" +
                    "2. Walk for some distance on the inner edge of the feet.\n" +
                    "\n" +
                    "3. Walk by bending the toes inward."
        )

        Spacer(modifier = Modifier.height(31.dp))

        DisplayPostures(
            heading = "Round Shoulders:",
            body = "Round shoulders refer to a posture where the shoulders curve forward, causing the upper back to hunch. Causes include poor posture, excessive sitting, and muscle weakness. Remedies typically involve exercises to strengthen back muscles, stretching to improve flexibility, and practicing proper posture habits.",
            correctiveMeasure = "1. Keep your tips of fingers on your shoulders and rotate your elbows in clockwise and anticlockwise directions for some time.\n" +
                    "\n" +
                    "2. Hold the horizontal bar regularly for some time.\n" +
                    "\n" +
                    "3. Doorway chest stretch: Lean into a doorway with arms on each side. Hold for 15-30 seconds, repeat 2-3 times.\n" +
                    "\n" +
                    "4. Wall angels: Stand against a wall, arms up. Slide arms down, then back up, keeping back and elbows on the wall. Repeat 10-15 times.\n" +
                    "\n" +
                    "5. Cat-Cow pose: Start on hands and knees. Inhale, arch back and look up. Exhale, round back and tuck chin. Repeat 10-15 times."
        )

        Spacer(modifier = Modifier.height(31.dp))
    }
}

@Composable
fun DisplayPostures(
    heading: String,
    body: String,
    correctiveMeasure: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(11.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        var expanded by remember {
            mutableStateOf(false)
        }

        Text(
            text = heading,
            fontSize = 21.sp
        )
        Text(
            text = body,
            modifier = modifier.padding(7.dp),
            textAlign = TextAlign.Justify
        )

        Spacer(modifier = Modifier.height(17.dp))

        Card(modifier) {
            Column(
                modifier
                    .animateContentSize(
                        animationSpec = spring(
                            dampingRatio = Spring.DampingRatioLowBouncy,
                            stiffness = Spring.StiffnessMedium
                        )
                    )
            ) {
                Row(
                    modifier = modifier.padding(11.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        text = "Corrective Measures",
                        Modifier.weight(1f)
                    )
                    IconButton(onClick = { expanded = !expanded }) {
                        Icon(
                            imageVector = if (expanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                            contentDescription = "See/Hide corrective Measures"
                        )

                    }
                }
                if (expanded)
                    Text(
                        text = correctiveMeasure,
                        modifier = modifier.padding(11.dp)
                    )
            }
        }
    }

}


@Preview(showBackground = true)
@Composable
fun PostureScreenPreview() {
    HealthEaseTheme {
        PostureScreen()
    }
}