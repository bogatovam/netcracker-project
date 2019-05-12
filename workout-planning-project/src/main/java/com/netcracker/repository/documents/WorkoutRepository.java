package com.netcracker.repository.documents;

import com.arangodb.ArangoCursor;
import com.arangodb.springframework.annotation.Query;
import com.arangodb.springframework.repository.ArangoRepository;
import com.netcracker.model.documents.Exercise;
import com.netcracker.model.documents.Workout;
import org.springframework.data.repository.query.Param;

import static com.netcracker.model.CollectionsNames.WORKOUT_TO_EXERCISE;

public interface WorkoutRepository extends ArangoRepository<Workout, String> {
    //@Query("FOR w IN 1..1 @exerciseId `" + WORKOUT_TO_EXERCISE + "` RETURN w")
    //ArangoCursor<Workout> findWorkoutsByExerciseId(@Param("exerciseId") String exerciseId);
    Iterable<Workout> findByExercisesId(String id);
}
