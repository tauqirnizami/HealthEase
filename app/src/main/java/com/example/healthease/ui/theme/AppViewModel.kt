package com.example.healthease.ui.theme

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.healthease.DietData.BreakfastDiet
import com.example.healthease.DietData.DinnerDiet
import com.example.healthease.DietData.FoodData
import com.example.healthease.DietData.LunchDiet
import com.example.healthease.DietData.PostWorkoutDiet
import com.example.healthease.DietData.breakfastDiet
import com.example.healthease.DietData.dinnerDiet
import com.example.healthease.DietData.lunchDiet
import com.example.healthease.DietData.notDietFood
import com.example.healthease.DietData.postWorkoutDiet
import com.example.healthease.data.AbsExercises
import com.example.healthease.data.AdductorAbductorLegs
import com.example.healthease.data.AntDeltsShoulder
import com.example.healthease.data.AppData
import com.example.healthease.data.AppDataRepository
import com.example.healthease.data.BackExercises
import com.example.healthease.data.BicepsExercises
import com.example.healthease.data.CalfLegs
import com.example.healthease.data.ChestExercises
import com.example.healthease.data.ErectorBack
import com.example.healthease.data.Exercises
import com.example.healthease.data.ExtensorsForearms
import com.example.healthease.data.FlexorsForearms
import com.example.healthease.data.ForearmsExercises
import com.example.healthease.data.GlutesLegs
import com.example.healthease.data.HamstringLegs
import com.example.healthease.data.LatTriceps
import com.example.healthease.data.LatsBack
import com.example.healthease.data.LegExercises
import com.example.healthease.data.LongBiceps
import com.example.healthease.data.LongTriceps
import com.example.healthease.data.LowerChest
import com.example.healthease.data.MedDeltsShoulder
import com.example.healthease.data.MedialTriceps
import com.example.healthease.data.MiddleChest
import com.example.healthease.data.ObliquesAbs
import com.example.healthease.data.PostDeltsShoulder
import com.example.healthease.data.QuadLegs
import com.example.healthease.data.RectusAbdominisAbs
import com.example.healthease.data.RhomboidsBack
import com.example.healthease.data.RotatorBack
import com.example.healthease.data.ShortBiceps
import com.example.healthease.data.ShoulderExercises
import com.example.healthease.data.TransverseAbs
import com.example.healthease.data.TrapsBack
import com.example.healthease.data.TricepsExercises
import com.example.healthease.data.UpperChest
import com.example.healthease.data.gymAbsExercises
import com.example.healthease.data.gymBackExercises
import com.example.healthease.data.gymBicepsExercises
import com.example.healthease.data.gymChestExercises
import com.example.healthease.data.gymForearmsExercises
import com.example.healthease.data.gymLegsExercises
import com.example.healthease.data.gymShoulderExercises
import com.example.healthease.data.gymTricepsExercises
import com.example.healthease.data.homeAbsExercises
import com.example.healthease.data.homeBackExercises
import com.example.healthease.data.homeBicepsExercises
import com.example.healthease.data.homeChestExercises
import com.example.healthease.data.homeForearmExercises
import com.example.healthease.data.homeLegExercises
import com.example.healthease.data.homeShoulderExercises
import com.example.healthease.data.homeTricepsExercises
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.math.ceil

