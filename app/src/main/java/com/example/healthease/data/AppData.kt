package com.example.healthease.data

abstract class Exercises(
    val name1: String,
    val muscle1: String,
    val muscleSubGroup1: String,
    val minorMuscles1: String? = null,
    val instructions1: String
)

/*
CHEST
CHEST
CHEST
 */
abstract class ChestExercises(
    val name0: String,
    val muscle0: String = "Chest",
    val muscleSubGroup0: String,
    val minorMuscles0: String?,
    val instructions0: String,
) : Exercises(name0, muscle0, muscleSubGroup0, minorMuscles0, instructions1 = instructions0)

data class UpperChest( //20-30%
    val name: String,
    val muscle: String = "Chest",
    val muscleSubGroup: String = "UpperChest",
    val minorMuscles: String? = null,
    val instructions: String = "",
//    val IsHomeExercise: Boolean
) : ChestExercises(name, muscle, muscleSubGroup, minorMuscles, instructions) //DONE

data class MiddleChest( //40-50%
    val name: String,
    val muscle: String = "Chest",
    val muscleSubGroup: String = "UpperChest",
    val minorMuscles: String? = null,
    val instructions: String = "",
//    val IsHomeExercise: Boolean
) : ChestExercises(name, muscle, muscleSubGroup, minorMuscles, instructions) //DONE

data class LowerChest( //20-30%
    val name: String,
    val muscle: String = "Chest",
    val muscleSubGroup: String = "UpperChest",
    val minorMuscles: String? = null,
    val instructions: String = "",
//    val IsHomeExercise: Boolean
) : ChestExercises(name, muscle, muscleSubGroup, minorMuscles, instructions) //DONE

/*
TRICEPS
TRICEPS
TRICEPS
 */
abstract class TricepsExercises(
    val name0: String,
    val muscle0: String = "Triceps",
    val muscleSubGroup0: String,
    val minorMuscles0: String?,
    val instructions0: String,
) : Exercises(name0, muscle0, muscleSubGroup0, minorMuscles0, instructions1 = instructions0)

data class LongTriceps( //40-50%
    val name: String,
    val muscle: String = "Triceps",
    val muscleSubGroup: String = "Long Head",
    val minorMuscles: String? = null,
    val instructions: String = "",
//    val IsHomeExercise: Boolean
) : TricepsExercises(name, muscle, muscleSubGroup, minorMuscles, instructions) //DONE

data class LatTriceps(  //30-40%
    val name: String,
    val muscle: String = "Triceps",
    val muscleSubGroup: String = "Lateral Head",
    val minorMuscles: String? = null,
    val instructions: String = "",
//    val IsHomeExercise: Boolean
) : TricepsExercises(name, muscle, muscleSubGroup, minorMuscles, instructions) //DONE

data class MedialTriceps( //20-30%
    val name: String,
    val muscle: String = "Triceps",
    val muscleSubGroup: String = "Medial Head",
    val minorMuscles: String? = null,
    val instructions: String = "",
//    val IsHomeExercise: Boolean
) : TricepsExercises(name, muscle, muscleSubGroup, minorMuscles, instructions) //DONE

/*
BICEPS
BICEPS
BICEPS
 */
abstract class BicepsExercises(
    val name0: String,
    val muscle0: String = "Biceps",
    val muscleSubGroup0: String,
    val minorMuscles0: String?,
    val instructions0: String,
) : Exercises(name0, muscle0, muscleSubGroup0, minorMuscles0, instructions1 = instructions0)

data class LongBiceps( //40-50%
    val name: String,
    val muscle: String = "Biceps",
    val muscleSubGroup: String = "Long Head",
    val minorMuscles: String? = null,
    val instructions: String = "",
//    val IsHomeExercise: Boolean
) : BicepsExercises(name, muscle, muscleSubGroup, minorMuscles, instructions) //DONE

data class ShortBiceps(  //30-40%
    val name: String,
    val muscle: String = "Biceps",
    val muscleSubGroup: String = "Short Head",
    val minorMuscles: String? = null,
    val instructions: String = "",
//    val IsHomeExercise: Boolean
) : BicepsExercises(name, muscle, muscleSubGroup, minorMuscles, instructions) //DONE

/*
BACK
BACK
BACK
 */
// Abstract superclass representing exercises for the back muscle group
abstract class BackExercises(
    val name0: String,
    val muscle0: String = "Back",
    val muscleSubGroup0: String,
    val minorMuscles0: String?,
    val instructions0: String,
) : Exercises(name0, muscle0, muscleSubGroup0, minorMuscles0, instructions1 = instructions0)

