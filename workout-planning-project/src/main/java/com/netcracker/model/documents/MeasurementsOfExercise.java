package com.netcracker.model.documents;

import com.arangodb.springframework.annotation.Document;
import com.arangodb.springframework.annotation.Field;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.List;

import static com.netcracker.model.CollectionsNames.MEASUREMENTS_OF_EXERCISE;

@Data
@Document(MEASUREMENTS_OF_EXERCISE)
public class MeasurementsOfExercise {

    @Id
    private String id;

    @Field("list-measurements")
    List<Measurement> listSet;
}
