package com.netcracker.services.api;

import com.netcracker.model.documents.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface PlanningService {

    Exercise createExercise(Exercise exercise);

    Workout createWorkout(Workout workout);

    ScheduledWorkout createScheduledWorkout(String id, ScheduledWorkout scheduledWorkout);

    WorkoutComplex createWorkoutComplex(WorkoutComplex workoutComplex);

    Exercise deleteExercise(String exerciseId);

    Workout deleteWorkout(String workoutId);

    WorkoutComplex deleteWorkoutComplex(String workoutComplexId);

    ScheduledWorkout deleteScheduledWorkout(String workoutId, String scheduledWorkoutId);

    String setExerciseName(String exerciseId, String name);

    // Добавить упражнение в тренировку
    Boolean addListExercises(String workoutId, List<Exercise> exerciseList);

    // Удалить упражнение из тренировки
    Boolean delListExercises(String workoutId, List<String> exerciseIdList);

    // Добавить упражнение в запланированную тренировку
    Boolean addExerciseToScheduledWorkout(String scheduledWorkoutId, Exercise exercise);

    // Измерить упражнение в запланированной тренировке
    MeasurementsOfExercise addExerciseMeasurement(String scheduledWorkoutId, String exerciseId, Measurement measurement);

    MeasurementsOfExercise updateExerciseMeasurement(String scheduledWorkoutId, String exerciseId, String numberMeasurement, Measurement measurement);

    MeasurementsOfExercise delExerciseMeasurement(String scheduledWorkoutId, String exerciseId, Measurement measurement);

    Measurement addMeasurementOfExercise(String exerciseId, String measurementOfExerciseId, Measurement measurement);

    Measurement delMeasurementOfExercise(String exerciseId, String measurementOfExerciseId, String number);

    // Удалить упражнение из запланированной тренировки
    Boolean deleteExerciseFromScheduledWorkout(String scheduledWorkoutId, Exercise exercise);

    // Добавить тренировку в комплекс
    Workout addWorkout(String workoutComplexId, Workout workout);

    // Удалить тренировку из комплекса
    Workout delWorkout(String workoutComplexId, String workoutId);

    // Запланировать тренировку на дату/время
    Workout setWorkoutToDate(String workoutId, Date date);

    // Назначить тренировку в другой комплекс
    Workout setWorkoutComplexToWorkout(String workoutComplexId, String workoutId);

    // Изменить название тренировки
    Workout setNameWorkout(String workoutId, String name);

    // Изменить дату запланированной тренировки
    Map<String, String> setDateScheduledWorkout(String scheduledWorkoutId, Date date);

    // Изменить статус запланированной тренировки
    Map<String, String> setStatusScheduledWorkout(String scheduledWorkoutId, String status);

    // Изменить имя тренировочного комплексаЫ
    WorkoutComplex setNameWorkoutComplex(String workoutComplexId, String name);

    MeasurementsOfExercise addExerciseMeasurement(String id, MeasurementsOfExercise measurement);

    MeasurementsOfExercise delExerciseMeasurement(String id, String mid);
}
