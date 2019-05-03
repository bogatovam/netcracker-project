package com.netcracker.model.edges;
import com.arangodb.springframework.annotation.Edge;
import com.arangodb.springframework.annotation.From;
import com.arangodb.springframework.annotation.To;
import com.netcracker.model.documents.Workout;
import com.netcracker.model.documents.WorkoutComplex;
import lombok.Data;
import org.springframework.data.annotation.Id;

import static com.netcracker.model.CollectionsNames.WORKOUT_COMPLEX_TO_WORKOUT;

@Data
@Edge(WORKOUT_COMPLEX_TO_WORKOUT)
public class WComplexToWorkout {
    @Id
    private String id;

    @From
    private WorkoutComplex workoutComplex;

    @To
    private Workout workout;
}
