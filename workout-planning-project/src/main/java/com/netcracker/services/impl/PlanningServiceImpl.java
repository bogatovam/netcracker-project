package com.netcracker.services.impl;

import com.netcracker.model.documents.*;
import com.netcracker.services.api.PlanningService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class PlanningServiceImpl implements PlanningService {

    @Override
    public Exercise createExercise(Exercise exercise) {
        return null;
    }

    @Override
    public Workout createWorkout(Workout workout) {
        return null;
    }

    @Override
    public ScheduledWorkout createScheduledWorkout(String id, ScheduledWorkout scheduledWorkout) {
        return null;
    }

    @Override
    public WorkoutComplex createWorkoutComplex(WorkoutComplex workoutComplex) {
        return null;
    }

    @Override
    public Exercise deleteExercise(String exerciseId) {
        return null;
    }

    @Override
    public Workout deleteWorkout(String workoutId) {
        return null;
    }

    @Override
    public WorkoutComplex deleteWorkoutComplex(String workoutComplexId) {
        return null;
    }

    @Override
    public ScheduledWorkout deleteScheduledWorkout(String workoutId, String scheduledWorkoutId) {
        return null;
    }

    @Override
    public String setExerciseName(String exerciseId, String name) {
        return null;
    }

    @Override
    public Boolean addListExercises(String workoutId, List<Exercise> exerciseList) {
        return null;
    }

    @Override
    public Boolean delListExercises(String workoutId, List<String> exerciseIdList) {
        return null;
    }

    @Override
    public Boolean addExerciseToScheduledWorkout(String scheduledWorkoutId, Exercise exercise) {
        return null;
    }

    @Override
    public MeasurementsOfExercise addExerciseMeasurement(String scheduledWorkoutId, String exerciseId, Measurement measurement) {
        return null;
    }

    @Override
    public MeasurementsOfExercise updateExerciseMeasurement(String scheduledWorkoutId, String exerciseId, String numberMeasurement, Measurement measurement) {
        return null;
    }

    @Override
    public MeasurementsOfExercise delExerciseMeasurement(String scheduledWorkoutId, String exerciseId, Measurement measurement) {
        return null;
    }

    @Override
    public Measurement addMeasurementOfExercise(String exerciseId, String measurementOfExerciseId, Measurement measurement) {
        return null;
    }

    @Override
    public Measurement delMeasurementOfExercise(String exerciseId, String measurementOfExerciseId, String number) {
        return null;
    }

    @Override
    public Boolean deleteExerciseFromScheduledWorkout(String scheduledWorkoutId, Exercise exercise) {
        return null;
    }

    @Override
    public Workout addWorkout(String workoutComplexId, Workout workout) {
        return null;
    }

    @Override
    public Workout delWorkout(String workoutComplexId, String workoutId) {
        return null;
    }

    @Override
    public Workout setWorkoutToDate(String workoutId, Date date) {
        return null;
    }

    @Override
    public Workout setWorkoutComplexToWorkout(String workoutComplexId, String workoutId) {
        return null;
    }

    @Override
    public Workout setNameWorkout(String workoutId, String name) {
        return null;
    }

    @Override
    public Map<String, String> setDateScheduledWorkout(String scheduledWorkoutId, Date date) {
        return null;
    }

    @Override
    public Map<String, String> setStatusScheduledWorkout(String scheduledWorkoutId, String status) {
        return null;
    }

    @Override
    public WorkoutComplex setNameWorkoutComplex(String workoutComplexId, String name) {
        return null;
    }

    @Override
    public MeasurementsOfExercise addExerciseMeasurement(String id, MeasurementsOfExercise measurement) {
        return null;
    }

    @Override
    public MeasurementsOfExercise delExerciseMeasurement(String id, String mid) {
        return null;
    }
}
