package com.netcracker.model.documents;

import com.arangodb.springframework.annotation.Document;
import com.arangodb.springframework.annotation.Relations;
import com.netcracker.model.edges.WorkoutToDate;
import com.netcracker.model.edges.WorkoutToExercise;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.List;

import static com.netcracker.model.CollectionsNames.WORKOUT;

@Data
@Document(WORKOUT)
@ApiModel(value = "Workout")
public class Workout {
    @Id
    @ApiModelProperty(hidden = true)
    private String id;

    private String name;
    private String description;

    @Relations(edges = WorkoutToDate.class, direction = Relations.Direction.OUTBOUND)
    private List<ScheduledWorkout> scheduledWorkouts;

    @Relations(edges = WorkoutToExercise.class, direction = Relations.Direction.OUTBOUND)
    private List<Exercise> exercises;
}