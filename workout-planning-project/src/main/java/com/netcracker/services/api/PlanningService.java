package com.netcracker.services.api;

import com.netcracker.model.documents.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PlanningService {

    Exercise createExercise(Exercise exercise, String userId);

    Workout createWorkout(String workoutComplexId, Workout workout, String userId);

    ResponseEntity<?> deleteUserById(String userId, String authUserId);

    WorkoutComplex createWorkoutComplex(WorkoutComplex workoutComplex, String userId);

    Exercise deleteExercise(String exerciseId, String userId);

    Workout deleteWorkout(String workoutId, String userId);

    WorkoutComplex deleteWorkoutComplex(String workoutComplexId, String userId);

    String setExerciseName(String exerciseId, String name, String userId);

    // Добавить упражнение в тренировку
    Workout addListExercises(String workoutId, List<Exercise> exerciseList, String userId);

    // Удалить упражнение из тренировки
    Workout delListExercises(String workoutId, List<Exercise> exerciseList, String userId);

    // Добавить тренировку в комплекс
    Workout addWorkout(String workoutComplexId, Workout workout, String userId);

    // Удалить тренировку из комплекса
    Workout deleteWorkout(String workoutComplexId, String workoutId, String userId);

    Workout changeSourceWorkoutComplex(String workoutId, String oldWorkoutComplexId, String newWorkoutComplexId, String userId);

    Workout updateWorkout(Workout workout, String userId);

    WorkoutComplex updateWorkoutComplex(WorkoutComplex workoutComplex, String userId);
}
