package com.example.healthease.DietData

abstract class FoodData(
    val name1: String,
    val caloriesPer100g1: Int,
    val proteinPer100g1: Float,
    val carbsPer100g1: Float,
    val fatPer100g1: Float,
    val otherNutrients1: String,
    val instructions1: String,
    val otherNames1: MutableList<String> = mutableListOf()
)

/***
 * BREAKFAST
 */
abstract class BreakFast(
    val name0: String,
    val caloriesPer100g0: Int,
    val proteinPer100g0: Float,
    val carbsPer100g0: Float,
    val fatPer100g0: Float,
    val otherNutrients0: String,
    val instructions0: String,
    val otherNames0: MutableList<String> = mutableListOf()
): FoodData(name0, caloriesPer100g0, proteinPer100g0, carbsPer100g0, fatPer100g0, otherNutrients0, instructions0, otherNames0)

data class BreakfastDiet(
    val name: String,
    val caloriesPer100g: Int,
    val proteinPer100g: Float,
    val carbsPer100g: Float,
    val fatPer100g: Float,
    val otherNutrients: String,
    val instructions: String,
    val isVeg: Boolean, // Added boolean parameter
    val otherNames: MutableList<String> = mutableListOf()
): BreakFast(name, caloriesPer100g, proteinPer100g, carbsPer100g, fatPer100g, otherNutrients, instructions, otherNames)

data class BreakfastFood(
    val name: String,
    val caloriesPer100g: Int,
    val proteinPer100g: Float,
    val carbsPer100g: Float,
    val fatPer100g: Float,
    val otherNutrients: String,
    val instructions: String,
    val isVeg: Boolean, // Added boolean parameter
    val otherNames: MutableList<String> = mutableListOf()
): BreakFast(name, caloriesPer100g, proteinPer100g, carbsPer100g, fatPer100g, otherNutrients, instructions, otherNames)

/***
 * LUNCH
 */
abstract class Lunch(
    val name0: String,
    val caloriesPer100g0: Int,
    val proteinPer100g0: Float,
    val carbsPer100g0: Float,
    val fatPer100g0: Float,
    val otherNutrients0: String,
    val instructions0: String,
    val otherNames0: MutableList<String> = mutableListOf()
): FoodData(name0, caloriesPer100g0, proteinPer100g0, carbsPer100g0, fatPer100g0, otherNutrients0, instructions0, otherNames0)

data class LunchDiet(
    val name: String,
    val caloriesPer100g: Int,
    val proteinPer100g: Float,
    val carbsPer100g: Float,
    val fatPer100g: Float,
    val otherNutrients: String,
    val instructions: String,
    val isVeg: Boolean, // Added boolean parameter
    val otherNames: MutableList<String> = mutableListOf()
): Lunch(name, caloriesPer100g, proteinPer100g, carbsPer100g, fatPer100g, otherNutrients, instructions, otherNames)

data class LunchFood(
    val name: String,
    val caloriesPer100g: Int,
    val proteinPer100g: Float,
    val carbsPer100g: Float,
    val fatPer100g: Float,
    val otherNutrients: String,
    val instructions: String,
    val isVeg: Boolean, // Added boolean parameter
    val otherNames: MutableList<String> = mutableListOf()
): Lunch(name, caloriesPer100g, proteinPer100g, carbsPer100g, fatPer100g, otherNutrients, instructions, otherNames)

/***
 * POST_WORKOUT
 */
abstract class PostWorkout(
    val name0: String,
    val caloriesPer100g0: Int,
    val proteinPer100g0: Float,
    val carbsPer100g0: Float,
    val fatPer100g0: Float,
    val otherNutrients0: String,
    val instructions0: String,
    val otherNames0: MutableList<String> = mutableListOf(),
    val timeToEatAfterWorkout0: String
): FoodData(name0, caloriesPer100g0, proteinPer100g0, carbsPer100g0, fatPer100g0, otherNutrients0, instructions0, otherNames0)

data class PostWorkoutDiet(
    val name: String,
    val caloriesPer100g: Int,
    val proteinPer100g: Float,
    val carbsPer100g: Float,
    val fatPer100g: Float,
    val otherNutrients: String,
    val instructions: String,
    val isVeg: Boolean, // Added boolean parameter
    val otherNames: MutableList<String> = mutableListOf(),
    val timeToEatAfterWorkout: String = "20 minutes"
): PostWorkout(name, caloriesPer100g, proteinPer100g, carbsPer100g, fatPer100g, otherNutrients, instructions, otherNames, timeToEatAfterWorkout)

data class PostWorkoutFood(
    val name: String,
    val caloriesPer100g: Int,
    val proteinPer100g: Float,
    val carbsPer100g: Float,
    val fatPer100g: Float,
    val otherNutrients: String,
    val instructions: String,
    val isVeg: Boolean, // Added boolean parameter
    val otherNames: MutableList<String> = mutableListOf(),
    val timeToEatAfterWorkout: String = "20 minutes"
): PostWorkout(name, caloriesPer100g, proteinPer100g, carbsPer100g, fatPer100g, otherNutrients, instructions, otherNames, timeToEatAfterWorkout)

/***
 * DINNER
 */
abstract class Dinner(
    val name0: String,
    val caloriesPer100g0: Int,
    val proteinPer100g0: Float,
    val carbsPer100g0: Float,
    val fatPer100g0: Float,
    val otherNutrients0: String,
    val instructions0: String,
    val otherNames0: MutableList<String> = mutableListOf()
): FoodData(name0, caloriesPer100g0, proteinPer100g0, carbsPer100g0, fatPer100g0, otherNutrients0, instructions0, otherNames0)

data class DinnerDiet(
    val name: String,
    val caloriesPer100g: Int,
    val proteinPer100g: Float,
    val carbsPer100g: Float,
    val fatPer100g: Float,
    val otherNutrients: String,
    val instructions: String,
    val isVeg: Boolean, // Added boolean parameter
    val otherNames: MutableList<String> = mutableListOf()
): Dinner(name, caloriesPer100g, proteinPer100g, carbsPer100g, fatPer100g, otherNutrients, instructions, otherNames)

data class DinnerFood(
    val name: String,
    val caloriesPer100g: Int,
    val proteinPer100g: Float,
    val carbsPer100g: Float,
    val fatPer100g: Float,
    val otherNutrients: String,
    val instructions: String,
    val isVeg: Boolean, // Added boolean parameter
    val otherNames: MutableList<String> = mutableListOf()
): Dinner(name, caloriesPer100g, proteinPer100g, carbsPer100g, fatPer100g, otherNutrients, instructions, otherNames)