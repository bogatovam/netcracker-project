package com.netcracker.services.api;

import com.netcracker.model.documents.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface DataDisplayService {
    List<Exercise> getAllExercise();
    List<WorkoutComplex> getAllWorkoutComplex();
    List<ScheduledWorkout> getAllScheduledWorkouts();

    Exercise getExercise( String exerciseId);
    Workout getWorkoutById(String workoutId);
    WorkoutComplex getWorkoutComplexById(String workoutComplexId);
    ScheduledWorkout getScheduledWorkout(String scheduledWorkoutId);

    Map<String, String> getAllExercisesNames();
    Map<String, String> getWorkoutComplexesNames();
    Map<String, String> getWorkoutsNames(String workoutComplexId);
    Map<String, String> getWorkoutsExercisesNames(String workoutId);
    Map<String, String> getScheduledWorkoutsExercisesNames(String scheduledWorkoutId);

    // Получить имя упражнения
    String getNameOfExercise(String exerciseId);

    // Получить описание упражнения
    String getDescriptionOfExercise(String exerciseId);

    // Получить меры упражнения
    List<String> getMeasuresOfExercise(String exerciseId);

    // Получить все измерения упражнения
    List<MeasurementsOfExercise> getMeasurementsOfExercise(String exerciseId);

    // Получить все измерения упражнения вместе с датами
    Map<Date, MeasurementsOfExercise> getMeasurementsOfExerciseWithDate(String exerciseId);

    // Получить последнее измерение упражнения
    MeasurementsOfExercise getLastMeasurementOfExercise(String exerciseId);

    // Получить имя тренировки
    String getWorkoutName(String workoutId);

    // Получить все упражнения тренировки
    List<Exercise> getExercises(String workoutId);

    // Получить все имена упражнений тренировки
    Map<String, String> getAllNamesOfExercises();

    // Получить все планы этой тренировки
    List<ScheduledWorkout> getScheduledWorkouts(String workoutId);

    List<ScheduledWorkout> getScheduledWorkoutsByStatus(String workoutId, String status);

    // Получить исходную тренировку
    Workout getSourceWorkout(String scheduledWorkoutId);

    // Получить исходный комплех
    WorkoutComplex getSourceWorkoutComplex(String workoutId);

    // Получить информацию(???) о некоторой запланированной тренировке
    Date getDateScheduledWorkout(String scheduledWorkoutId);

    String getStatusScheduledWorkout(String scheduledWorkoutId);

    Map<String, String> getScheduledWorkoutInformation(String id);

    // Получить все текущие упражнения тренировки
    List<Exercise> getAllCurrentExercises(String scheduledWorkoutId);

    // Получить измерения всех текущих упражнений в запланированной тренировке
    Map<Exercise, MeasurementsOfExercise> getExercisesMeasurements(String scheduledWorkoutId);

    // Получить имя комплекса
    String getNameWorkoutComplex(String workoutComplexId);

    // Получить все тренировки комплекса
    List<Workout> getWorkouts(String workoutComplexId);
}
