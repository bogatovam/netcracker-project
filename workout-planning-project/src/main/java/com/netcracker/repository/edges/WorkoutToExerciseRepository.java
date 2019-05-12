package com.netcracker.repository.edges;

import com.arangodb.springframework.repository.ArangoRepository;
import com.netcracker.model.documents.Exercise;
import com.netcracker.model.edges.WorkoutToExercise;

import java.util.List;

public interface WorkoutToExerciseRepository extends ArangoRepository<WorkoutToExercise, String> {
    List<WorkoutToExercise> findAllByExerciseId(String exercise);
    List<WorkoutToExercise> removeAllByExerciseId(String exercise);
    List<WorkoutToExercise> removeAllByWorkoutId(String workout);
}
