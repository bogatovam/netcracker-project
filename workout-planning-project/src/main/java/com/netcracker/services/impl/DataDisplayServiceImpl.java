package com.netcracker.services.impl;

import com.netcracker.model.documents.*;
import com.netcracker.services.api.DataDisplayService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class DataDisplayServiceImpl implements DataDisplayService {

    @Override
    public List<Exercise> getAllExercise() {
        return null;
    }

    @Override
    public List<WorkoutComplex> getAllWorkoutComplex() {
        return null;
    }

    @Override
    public List<ScheduledWorkout> getAllScheduledWorkouts() {
        return null;
    }

    @Override
    public Exercise getExercise(String exerciseId) {
        return null;
    }

    @Override
    public Workout getWorkoutById(String workoutId) {
        return null;
    }

    @Override
    public WorkoutComplex getWorkoutComplexById(String workoutComplexId) {
        return null;
    }

    @Override
    public ScheduledWorkout getScheduledWorkout(String scheduledWorkoutId) {
        return null;
    }

    @Override
    public Map<String, String> getAllExercisesNames() {
        return null;
    }

    @Override
    public Map<String, String> getWorkoutComplexesNames() {
        return null;
    }

    @Override
    public Map<String, String> getWorkoutsNames(String workoutComplexId) {
        return null;
    }

    @Override
    public Map<String, String> getWorkoutsExercisesNames(String workoutId) {
        return null;
    }

    @Override
    public Map<String, String> getScheduledWorkoutsExercisesNames(String scheduledWorkoutId) {
        return null;
    }

    @Override
    public String getNameOfExercise(String exerciseId) {
        return null;
    }

    @Override
    public String getDescriptionOfExercise(String exerciseId) {
        return null;
    }

    @Override
    public List<String> getMeasuresOfExercise(String exerciseId) {
        return null;
    }

    @Override
    public List<MeasurementsOfExercise> getMeasurementsOfExercise(String exerciseId) {
        return null;
    }

    @Override
    public Map<Date, MeasurementsOfExercise> getMeasurementsOfExerciseWithDate(String exerciseId) {
        return null;
    }

    @Override
    public MeasurementsOfExercise getLastMeasurementOfExercise(String exerciseId) {
        return null;
    }

    @Override
    public String getWorkoutName(String workoutId) {
        return null;
    }

    @Override
    public List<Exercise> getExercises(String workoutId) {
        return null;
    }

    @Override
    public Map<String, String> getAllNamesOfExercises() {
        return null;
    }

    @Override
    public List<ScheduledWorkout> getScheduledWorkouts(String workoutId) {
        return null;
    }

    @Override
    public List<ScheduledWorkout> getScheduledWorkoutsByStatus(String workoutId, String status) {
        return null;
    }

    @Override
    public Workout getSourceWorkout(String scheduledWorkoutId) {
        return null;
    }

    @Override
    public WorkoutComplex getSourceWorkoutComplex(String workoutId) {
        return null;
    }

    @Override
    public Date getDateScheduledWorkout(String scheduledWorkoutId) {
        return null;
    }

    @Override
    public String getStatusScheduledWorkout(String scheduledWorkoutId) {
        return null;
    }

    @Override
    public Map<String, String> getScheduledWorkoutInformation(String id) {
        return null;
    }

    @Override
    public List<Exercise> getAllCurrentExercises(String scheduledWorkoutId) {
        return null;
    }

    @Override
    public Map<Exercise, MeasurementsOfExercise> getExercisesMeasurements(String scheduledWorkoutId) {
        return null;
    }

    @Override
    public String getNameWorkoutComplex(String workoutComplexId) {
        return null;
    }

    @Override
    public List<Workout> getWorkouts(String workoutComplexId) {
        return null;
    }
}
