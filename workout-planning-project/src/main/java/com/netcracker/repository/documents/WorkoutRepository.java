package com.netcracker.repository.documents;

import com.arangodb.ArangoCursor;
import com.arangodb.springframework.annotation.Query;
import com.arangodb.springframework.repository.ArangoRepository;
import com.netcracker.model.documents.Exercise;
import com.netcracker.model.documents.Workout;
import org.springframework.data.repository.query.Param;

import static com.netcracker.model.CollectionsNames.WORKOUT_TO_EXERCISE;

public interface WorkoutRepository extends ArangoRepository<Workout, String> {
}
