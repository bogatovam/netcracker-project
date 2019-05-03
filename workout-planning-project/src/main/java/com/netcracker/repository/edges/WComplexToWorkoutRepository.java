package com.netcracker.repository.edges;

import com.arangodb.springframework.repository.ArangoRepository;
import com.netcracker.model.edges.WComplexToWorkout;

public interface WComplexToWorkoutRepository extends ArangoRepository<WComplexToWorkout, String> {
}
