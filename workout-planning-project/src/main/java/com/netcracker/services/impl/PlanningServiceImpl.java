package com.netcracker.services.impl;

import com.google.common.collect.Lists;
import com.netcracker.model.documents.*;
import com.netcracker.model.edges.*;
import com.netcracker.repository.documents.*;
import com.netcracker.repository.edges.*;
import com.netcracker.services.api.PlanningService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.rmi.NoSuchObjectException;
import java.util.*;

@Service
@RequiredArgsConstructor
public class PlanningServiceImpl implements PlanningService {

    private final UserRepository userRepository;
    private final ExerciseRepository exerciseRepository;
    private final WorkoutRepository workoutRepository;
    private final ScheduledWorkoutRepository scheduledWorkoutRepository;
    private final MeasurementsOfExerciseRepository measurementsOfExerciseRepository;
    private final WorkoutComplexRepository workoutComplexRepository;

    private final WComplexToWorkoutRepository wComplexToWorkoutRepository;
    private final WorkoutToExerciseRepository workoutToExerciseRepository;
    private final WorkoutToDateRepository workoutToDateRepository;
    private final ExerciseToMeasurementsRepository exerciseToMeasurementsRepository;
    private final UserToWComplexRepository userToWComplexRepository;
    private final ScheduledWorkoutToExerciseMeasurementRepository sWToExMeasurementRepository;

    @Override
    public Exercise createExercise(Exercise exercise) {
        Exercise newExercise = exerciseRepository.save(exercise);
        // #TODO validation by name exercise is probably necessary
        return newExercise;
    }

    @Override
    public Workout createWorkout(Workout workout) {
        Workout newWorkout;
        WorkoutComplex sourceWorkoutComplex;
        WComplexToWorkout wComplexToWorkout;

        // #TODO need to add own exceptions
        sourceWorkoutComplex = workoutComplexRepository.findById(WorkoutComplex.DEFAULT_WORKOUT_COMPLEX_ID)
                .orElseThrow(() -> new NoSuchElementException("DEFAULT_WORKOUT_COMPLEX_ID has bad value"));

        newWorkout = workoutRepository.save(workout);
        wComplexToWorkout = WComplexToWorkout.builder()
                .workoutComplex(sourceWorkoutComplex)
                .workout(newWorkout).build();
        wComplexToWorkoutRepository.save(wComplexToWorkout);
        return newWorkout;
    }

    @Override
    public Workout createWorkout(String workoutComplexId, Workout workout) {
        Workout newWorkout;
        WorkoutComplex sourceWorkoutComplex;
        WComplexToWorkout wComplexToWorkout;

        // #TODO need to add own exceptions
        sourceWorkoutComplex = workoutComplexRepository.findById(workoutComplexId)
                .orElseThrow(() -> new NoSuchElementException(
                        "Workout complex id" + workoutComplexId + " has bad value"));

        newWorkout = workoutRepository.save(workout);
        wComplexToWorkout = WComplexToWorkout.builder().
                workoutComplex(sourceWorkoutComplex).
                workout(newWorkout).build();
        wComplexToWorkoutRepository.save(wComplexToWorkout);
        return newWorkout;
    }

    @Override
    public ScheduledWorkout createScheduledWorkout(String workoutId, ScheduledWorkout scheduledWorkout) {
        Workout sourceWorkout;
        WorkoutToDate workoutToDate;
        ScheduledWorkout newScheduledWorkout;

        sourceWorkout = workoutRepository.findById(workoutId)
                .orElseThrow(() -> new NoSuchElementException(
                        "Workout id" + workoutId + " has bad value"));

        newScheduledWorkout = scheduledWorkoutRepository.save(scheduledWorkout);

        workoutToDate = WorkoutToDate.builder()
                .scheduledWorkout(newScheduledWorkout)
                .workout(sourceWorkout).build();

        workoutToDateRepository.save(workoutToDate);
        return newScheduledWorkout;
    }

    @Override
    public WorkoutComplex createWorkoutComplex(WorkoutComplex workoutComplex) {
        // #TODO change when spring security is added!
        User sourceUser;
        UserToWComplex userToWComplex;
        WorkoutComplex newWorkoutComplex;

        sourceUser = userRepository.findById(User.DEFAULT_USER_ID)
                .orElseThrow(() -> new NoSuchElementException("DEFAULT_USER_ID has bad value"));
        newWorkoutComplex = workoutComplexRepository.save(workoutComplex);
        userToWComplex = UserToWComplex.builder()
                .user(sourceUser)
                .wcomplex(newWorkoutComplex).build();
        userToWComplexRepository.save(userToWComplex);
        return newWorkoutComplex;
    }

    @Override
    public Exercise deleteExercise(String exerciseId) {
        Exercise delExercise = exerciseRepository.findById(exerciseId).
                orElseThrow(() -> new NoSuchElementException(
                        "Exercise id " + exerciseId + " has bad value"));
        workoutToExerciseRepository.removeAllByExerciseId(delExercise.getId());

        for (MeasurementsOfExercise measurement : delExercise.getMeasurements()) {
            measurementsOfExerciseRepository.removeById(measurement.getId());
        }
        exerciseToMeasurementsRepository.removeAllByExerciseId(delExercise.getId());

        exerciseRepository.removeById(exerciseId);
        return delExercise;
    }

    @Override
    public Workout deleteWorkout(String workoutId) {
        Workout workout = workoutRepository.findById(workoutId)
                .orElseThrow(() -> new NoSuchElementException(
                        "Workout id " + workoutId + " has bad value"));
        wComplexToWorkoutRepository.removeAllByWorkoutId(workoutId);
        workoutToExerciseRepository.removeAllByWorkoutId(workoutId);
        workoutToDateRepository.removeAllByWorkoutId(workoutId);
        workoutRepository.delete(workout);
        return workout;
    }

    @Override
    public WorkoutComplex deleteWorkoutComplex(String workoutComplexId) {
        WorkoutComplex workoutComplex = workoutComplexRepository.findById(workoutComplexId)
                .orElseThrow(() -> new NoSuchElementException(
                        "Workout Complex id " + workoutComplexId + " has bad value"));

        userToWComplexRepository.removeAllByWcomplexId(workoutComplexId);
        wComplexToWorkoutRepository.removeAllByWcomplexId(workoutComplexId);

        workoutComplexRepository.removeById(workoutComplexId);
        return workoutComplex;
    }

    @Override
    public ScheduledWorkout deleteScheduledWorkout(String workoutId, String scheduledWorkoutId) {
        ScheduledWorkout scheduledWorkout = scheduledWorkoutRepository.findById(scheduledWorkoutId)
                .orElseThrow(() -> new NoSuchElementException(
                "Scheduled Workout id " + scheduledWorkoutId + " has bad value"));
        workoutToDateRepository.removeAllByScheduledWorkoutId(scheduledWorkoutId);

        for (ExerciseToMeasurements etm : scheduledWorkout.getExerciseToMeasurements()) {
            measurementsOfExerciseRepository.removeById(etm.getMeasures().getId());
            exerciseToMeasurementsRepository.removeById(etm.getId());
        }

        sWToExMeasurementRepository.removeAllByScheduledWorkoutId(scheduledWorkoutId);
        scheduledWorkoutRepository.removeById(scheduledWorkoutId);
        return scheduledWorkout;
    }

    @Override
    public String setExerciseName(String exerciseId, String name) {
        Exercise exercise = exerciseRepository.findById(exerciseId).
                orElseThrow(() -> new NoSuchElementException(
                        "Exercise id " + exerciseId + " has bad value"));
        exercise.setName(name);
        return exercise.getName();
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
