package com.netcracker.services.api;

import com.netcracker.model.documents.Exercise;
import com.netcracker.model.documents.WorkoutComplex;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface DemoService {
    List<WorkoutComplex> getAllWorkoutComplexByUser(String userId);

    WorkoutComplex createWorkoutComplex(String userId, WorkoutComplex workoutComplex);

    List<Exercise> getAllExercise();

    Exercise getExercise(@PathVariable String exerciseId);

}
