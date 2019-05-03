package com.netcracker.model.edges;
import com.arangodb.springframework.annotation.Edge;
import com.arangodb.springframework.annotation.From;
import com.arangodb.springframework.annotation.To;
import com.netcracker.model.documents.ScheduledWorkout;
import com.netcracker.model.documents.User;
import com.netcracker.model.documents.Workout;
import com.netcracker.model.documents.WorkoutComplex;
import lombok.Data;
import org.springframework.data.annotation.Id;

import static com.netcracker.model.CollectionsNames.WORKOUT_TO_DATE;

@Data
@Edge(WORKOUT_TO_DATE)
public class WorkoutToDate {
    @Id
    private String id;

    @From
    private Workout workout;

    @To
    private ScheduledWorkout scheduledWorkout;
}
