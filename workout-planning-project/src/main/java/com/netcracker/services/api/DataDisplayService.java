package com.netcracker.services.api;

import com.netcracker.model.documents.*;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface DataDisplayService {
    ResponseEntity<?> getUserById(String userId, String authUserId);

    List<Exercise> getAllExercise();

    List<WorkoutComplex> getAllWorkoutComplex(String userId);

    Exercise getExercise(String exerciseId);

    Workout getWorkoutById(String workoutId, String userId);

    WorkoutComplex getWorkoutComplexById(String workoutComplexId, String userId);

    // Получить все упражнения тренировки
    List<Exercise> getExercises(String workoutId, String userId);

    // Получить исходный комплех
    WorkoutComplex getSourceWorkoutComplex(String workoutId, String userId);

    // Получить все тренировки комплекса
    List<Workout> getWorkouts(String workoutComplexId, String userId);

    ResponseEntity<?> getAllWorkout(String userId);
}
