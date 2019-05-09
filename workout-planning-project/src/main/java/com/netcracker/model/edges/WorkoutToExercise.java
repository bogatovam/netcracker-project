package com.netcracker.model.edges;
import com.arangodb.springframework.annotation.Edge;
import com.arangodb.springframework.annotation.From;
import com.arangodb.springframework.annotation.To;
import com.netcracker.model.documents.*;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springframework.data.annotation.Id;

import static com.netcracker.model.CollectionsNames.WORKOUT_TO_EXERCISE;

@Data
@Edge(WORKOUT_TO_EXERCISE)
@ApiModel(description = "A helper class that describes the relationship between workout and exercise")
public class WorkoutToExercise {
    @Id
    private String id;

    @From
    private Workout workout;

    @To
    private Exercise exercise;

}
