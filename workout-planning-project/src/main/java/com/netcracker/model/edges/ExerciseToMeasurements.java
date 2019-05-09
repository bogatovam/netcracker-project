package com.netcracker.model.edges;

import com.arangodb.springframework.annotation.Edge;
import com.arangodb.springframework.annotation.From;
import com.arangodb.springframework.annotation.To;
import com.netcracker.model.documents.Exercise;
import com.netcracker.model.documents.MeasurementsOfExercise;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springframework.data.annotation.Id;

import static com.netcracker.model.CollectionsNames.EXERCISE_TO_MEASUREMENTS;

@Data
@Edge(EXERCISE_TO_MEASUREMENTS)
@ApiModel(description = "A helper class that describes the relationship between exercise and measures")
public class ExerciseToMeasurements {
    @Id
    private String id;

    @From
    private Exercise exercise;

    @To
    private MeasurementsOfExercise measures;
}
