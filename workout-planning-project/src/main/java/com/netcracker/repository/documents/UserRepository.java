package com.netcracker.repository.documents;

import com.arangodb.ArangoCursor;
import com.arangodb.springframework.annotation.Query;
import com.arangodb.springframework.repository.ArangoRepository;
import com.netcracker.model.documents.User;
import com.netcracker.model.documents.WorkoutComplex;
import org.springframework.data.repository.query.Param;

import static com.netcracker.model.CollectionsNames.USER_TO_WORKOUT_COMPLEX;

public interface UserRepository extends ArangoRepository<User, String> {
    User findByLogin(String login);
    Boolean existsByLogin(String username);
    Boolean existsByEmail(String email);

    @Query("FOR u IN 1..1 INBOUND @workoutComplexId `" + USER_TO_WORKOUT_COMPLEX + "` RETURN u")
    ArangoCursor<User> findUserByWorkoutComplexId(@Param("workoutComplexId") String workoutComplexId);

}
