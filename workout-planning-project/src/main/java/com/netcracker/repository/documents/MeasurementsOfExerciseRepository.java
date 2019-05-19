package com.netcracker.repository.documents;

import com.arangodb.springframework.repository.ArangoRepository;
import com.netcracker.model.documents.MeasurementsOfExercise;

import java.util.List;


public interface MeasurementsOfExerciseRepository extends ArangoRepository<MeasurementsOfExercise, String> {
    List<MeasurementsOfExercise> removeById(String id);
}
