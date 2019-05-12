package com.netcracker.model.documents;

import com.arangodb.springframework.annotation.Document;
import com.arangodb.springframework.annotation.Field;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.List;

import static com.netcracker.model.CollectionsNames.MEASUREMENTS_OF_EXERCISE;

@Data
@Builder
@Document(MEASUREMENTS_OF_EXERCISE)
@ApiModel(value = "Describes measurements of exercise")
public class MeasurementsOfExercise {

    @Id
    @ApiModelProperty(hidden = true)
    private String id;

    @Field("list-measurements")
    List<Measurement> listSet;
}
