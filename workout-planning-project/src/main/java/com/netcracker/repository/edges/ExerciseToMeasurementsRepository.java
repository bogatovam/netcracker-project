package com.netcracker.repository.edges;

import com.arangodb.springframework.repository.ArangoRepository;
import com.netcracker.model.edges.ExerciseToMeasurements;
import com.netcracker.model.edges.WorkoutToExercise;

import java.util.List;

public interface ExerciseToMeasurementsRepository extends ArangoRepository<ExerciseToMeasurements, String> {
    List<WorkoutToExercise> removeAllByExerciseId(String exercise);
    List<WorkoutToExercise> removeById(String exercise);
}
