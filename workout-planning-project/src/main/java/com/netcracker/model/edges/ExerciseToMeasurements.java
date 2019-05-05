package com.netcracker.model.edges;

import com.arangodb.springframework.annotation.Edge;
import com.arangodb.springframework.annotation.From;
import com.arangodb.springframework.annotation.To;
import com.netcracker.model.documents.MeasurementsOfExercise;
import lombok.Data;
import org.springframework.data.annotation.Id;

import static com.netcracker.model.CollectionsNames.EXERCISE_TO_MEASURES;

@Data
@Edge(EXERCISE_TO_MEASURES)
public class ExerciseToMeasures {
    @Id
    private String id;

    @From
    private WorkoutToExercise sourceConnection;

    @To
    private MeasurementsOfExercise measures;
}