class CalculationsViewModel(
    val appDataRepository: AppDataRepository
) : ViewModel() {

    // Game UI state

    private val _uiState = MutableStateFlow(CalculationsUiState())
    val uiState: StateFlow<CalculationsUiState> = _uiState.asStateFlow()

    var weight: String by mutableStateOf("")
    var height: String by mutableStateOf("")
    var age: String by mutableStateOf("")
    var gender: String by mutableStateOf("")
    private var activityLvl: String by mutableStateOf("")
    var exerciseLevel = 1

    var goalWeight: String by mutableStateOf("")
    private var goalActLvl: String by mutableStateOf("")

    fun levelSet(newLevel: Int) {
        exerciseLevel = newLevel
        _uiState.update { currentState ->
            currentState.copy(level = exerciseLevel)
        }
    }

    fun weightSet(newWeight: String) {
        weight = newWeight
        bmiCalc(weight, height)
        _uiState.update { currentState ->
            currentState.copy(currentWeight = weight.toFloatOrNull() ?: 0.0f)
        }
        calCounter(weight = weight, height = height, age = age, gender = gender, activityLvl)
    }

    fun heightSet(newHeight: String) {
        height = newHeight
        bmiCalc(weight, height)
        calCounter(weight = weight, height = height, age = age, gender = gender, activityLvl)
    }

    fun ageSet(newAge: String) {
        age = newAge
        calCounter(weight = weight, height = height, age = age, gender = gender, activityLvl)
    }

    fun goalWeightSet(newGoalWeight: String) {
        goalWeight = newGoalWeight
        _uiState.update { currentState ->
            currentState.copy(goalWeight = goalWeight.toFloatOrNull() ?: 0.0f)
        }
        calCounter(
            weight = goalWeight,
            height = height,
            age = age,
            gender = gender,
            goalActLvl,
            false
        )
    }

    fun goalActLvlSet(newGoalActLvl: String) {
        goalActLvl = newGoalActLvl
        calCounter(
            weight = goalWeight,
            height = height,
            age = age,
            gender = gender,
            goalActLvl,
            false
        )
    }

    fun setGender1(desiredGender: String) {
        gender = desiredGender
        calCounter(weight = weight, height = height, age = age, gender = gender, activityLvl)
    }

    fun setActLvl1(desiredActLvl: String) {
        activityLvl = desiredActLvl
        calCounter(weight = weight, height = height, age = age, gender = gender, activityLvl)
    }


    private fun bmiCalc(weight: String, height: String) {
        val bmi: Float =
            if ((height != "") && (height != "0")) {
                (weight.toFloatOrNull() ?: 0f) / ((height.toFloatOrNull()
                    ?: 1f) * (height.toFloatOrNull()
                    ?: 1f))
            } else 0.0f
        _uiState.update { currentState ->
            currentState.copy(bmi = bmi)
        }
    }

    private fun calCounter(
        weight: String,
        height: String,
        age: String,
        gender: String,
        actLvl: String = "Little or no exercise",
        current: Boolean = true
    ) {
        val actLvlFloat: Float =
            when (actLvl) {
                "Little or no exercise" -> 1.2f
                "Light exercise" -> 1.375f
                "Moderate exercise" -> 1.55f
                "Very active" -> 1.725f
                "Extremely active" -> 1.9f
                else -> 1f
            }

        val calories =
            if (weight == "" || weight == "0" || height == "" || height == "0" || age == "" || age == "0" || gender == "")
                0.0f
            else if (gender == "Male") {
                (10f * (weight.toFloatOrNull() ?: 0f)) + (6.25f * (height.toFloatOrNull()
                    ?: 0f) * 100f) - (5f * (age.toFloatOrNull() ?: 0f)) + 5f
            } else {
                (10f * (weight.toFloatOrNull() ?: 0f)) + (6.25f * (height.toFloatOrNull()
                    ?: 0f) * 100f) - (5f * (age.toFloatOrNull() ?: 0f)) - 161f
            }

        waterNeeded(age, calories * actLvlFloat)

        if (current) {
            _uiState.update { currentState ->
                currentState.copy(currentCaloryNeed = calories * actLvlFloat)
            }
            _uiState.update { currentState ->
                currentState.copy(currentFluidNeed = waterNeeded(age, calories * actLvlFloat))
            }

        } else {
            _uiState.update { currentState ->
                currentState.copy(goalCaloryNeed = calories * actLvlFloat)
            }
            _uiState.update { currentState ->
                currentState.copy(goalFluidNeed = waterNeeded(age, calories * actLvlFloat))
            }
        }
    }

    private fun waterNeeded(
        age: String,
        calories: Float
    ): Float {
        val ageFloat = age.toFloatOrNull() ?: 0.0f

        return if (ageFloat <= 2) 1.5f * calories / 1000f
        else if ((2f < ageFloat) && (ageFloat <= 15f)) 1.2f * calories / 1000f
        else if ((15f < ageFloat) && (ageFloat < 60f)) 1f * calories / 1000f
        else if (ageFloat >= 160) 1.1f * calories / 1000f
        else 0.0f
    }

    fun minWeight(bmi: Float = 0.0f): Float {
        return if (bmi != 0.0f) 18.5f * ((height.toFloatOrNull() ?: 0.0f) * (height.toFloatOrNull()
            ?: 0.0f)) else 0.0f
    }

    fun maxWeight(bmi: Float = 0.0f): Float {
        return if (bmi != 0.0f) 24.9f * ((height.toFloatOrNull() ?: 0.0f) * (height.toFloatOrNull()
            ?: 0.0f)) else 0.0f
    }


    /***
     * WORKOUT PLANNER
     * WORKOUT PLANNER
     * WORKOUT PLANNER
     */


    fun maxSetsCounter(muscleGrp: Int, intensityLvl: Int): MutableMap<String, Int> {
        val setsMap = mutableMapOf<String, Int>()
        when (muscleGrp) {
            1 -> { //ABS
                when (intensityLvl) {
                    0 -> {
                        setsMap.put("maxLowerAbsCount", 0)
                        setsMap.put("maxMiddleAbsCount", 0)
                        setsMap.put("maxTransverseCount", 0)
                        setsMap.put("maxObliquesCount", 0)
                        setsMap.put("maxUpperAbsCount", 0)
                    }

                    1 -> {
                        setsMap.put("maxLowerAbsCount", 0)
                        setsMap.put("maxMiddleAbsCount", 0)
                        setsMap.put("maxTransverseCount", 0)
                        setsMap.put("maxObliquesCount", 0)
                        setsMap.put("maxUpperAbsCount", 0)
                    }

                    2 -> {
                        setsMap.put("maxLowerAbsCount", 2)
                        setsMap.put("maxMiddleAbsCount", 3)
                        setsMap.put("maxTransverseCount", 5)
                        setsMap.put("maxObliquesCount", 4)
                        setsMap.put("maxUpperAbsCount", 2)
                    }

                    3 -> {
                        setsMap.put("maxLowerAbsCount", 3)
                        setsMap.put("maxMiddleAbsCount", 4)
                        setsMap.put("maxTransverseCount", 6)
                        setsMap.put("maxObliquesCount", 4)
                        setsMap.put("maxUpperAbsCount", 3)
                    }

                    else -> {
                        setsMap.put("maxLowerAbsCount", 4)
                        setsMap.put("maxMiddleAbsCount", 5)
                        setsMap.put("maxTransverseCount", 7)
                        setsMap.put("maxObliquesCount", 5)
                        setsMap.put("maxUpperAbsCount", 4)
                    }
                }
            }


            2 -> { //BACK
                when (intensityLvl) {
                    0 -> {
                        setsMap.put("maxLatsCount", 6)
                        setsMap.put("maxErectorCount", 1)
                        setsMap.put("maxRhomboidsCount", 1)
                        setsMap.put("maxTrapsCount", 0)
                        setsMap.put("maxRotatorCount", 0)
                    }

                    1 -> {
                        setsMap.put("maxLatsCount", 8)
                        setsMap.put("maxErectorCount", 1)
                        setsMap.put("maxRhomboidsCount", 1)
                        setsMap.put("maxTrapsCount", 0)
                        setsMap.put("maxRotatorCount", 0)
                    }

                    2 -> {
                        setsMap.put("maxLatsCount", 12)
                        setsMap.put("maxErectorCount", 1)
                        setsMap.put("maxRhomboidsCount", 1)
                        setsMap.put("maxTrapsCount", 12)
                        setsMap.put("maxRotatorCount", 0)
                    }

                    3 -> {
                        setsMap.put("maxLatsCount", 12)
                        setsMap.put("maxErectorCount", 4)
                        setsMap.put("maxRhomboidsCount", 4)
                        setsMap.put("maxTrapsCount", 20)
                        setsMap.put("maxRotatorCount", 2)
                    }

                    else -> {
                        setsMap.put("maxLatsCount", 15)
                        setsMap.put("maxErectorCount", 4)
                        setsMap.put("maxRhomboidsCount", 4)
                        setsMap.put("maxTrapsCount", 26)
                        setsMap.put("maxRotatorCount", 2)
                    }
                }
            }


            3 -> { //BICEPS
                when (intensityLvl) {
                    0 -> {
                        setsMap.put("maxLongCount", 3)
                        setsMap.put("maxShortCount", 2)
                    }

                    1 -> {
                        setsMap.put("maxLongCount", 5)
                        setsMap.put("maxShortCount", 3)
                    }

                    2 -> {
                        setsMap.put("maxLongCount", 8)
                        setsMap.put("maxShortCount", 6)
                    }

                    3 -> {
                        setsMap.put("maxLongCount", 11)
                        setsMap.put("maxShortCount", 9)
                    }

                    else -> {
                        setsMap.put("maxLongCount", 14)
                        setsMap.put("maxShortCount", 12)
                    }
                }
            }


            4 -> { //TRICEPS
                when (intensityLvl) {
                    0 -> {
                        setsMap.put("maxLongCount", 2)
                        setsMap.put("maxLatCount", 1)
                        setsMap.put("maxMedialCount", 1)
                    }

                    1 -> {
                        setsMap.put("maxLongCount", 3)
                        setsMap.put("maxLatCount", 2)
                        setsMap.put("maxMedialCount", 1)
                    }

                    2 -> {
                        setsMap.put("maxLongCount", 5)
                        setsMap.put("maxLatCount", 3)
                        setsMap.put("maxMedialCount", 2)
                    }

                    3 -> {
                        setsMap.put("maxLongCount", 6)
                        setsMap.put("maxLatCount", 4)
                        setsMap.put("maxMedialCount", 4)
                    }

                    else -> {
                        setsMap.put("maxLongCount", 7)
                        setsMap.put("maxLatCount", 6)
                        setsMap.put("maxMedialCount", 5)
                    }
                }
            }

            5 -> { //LEGS
                when (intensityLvl) {
                    0 -> {
                        setsMap.put("maxQuadCount", 6)
                        setsMap.put("maxAbductorCount", 0)
                        setsMap.put("maxAdductorsCount", 0)
                        setsMap.put("maxHamstringCount", 4)
                        setsMap.put("maxCalfCount", 1)
                        setsMap.put("maxGlutesCount", 0)
                    }

                    1 -> {
                        setsMap.put("maxQuadCount", 8)
                        setsMap.put("maxAbductorCount", 0)
                        setsMap.put("maxAdductorsCount", 0)
                        setsMap.put("maxHamstringCount", 4)
                        setsMap.put("maxCalfCount", 2)
                        setsMap.put("maxGlutesCount", 2)
                    }

                    2 -> {
                        setsMap.put("maxQuadCount", 12)
                        setsMap.put("maxAbductorCount", 1)
                        setsMap.put("maxAdductorsCount", 1)
                        setsMap.put("maxHamstringCount", 8)
                        setsMap.put("maxCalfCount", 6)
                        setsMap.put("maxGlutesCount", 4)
                    }

                    3 -> {
                        setsMap.put("maxQuadCount", 18)
                        setsMap.put("maxAbductorCount", 1)
                        setsMap.put("maxAdductorsCount", 1)
                        setsMap.put("maxHamstringCount", 16)
                        setsMap.put("maxCalfCount", 10)
                        setsMap.put("maxGlutesCount", 10)
                    }

                    else -> {
                        setsMap.put("maxQuadCount", 20)
                        setsMap.put("maxAbductorCount", 2)
                        setsMap.put("maxAdductorsCount", 2)
                        setsMap.put("maxHamstringCount", 16)
                        setsMap.put("maxCalfCount", 16)
                        setsMap.put("maxGlutesCount", 16)
                    }
                }
            }


            6 -> { //SHOULDER
                when (intensityLvl) {
                    0 -> {
                        setsMap.put("maxAntDeltsCount", 0)
                        setsMap.put("maxMedDeltsCount", 0)
                        setsMap.put("maxPostDeltsCount", 0)
                    }

                    1 -> {
                        setsMap.put("maxAntDeltsCount", 0)
                        setsMap.put("maxMedDeltsCount", 4)
                        setsMap.put("maxPostDeltsCount", 4)
                    }

                    2 -> {
                        setsMap.put("maxAntDeltsCount", 6)
                        setsMap.put("maxMedDeltsCount", 8)
                        setsMap.put("maxPostDeltsCount", 8)
                    }

                    3 -> {
                        setsMap.put("maxAntDeltsCount", 8)
                        setsMap.put("maxMedDeltsCount", 11)
                        setsMap.put("maxPostDeltsCount", 11)
                    }

                    else -> {
                        setsMap.put("maxAntDeltsCount", 12)
                        setsMap.put("maxMedDeltsCount", 13)
                        setsMap.put("maxPostDeltsCount", 13)
                    }
                }
            }


            7 -> { //FOREARMS
                when (intensityLvl) {
                    0 -> {
                        setsMap.put("maxFlexorsCount", 0)
                        setsMap.put("maxExtensorsCount", 0)
                    }

                    1 -> {
                        setsMap.put("maxFlexorsCount", 0)
                        setsMap.put("maxExtensorsCount", 0)
                    }

                    2 -> {
                        setsMap.put("maxFlexorsCount", 3)
                        setsMap.put("maxExtensorsCount", 3)
                    }

                    3 -> {
                        setsMap.put("maxFlexorsCount", 5)
                        setsMap.put("maxExtensorsCount", 5)
                    }

                    else -> {
                        setsMap.put("maxFlexorsCount", 10)
                        setsMap.put("maxExtensorsCount", 10)
                    }
                }
            }


            else -> { //CHEST
                when (intensityLvl) {
                    0 -> {
                        setsMap.put("maxUpperChestCount", 2)
                        setsMap.put("maxMiddleChestCount", 4)
                        setsMap.put("maxLowerChestCount", 2)
                    }

                    1 -> {
                        setsMap.put("maxUpperChestCount", 3)
                        setsMap.put("maxMiddleChestCount", 4)
                        setsMap.put("maxLowerChestCount", 3)
                    }

                    2 -> {
                        setsMap.put("maxUpperChestCount", 4)
                        setsMap.put("maxMiddleChestCount", 4)
                        setsMap.put("maxLowerChestCount", 4)
                    }

                    3 -> {
                        setsMap.put("maxUpperChestCount", 4)
                        setsMap.put("maxMiddleChestCount", 8)
                        setsMap.put("maxLowerChestCount", 6)
                    }

                    else -> {
                        setsMap.put("maxUpperChestCount", 7)
                        setsMap.put("maxMiddleChestCount", 8)
                        setsMap.put("maxLowerChestCount", 7)
                    }

                }
            }
        }

        return setsMap
    }


    suspend fun selectAbsExercises(
        homeOrGym: List<AbsExercises>,

        setsMap: MutableMap<String, Int>
    ): MutableList<AbsExercises> {
        val selectedExercises = mutableListOf<AbsExercises>()

        val shuffledList = homeOrGym.shuffled()

        val maxLowerAbsCount: Int =
            (ceil(setsMap.getValue("maxLowerAbsCount").toFloat() / 3f)).toInt()
        val maxMiddleAbsCount: Int =
            (ceil(setsMap.getValue("maxMiddleAbsCount").toFloat() / 3f)).toInt()
        val maxTransverseCount: Int =
            (ceil(setsMap.getValue("maxTransverseCount").toFloat() / 3f)).toInt()
        val maxObliquesCount: Int =
            (ceil(setsMap.getValue("maxObliquesCount").toFloat() / 3f)).toInt()
        val maxUpperAbsCount: Int =
            (ceil(setsMap.getValue("maxUpperAbsCount").toFloat() / 3f)).toInt()

        var lowerAbsCount = 0
        var middleAbsCount = 0
        var upperAbsCount = 0
        var obliquesCount = 0
        var transverseCount = 0

        for (exercise in shuffledList) {
            when (exercise) {
                is RectusAbdominisAbs -> {
                    if ((exercise.muscleSubGroup == "Upper Abs") && (upperAbsCount < maxUpperAbsCount)) {
                        selectedExercises.add(exercise)
                        upperAbsCount++
                    } else if ((exercise.muscleSubGroup == "Lower Abs") && (lowerAbsCount < maxLowerAbsCount)) {
                        selectedExercises.add(exercise)
                        lowerAbsCount++
                    } else if ((exercise.muscleSubGroup == "Middle Abs") && (middleAbsCount < maxMiddleAbsCount)) {
                        selectedExercises.add(exercise)
                        middleAbsCount++
                    }
                }

                is TransverseAbs -> {
                    if (transverseCount < maxTransverseCount) {
                        selectedExercises.add(exercise)
                        transverseCount++
                    }
                }

                is ObliquesAbs -> {
                    if (obliquesCount < maxObliquesCount) {
                        selectedExercises.add(exercise)
                        obliquesCount++
                    }
                }
            }

        }

        val temporaryUpperAbs = mutableListOf<RectusAbdominisAbs>()
        val temporaryLowerAbs = mutableListOf<RectusAbdominisAbs>()
        val temporaryMiddleAbs = mutableListOf<RectusAbdominisAbs>()
        val temporaryTransverse = mutableListOf<TransverseAbs>()
        val temporaryObliques = mutableListOf<ObliquesAbs>()
        for (item in selectedExercises) {
            when (item) {
                is RectusAbdominisAbs -> {
                    when (item.name) {
                        "Upper Abs" -> temporaryUpperAbs.add(item)
                        "Lower Abs" -> temporaryLowerAbs.add(item)
                        else -> temporaryMiddleAbs.add(item)
                    }
                }

                is TransverseAbs -> temporaryTransverse.add(item)
                is ObliquesAbs -> temporaryObliques.add(item)
            }
        }

        val absExercises = mutableListOf<AbsExercises>()
        absExercises.addAll(temporaryLowerAbs)
        absExercises.addAll(temporaryMiddleAbs)
        absExercises.addAll(temporaryTransverse)
        absExercises.addAll(temporaryObliques)
        absExercises.addAll(temporaryUpperAbs)

        return absExercises
    }


    suspend fun selectBackExercises(
        homeOrGym: List<BackExercises>,

        setsMap: MutableMap<String, Int>
    ): MutableList<BackExercises> {
        val selectedExercises = mutableListOf<BackExercises>()

        val shuffledList = homeOrGym.shuffled()

        val maxLatsCount: Int = (ceil(setsMap.getValue("maxLatsCount").toFloat() / 3f)).toInt()
        val maxRhomboidsCount: Int =
            (ceil(setsMap.getValue("maxRhomboidsCount").toFloat() / 3f)).toInt()
        val maxErectorCount: Int =
            (ceil(setsMap.getValue("maxErectorCount").toFloat() / 3f)).toInt()
        val maxTrapsCount: Int = (ceil(setsMap.getValue("maxTrapsCount").toFloat() / 3f)).toInt()
        val maxRotatorCount: Int =
            (ceil(setsMap.getValue("maxRotatorCount").toFloat() / 3f)).toInt()

        var latsCount = 0
        var rhomboidsCount = 0
        var erectorCount = 0
        var trapsCount = 0
        var rotatorCount = 0

        for (exercise in shuffledList) {
            when (exercise) {
                is LatsBack -> {
                    if (latsCount < maxLatsCount) {
                        selectedExercises.add(exercise)
                        latsCount++
                    }
                }

                is RhomboidsBack -> {
                    if (rhomboidsCount < maxRhomboidsCount) {
                        selectedExercises.add(exercise)
                        rhomboidsCount++
                    }
                }

                is ErectorBack -> {
                    if (erectorCount < maxErectorCount) {
                        selectedExercises.add(exercise)
                        erectorCount++
                    }
                }

                is TrapsBack -> {
                    if (trapsCount < maxTrapsCount) {
                        selectedExercises.add(exercise)
                        trapsCount++
                    }
                }

                is RotatorBack -> {
                    if (rotatorCount < maxRotatorCount) {
                        selectedExercises.add(exercise)
                        rotatorCount++
                    }
                }

                else -> {
                    // Ignore
                }
            }
        }

        val temporaryLats = mutableListOf<LatsBack>()
        val temporaryRhomboids = mutableListOf<RhomboidsBack>()
        val temporaryErector = mutableListOf<ErectorBack>()
        val temporaryTraps = mutableListOf<TrapsBack>()
        val temporaryRotator = mutableListOf<RotatorBack>()
        for (item in selectedExercises) {
            when (item) {
                is LatsBack -> temporaryLats.add(item)
                is RhomboidsBack -> temporaryRhomboids.add(item)
                is ErectorBack -> temporaryErector.add(item)
                is TrapsBack -> temporaryTraps.add(item)
                is RotatorBack -> temporaryRotator.add(item)
            }
        }

        val backExercises = mutableListOf<BackExercises>()
        backExercises.addAll(temporaryLats)
        backExercises.addAll(temporaryRhomboids)
        backExercises.addAll(temporaryTraps)
        backExercises.addAll(temporaryErector)
        backExercises.addAll(temporaryRotator)

        return backExercises
    }


    suspend fun selectBicepsExercises(
        homeOrGym: List<BicepsExercises>,

        setsMap: MutableMap<String, Int>
    ): MutableList<BicepsExercises> {
        val selectedExercises = mutableListOf<BicepsExercises>()

        val shuffledList = homeOrGym.shuffled()

        val maxLongCount: Int = (ceil(setsMap.getValue("maxLongCount").toFloat() / 3f)).toInt()
        val maxShortCount: Int = (ceil(setsMap.getValue("maxShortCount").toFloat() / 3f)).toInt()

        var longCount = 0
        var shortCount = 0

        for (exercise in shuffledList) {
            when (exercise) {
                is LongBiceps -> {
                    if (longCount < maxLongCount) {
                        selectedExercises.add(exercise)
                        longCount++
                    }
                }

                is ShortBiceps -> {
                    if (shortCount < maxShortCount) {
                        selectedExercises.add(exercise)
                        shortCount++
                    }
                }

                else -> {
                    // Ignore
                }
            }
        }

        val temporaryLong = mutableListOf<LongBiceps>()
        val temporaryShort = mutableListOf<ShortBiceps>()
        for (item in selectedExercises) {
            when (item) {
                is LongBiceps -> temporaryLong.add(item)
                is ShortBiceps -> temporaryShort.add(item)
            }
        }

        val bicepsExercises = mutableListOf<BicepsExercises>()
        bicepsExercises.addAll(temporaryLong)
        bicepsExercises.addAll(temporaryShort)

        return bicepsExercises
    }


    suspend fun selectTricepsExercises(
        homeOrGym: List<TricepsExercises>,

        setsMap: MutableMap<String, Int>
    ): MutableList<TricepsExercises> {
        val selectedExercises = mutableListOf<TricepsExercises>()

        val shuffledList = homeOrGym.shuffled()

        val maxLongCount: Int = (ceil(setsMap.getValue("maxLongCount").toFloat() / 3f)).toInt()
        val maxLatCount: Int = (ceil(setsMap.getValue("maxLatCount").toFloat() / 3f)).toInt()
        val maxMedialCount: Int = (ceil(setsMap.getValue("maxMedialCount").toFloat() / 3f)).toInt()

        var longCount = 0
        var latCount = 0
        var medialCount = 0

        for (exercise in shuffledList) {
            when (exercise) {
                is LongTriceps -> {
                    if (longCount < maxLongCount) {
                        selectedExercises.add(exercise)
                        longCount++
                    }
                }

                is LatTriceps -> {
                    if (latCount < maxLatCount) {
                        selectedExercises.add(exercise)
                        latCount++
                    }
                }

                is MedialTriceps -> {
                    if (medialCount < maxMedialCount) {
                        selectedExercises.add(exercise)
                        medialCount++
                    }
                }

                else -> {
                    // Ignore
                }
            }
        }

        val temporaryLong = mutableListOf<LongTriceps>()
        val temporaryLat = mutableListOf<LatTriceps>()
        val temporaryMedial = mutableListOf<MedialTriceps>()
        for (item in selectedExercises) {
            when (item) {
                is LongTriceps -> temporaryLong.add(item)
                is LatTriceps -> temporaryLat.add(item)
                is MedialTriceps -> temporaryMedial.add(item)
            }
        }

        val tricepsExercises = mutableListOf<TricepsExercises>()
        tricepsExercises.addAll(temporaryLat)
        tricepsExercises.addAll(temporaryLong)
        tricepsExercises.addAll(temporaryMedial)

        return tricepsExercises
    }


    suspend fun selectLegExercises(
        homeOrGym: List<LegExercises>,

        setsMap: MutableMap<String, Int>
    ): MutableList<LegExercises> {
        val selectedExercises = mutableListOf<LegExercises>()

        val shuffledList = homeOrGym.shuffled()

        val maxQuadCount: Int = (ceil(setsMap.getValue("maxQuadCount").toFloat() / 3f)).toInt()
        val maxHamstringCount: Int =
            (ceil(setsMap.getValue("maxHamstringCount").toFloat() / 3f)).toInt()
        val maxCalfCount: Int = (ceil(setsMap.getValue("maxCalfCount").toFloat() / 3f)).toInt()
        val maxGlutesCount: Int = (ceil(setsMap.getValue("maxGlutesCount").toFloat() / 3f)).toInt()
        val maxAdductorsCount: Int =
            (ceil(setsMap.getValue("maxAdductorsCount").toFloat() / 3f)).toInt()
        val maxAbductorCount: Int =
            (ceil(setsMap.getValue("maxAbductorCount").toFloat() / 3f)).toInt()

        var quadCount = 0
        var hamstringsCount = 0
        var calfCount = 0
        var glutesCount = 0
        var adductorCount = 0
        var abductorCount = 0

        for (exercise in shuffledList) {
            when (exercise) {
                is QuadLegs -> {
                    if (quadCount < maxQuadCount) {
                        selectedExercises.add(exercise)
                        quadCount++
                    }
                }

                is HamstringLegs -> {
                    if (hamstringsCount < maxHamstringCount) {
                        selectedExercises.add(exercise)
                        hamstringsCount++
                    }
                }

                is CalfLegs -> {
                    if (calfCount < maxCalfCount) {
                        selectedExercises.add(exercise)
                        calfCount++
                    }
                }

                is GlutesLegs -> {
                    if (glutesCount < maxGlutesCount) {
                        selectedExercises.add(exercise)
                        glutesCount++
                    }
                }

                is AdductorAbductorLegs -> {
                    if ((adductorCount < maxAdductorsCount) && (exercise.muscleSubGroup == "Adductors")) {
                        selectedExercises.add(exercise)
                        adductorCount++
                    } else if ((abductorCount < maxAbductorCount) && (exercise.muscleSubGroup == "Abductors")) {
                        selectedExercises.add(exercise)
                        abductorCount++
                    }
                }

                else -> {
                    // Ignore
                }
            }
        }

        val temporaryQuads = mutableListOf<QuadLegs>()
        val temporaryHamstring = mutableListOf<HamstringLegs>()
        val temporaryCalf = mutableListOf<CalfLegs>()
        val temporaryGlutes = mutableListOf<GlutesLegs>()
        val temporaryAdductorAbductor = mutableListOf<AdductorAbductorLegs>()
        for (item in selectedExercises) {
            when (item) {
                is QuadLegs -> temporaryQuads.add(item)
                is HamstringLegs -> temporaryHamstring.add(item)
                is CalfLegs -> temporaryCalf.add(item)
                is GlutesLegs -> temporaryGlutes.add(item)
                is AdductorAbductorLegs -> temporaryAdductorAbductor.add(item)
            }
        }

        val legsExercises = mutableListOf<LegExercises>()
        legsExercises.addAll(temporaryQuads)
        legsExercises.addAll(temporaryAdductorAbductor)
        legsExercises.addAll(temporaryHamstring)
        legsExercises.addAll(temporaryCalf)
        legsExercises.addAll(temporaryGlutes)

        return legsExercises
    }


    suspend fun selectChestExercises(
        homeOrGym: List<ChestExercises>,

        setsMap: MutableMap<String, Int>
    ): MutableList<ChestExercises> {
        val selectedExercises = mutableListOf<ChestExercises>()

        val shuffledList = homeOrGym.shuffled()

        val maxUpperChestCount: Int =
            (ceil(setsMap.getValue("maxUpperChestCount").toFloat() / 3f)).toInt()
        val maxMiddleChestCount: Int =
            (ceil(setsMap.getValue("maxMiddleChestCount").toFloat() / 3f)).toInt()
        val maxLowerChestCount: Int =
            (ceil(setsMap.getValue("maxLowerChestCount").toFloat() / 3f)).toInt()

        var upperChestCount = 0
        var middleChestCount = 0
        var lowerChestCount = 0

        for (exercise in shuffledList) {
            when (exercise) {
                is UpperChest -> {
                    if (upperChestCount < maxUpperChestCount) {
                        selectedExercises.add(exercise)
                        upperChestCount++
                    }
                }

                is MiddleChest -> {
                    if (middleChestCount < maxMiddleChestCount) {
                        selectedExercises.add(exercise)
                        middleChestCount++
                    }
                }

                is LowerChest -> {
                    if (lowerChestCount < maxLowerChestCount) {
                        selectedExercises.add(exercise)
                        lowerChestCount++
                    }
                }

                else -> {
                    // Ignore
                }
            }
        }

        val temporaryUpperChest = mutableListOf<UpperChest>()
        val temporaryMiddleChest = mutableListOf<MiddleChest>()
        val temporaryLowerChest = mutableListOf<LowerChest>()
        for (item in selectedExercises) {
            when (item) {
                is UpperChest -> temporaryUpperChest.add(item)
                is MiddleChest -> temporaryMiddleChest.add(item)
                is LowerChest -> temporaryLowerChest.add(item)
            }
        }

        val chestExercises = mutableListOf<ChestExercises>()
        chestExercises.addAll(temporaryUpperChest)
        chestExercises.addAll(temporaryLowerChest)
        chestExercises.addAll(temporaryMiddleChest)

        return chestExercises
    }


    suspend fun selectShoulderExercises(
        homeOrGym: List<ShoulderExercises>,

        setsMap: MutableMap<String, Int>
    ): MutableList<ShoulderExercises> {
        val selectedExercises = mutableListOf<ShoulderExercises>()

        val shuffledList = homeOrGym.shuffled()

        val maxAntDeltsCount: Int =
            (ceil(setsMap.getValue("maxAntDeltsCount").toFloat() / 3f)).toInt()
        val maxMedDeltsCount: Int =
            (ceil(setsMap.getValue("maxMedDeltsCount").toFloat() / 3f)).toInt()
        val maxPostDeltsCount: Int =
            (ceil(setsMap.getValue("maxPostDeltsCount").toFloat() / 3f)).toInt()

        // Counters for each subclass
        var antDeltsCount = 0
        var medDeltsCount = 0
        var postDeltsCount = 0

        for (exercise in shuffledList) {
            when (exercise) {
                is AntDeltsShoulder -> {
                    if (antDeltsCount < maxAntDeltsCount) {
                        selectedExercises.add(exercise)
                        antDeltsCount++
                    }
                }

                is MedDeltsShoulder -> {
                    if (medDeltsCount < maxMedDeltsCount) {
                        selectedExercises.add(exercise)
                        medDeltsCount++
                    }
                }

                is PostDeltsShoulder -> {
                    if (postDeltsCount < maxPostDeltsCount) {
                        selectedExercises.add(exercise)
                        postDeltsCount++
                    }
                }

                else -> {
                    // Ignore
                }
            }
        }

        val temporaryAntDelts = mutableListOf<AntDeltsShoulder>()
        val temporaryMedDelts = mutableListOf<MedDeltsShoulder>()
        val temporaryPostDelts = mutableListOf<PostDeltsShoulder>()
        for (item in selectedExercises) {
            when (item) {
                is AntDeltsShoulder -> temporaryAntDelts.add(item)
                is MedDeltsShoulder -> temporaryMedDelts.add(item)
                is PostDeltsShoulder -> temporaryPostDelts.add(item)
            }
        }

        val shoulderExercises = mutableListOf<ShoulderExercises>()
        shoulderExercises.addAll(temporaryMedDelts)
        shoulderExercises.addAll(temporaryPostDelts)
        shoulderExercises.addAll(temporaryAntDelts)

        return shoulderExercises
    }


    suspend fun selectForearmsExercises(
        homeOrGym: List<ForearmsExercises>,

        setsMap: MutableMap<String, Int>
    ): MutableList<ForearmsExercises> {
        val selectedExercises = mutableListOf<ForearmsExercises>()

        val shuffledList = homeOrGym.shuffled()

        val maxFlexorsCount: Int =
            (ceil(setsMap.getValue("maxFlexorsCount").toFloat() / 3f)).toInt()
        val maxExtensorsCount: Int =
            (ceil(setsMap.getValue("maxExtensorsCount").toFloat() / 3f)).toInt()

        var flexorsCount = 0
        var extensorsCount = 0

        for (exercise in shuffledList) {
            when (exercise) {
                is FlexorsForearms -> {
                    if (flexorsCount < maxFlexorsCount) {
                        selectedExercises.add(exercise)
                        flexorsCount++
                    }
                }

                is ExtensorsForearms -> {
                    if (extensorsCount < maxExtensorsCount) {
                        selectedExercises.add(exercise)
                        extensorsCount++
                    }
                }

                else -> {
                    // Ignore
                }
            }
        }

        val temporaryFlexors = mutableListOf<FlexorsForearms>()
        val temporaryExtensors = mutableListOf<ExtensorsForearms>()
        for (item in selectedExercises) {
            when (item) {
                is FlexorsForearms -> temporaryFlexors.add(item)
                is ExtensorsForearms -> temporaryExtensors.add(item)
            }
        }

        val forearmsExercises = mutableListOf<ForearmsExercises>()
        forearmsExercises.addAll(temporaryFlexors)
        forearmsExercises.addAll(temporaryExtensors)

        return forearmsExercises
    }


    fun workoutSearching() {
        _uiState.update { currentState ->
            currentState.copy(workoutSearchScreen = !_uiState.value.workoutSearchScreen)
        }
    }


    /***
     * DIET
     * DIET
     * DIET
     */


    fun dietSelection(
        isVeg: Boolean = false,
        breakFastList: List<BreakfastDiet> = breakfastDiet,
        lunchDietList: List<LunchDiet> = lunchDiet,
        postWorkoutList: List<PostWorkoutDiet> = postWorkoutDiet,
        dinnerDietList: List<DinnerDiet> = dinnerDiet,
    ): MutableList<FoodData> {

        val dietList = mutableListOf<FoodData>()

        val maxBreakfastIndex: Int
        val maxLunchIndex: Int
        val maxPostWorkoutIndex: Int
        val maxDinnerIndex: Int

        if (!isVeg) {
            val whichOne = (1..3).random()
            when (whichOne) {
                1 -> {
                    maxBreakfastIndex = 19
                    maxLunchIndex = 9
                    maxDinnerIndex = 9
                }

                2 -> {
                    maxBreakfastIndex = 9
                    maxLunchIndex = 19
                    maxDinnerIndex = 9
                }

                else -> {
                    maxBreakfastIndex = 9
                    maxLunchIndex = 9
                    maxDinnerIndex = 19
                }
            }
            maxPostWorkoutIndex = 19
        } else {
            maxBreakfastIndex = 9
            maxLunchIndex = 9
            maxPostWorkoutIndex = 9
            maxDinnerIndex = 9
        }
        for (i in 1..3) {
            val breakfastIndex = (0..maxBreakfastIndex).random()
            dietList.add(breakFastList[breakfastIndex])

            val lunchIndex = (0..maxLunchIndex).random()
            dietList.add(lunchDietList[lunchIndex])

            val postWorkoutIndex = (0..maxPostWorkoutIndex).random()
            dietList.add(postWorkoutList[postWorkoutIndex])

            val dinnerIndex = (0..maxDinnerIndex).random()
            dietList.add(dinnerDietList[dinnerIndex])
        }

        return dietList
    }

    fun dietSearching() {
        _uiState.update { currentState ->
            currentState.copy(dietSearchScreen = !_uiState.value.dietSearchScreen)
        }
    }


    /***
     * MOTIVATION
     * MOTIVATION
     * MOTIVATION
     */


    var currentIndex = 0

    fun currentIndexing() {
//        currentIndex = newIndex
        if (currentIndex == 16) {
            currentIndex = 0
        } else {
            currentIndex++
        }
        _uiState.update { currentState ->
            currentState.copy(currentIndex = currentIndex)
        }
    }


    /***
     * dataBASE
     * dataBASE
     * dataBASE
     */


    suspend fun saveData(type: String, content: String) {
        appDataRepository.upsertData(AppData(type = type, content = content))
    }

    suspend fun retrieveData(type: String) {
        appDataRepository.getDataStream(type)
    }

    suspend fun deleteData(id: Int, type: String, content: String) {
        appDataRepository.deleteData(AppData(id = id, type = type, content = content))
    }

    suspend fun updateData(id: Int, type: String, content: String) {
        appDataRepository.upsertData(AppData(id = id, type = type, content = content))
    }
    suspend fun streakDataHandler(givenType: String = "lastDate"): AppData? {
        val lastDateData: Flow<AppData?> = appDataRepository.getDataStream(givenType)
            .map { dataList ->
                dataList.firstOrNull()
            }
        val coroutineScope = CoroutineScope(Dispatchers.Main)
        var data: AppData? = null
        coroutineScope.launch {
            lastDateData.collect { appData ->
                if (appData != null) {
                    data = appData
                }
                else {
                    data = null
                }
            }
        }
        return data
    }
}


