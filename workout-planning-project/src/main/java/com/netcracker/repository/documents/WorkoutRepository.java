package com.netcracker.repository.documents;

import com.arangodb.springframework.repository.ArangoRepository;
import com.netcracker.model.documents.Workout;

public interface WorkoutRepository extends ArangoRepository<Workout, String> {
}
