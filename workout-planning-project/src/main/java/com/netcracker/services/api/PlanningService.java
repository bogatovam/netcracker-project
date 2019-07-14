package com.netcracker.services.api;

import com.netcracker.model.documents.*;

import java.util.List;

public interface PlanningService {

    Exercise createExercise(Exercise exercise, String userId);

    Workout createWorkout(String workoutComplexId, Workout workout, String userId);

    User deleteUserById(String userId, String authUserId);

    WorkoutComplex createWorkoutComplex(WorkoutComplex workoutComplex, String userId);

    Exercise deleteExercise(String exerciseId, String userId);

    Workout deleteWorkout(String workoutId, String userId);

    WorkoutComplex deleteWorkoutComplex(String workoutComplexId, String userId);

    String setExerciseName(String exerciseId, String name, String userId);

    Workout addListExercises(String workoutId, List<Exercise> exerciseList, String userId);

    Workout delListExercises(String workoutId, List<Exercise> exerciseList, String userId);

    Workout addWorkout(String workoutComplexId, Workout workout, String userId);

    Workout deleteWorkout(String workoutComplexId, String workoutId, String userId);

    WorkoutComplex changeSourceWorkoutComplex(String workoutId, String oldWorkoutComplexId, String newWorkoutComplexId, String userId);

    Workout updateWorkout(Workout workout, String userId);

    WorkoutComplex updateWorkoutComplex(WorkoutComplex workoutComplex, String userId);
}
