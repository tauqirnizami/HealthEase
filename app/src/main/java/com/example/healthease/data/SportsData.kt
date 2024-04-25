package com.example.healthease.data


import androidx.compose.runtime.Immutable

@Immutable
data class Sports(
    val sportsName: String,
    val sportsDescription: String
)

object SportsDatabase {
    val sportsList = listOf(
        Sports(
            sportsName = "Football",
            sportsDescription = "Football, also known as soccer, is a team sport played between two teams of eleven players using a spherical ball. It is the world's most popular sport."
        ),
        Sports(
            sportsName = "Scuba Diving",
            sportsDescription = "Scuba diving is a mode of underwater diving where the diver uses a self-contained underwater breathing apparatus (scuba), which is completely independent of surface supply, to breathe underwater."
        ),
        Sports(
            sportsName = "Martial Arts",
            sportsDescription = "Martial arts are codified systems and traditions of combat practiced for various reasons such as self-defense, physical fitness, mental discipline, and competition."
        ),
        Sports(
            sportsName = "Running",
            sportsDescription = "Running is a method of terrestrial locomotion allowing humans and other animals to move rapidly on foot. It is a popular form of exercise, recreation, and sport."
        ),
        Sports(
            sportsName = "Hiking",
            sportsDescription = "Hiking is an outdoor activity that involves walking or trekking on trails or paths in natural environments, often in mountainous or other scenic terrain."
        ),
        Sports(
            sportsName = "Surfing",
            sportsDescription = "Surfing is a surface water sport in which an individual, referred to as a surfer, rides on the forward or deep face of a moving wave, usually carrying the surfer towards the shore."
        ),
//        Sports(
//            sportsName = "Yoga",
//            sportsDescription = "Yoga is a group of physical, and mental practices that originated in ancient India. It includes various postures (asanas), breathing exercises (pranayama), and meditation techniques."
//        ),
        Sports(
            sportsName = "Golf",
            sportsDescription = "Golf is a club-and-ball sport in which players use various clubs to hit balls into a series of holes on a course in as few strokes as possible."
        ),
        Sports(
            sportsName = "Canoeing",
            sportsDescription = "Canoeing is an activity that involves paddling a canoe, a small narrow boat, with a single-bladed paddle. It is a popular recreational activity, particularly in calm waters."
        ),
        Sports(
            sportsName = "Boxing",
            sportsDescription = "Boxing is a combat sport in which two people, usually wearing protective gloves and other protective equipment, throw punches at each other for a predetermined amount of time in a boxing ring."
        ),
        Sports(
            sportsName = "Bowling",
            sportsDescription = "Bowling is a target sport and recreational activity in which a player rolls a bowling ball toward pins, a ball at the end of a lane, in an effort to knock them down."
        ),
        Sports(
            sportsName = "Badminton",
            sportsDescription = "Badminton is a racket sport played using rackets to hit a shuttlecock across a net. It can be played individually or as doubles."
        ),
        Sports(
            sportsName = "Skiing",
            sportsDescription = "Skiing is a recreational activity and competitive winter sport in which participants use skis to glide on snow."
        ),
        Sports(
            sportsName = "Table Tennis",
            sportsDescription = "Table tennis, also known as ping-pong, is a sport in which two or four players hit a lightweight ball, also known as the ping-pong ball, back and forth across a table using small solid paddles."
        ),
        Sports(
            sportsName = "Rock Climbing",
            sportsDescription = "Rock climbing is a sport in which participants climb up, down, or across natural rock formations or artificial climbing walls. The goal is to reach the summit of a formation or the endpoint of a usually pre-defined route without falling."
        ),
        Sports(
            sportsName = "Horseback Riding",
            sportsDescription = "Horseback riding, also known as equestrianism, is the skill of riding, driving, steeplechasing, or vaulting with horses. This broad description includes the use of horses for practical working purposes, transportation, recreational activities, artistic or cultural activities, and competitive sports."
        ),
        Sports(
            sportsName = "Tennis",
            sportsDescription = "Tennis is a racket sport that can be played individually against a single opponent (singles) or between two teams of two players each (doubles)."
        ),
        Sports(
            sportsName = "Skydiving",
            sportsDescription = "Skydiving, also known as parachuting, is a method of transiting from a high point to Earth, involving the control of speed during the descent using a parachute or parachutes."
        ),
        Sports(
            sportsName = "Rugby",
            sportsDescription = "Rugby is a team sport that originated in England in the 19th century. It is played by two teams with an oval-shaped ball, typically on a grass field. The aim is to carry the ball over the opponent's goal line to score tries."
        ),
        Sports(
            sportsName = "Gymnastics",
            sportsDescription = "Gymnastics is a sport that includes exercises requiring balance, strength, flexibility, agility, coordination, and endurance. The movements involved in gymnastics contribute to the development of the arms, legs, shoulders, back, chest, and abdominal muscle groups."
        ),
        Sports(
            sportsName = "Rafting",
            sportsDescription = "Rafting or white water rafting is a challenging recreational outdoor activity using an inflatable raft to navigate a river or other bodies of water. This is usually done on white water or different degrees of rough water to thrill and excite the raft passengers."
        ),
        Sports(
            sportsName = "Swimming",
            sportsDescription = "Swimming is an individual or team racing sport that requires the use of one's entire body to move through water. It is one of the most popular recreational activities and competitive sports worldwide."
        ),
        Sports(
            sportsName = "Archery",
            sportsDescription = "Archery is the art, sport, practice, or skill of using a bow to shoot arrows. Historically, archery has been used for hunting and combat, while in modern times, it has mainly become a competitive sport and recreational activity."
        ),
        Sports(
            sportsName = "Rock Climbing",
            sportsDescription = "Rock climbing is a sport in which participants climb up, down, or across natural rock formations or artificial climbing walls. The goal is to reach the summit of a formation or the endpoint of a usually pre-defined route without falling."
        ),
        Sports(
            sportsName = "Kick Boxing",
            sportsDescription = "Kickboxing is a group of stand-up combat sports based on kicking and punching, historically developed from karate mixed with boxing. Some of the famous kickboxing styles today include Muay Thai, Taekwondo, etc."
        ),
        Sports(
            sportsName = "Hockey",
            sportsDescription = "Hockey is a sport in which two teams play against each other by trying to maneuver a ball or a puck into the opponent's goal using a hockey stick."
        ),
        Sports(
            sportsName = "Kayaking",
            sportsDescription = "Kayaking is a watersport activity that involves paddling a kayak, a small, narrow watercraft, using a double-bladed paddle. It is distinguished from canoeing by the sitting position of the paddler and the number of blades on the paddle."
        ),
        Sports(
            sportsName = "Wrestling",
            sportsDescription = "Wrestling is a combat sport involving grappling techniques such as clinch fighting, throws, takedowns, joint locks, pins, and other grappling holds. The sport can either be theatrical for entertainment or genuinely competitive."
        ),
        Sports(
            sportsName = "Rock Climbing",
            sportsDescription = "Rock climbing is a sport in which participants climb up, down, or across natural rock formations or artificial climbing walls. The goal is to reach the summit of a formation or the endpoint of a usually pre-defined route without falling."
        ),
        Sports(
            sportsName = "Skydiving",
            sportsDescription = "Skydiving, also known as parachuting, is a method of transiting from a high point to Earth with the aid of gravity, involving the control of speed during the descent using a parachute or parachutes."
        ),
        Sports(
            sportsName = "Rafting",
            sportsDescription = "Rafting or white water rafting is a challenging recreational outdoor activity using an inflatable raft to navigate a river or other bodies of water. This is usually done on white water or different degrees of rough water to thrill and excite the raft passengers."
        )
    )
}