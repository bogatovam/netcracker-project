package com.netcracker.services.api;

import com.netcracker.model.documents.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface DataDisplayService {
    List<Exercise> getAllExercise();

    List<WorkoutComplex> getAllWorkoutComplex(String userId);

    List<ScheduledWorkout> getAllScheduledWorkouts(String userId);

    Exercise getExercise(String exerciseId);

    Workout getWorkoutById(String workoutId, String userId);

    WorkoutComplex getWorkoutComplexById(String workoutComplexId, String userId);

    ScheduledWorkout getScheduledWorkout(String scheduledWorkoutId, String userId);

    Map<String, String> getAllExercisesNames();

    Map<String, String> getWorkoutComplexesNames(String userId);

    Map<String, String> getWorkoutsNames(String workoutComplexId, String userId);

    Map<String, String> getWorkoutsExercisesNames(String workoutId, String userId);

    Map<String, String> getScheduledWorkoutsExercisesNames(String scheduledWorkoutId, String userId);

    // Получить имя упражнения
    String getNameOfExercise(String exerciseId);

    // Получить описание упражнения
    Exercise.Description getDescriptionOfExercise(String exerciseId);

    // Получить меры упражнения
    List<String> getMeasuresOfExercise(String exerciseId);

    // Получить все измерения упражнения
    List<MeasurementsOfExercise> getMeasurementsOfExercise(String exerciseId, String userId);

    // Получить все измерения упражнения вместе с датами
    Map<Date, MeasurementsOfExercise> getMeasurementsOfExerciseWithDate(String exerciseId, String userId);

    // Получить последнее измерение упражнения
    MeasurementsOfExercise getLastMeasurementOfExercise(String exerciseId, String userId);

    // Получить имя тренировки
    String getWorkoutName(String workoutId, String userId);

    // Получить все упражнения тренировки
    List<Exercise> getExercises(String workoutId, String userId);

    // Получить все имена упражнений тренировки
    Map<String, String> getAllNamesOfExercises(String workoutId, String userId);

    // Получить все планы этой тренировки
    List<ScheduledWorkout> getScheduledWorkouts(String workoutId, String userId);

    List<ScheduledWorkout> getScheduledWorkoutsByStatus(String workoutId, String status, String userId);

    // Получить исходную тренировку
    Workout getSourceWorkout(String scheduledWorkoutId, String userId);

    // Получить исходный комплех
    WorkoutComplex getSourceWorkoutComplex(String workoutId, String userId);

    // Получить информацию(???) о некоторой запланированной тренировке
    Date getDateScheduledWorkout(String scheduledWorkoutId, String userId);

    String getStatusScheduledWorkout(String scheduledWorkoutId, String userId);

    Map<String, String> getScheduledWorkoutInformation(String id, String userId);

    // Получить все текущие упражнения тренировки
    List<Exercise> getAllCurrentExercises(String scheduledWorkoutId, String userId);

    // Получить измерения всех текущих упражнений в запланированной тренировке
    Map<Exercise, MeasurementsOfExercise> getExercisesMeasurements(String scheduledWorkoutId, String userId);

    // Получить имя комплекса
    String getNameWorkoutComplex(String workoutComplexId, String userId);

    // Получить все тренировки комплекса
    List<Workout> getWorkouts(String workoutComplexId, String userId);
}
