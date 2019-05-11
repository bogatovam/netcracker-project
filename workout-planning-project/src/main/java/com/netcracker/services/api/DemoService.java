package com.netcracker.services.api;

import com.netcracker.model.documents.*;
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

    WorkoutComplex createWorkoutComplex(WorkoutComplex workoutComplex);

    List<WorkoutComplex> getAllWorkoutComplex();

    Map<String, String> getAllNamesWorkoutComplex();

    String getNameWorkoutComplex(String id);

    List<Workout> getWorkouts(String id);

    Map<String, String> getWorkoutsNames(String id);

    Workout addWorkout(String id, Workout workout);

    Workout delWorkout(String id, String wid);

    Workout setNameWorkout(String id, String wid, String name);

    WorkoutComplex setNameWorkoutComplex(String id, String name);

    WorkoutComplex deleteWorkoutComplex(String id);

    Exercise createWorkout(Workout workout);

    List<Workout> getWorkoutById(String id);

    String getWorkoutName(String id);

    WorkoutComplex getSourceWorkoutComplex(String id);

    List<Exercise> getExercises(String id);

    Map<String, String> getExercisesNames(String id);

    List<ScheduledWorkout> getScheduledWorkouts(String id);

    boolean addListExercises(String id, List<Exercise> exerciseList);

    boolean delListExercises(String id, List<String> exerciseIdList);

    String setWorkoutName(String id, String name);

    String deleteWorkout(String id);

    ScheduledWorkout createScheduledWorkout(String id, ScheduledWorkout scheduledWorkout);

    ScheduledWorkout getScheduledWorkout(String id);

    Workout getSourceScheduledWorkout(String id);

    List<ScheduledWorkout> getAllScheduledWorkouts();

    String getNameSourceWorkout(String id);

    Date getDateScheduledWorkout(String id);

    String getStatusScheduledWorkout(String id);

    List<Exercise> getAllCurrentExercises(String id);

    Map<Exercise, MeasurementsOfExercise> getExercisesMeasurements(String id);

    Map<Exercise, MeasurementsOfExercise> getExerciseMeasurement(String id);

    Map<String, String> getScheduledWorkoutInformation(String id);

    Map<String, String> setStatusScheduledWorkout(String id, String status);

    ScheduledWorkout deleteScheduledWorkout(String id, String swid);

    MeasurementsOfExercise addExerciseMeasurement(String id, String eid, Measurement measurement);

    MeasurementsOfExercise updateExerciseMeasurement(String id, String eid, String num, Measurement measurement);

    MeasurementsOfExercise delExerciseMeasurement(String id, String eid, Measurement measurement);
}
