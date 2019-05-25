package com.netcracker.services.api;

import com.netcracker.model.documents.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface PlanningService {

    Exercise createExercise(Exercise exercise, String userId);

    Workout createWorkout(Workout workout, String userId);

    Workout createWorkout(String workoutComplexId, Workout workout, String userId);

    ScheduledWorkout createScheduledWorkout(String id, ScheduledWorkout scheduledWorkout, String userId);

    WorkoutComplex createWorkoutComplex(WorkoutComplex workoutComplex, String userId);

    Exercise deleteExercise(String exerciseId, String userId);

    Workout deleteWorkout(String workoutId, String userId);

    WorkoutComplex deleteWorkoutComplex(String workoutComplexId, String userId);

    ScheduledWorkout deleteScheduledWorkout(String workoutId, String scheduledWorkoutId, String userId);

    String setExerciseName(String exerciseId, String name, String userId);

    // Добавить упражнение в тренировку
    Workout addListExercises(String workoutId, List<String> exerciseIdList, String userId);

    // Удалить упражнение из тренировки
    Workout delListExercises(String workoutId, List<String> exerciseIdList, String userId);

    // Добавить упражнение в запланированную тренировку
    ScheduledWorkout addExerciseToScheduledWorkout(String scheduledWorkoutId, String exerciseId, String userId);

    // Измерить упражнение в запланированной тренировке
    MeasurementsOfExercise addExerciseMeasurement(String scheduledWorkoutId, String exerciseId, Measurement measurement, String userId);

    MeasurementsOfExercise updateExerciseMeasurement(String scheduledWorkoutId, String exerciseId, String numberMeasurement, Measurement measurement, String userId);

    MeasurementsOfExercise delExerciseMeasurement(String scheduledWorkoutId, String exerciseId, String numberMeasurement, String userId);

    // Measurement addMeasurementOfExercise(String exerciseId, String measurementOfExerciseId, Measurement measurement);

    // Measurement delMeasurementOfExercise(String exerciseId, String measurementOfExerciseId, String number);

    // Удалить упражнение из запланированной тренировки
    ScheduledWorkout deleteExerciseFromScheduledWorkout(String scheduledWorkoutId, String exerciseId, String userId);

    // Добавить тренировку в комплекс
    Workout addWorkout(String workoutComplexId, Workout workout, String userId);

    // Удалить тренировку из комплекса
    Workout delWorkout(String workoutComplexId, String workoutId, String userId);

    // Запланировать тренировку на дату/время
    Workout setWorkoutToDate(String workoutId, Date date, String userId);

    // Назначить тренировку в другой комплекс
    Workout setWorkoutComplexToWorkout(String workoutComplexId, String workoutId, String userId);

    // Изменить название тренировки
    Workout setNameWorkout(String workoutId, String name, String userId);

    // Изменить дату запланированной тренировки
    void setDateScheduledWorkout(String scheduledWorkoutId, Date date, String userId);

    // Изменить статус запланированной тренировки
    void setStatusScheduledWorkout(String scheduledWorkoutId, String status, String userId);

    // Изменить имя тренировочного комплексаЫ
    WorkoutComplex setNameWorkoutComplex(String workoutComplexId, String name, String userId);
}
