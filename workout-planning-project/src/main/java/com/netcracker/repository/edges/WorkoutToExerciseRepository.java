package com.netcracker.repository.edges;

import com.arangodb.springframework.repository.ArangoRepository;
import com.netcracker.model.edges.WorkoutToExercise;

public interface WorkoutToExerciseRepository extends ArangoRepository<WorkoutToExercise, String> {
}
