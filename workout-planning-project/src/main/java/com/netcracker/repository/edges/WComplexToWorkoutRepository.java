package com.netcracker.repository.edges;

import com.arangodb.springframework.repository.ArangoRepository;
import com.netcracker.model.edges.UserToWComplex;
import com.netcracker.model.edges.WComplexToWorkout;

import java.util.List;

public interface WComplexToWorkoutRepository extends ArangoRepository<WComplexToWorkout, String> {

    WComplexToWorkout findByWorkoutId(String workoutId);

    List<WComplexToWorkout> removeAllByWorkoutId(String Id);

    List<WComplexToWorkout> removeAllByWorkoutComplexId(String id);

    WComplexToWorkout removeByWorkoutIdAndWorkoutComplexId(String workoutComplexId, String workoutId);

}
