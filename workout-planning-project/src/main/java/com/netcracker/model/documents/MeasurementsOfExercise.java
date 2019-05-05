package com.netcracker.model.documents;

import com.arangodb.springframework.annotation.Document;
import com.arangodb.springframework.annotation.Field;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.List;

import static com.netcracker.model.CollectionsNames.MEASURES_OF_EXERCISE;

@Data
@Document(MEASURES_OF_EXERCISE)
public class MeasuresOfExercise {

    @Id
    private String id;

    @Field("list-measures")
    List<Measure> listSet;
}
