package com.netcracker.repository.documents;

import com.arangodb.springframework.repository.ArangoRepository;
import com.netcracker.model.documents.WorkoutComplex;

public interface WorkoutComplexRepository extends ArangoRepository<WorkoutComplex, String> {
}
