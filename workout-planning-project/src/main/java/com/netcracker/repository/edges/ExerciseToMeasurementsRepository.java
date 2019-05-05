package com.netcracker.repository.edges;

import com.arangodb.springframework.repository.ArangoRepository;
import com.netcracker.model.edges.ExerciseToMeasurements;

public interface ExerciseToMeasuresRepository extends ArangoRepository<ExerciseToMeasurements, String> {
}
