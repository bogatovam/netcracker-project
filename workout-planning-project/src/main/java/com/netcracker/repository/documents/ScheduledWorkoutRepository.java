package com.netcracker.repository.documents;

import com.arangodb.springframework.repository.ArangoRepository;
import com.netcracker.model.documents.ScheduledWorkout;

public interface ScheduledWorkoutRepository extends ArangoRepository<ScheduledWorkout, String> {
}
