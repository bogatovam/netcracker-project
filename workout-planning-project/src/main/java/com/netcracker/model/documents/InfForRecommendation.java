package com.netcracker.model.documents;

import lombok.Data;

import java.util.Map;

@Data
public class InfForRecommendation {
    private Double complexity;
    private Map<String, Double> muscleLoad;
}
