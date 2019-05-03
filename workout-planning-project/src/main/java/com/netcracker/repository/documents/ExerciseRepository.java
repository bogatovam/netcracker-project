package com.netcracker.repository.documents;

import com.arangodb.springframework.repository.ArangoRepository;
import com.netcracker.model.documents.Exercise;

public interface ExerciseRepository extends ArangoRepository<Exercise, String> {
}
