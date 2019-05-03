package com.netcracker.repository.edges;

import com.arangodb.springframework.repository.ArangoRepository;
import com.netcracker.model.edges.WorkoutToDate;

public interface WorkoutToDateRepository extends ArangoRepository<WorkoutToDate, String> {
}
