package com.netcracker.model.documents;

import com.arangodb.springframework.annotation.Document;
import com.arangodb.springframework.annotation.Field;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.List;

import static com.netcracker.model.CollectionsNames.EXERCISE;

@Data
@Document(EXERCISE)
public class Exercise {

    @Id
    private String id;

    @Field("measure-list")
    private List<String> measureList;

    private String name;
    private String description;
}
