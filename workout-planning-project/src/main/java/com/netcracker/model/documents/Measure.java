package com.netcracker.model.documents;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.Map;

import static com.netcracker.model.CollectionsNames.USER;

@Data
public class Measure {

    @Id
    private String id;

    private Map<String, Float> measure;
    private String comment;
}
