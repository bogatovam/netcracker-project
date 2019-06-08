package com.netcracker.services.impl;

import com.google.common.collect.Lists;
import com.netcracker.model.documents.*;
import com.netcracker.model.edges.ExerciseToMeasurements;
import com.netcracker.repository.documents.*;
import com.netcracker.repository.edges.*;
import com.netcracker.services.api.AuthenticationService;
import com.netcracker.services.api.DataDisplayService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Data
@Service
@RequiredArgsConstructor
public class DataDisplayServiceImpl implements DataDisplayService {

    private final UserRepository userRepository;
    private final ExerciseRepository exerciseRepository;
    private final WorkoutRepository workoutRepository;
    private final ScheduledWorkoutRepository scheduledWorkoutRepository;
    private final MeasurementsOfExerciseRepository measurementsOfExerciseRepository;
    private final WorkoutComplexRepository workoutComplexRepository;

    private final AuthenticationService authenticationService;

    private final WComplexToWorkoutRepository wComplexToWorkoutRepository;
    private final WorkoutToExerciseRepository workoutToExerciseRepository;
    private final WorkoutToDateRepository workoutToDateRepository;
    private final ExerciseToMeasurementsRepository exerciseToMeasurementsRepository;
    private final UserToWComplexRepository userToWComplexRepository;
    private final ScheduledWorkoutToExerciseMeasurementRepository sWToExMeasurementRepository;


    @Override
    public List<Exercise> getAllExercise() {
        return Lists.newArrayList(exerciseRepository.findAll());
    }

    @Override
    public List<WorkoutComplex> getAllWorkoutComplex(String userId) {
        return Lists.newArrayList(workoutComplexRepository.findWorkoutComplexesByUserId("user/" +     userId).asListRemaining());
    }

    @Override
    public List<ScheduledWorkout> getAllScheduledWorkouts(String userId) {
        List<ScheduledWorkout> result = new ArrayList<>();

        // #TODO Add method getFullId()
        List<WorkoutComplex> workoutComplexList =
                workoutComplexRepository.findWorkoutComplexesByUserId("user/" + userId).asListRemaining();

        workoutComplexList.forEach((workoutComplex) ->
                workoutComplex.getWorkouts().forEach((workout) ->
                        result.addAll(workout.getScheduledWorkouts())
                ));
        return result;
    }

    @Override
    public Exercise getExercise(String exerciseId) {
        return exerciseRepository.findById(exerciseId)
                .orElseThrow(() -> new NoSuchElementException("Exercise id " + exerciseId + " has bad value"));
    }

    @Override
    public Workout getWorkoutById(String workoutId, String userId) {
        // #TODO Change when spring security is added!
        Workout workout = workoutRepository.findById(workoutId)
                .orElseThrow(() -> new NoSuchElementException("Workout id " + workoutId + " has bad value"));
        if (!authenticationService.checkAccessRightsToWorkout(workoutId, userId))
            return null;
        return workout;
    }

    @Override
    public WorkoutComplex getWorkoutComplexById(String workoutComplexId, String userId) {
        WorkoutComplex workoutComplex = workoutComplexRepository.findById(workoutComplexId)
                .orElseThrow(() -> new NoSuchElementException("Workout Complex id " + workoutComplexId + " has bad value"));
        if (!authenticationService.checkAccessRightsToWorkoutComplex(workoutComplexId, userId))
            return null;
        return workoutComplex;
    }

    @Override
    public ScheduledWorkout getScheduledWorkout(String scheduledWorkoutId, String userId) {
        ScheduledWorkout scheduledWorkout = scheduledWorkoutRepository.findById(scheduledWorkoutId)
                .orElseThrow(() -> new NoSuchElementException("Scheduled Workout id " + scheduledWorkoutId + " has bad value"));
        if (!authenticationService.checkAccessRightsToScheduledWorkout(scheduledWorkoutId, userId))
            return null;
        return scheduledWorkout;
    }

    @Override
    public Map<String, String> getAllExercisesNames() {
        Map<String, String> result = new HashMap<String, String>();
        getAllExercise().forEach((exercise) -> result.put(exercise.getId(), exercise.getName()));
        return result;
    }

    @Override
    public Map<String, String> getWorkoutComplexesNames(String userId) {
        Map<String, String> result = new HashMap<String, String>();
        getAllWorkoutComplex(userId).forEach((workoutComplex) ->
                result.put(workoutComplex.getId(), workoutComplex.getName()));
        return result;
    }