class SearchViewModel() : ViewModel() {

    val breakfastNames = mutableListOf<String>()
    val lunchNames = mutableListOf<String>()
    val postWorkoutNames = mutableListOf<String>()
    val dinnerNames = mutableListOf<String>()
    val nonDietNames = mutableListOf<String>()

    init {
        breakfastDiet.forEach() { item ->
            breakfastNames.add(item.name)
        }
        lunchDiet.forEach() { item ->
            lunchNames.add(item.name)
        }
        postWorkoutDiet.forEach() { item ->
            postWorkoutNames.add(item.name)
        }
        dinnerDiet.forEach() { item ->
            dinnerNames.add(item.name)
        }
        notDietFood.forEach() { item ->
            nonDietNames.add(item.name1)
        }
    }

    suspend fun searchItem(search: String): MutableList<FoodData> {
        var breakfastList = mutableListOf<FoodData>()
        var lunchList = mutableListOf<FoodData>()
        val postWorkoutList = mutableListOf<PostWorkoutDiet>()
        val dinnerList = mutableListOf<DinnerDiet>()
        val nonDietList = mutableListOf<FoodData>()

        val breakfastOtherNamesList = mutableListOf<FoodData>()
        val lunchOtherNamesList = mutableListOf<LunchDiet>()
        val postWorkoutOtherNamesList = mutableListOf<PostWorkoutDiet>()
        val dinnerOtherNamesList = mutableListOf<DinnerDiet>()
        val nonDietOtherNamesList = mutableListOf<FoodData>()

        breakfastNames.forEachIndexed { index, it ->
            if (it.contains(search, ignoreCase = true)) {
                breakfastList.add(breakfastDiet[index])
            }
        }
        lunchNames.forEachIndexed { index, it ->
            if (it.contains(search, ignoreCase = true)) {
                lunchList.add(lunchDiet[index])
            }
        }
        postWorkoutNames.forEachIndexed { index, it ->
            if (it.contains(search, ignoreCase = true)) {
                postWorkoutList.add(postWorkoutDiet[index])
            }
        }
        dinnerNames.forEachIndexed { index, it ->
            if (it.contains(search, ignoreCase = true)) {
                dinnerList.add(dinnerDiet[index])
            }
        }
        nonDietNames.forEachIndexed { index, it ->
            if (it.contains(search, ignoreCase = true)) {
                nonDietList.add(notDietFood[index])
            }
        }


        breakfastDiet.forEach() { item ->
            item.otherNames.forEach() { name ->
                if (name.contains(search, ignoreCase = true)) {
                    breakfastOtherNamesList.add(item)
                }
            }
        }
        lunchDiet.forEach() { item ->
            item.otherNames.forEach() { name ->
                if (name.contains(search, ignoreCase = true)) {
                    lunchOtherNamesList.add(item)
                }
            }
        }
        postWorkoutDiet.forEach() { item ->
            item.otherNames.forEach() { name ->
                if (name.contains(search, ignoreCase = true)) {
                    postWorkoutOtherNamesList.add(item)
                }
            }
        }
        dinnerDiet.forEach() { item ->
            item.otherNames.forEach() { name ->
                if (name.contains(search, ignoreCase = true)) {
                    dinnerOtherNamesList.add(item)
                }
            }
        }
        notDietFood.forEach() { item ->
            item.otherNames1.forEach() { name ->
                if (name.contains(search, ignoreCase = true)) {
                    nonDietOtherNamesList.add(item)
                }
            }
        }

        breakfastList.addAll(lunchList)
        breakfastList.addAll(postWorkoutList)
        breakfastList.addAll(dinnerList)
        breakfastList.addAll(nonDietList)
        breakfastList = breakfastList.shuffled().toMutableList()

        lunchList.clear()
        lunchList.addAll(breakfastOtherNamesList)
        lunchList.addAll(lunchOtherNamesList)
        lunchList.addAll(postWorkoutOtherNamesList)
        lunchList.addAll(dinnerOtherNamesList)
        lunchList.addAll(nonDietOtherNamesList)
        lunchList = lunchList.shuffled().toMutableList()

        breakfastList.addAll(lunchList)

        return breakfastList
    }

}


