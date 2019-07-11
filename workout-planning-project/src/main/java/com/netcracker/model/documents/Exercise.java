package com.netcracker.model.documents;

import com.arangodb.springframework.annotation.Document;
import com.arangodb.springframework.annotation.Field;
import com.arangodb.springframework.annotation.Relations;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.List;

import static com.netcracker.model.CollectionsNames.EXERCISE;

@Data
@Document(EXERCISE)
@ApiModel(value = "Describes an exercise")
public class Exercise {
    public static final String[] muscleLoad = {"hips", "biceps", "abs", "chest", "shoulders", "back"};

    @Id
    @ApiModelProperty(hidden = true)
    private String id;

    @Field("measure-list")
    private List<String> measureList;

    private String name;
    private Description description;
    private InfForRecommendation infForRecommendation;

    @Data
    public static class Description {
        String technique;
        String features;
    }

}
