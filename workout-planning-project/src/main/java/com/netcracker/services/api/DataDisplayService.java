package com.netcracker.services.api;

import com.netcracker.model.documents.*;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface DataDisplayService {
    User getUserById(String userId);

    User getUserById(String userId, String authUserId);

    List<Exercise> getAllExercise();

    List<WorkoutComplex> getAllWorkoutComplex(String userId);

    Exercise getExercise(String exerciseId);

    Workout getWorkoutById(String workoutId, String userId);

    WorkoutComplex getWorkoutComplexById(String workoutComplexId, String userId);

    List<Exercise> getExercises(String workoutId, String userId);

    WorkoutComplex getSourceWorkoutComplex(String workoutId, String userId);

    List<Workout> getWorkouts(String workoutComplexId, String userId);

    List<Workout> getAllWorkout(String userId);
}
