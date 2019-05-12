package com.netcracker.repository.edges;

import com.arangodb.springframework.repository.ArangoRepository;
import com.netcracker.model.edges.ScheduledWorkoutToExerciseMeasurement;

import java.util.List;

public interface ScheduledWorkoutToExerciseMeasurementRepository extends ArangoRepository<ScheduledWorkoutToExerciseMeasurement, String> {

    List<ScheduledWorkoutToExerciseMeasurement> removeAllByScheduledWorkoutId(String id);

    List<ScheduledWorkoutToExerciseMeasurement> removeAllByExerciseToMeasurementsId(String id);

    ScheduledWorkoutToExerciseMeasurement findByExerciseToMeasurementsId(String etmId);
}
