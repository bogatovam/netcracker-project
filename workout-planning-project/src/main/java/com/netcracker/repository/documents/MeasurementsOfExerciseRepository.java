package com.netcracker.repository.documents;

import com.arangodb.springframework.repository.ArangoRepository;
import com.netcracker.model.documents.MeasurementsOfExercise;

public interface MeasurementsOfExerciseRepository extends ArangoRepository<MeasurementsOfExercise, String> {
}
