package com.netcracker.repository.documents;

import com.arangodb.springframework.repository.ArangoRepository;
import com.netcracker.model.documents.ScheduledWorkout;

import java.util.List;

public interface ScheduledWorkoutRepository extends ArangoRepository<ScheduledWorkout, String> {

    List<ScheduledWorkout> removeById(String id);

}