class ExerciseSearchViewModel() : ViewModel() {

    val chestExercisesName = mutableListOf<String>()
    val shoulderExercisesName = mutableListOf<String>()
    val tricepsExercisesName = mutableListOf<String>()
    val bicepsExercisesName = mutableListOf<String>()
    val backExercisesName = mutableListOf<String>()
    val legExercisesName = mutableListOf<String>()
    val forearmsExercisesName = mutableListOf<String>()
    val absExercisesName = mutableListOf<String>()

    var homeChestListSize: Int = 0
    var homeShoulderListSize: Int = 0
    var homeTricepsListSize: Int = 0
    var homeBicepsListSize: Int = 0
    var homeBackListSize: Int = 0
    var homeLegListSize: Int = 0
    var homeForearmsListSize: Int = 0
    var homeAbsListSize: Int = 0

    init {
        val chestWorkouts = mutableListOf<ChestExercises>()
        chestWorkouts.addAll(homeChestExercises)
        homeChestListSize = chestWorkouts.size-1
        chestWorkouts.addAll(gymChestExercises)
        chestWorkouts.forEach(){
            chestExercisesName.add(it.name0)
        }
        chestWorkouts.clear()

        val shoulderExercises = mutableListOf<ShoulderExercises>()
        shoulderExercises.addAll(homeShoulderExercises)
        homeShoulderListSize = shoulderExercises.size-1
        shoulderExercises.addAll(gymShoulderExercises)
        shoulderExercises.forEach(){
            shoulderExercisesName.add(it.name0)
        }
        shoulderExercises.clear()

        val tricepsExercises = mutableListOf<TricepsExercises>()
        tricepsExercises.addAll(homeTricepsExercises)
        homeTricepsListSize = tricepsExercises.size-1
        tricepsExercises.addAll(gymTricepsExercises)
        tricepsExercises.forEach(){
            tricepsExercisesName.add(it.name0)
        }
        tricepsExercises.clear()

        val bicepsExercises = mutableListOf<BicepsExercises>()
        bicepsExercises.addAll(homeBicepsExercises)
        homeBicepsListSize = bicepsExercises.size-1
        bicepsExercises.addAll(gymBicepsExercises)
        bicepsExercises.forEach(){
            bicepsExercisesName.add(it.name0)
        }
        bicepsExercises.clear()

        val backExercises = mutableListOf<BackExercises>()
        backExercises.addAll(homeBackExercises)
        homeBackListSize = backExercises.size-1
        backExercises.addAll(gymBackExercises)
        backExercises.forEach(){
            backExercisesName.add(it.name0)
        }
        backExercises.clear()

        val legExercises = mutableListOf<LegExercises>()
        legExercises.addAll(homeLegExercises)
        homeLegListSize = legExercises.size-1
        legExercises.addAll(gymLegsExercises)
        legExercises.forEach(){
            legExercisesName.add(it.name0)
        }
        legExercises.clear()

        val forearmsExercises = mutableListOf<ForearmsExercises>()
        forearmsExercises.addAll(homeForearmExercises)
        homeForearmsListSize = forearmsExercises.size-1
        forearmsExercises.addAll(gymForearmsExercises)
        forearmsExercises.forEach(){
            forearmsExercisesName.add(it.name0)
        }
        forearmsExercises.clear()

        val absExercises = mutableListOf<AbsExercises>()
        absExercises.addAll(homeAbsExercises)
        homeAbsListSize = absExercises.size-1
        absExercises.addAll(gymAbsExercises)
        absExercises.forEach(){
            absExercisesName.add(it.name0)
        }
        absExercises.clear()
    }

