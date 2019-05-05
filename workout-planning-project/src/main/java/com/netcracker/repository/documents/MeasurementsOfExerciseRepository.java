package com.netcracker.repository.documents;

import com.arangodb.springframework.repository.ArangoRepository;
import com.netcracker.model.documents.MeasurementsOfExercise;

public interface MeasureOfExerciseRepository extends ArangoRepository<MeasurementsOfExercise, String> {
}
