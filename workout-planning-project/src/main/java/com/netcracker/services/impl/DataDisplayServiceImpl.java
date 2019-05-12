package com.netcracker.services.impl;

import com.google.common.collect.Lists;
import com.netcracker.model.documents.*;
import com.netcracker.model.edges.ExerciseToMeasurements;
import com.netcracker.repository.documents.*;
import com.netcracker.repository.edges.*;
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
    public List<WorkoutComplex> getAllWorkoutComplex() {
        // #TODO Change when spring security is added!
        return Lists.newArrayList(workoutComplexRepository.findWorkoutComplexesByUserId(User.DEFAULT_USER_ID).asListRemaining());
    }

    @Override
    public List<ScheduledWorkout> getAllScheduledWorkouts() {
        List<ScheduledWorkout> result = new ArrayList<>();

        // #TODO Change when spring security is added!
        List<WorkoutComplex> workoutComplexList =
                workoutComplexRepository.findWorkoutComplexesByUserId(User.DEFAULT_USER_ID).asListRemaining();

        for (WorkoutComplex workoutComplex : workoutComplexList) {
            for (Workout workout : workoutComplex.getWorkouts()) {
                result.addAll(workout.getScheduledWorkouts());
            }
        }
        return result;
    }

    @Override
    public Exercise getExercise(String exerciseId) {
        return exerciseRepository.findById(exerciseId)
                .orElseThrow(() -> new NoSuchElementException("Exercise id " + exerciseId + " has bad value"));
    }

    @Override
    public Workout getWorkoutById(String workoutId) {
        // #TODO Change when spring security is added!
        return workoutRepository.findById(workoutId)
                .orElseThrow(() -> new NoSuchElementException("Workout id " + workoutId + " has bad value"));
    }

    @Override
    public WorkoutComplex getWorkoutComplexById(String workoutComplexId) {
        // #TODO Change when spring security is added!
        return workoutComplexRepository.findById(workoutComplexId)
                .orElseThrow(() -> new NoSuchElementException("Workout Complex id " + workoutComplexId + " has bad value"));
    }

    @Override
    public ScheduledWorkout getScheduledWorkout(String scheduledWorkoutId) {
        // #TODO Change when spring security is added!
        return scheduledWorkoutRepository.findById(scheduledWorkoutId)
                .orElseThrow(() -> new NoSuchElementException("Scheduled Workout id " + scheduledWorkoutId + " has bad value"));
    }

    @Override
    public Map<String, String> getAllExercisesNames() {
        Map<String, String> result = new HashMap<String, String>();
        for (Exercise exercise : getAllExercise()) {
            result.put(exercise.getId(), exercise.getName());
        }
        return result;
    }

    @Override
    public Map<String, String> getWorkoutComplexesNames() {
        Map<String, String> result = new HashMap<String, String>();
        for (WorkoutComplex workoutComplex : getAllWorkoutComplex()) {
            result.put(workoutComplex.getId(), workoutComplex.getName());
        }
        return result;
    }

    @Override
    public Map<String, String> getWorkoutsNames(String workoutComplexId) {
        Map<String, String> result = new HashMap<String, String>();
        for (Workout workout : getWorkouts(workoutComplexId)) {
            result.put(workout.getId(), workout.getName());
        }
        return result;
    }


    @Override
    public Map<String, String> getWorkoutsExercisesNames(String workoutId) {
        Map<String, String> result = new HashMap<String, String>();
        for (Exercise exercise : getExercises(workoutId)) {
            result.put(exercise.getId(), exercise.getName());
        }
        return result;
    }

    @Override
    public Map<String, String> getScheduledWorkoutsExercisesNames(String scheduledWorkoutId) {
        Map<String, String> result = new HashMap<String, String>();
        for (Exercise exercise : getAllCurrentExercises(scheduledWorkoutId)) {
            result.put(exercise.getId(), exercise.getName());
        }
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
    public List<MeasurementsOfExercise> getMeasurementsOfExercise(String exerciseId) {
        return getExercise(exerciseId).getMeasurements();
    }

    @Override
    public Map<Date, MeasurementsOfExercise> getMeasurementsOfExerciseWithDate(String exerciseId) {
        Map<Date, MeasurementsOfExercise> result = new HashMap<>();
        List<MeasurementsOfExercise> measurementsOfExerciseList = getMeasurementsOfExercise(exerciseId);
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
    public MeasurementsOfExercise getLastMeasurementOfExercise(String exerciseId) {
        MeasurementsOfExercise result = null;
        Date min = null;
        List<MeasurementsOfExercise> measurementsOfExerciseList = getMeasurementsOfExercise(exerciseId);

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
    public String getWorkoutName(String workoutId) {
        return getWorkoutById(workoutId).getName();
    }

    @Override
    public List<Exercise> getExercises(String workoutId) {
        return getWorkoutById(workoutId).getExercises();
    }

    @Override
    public Map<String, String> getAllNamesOfExercises(String workoutId) {
        Map<String, String> result = new HashMap<String, String>();
        for (Exercise exercise : getExercises(workoutId)) {
            result.put(exercise.getId(), exercise.getName());
        }
        return result;
    }

    @Override
    public List<ScheduledWorkout> getScheduledWorkouts(String workoutId) {
        return getWorkoutById(workoutId).getScheduledWorkouts();
    }

    @Override
    public List<ScheduledWorkout> getScheduledWorkoutsByStatus(String workoutId, String status) {
        List<ScheduledWorkout> result = new ArrayList<>();
        for (ScheduledWorkout scheduledWorkout : getWorkoutById(workoutId).getScheduledWorkouts()) {
            ScheduledWorkout.Status s = (status.equals("DONE")) ? ScheduledWorkout.Status.DONE : ScheduledWorkout.Status.SCHEDULED;
            if (scheduledWorkout.getStatus().equals(s)) {
                result.add(scheduledWorkout);
            }
        }
        return result;
    }

    @Override
    public Workout getSourceWorkout(String scheduledWorkoutId) {
        return workoutToDateRepository.findByScheduledWorkoutId(scheduledWorkoutId).getWorkout();
    }

    @Override
    public WorkoutComplex getSourceWorkoutComplex(String workoutId) {
        return wComplexToWorkoutRepository.findByWorkoutId(workoutId).getWorkoutComplex();
    }

    @Override
    public Date getDateScheduledWorkout(String scheduledWorkoutId) {
        return getScheduledWorkout(scheduledWorkoutId).getDateWorkout();
    }

    @Override
    public String getStatusScheduledWorkout(String scheduledWorkoutId) {
        return getScheduledWorkout(scheduledWorkoutId).getStatus()
                .equals(ScheduledWorkout.Status.DONE) ? "DONE" : "SCHEDULED";
    }

    @Override
    public Map<String, String> getScheduledWorkoutInformation(String id) {
        Map<String, String> result = new HashMap<String, String>();
        ScheduledWorkout scheduledWorkout =  getScheduledWorkout(id);
        result.put("AerobicWorkload", scheduledWorkout.getAerobicWorkload().toString());
        result.put("PowerWorkload", scheduledWorkout.getPowerWorkload().toString());
        result.put("Calories", scheduledWorkout.getCalories().toString());
        result.put("Duration", scheduledWorkout.getDuration().toString());
        return result;
    }

    @Override
    public List<Exercise> getAllCurrentExercises(String scheduledWorkoutId) {
        List<Exercise> exerciseList = new ArrayList<>();
        for (ExerciseToMeasurements exerciseToMeasurements :
                getScheduledWorkout(scheduledWorkoutId).getExerciseToMeasurements()) {
            exerciseList.add(exerciseToMeasurements.getExercise());
        }
        return exerciseList;
    }

    @Override
    public Map<Exercise, MeasurementsOfExercise> getExercisesMeasurements(String scheduledWorkoutId) {
        Map<Exercise, MeasurementsOfExercise> result = new HashMap<>();
        for (ExerciseToMeasurements exerciseToMeasurements :
                getScheduledWorkout(scheduledWorkoutId).getExerciseToMeasurements()) {
            result.put(exerciseToMeasurements.getExercise(), exerciseToMeasurements.getMeasures());
        }
        return result;
    }

    @Override
    public String getNameWorkoutComplex(String workoutComplexId) {
        return getWorkoutComplexById(workoutComplexId).getName();
    }

    @Override
    public List<Workout> getWorkouts(String workoutComplexId) {
        return getWorkoutComplexById(workoutComplexId).getWorkouts();
    }
}
