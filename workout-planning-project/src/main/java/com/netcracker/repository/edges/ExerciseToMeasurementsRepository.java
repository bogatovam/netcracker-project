package com.netcracker.repository.edges;

import com.arangodb.springframework.repository.ArangoRepository;
import com.netcracker.model.edges.ExerciseToMeasurements;
import com.netcracker.model.edges.WorkoutToExercise;

import java.util.List;

public interface ExerciseToMeasurementsRepository extends ArangoRepository<ExerciseToMeasurements, String> {
    List<ExerciseToMeasurements> removeById(String exercise);

    List<ExerciseToMeasurements> removeAllByExerciseId(String exercise);

    ExerciseToMeasurements findByExerciseIdAndMeasuresId(String exerciseId, String measuresId);
}
