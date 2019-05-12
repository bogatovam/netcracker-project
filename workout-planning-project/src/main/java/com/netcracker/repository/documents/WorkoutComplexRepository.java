package com.netcracker.repository.documents;

import com.arangodb.ArangoCursor;
import com.arangodb.springframework.annotation.Query;
import com.arangodb.springframework.repository.ArangoRepository;
import com.netcracker.model.documents.WorkoutComplex;
import org.springframework.data.repository.query.Param;

import java.util.List;

import static com.netcracker.model.CollectionsNames.USER_TO_WORKOUT_COMPLEX;
import static com.netcracker.model.CollectionsNames.WORKOUT_COMPLEX_TO_WORKOUT;

public interface WorkoutComplexRepository extends ArangoRepository<WorkoutComplex, String> {
    @Query("FOR w IN 1..1 OUTBOUND @userId `" + USER_TO_WORKOUT_COMPLEX + "` RETURN w")
    ArangoCursor<WorkoutComplex> findWorkoutComplexesByUserId(@Param("userId") String userId);

    @Query("FOR w IN 1..1 INBOUND @workoutId `" + WORKOUT_COMPLEX_TO_WORKOUT + "` RETURN w")
    ArangoCursor<WorkoutComplex> findWorkoutComplexesByWorkoutsId(@Param("workoutId") String workoutId);

    List<WorkoutComplex> removeById(String id);
}
