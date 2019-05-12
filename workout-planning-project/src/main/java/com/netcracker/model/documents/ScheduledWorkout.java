package com.netcracker.model.documents;

import com.arangodb.springframework.annotation.Document;
import com.arangodb.springframework.annotation.Field;
import com.arangodb.springframework.annotation.Relations;
import com.netcracker.model.edges.ExerciseToMeasurements;
import com.netcracker.model.edges.ScheduledWorkoutToExerciseMeasurement;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.Date;
import java.util.List;

import static com.netcracker.model.CollectionsNames.SCHEDULED_WORKOUT;

@Data
@Builder
@Document(SCHEDULED_WORKOUT)
@ApiModel(value = "Workout that was scheduled for a certain date")
public class ScheduledWorkout {

    @Id
    @ApiModelProperty(hidden = true)
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

    private void calculateDuration(Date date){
        duration = (double) (dateWorkout.getSeconds() - date.getSeconds());
    }
    private void calculateCalories(){
       calories = 0.0;
    }
    private void calculateWorkload(){
        aerobicWorkload = 0.0;
        powerWorkload = 0.0;
    }

    public void calculateInformation(Date date){
        calculateDuration(date);
        calculateCalories();
        calculateWorkload();
    }
}
