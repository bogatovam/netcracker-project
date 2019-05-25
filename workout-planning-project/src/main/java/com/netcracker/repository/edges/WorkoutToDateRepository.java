package com.netcracker.repository.edges;

import com.arangodb.springframework.repository.ArangoRepository;
import com.netcracker.model.edges.WorkoutToDate;

import java.util.List;
import java.util.Optional;

public interface WorkoutToDateRepository extends ArangoRepository<WorkoutToDate, String> {
    List<WorkoutToDate> removeAllByWorkoutId(String workout);

    List<WorkoutToDate> removeAllByScheduledWorkoutId(String workout);

    Optional<WorkoutToDate> findByScheduledWorkoutId(String scheduledWorkoutId);
}
