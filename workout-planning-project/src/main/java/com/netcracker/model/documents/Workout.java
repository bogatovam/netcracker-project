package com.netcracker.model.documents;

import com.arangodb.springframework.annotation.Document;
import com.arangodb.springframework.annotation.Relations;
import com.netcracker.model.edges.WorkoutToDate;
import com.netcracker.model.edges.WorkoutToExercise;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.List;

import static com.netcracker.model.CollectionsNames.WORKOUT;

@Data
@Document(WORKOUT)
public class Workout {
    @Id
    private String id;

    private String name;

    @Relations(edges = WorkoutToDate.class, direction = Relations.Direction.OUTBOUND)
    private List<ScheduledWorkout> scheduledWorkouts;

    @Relations(edges = WorkoutToExercise.class, direction = Relations.Direction.OUTBOUND)
    private List<Exercise> exercises;
}