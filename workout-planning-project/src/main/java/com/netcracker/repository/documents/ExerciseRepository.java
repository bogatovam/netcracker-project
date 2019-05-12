package com.netcracker.repository.documents;

import com.arangodb.ArangoCursor;
import com.arangodb.springframework.annotation.Query;
import com.arangodb.springframework.repository.ArangoRepository;
import com.netcracker.model.documents.Exercise;
import com.netcracker.model.documents.MeasurementsOfExercise;
import com.netcracker.model.documents.Workout;
import com.netcracker.model.documents.WorkoutComplex;
import org.springframework.data.repository.query.Param;

import java.util.List;

import static com.netcracker.model.CollectionsNames.WORKOUT_TO_EXERCISE;

public interface ExerciseRepository extends ArangoRepository<Exercise, String> {
    List<Exercise> removeById(String id);
}
