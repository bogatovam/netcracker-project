package com.netcracker.repository.edges;

import com.arangodb.springframework.repository.ArangoRepository;
import com.netcracker.model.edges.ScheduledWorkoutToExerciseMeasurement;

public interface ScheduledWorkoutToExerciseMeasurementRepository extends ArangoRepository<ScheduledWorkoutToExerciseMeasurement, String> {
}
