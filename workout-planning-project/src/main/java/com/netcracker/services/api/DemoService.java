package com.netcracker.services.api;

import com.netcracker.model.documents.Exercise;
import com.netcracker.model.documents.Measurement;
import com.netcracker.model.documents.MeasurementsOfExercise;
import com.netcracker.model.documents.WorkoutComplex;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface DemoService {
    List<WorkoutComplex> getAllWorkoutComplexByUser(String userId);

    WorkoutComplex createWorkoutComplex(String userId, WorkoutComplex workoutComplex);

    List<Exercise> getAllExercise();

    Exercise getExercise(@PathVariable String exerciseId);

    Map<String, String> getAllNamesOfExercises();

    String getNameOfExercise(String id);

    String getDescriptionOfExercise(String id);

    List<String> getMeasuresOfExercise(String id);

    List<MeasurementsOfExercise> getMeasurementOfExercise(String id);

    Map<Date, MeasurementsOfExercise> getMeasurementOfExerciseWithDate(String id);

    MeasurementsOfExercise getLastMeasurementOfExercise(String id);

    Measurement addMeasurementOfExercise(String id, String mid, Measurement measurement);

    MeasurementsOfExercise addMeasurementsOfExercise(String id, MeasurementsOfExercise measurement);

    String setExerciseName(String id, String name);

    MeasurementsOfExercise delMeasurementsOfExercise(String id, String mid);

    Measurement delMeasurementOfExercise(String id, String mid, String num);

    Exercise deleteExercise(String id);

    Exercise createExercise(Exercise exercise);
}
