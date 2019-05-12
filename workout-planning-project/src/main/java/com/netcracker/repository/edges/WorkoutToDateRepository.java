package com.netcracker.repository.edges;

import com.arangodb.springframework.repository.ArangoRepository;
import com.netcracker.model.edges.WorkoutToDate;

import java.util.List;

public interface WorkoutToDateRepository extends ArangoRepository<WorkoutToDate, String> {
    List<WorkoutToDate> removeAllByWorkoutId(String workout);

    List<WorkoutToDate> removeAllByScheduledWorkoutId(String workout);

    WorkoutToDate findByScheduledWorkoutId(String scheduledWorkoutId);
}
