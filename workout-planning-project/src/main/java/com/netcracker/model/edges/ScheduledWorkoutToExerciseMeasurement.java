package com.netcracker.model.edges;

import com.arangodb.springframework.annotation.Edge;
import com.arangodb.springframework.annotation.From;
import com.arangodb.springframework.annotation.To;
import com.netcracker.model.documents.ScheduledWorkout;
import lombok.Data;
import org.springframework.data.annotation.Id;

import static com.netcracker.model.CollectionsNames.EXERCISES_MEASUREMENT_IN_SCHEDULED_WORKOUT;

@Data
@Edge(EXERCISES_MEASUREMENT_IN_SCHEDULED_WORKOUT)
public class ScheduledWorkoutToExerciseMeasurement {
    @Id
    private String id;

    @From
    private ScheduledWorkout scheduledWorkout;

    @To
    private ExerciseToMeasurements exerciseToMeasurements;
}
