package com.netcracker.repository.documents;

import com.arangodb.springframework.repository.ArangoRepository;
import com.netcracker.model.documents.Exercise;

import java.util.List;

public interface ExerciseRepository extends ArangoRepository<Exercise, String> {
    List<Exercise> removeById(String id);
}