// Concrete subclass representing exercises for the lats (latissimus dorsi) sub-group
data class LatsBack( //40-50%
    val name: String,
    val muscleSubGroup: String = "Lats (Latissimus Dorsi)",
    val minorMuscles: String? = null,
    val instructions: String = "",
//    val IsHomeExercise: Boolean
) : BackExercises(name, muscleSubGroup0 = muscleSubGroup, minorMuscles0 = minorMuscles, instructions0 = instructions) //DONE

// Concrete subclass representing exercises for the traps (trapezius) sub-group
data class TrapsBack( //20-30%
    val name: String,
    val muscleSubGroup: String = "Traps (Trapezius)",
    val minorMuscles: String? = null,
    val instructions: String = "",
//    val IsHomeExercise: Boolean
) : BackExercises(name, muscleSubGroup0 = muscleSubGroup, minorMuscles0 = minorMuscles, instructions0 = instructions) //DONE

// Concrete subclass representing exercises for the rhomboids sub-group
data class RhomboidsBack( //10-15%
    val name: String,
    val muscleSubGroup: String = "Rhomboids",
    val minorMuscles: String? = null,
    val instructions: String = "",
//    val IsHomeExercise: Boolean
) : BackExercises(name, muscleSubGroup0 = muscleSubGroup, minorMuscles0 = minorMuscles, instructions0 = instructions) //DONE

// Concrete subclass representing exercises for the erector spinae (lower back) sub-group
data class ErectorBack(// 10-15%
    val name: String,
    val muscleSubGroup: String = "Erector Spinae (Lower Back)",
    val minorMuscles: String? = null,
    val instructions: String = "",
//    val IsHomeExercise: Boolean
) : BackExercises(name, muscleSubGroup0 = muscleSubGroup, minorMuscles0 = minorMuscles, instructions0 = instructions) //DONE

data class RotatorBack(// 0-5-10%
    val name: String,
    val muscleSubGroup: String = "Rotator Cuff",
    val minorMuscles: String? = null,
    val instructions: String = "",
//    val IsHomeExercise: Boolean
) : BackExercises(name, muscleSubGroup0 = muscleSubGroup, minorMuscles0 = minorMuscles, instructions0 = instructions) //DONE

/*
LEGS
LEGS
LEGS
 */
// Abstract superclass representing exercises for the leg muscle group
abstract class LegExercises(
    val name0: String,
    val muscle0: String = "Legs",
    val muscleSubGroup0: String,
    val minorMuscles0: String?,
    val instructions0: String,
) : Exercises(name0, muscle0, muscleSubGroup0, minorMuscles0, instructions1 = instructions0)

// Concrete subclass representing exercises for the quadriceps sub-group
data class QuadLegs( //30-40%
    val name: String,
    val muscleSubGroup: String = "Quadriceps",
    val minorMuscles: String? = null,
    val instructions: String = "",
//    val IsHomeExercise: Boolean
) : LegExercises(name, muscleSubGroup0 = muscleSubGroup, minorMuscles0 = minorMuscles, instructions0 = instructions) //DONE

// Concrete subclass representing exercises for the hamstrings sub-group
data class HamstringLegs( //25-35%
    val name: String,
    val muscleSubGroup: String = "Hamstrings",
    val minorMuscles: String? = null,
    val instructions: String = "",
//    val IsHomeExercise: Boolean
) : LegExercises(name, muscleSubGroup0 = muscleSubGroup, minorMuscles0 = minorMuscles, instructions0 = instructions) //DONE

// Concrete subclass representing exercises for the calves sub-group
data class CalfLegs( //15-20%
    val name: String,
    val muscleSubGroup: String = "Calves",
    val minorMuscles: String? = null,
    val instructions: String = "",
//    val IsHomeExercise: Boolean
) : LegExercises(name, muscleSubGroup0 = muscleSubGroup, minorMuscles0 = minorMuscles, instructions0 = instructions) //DONE

data class GlutesLegs( //25٪
    val name: String,
    val muscleSubGroup: String = "Glutes",
    val minorMuscles: String? = null,
    val instructions: String = "",
//    val IsHomeExercise: Boolean
) : LegExercises(name, muscleSubGroup0 = muscleSubGroup, minorMuscles0 = minorMuscles, instructions0 = instructions)

// Concrete subclass representing exercises for the adductor and abductor muscles sub-group
data class AdductorAbductorLegs( //10-15% //5-10٪
    val name: String,
    val muscleSubGroup: String = "Adductor/Abductor Muscles",
    val minorMuscles: String? = null,
    val instructions: String = "",
//    val IsHomeExercise: Boolean
) : LegExercises(name, muscleSubGroup0 = muscleSubGroup, minorMuscles0 = minorMuscles, instructions0 = instructions) //DONE

/*
SHOULDERS
SHOULDER
SHOULDER
 */
