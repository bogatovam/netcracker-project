package com.netcracker.model.documents;

import com.arangodb.springframework.annotation.Document;
import com.arangodb.springframework.annotation.Relations;
import com.netcracker.model.edges.WComplexToWorkout;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.List;

import static com.netcracker.model.CollectionsNames.WORKOUT_COMPLEX;

@Data
@Document(WORKOUT_COMPLEX)
public class WorkoutComplex {
    @Id
    private String id;

    private String name;
    private String description;

    @Relations(edges = WComplexToWorkout.class, direction = Relations.Direction.OUTBOUND)
    private List<Workout> workouts;
}