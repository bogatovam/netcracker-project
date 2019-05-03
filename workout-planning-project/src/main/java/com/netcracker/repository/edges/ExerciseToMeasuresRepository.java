package com.netcracker.repository.edges;

import com.arangodb.springframework.repository.ArangoRepository;
import com.netcracker.model.edges.ExerciseToMeasures;

public interface ExerciseToMeasuresRepository extends ArangoRepository<ExerciseToMeasures, String> {
}