    @Override
    public Map<String, String> getWorkoutsNames(String workoutComplexId, String userId) {

        Map<String, String> result = new HashMap<String, String>();
        getWorkouts(workoutComplexId, userId).forEach(
                (workout -> result.put(workout.getId(), workout.getName())));
        return result;
    }

    @Override
    public Map<String, String> getWorkoutsExercisesNames(String workoutId, String userId) {
        Map<String, String> result = new HashMap<String, String>();
        getExercises(workoutId, userId).forEach(
                exercise -> result.put(exercise.getId(), exercise.getName()));
        return result;
    }

    @Override
    public Map<String, String> getScheduledWorkoutsExercisesNames(String scheduledWorkoutId, String userId) {
        Map<String, String> result = new HashMap<String, String>();
        getAllCurrentExercises(scheduledWorkoutId, userId).forEach(
                exercise -> result.put(exercise.getId(), exercise.getName()));
        return result;
    }

    @Override
    public String getNameOfExercise(String exerciseId) {
        return getExercise(exerciseId).getName();
    }

    @Override
    public String getDescriptionOfExercise(String exerciseId) {
        return getExercise(exerciseId).getDescription();
    }

    @Override
    public List<String> getMeasuresOfExercise(String exerciseId) {
        return getExercise(exerciseId).getMeasureList();
    }

    @Override
    public List<MeasurementsOfExercise> getMeasurementsOfExercise(String exerciseId, String userId) {
        List<MeasurementsOfExercise> result = new ArrayList<>();
        getAllScheduledWorkouts(userId).forEach((scheduledWorkout -> {
            scheduledWorkout.getExerciseToMeasurements().stream()
                    .filter((exerciseToMeasurements -> exerciseToMeasurements.getExercise().getId().equals(exerciseId)))
                    .forEach((exerciseToMeasurements -> result.add(exerciseToMeasurements.getMeasures())));
        }));
        return result;
    }

    @Override
    public Map<Date, MeasurementsOfExercise> getMeasurementsOfExerciseWithDate(String exerciseId, String userId) {
        Map<Date, MeasurementsOfExercise> result = new HashMap<>();
        List<MeasurementsOfExercise> measurementsOfExerciseList = getMeasurementsOfExercise(exerciseId, userId);
        for (MeasurementsOfExercise measurements : measurementsOfExerciseList) {
            ExerciseToMeasurements exerciseToMeasurements =
                    exerciseToMeasurementsRepository.findByExerciseIdAndMeasuresId(exerciseId, measurements.getId());
            ScheduledWorkout scheduledWorkout =
                    sWToExMeasurementRepository.findByExerciseToMeasurementsId(exerciseToMeasurements.getId()).getScheduledWorkout();

            result.put(scheduledWorkout.getDateWorkout(), measurements);
        }
        return result;
    }

    @Override
    public MeasurementsOfExercise getLastMeasurementOfExercise(String exerciseId, String userId) {
        MeasurementsOfExercise result = null;
        Date min = null;
        List<MeasurementsOfExercise> measurementsOfExerciseList = getMeasurementsOfExercise(exerciseId, userId);

        for (MeasurementsOfExercise measurements : measurementsOfExerciseList) {
            ExerciseToMeasurements exerciseToMeasurements =
                    exerciseToMeasurementsRepository.findByExerciseIdAndMeasuresId(exerciseId, measurements.getId());
            ScheduledWorkout scheduledWorkout =
                    sWToExMeasurementRepository.findByExerciseToMeasurementsId(exerciseToMeasurements.getId()).getScheduledWorkout();
            if (min == null || scheduledWorkout.getDateWorkout().compareTo(min) < 0) {
                result = measurements;
                min = scheduledWorkout.getDateWorkout();
            }
        }
        return result;
    }

    @Override
    public String getWorkoutName(String workoutId, String userId) {
        return getWorkoutById(workoutId, userId).getName();
    }

    @Override
    public List<Exercise> getExercises(String workoutId, String userId) {
        Workout workout = getWorkoutById(workoutId, userId);
        return workout == null ? null : workout.getExercises();
    }

    @Override
    public Map<String, String> getAllNamesOfExercises(String workoutId, String userId) {
        Map<String, String> result = new HashMap<String, String>();
        getExercises(workoutId, userId).forEach(exercise -> result.put(exercise.getId(), exercise.getName()));
        return result;
    }

    @Override
    public List<ScheduledWorkout> getScheduledWorkouts(String workoutId, String userId) {
        Workout workout = getWorkoutById(workoutId, userId);
        return workout == null ? null : workout.getScheduledWorkouts();
    }

