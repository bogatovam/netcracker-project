package com.netcracker.repository.documents;

import com.arangodb.springframework.repository.ArangoRepository;
import com.netcracker.model.documents.MeasuresOfExercise;

public interface MeasureOfExerciseRepository extends ArangoRepository<MeasuresOfExercise, String> {
}
