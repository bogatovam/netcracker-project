package com.netcracker.repository.documents;

import com.arangodb.ArangoCursor;
import com.arangodb.springframework.annotation.Query;
import com.arangodb.springframework.repository.ArangoRepository;
import com.netcracker.model.documents.WorkoutComplex;
import org.springframework.data.repository.query.Param;

import java.util.List;

import static com.netcracker.model.CollectionsNames.USER_TO_WORKOUT_COMPLEX;

public interface WorkoutComplexRepository extends ArangoRepository<WorkoutComplex, String> {
    @Query("FOR int IN 1..1 OUTBOUND @userId `" + USER_TO_WORKOUT_COMPLEX + "` RETURN int")
    ArangoCursor<WorkoutComplex> findWorkoutComplexesByUserId(@Param("userId") String userId);

    List<WorkoutComplex> removeById(String id);
}
