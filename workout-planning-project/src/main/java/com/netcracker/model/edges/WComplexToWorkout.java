package com.netcracker.model.edges;
import com.arangodb.springframework.annotation.Edge;
import com.arangodb.springframework.annotation.From;
import com.arangodb.springframework.annotation.To;
import com.netcracker.model.documents.Workout;
import com.netcracker.model.documents.WorkoutComplex;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;

import static com.netcracker.model.CollectionsNames.WORKOUT_COMPLEX_TO_WORKOUT;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@Edge(WORKOUT_COMPLEX_TO_WORKOUT)
@ApiModel(description = "A helper class that describes the relationship between workout and workout complex")
public class WComplexToWorkout {
    @Id
    @ApiModelProperty(hidden = true)
    private String id;

    @From
    private WorkoutComplex workoutComplex;

    @To
    private Workout workout;
}
