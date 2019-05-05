package com.netcracker.model.documents;

import com.arangodb.springframework.annotation.Document;
import com.arangodb.springframework.annotation.Field;
import com.arangodb.springframework.annotation.Relations;
import com.netcracker.model.edges.ExerciseToMeasurements;
import com.netcracker.model.edges.ScheduledWorkoutToExerciseMeasurement;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import static com.netcracker.model.CollectionsNames.SCHEDULED_WORKOUT;

@Data
@Document(SCHEDULED_WORKOUT)
public class ScheduledWorkout {

    @Id
    private String id;

    @Field("date-workout")
    private Date dateWorkout;

    @Field("power-workload")
    private Double powerWorkload;

    @Field("aerobic-workload")
    private Double aerobicWorkload;

    private Status status;
    private Double duration;
    private Double calories;

    @Relations(edges = ScheduledWorkoutToExerciseMeasurement.class, direction = Relations.Direction.OUTBOUND)
    private List<ExerciseToMeasurements> exerciseToMeasurements;

    public static enum Status {
        DONE,
        SCHEDULED
    }
}