    @Override
    public List<ScheduledWorkout> getScheduledWorkoutsByStatus(String workoutId, String status, String userId) {
        List<ScheduledWorkout> result = new ArrayList<>();
        Workout workout = getWorkoutById(workoutId, userId);
        if (workout != null)
            workout.getScheduledWorkouts().forEach(scheduledWorkout -> {
                ScheduledWorkout.Status s = (status.equals("DONE")) ? ScheduledWorkout.Status.DONE : ScheduledWorkout.Status.SCHEDULED;
                if (scheduledWorkout.getStatus().equals(s)) {
                    result.add(scheduledWorkout);
                }
            });
        return result;
    }

    @Override
    public Workout getSourceWorkout(String scheduledWorkoutId, String userId) {
        return workoutToDateRepository.findByScheduledWorkoutId(scheduledWorkoutId).get().getWorkout();
    }

    @Override
    public WorkoutComplex getSourceWorkoutComplex(String workoutId, String userId) {
        authenticationService.checkAccessRightsToWorkout(workoutId, userId);
        return wComplexToWorkoutRepository.findByWorkoutId(workoutId)
                .orElseThrow(() -> {
                    return new IllegalArgumentException("Invalid scheduledWorkoutId:" + workoutId);
                })
                .getWorkoutComplex();
    }

    @Override
    public Date getDateScheduledWorkout(String scheduledWorkoutId, String userId) {
        if(!authenticationService.checkAccessRightsToScheduledWorkout(scheduledWorkoutId, userId))
            return null;
        return getScheduledWorkout(scheduledWorkoutId, userId).getDateWorkout();
    }

    @Override
    public String getStatusScheduledWorkout(String scheduledWorkoutId, String userId) {
        if(!authenticationService.checkAccessRightsToScheduledWorkout(scheduledWorkoutId, userId))
            return null;
        return getScheduledWorkout(scheduledWorkoutId, userId).getStatus()
                .equals(ScheduledWorkout.Status.DONE) ? "DONE" : "SCHEDULED";
    }

    @Override
    public Map<String, String> getScheduledWorkoutInformation(String id, String userId) {
        Map<String, String> result = new HashMap<String, String>();
        ScheduledWorkout scheduledWorkout = getScheduledWorkout(id, userId);
        result.put("AerobicWorkload", scheduledWorkout.getAerobicWorkload().toString());
        result.put("PowerWorkload", scheduledWorkout.getPowerWorkload().toString());
        result.put("Calories", scheduledWorkout.getCalories().toString());
        result.put("Duration", scheduledWorkout.getDuration().toString());
        return result;
    }

    @Override
    public List<Exercise> getAllCurrentExercises(String scheduledWorkoutId, String userId) {
        if(!authenticationService.checkAccessRightsToScheduledWorkout(scheduledWorkoutId, userId))
            return null;
        List<Exercise> exerciseList = new ArrayList<>();
        for (ExerciseToMeasurements exerciseToMeasurements :
                getScheduledWorkout(scheduledWorkoutId, userId).getExerciseToMeasurements()) {
            exerciseList.add(exerciseToMeasurements.getExercise());
        }
        return exerciseList;
    }

    @Override
    public Map<Exercise, MeasurementsOfExercise> getExercisesMeasurements(String scheduledWorkoutId, String userId) {
        if(!authenticationService.checkAccessRightsToScheduledWorkout(scheduledWorkoutId, userId))
            return null;
        Map<Exercise, MeasurementsOfExercise> result = new HashMap<>();
        for (ExerciseToMeasurements exerciseToMeasurements :
                getScheduledWorkout(scheduledWorkoutId, userId).getExerciseToMeasurements()) {
            result.put(exerciseToMeasurements.getExercise(), exerciseToMeasurements.getMeasures());
        }
        return result;
    }

    @Override
    public String getNameWorkoutComplex(String workoutComplexId, String userId) {
        if(!authenticationService.checkAccessRightsToWorkoutComplex(workoutComplexId, userId))
            return null;
        return getWorkoutComplexById(workoutComplexId, userId).getName();
    }

    @Override
    public List<Workout> getWorkouts(String workoutComplexId, String userId) {
        if(!authenticationService.checkAccessRightsToWorkoutComplex(workoutComplexId, userId))
            return null;
        return getWorkoutComplexById(workoutComplexId, userId).getWorkouts();
    }

}