    suspend fun searchItem(search: String): MutableList<Exercises> {

        var chestExercisesList = mutableListOf<Exercises>()
        val shoulderExercisesList = mutableListOf<ShoulderExercises>()
        val tricepsExercisesList = mutableListOf<TricepsExercises>()
        val bicepsExercisesList = mutableListOf<BicepsExercises>()
        val backExercisesList = mutableListOf<BackExercises>()
        val legExercisesList = mutableListOf<LegExercises>()
        val forearmsExercisesList = mutableListOf<ForearmsExercises>()
        val absExercisesList = mutableListOf<AbsExercises>()

        chestExercisesName.forEachIndexed { index, it ->
            var indexxx = index
            if (it.contains(search, ignoreCase = true)) {
                if (index > homeChestListSize){
                    indexxx -= homeChestListSize
                    chestExercisesList.add(gymChestExercises[indexxx])
                }
                else{
                chestExercisesList.add(homeChestExercises[indexxx])
                }
            }
        }

        shoulderExercisesName.forEachIndexed { index, it ->
            var indexxx = index
            if (it.contains(search, ignoreCase = true)) {
                if (index > homeShoulderListSize){
                    indexxx -= homeShoulderListSize
                    shoulderExercisesList.add(gymShoulderExercises[indexxx])
                }
                else{
                    shoulderExercisesList.add(homeShoulderExercises[indexxx])
                }
            }
        }

        tricepsExercisesName.forEachIndexed { index, it ->
            var indexxx = index
            if (it.contains(search, ignoreCase = true)) {
                if (index > homeTricepsListSize){
                    indexxx -= homeTricepsListSize
                    tricepsExercisesList.add(gymTricepsExercises[indexxx])
                }
                else{
                    tricepsExercisesList.add(homeTricepsExercises[indexxx])
                }
            }
        }

        bicepsExercisesName.forEachIndexed { index, it ->
            var indexxx = index
            if (it.contains(search, ignoreCase = true)) {
                if (index > homeBicepsListSize){
                    indexxx -= homeBicepsListSize
                    bicepsExercisesList.add(gymBicepsExercises[indexxx])
                }
                else{
                    bicepsExercisesList.add(homeBicepsExercises[indexxx])
                }
            }
        }

        backExercisesName.forEachIndexed { index, it ->
            var indexxx = index
            if (it.contains(search, ignoreCase = true)) {
                if (index > homeBackListSize){
                    indexxx -= homeBackListSize
                    backExercisesList.add(gymBackExercises[indexxx])
                }
                else{
                    backExercisesList.add(homeBackExercises[indexxx])
                }
            }
        }

        legExercisesName.forEachIndexed { index, it ->
            var indexxx = index
            if (it.contains(search, ignoreCase = true)) {
                if (index > homeLegListSize){
                    indexxx -= homeLegListSize
                    legExercisesList.add(gymLegsExercises[indexxx])
                }
                else{
                    legExercisesList.add(homeLegExercises[indexxx])
                }
            }
        }

        forearmsExercisesName.forEachIndexed { index, it ->
            var indexxx = index
            if (it.contains(search, ignoreCase = true)) {
                if (index > homeForearmsListSize){
                    indexxx -= homeForearmsListSize
                    forearmsExercisesList.add(gymForearmsExercises[indexxx])
                }
                else{
                    forearmsExercisesList.add(homeForearmExercises[indexxx])
                }
            }
        }

        absExercisesName.forEachIndexed { index, it ->
            var indexxx = index
            if (it.contains(search, ignoreCase = true)) {
                if (index > homeAbsListSize){
                    indexxx -= homeAbsListSize
                    absExercisesList.add(gymAbsExercises[indexxx])
                }
                else{
                    absExercisesList.add(homeAbsExercises[indexxx])
                }
            }
        }

        chestExercisesList.addAll(shoulderExercisesList)
        chestExercisesList.addAll(tricepsExercisesList)
        chestExercisesList.addAll(bicepsExercisesList)
        chestExercisesList.addAll(backExercisesList)
        chestExercisesList.addAll(legExercisesList)
        chestExercisesList.addAll(forearmsExercisesList)
        chestExercisesList.addAll(absExercisesList)
        chestExercisesList = chestExercisesList.shuffled().toMutableList()
        return chestExercisesList
    }

}