// Abstract superclass representing exercises for the shoulder muscle group
abstract class ShoulderExercises(
    val name0: String,
    val muscle0: String = "Shoulders",
    val muscleSubGroup0: String,
    val minorMuscles0: String?,
    val instructions0: String,
) : Exercises(name0, muscle0, muscleSubGroup0, minorMuscles0, instructions1 = instructions0)

// Concrete subclass representing exercises for the anterior deltoids sub-group
data class AntDeltsShoulder( //30-40%
    val name: String,
    val muscleSubGroup: String = "Anterior Deltoids",
    val minorMuscles: String? = null,
    val instructions: String = "",
//    val IsHomeExercise: Boolean
) : ShoulderExercises(name, muscleSubGroup0 = muscleSubGroup, minorMuscles0 = minorMuscles, instructions0 = instructions) //DONE

// Concrete subclass representing exercises for the middle deltoids sub-group
data class MedDeltsShoulder( //25-35%
    val name: String,
    val muscleSubGroup: String = "Medial Deltoids",
    val minorMuscles: String? = null,
    val instructions: String = "",
//    val IsHomeExercise: Boolean
) : ShoulderExercises(name, muscleSubGroup0 = muscleSubGroup, minorMuscles0 = minorMuscles, instructions0 = instructions) //DONE

// Concrete subclass representing exercises for the posterior deltoids sub-group
data class PostDeltsShoulder( //20-30%
    val name: String,
    val muscleSubGroup: String = "Posterior Deltoids",
    val minorMuscles: String? = null,
    val instructions: String = "",
//    val IsHomeExercise: Boolean
) : ShoulderExercises(name, muscleSubGroup0 = muscleSubGroup, minorMuscles0 = minorMuscles, instructions0 = instructions) //DONE

/*
FOREARMS
FOREARMS
FOREARMS
 */
// Abstract superclass representing exercises for the forearm muscle group
abstract class ForearmsExercises(
    val name0: String,
    val muscle0: String = "Forearms",
    val muscleSubGroup0: String,
    val minorMuscles0: String?,
    val instructions0: String,
) : Exercises(name0, muscle0, muscleSubGroup0, minorMuscles0, instructions1 = instructions0)

// Concrete subclass representing exercises for the flexors sub-group of the forearms
data class FlexorsForearms( //50%
    val name: String,
    val muscleSubGroup: String = "Flexors",
    val minorMuscles: String? = null,
    val instructions: String = "",
//    val IsHomeExercise: Boolean
) : ForearmsExercises(name, muscleSubGroup0 = muscleSubGroup, minorMuscles0 = minorMuscles, instructions0 = instructions) //DONE

// Concrete subclass representing exercises for the extensors sub-group of the forearms
data class ExtensorsForearms( //50%
    val name: String,
    val muscleSubGroup: String = "Extensors",
    val minorMuscles: String? = null,
    val instructions: String = "",
//    val IsHomeExercise: Boolean
) : ForearmsExercises(name, muscleSubGroup0 = muscleSubGroup, minorMuscles0 = minorMuscles, instructions0 = instructions)

/*
ABS
ABS
ABS
 */
// Abstract superclass representing exercises for the abdominal muscle group
abstract class AbsExercises(
    val name0: String,
    val muscle0: String = "Abdominals",
    val muscleSubGroup0: String,
    val minorMuscles0: String?,
    val instructions0: String,
) : Exercises(name0, muscle0, muscleSubGroup0, minorMuscles0, instructions1 = instructions0)

// Concrete subclass representing exercises for the rectus abdominis sub-group of the abs
data class RectusAbdominisAbs( //30-40%
    val name: String,
    val muscleSubGroup: String = "Rectus Abdominis",
    val minorMuscles: String? = null,
    val instructions: String = "",
//    val IsHomeExercise: Boolean
) : AbsExercises(name, muscleSubGroup0 = muscleSubGroup, minorMuscles0 = minorMuscles, instructions0 = instructions) //DONE

// Concrete subclass representing exercises for the obliques sub-group of the abs
data class ObliquesAbs( //20-30%
    val name: String,
    val muscleSubGroup: String = "Obliques",
    val minorMuscles: String? = null,
    val instructions: String = "",
//    val IsHomeExercise: Boolean
) : AbsExercises(name, muscleSubGroup0 = muscleSubGroup, minorMuscles0 = minorMuscles, instructions0 = instructions) //DONE

// Concrete subclass representing exercises for the transverse abdominis sub-group of the abs
data class TransverseAbs( //30-40%
    val name: String,
    val muscleSubGroup: String = "Transverse Abdominis",
    val minorMuscles: String? = null,
    val instructions: String = "",
//    val IsHomeExercise: Boolean
) : AbsExercises(name, muscleSubGroup0 = muscleSubGroup, minorMuscles0 = minorMuscles, instructions0 = instructions) //DONE