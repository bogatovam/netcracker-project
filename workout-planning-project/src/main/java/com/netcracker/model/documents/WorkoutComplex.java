package com.netcracker.model.documents;

import com.arangodb.springframework.annotation.Document;
import com.arangodb.springframework.annotation.Relations;
import com.netcracker.model.edges.WComplexToWorkout;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.List;

import static com.netcracker.model.CollectionsNames.WORKOUT_COMPLEX;

@Data
@Document(WORKOUT_COMPLEX)
@ApiModel(value = "Workout Complex")
public class WorkoutComplex {
    @Id
    @ApiModelProperty(hidden = true)
    private String id;

    private String name;
    private String description;

    @Relations(edges = WComplexToWorkout.class, direction = Relations.Direction.OUTBOUND)
    private List<Workout> workouts;
}