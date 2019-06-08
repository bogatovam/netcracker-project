package com.netcracker.services.impl;

import com.netcracker.model.edges.*;
import com.netcracker.model.documents.*;
import com.netcracker.repository.edges.*;
import com.netcracker.repository.documents.*;
import com.netcracker.services.api.AuthenticationService;
import com.netcracker.services.api.PlanningService;

import lombok.RequiredArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

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
    private final AuthenticationService authenticationService;

    private final WComplexToWorkoutRepository wComplexToWorkoutRepository;
    private final WorkoutToExerciseRepository workoutToExerciseRepository;
    private final WorkoutToDateRepository workoutToDateRepository;
    private final ExerciseToMeasurementsRepository exerciseToMeasurementsRepository;
    private final UserToWComplexRepository userToWComplexRepository;
    private final ScheduledWorkoutToExerciseMeasurementRepository sWToExMeasurementRepository;

    private static final Logger logger = LoggerFactory.getLogger(PlanningServiceImpl.class);

    @Override
    public Exercise createExercise(Exercise exercise, String userId) {
        Exercise newExercise = exerciseRepository.save(exercise);
        // #TODO validation by name exercise is probably necessary
        logger.info("User " + userId + " create exercise  " + exercise);
        return newExercise;
    }

    @Override
    public Workout createWorkout(Workout workout, String userId) {
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
        logger.info("User " + userId + " create workout  " + workout);
        return newWorkout;
    }

    @Override
    public Workout createWorkout(String workoutComplexId, Workout workout, String userId) {
        Workout newWorkout;
        WorkoutComplex sourceWorkoutComplex;
        WComplexToWorkout wComplexToWorkout;

        if (!authenticationService.checkAccessRightsToWorkoutComplex(workoutComplexId, userId))
            return null;

        // #TODO need to add own exceptions
        sourceWorkoutComplex = workoutComplexRepository.findById(workoutComplexId)
                .orElseThrow(() -> new NoSuchElementException(
                        "Workout complex id" + workoutComplexId + " has bad value"));

        newWorkout = workoutRepository.save(workout);
        wComplexToWorkout = WComplexToWorkout.builder().
                workoutComplex(sourceWorkoutComplex).
                workout(newWorkout).build();
        wComplexToWorkoutRepository.save(wComplexToWorkout);
        logger.info("User " + userId + " create workout  " + workout);

        return newWorkout;
    }

    @Override
    public ScheduledWorkout createScheduledWorkout(String workoutId, ScheduledWorkout scheduledWorkout, String userId) {
        Workout sourceWorkout;
        WorkoutToDate workoutToDate;
        ScheduledWorkout newScheduledWorkout;

        authenticationService.checkAccessRightsToWorkout(workoutId, userId);
        sourceWorkout = workoutRepository.findById(workoutId)
                .orElseThrow(() -> new NoSuchElementException(
                        "Workout id" + workoutId + " has bad value"));

        newScheduledWorkout = scheduledWorkoutRepository.save(scheduledWorkout);

        workoutToDate = WorkoutToDate.builder()
                .scheduledWorkout(newScheduledWorkout)
                .workout(sourceWorkout).build();

        workoutToDateRepository.save(workoutToDate);
        logger.info("User " + userId + " create workout  " + workoutId);
        return newScheduledWorkout;
    }

    @Override
    public WorkoutComplex createWorkoutComplex(WorkoutComplex workoutComplex, String userId) {
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
        logger.info("User " + userId + " create workout complex  " + workoutComplex);
        return newWorkoutComplex;
    }

    @Override
    public Exercise deleteExercise(String exerciseId, String userId) {
        Exercise delExercise = exerciseRepository.findById(exerciseId).
                orElseThrow(() -> new NoSuchElementException(
                        "Exercise id " + exerciseId + " has bad value"));
        workoutToExerciseRepository.removeAllByExerciseId(delExercise.getId());

        delExercise.getMeasurements().forEach(measurement ->
                measurementsOfExerciseRepository.removeById(measurement.getId()));

        exerciseToMeasurementsRepository.removeAllByExerciseId(delExercise.getId());

        exerciseRepository.removeById(exerciseId);
        logger.info("User " + userId + " delete exercise " + exerciseId);
        return delExercise;
    }

    @Override
    public Workout deleteWorkout(String workoutId, String userId) {
        Workout workout = workoutRepository.findById(workoutId)
                .orElseThrow(() -> new NoSuchElementException("Workout id " + workoutId + " has bad value"));

        if (!authenticationService.checkAccessRightsToWorkout(workoutId, userId))
            return null;
        wComplexToWorkoutRepository.removeAllByWorkoutId(workoutId);
        workoutToExerciseRepository.removeAllByWorkoutId(workoutId);
        workoutToDateRepository.removeAllByWorkoutId(workoutId);

        workoutRepository.delete(workout);
        logger.info("User " + userId + " delete workout  " + workoutId);
        return workout;
    }

    @Override
    public WorkoutComplex deleteWorkoutComplex(String workoutComplexId, String userId) {
        if (!authenticationService.checkAccessRightsToWorkoutComplex(workoutComplexId, userId))
            return null;
        WorkoutComplex workoutComplex = workoutComplexRepository.findById(workoutComplexId)
                .orElseThrow(() -> new NoSuchElementException(
                        "Workout Complex id " + workoutComplexId + " has bad value"));

        userToWComplexRepository.removeAllByWcomplexId(workoutComplexId);
        wComplexToWorkoutRepository.removeAllByWorkoutComplexId(workoutComplexId);

        workoutComplexRepository.removeById(workoutComplexId);
        logger.info("User " + userId + " delete workout complex " + workoutComplexId);
        return workoutComplex;
    }

    @Override
    public ScheduledWorkout deleteScheduledWorkout(String workoutId, String scheduledWorkoutId, String userId) {
        if (!authenticationService.checkAccessRightsToScheduledWorkout(scheduledWorkoutId, userId)) return null;
        ScheduledWorkout scheduledWorkout = scheduledWorkoutRepository.findById(scheduledWorkoutId)
                .orElseThrow(() -> new NoSuchElementException("Scheduled Workout id " + scheduledWorkoutId + " has bad value"));
        workoutToDateRepository.removeAllByScheduledWorkoutId(scheduledWorkoutId);

        scheduledWorkout.getExerciseToMeasurements().forEach(etm -> {
            measurementsOfExerciseRepository.removeById(etm.getMeasures().getId());
            exerciseToMeasurementsRepository.removeById(etm.getId());
        });

        sWToExMeasurementRepository.removeAllByScheduledWorkoutId(scheduledWorkoutId);
        scheduledWorkoutRepository.removeById(scheduledWorkoutId);
        logger.info("User " + userId + " delete scheduled  workout " + scheduledWorkoutId);
        return scheduledWorkout;
    }

    @Override
    public String setExerciseName(String exerciseId, String name, String userId) {
        Exercise exercise = exerciseRepository.findById(exerciseId)
                .orElseThrow(() -> new NoSuchElementException("Exercise id " + exerciseId + " has bad value"));
        exercise.setName(name);
        logger.info("User " + userId + " set exercise" + exercise + " name  " + name);

        return exercise.getName();
    }

    @Override
    public Workout addListExercises(String workoutId, List<String> exerciseIdList, String userId) {
        if (!authenticationService.checkAccessRightsToWorkout(workoutId, userId)) return null;
        Workout workout = workoutRepository.findById(workoutId)
                .orElseThrow(() -> new NoSuchElementException("Workout id " + workoutId + " has bad value"));

        exerciseIdList.forEach(exerciseId -> {
            Exercise exercise = exerciseRepository.findById(exerciseId).
                    orElseThrow(() -> new NoSuchElementException("Exercise id " + exerciseId + " has bad value"));
            if (!workoutToExerciseRepository.findByWorkoutIdAndExerciseId(workoutId, exerciseId).isPresent()) {
                WorkoutToExercise workoutToExercise = WorkoutToExercise.builder()
                        .workout(workout)
                        .exercise(exercise)
                        .build();
                workoutToExerciseRepository.save(workoutToExercise);
            } else
                System.out.println("Exercise " + exercise.getName() + " with id:" + exerciseId + " already exist in workout with id:" + workoutId);
        });
        workoutRepository.save(workout);
        logger.info("User " + userId + " add list exercises " + exerciseIdList + " to workout " + workoutId);

        return workout;
    }

    @Override
    public Workout delListExercises(String workoutId, List<String> exerciseIdList, String userId) {
        if (!authenticationService.checkAccessRightsToWorkout(workoutId, userId)) return null;

        Workout workout = workoutRepository.findById(workoutId)
                .orElseThrow(() -> new NoSuchElementException("Workout id " + workoutId + " has bad value"));
        exerciseIdList.forEach(exerciseId -> {
            Exercise exercise = exerciseRepository.findById(exerciseId)
                    .orElseThrow(() -> new NoSuchElementException("Exercise id " + exerciseId + " has bad value"));

            Optional<WorkoutToExercise> workoutToExercise =
                    workoutToExerciseRepository.findByWorkoutIdAndExerciseId(workoutId, exerciseId);
            if (workoutToExercise.isPresent()) {
                workoutToExerciseRepository.removeById(workoutToExercise.get().getId());
            } else
                System.out.println("Exercise " + exercise.getName() + " with id:" + exerciseId + " don't exist in workout with id:" + workoutId);

        });
        workoutRepository.save(workout);
        logger.info("User " + userId + " del list exercises " + exerciseIdList + " from workout " + workoutId);

        return workout;
    }

    @Override
    public ScheduledWorkout addExerciseToScheduledWorkout(String scheduledWorkoutId, String exerciseId, String userId) {
        if (!authenticationService.checkAccessRightsToScheduledWorkout(scheduledWorkoutId, userId)) return null;
        ScheduledWorkout scheduledWorkout = scheduledWorkoutRepository.findById(scheduledWorkoutId)
                .orElseThrow(() -> new NoSuchElementException("Scheduled Workout id " + scheduledWorkoutId + " has bad value"));

        Exercise exercise = exerciseRepository.findById(exerciseId)
                .orElseThrow(() -> new NoSuchElementException("Exercise id " + scheduledWorkoutId + " has bad value"));
        Boolean res = scheduledWorkout.getExerciseToMeasurements().stream().anyMatch(e -> e.getExercise().equals(exercise));

        if (res)
            throw new IllegalArgumentException(
                    "Exercise with id:" + exercise.getId() + " already exists in scheduled workout with id" + scheduledWorkoutId);

        addExerciseToScheduledWorkout(scheduledWorkout, exercise, userId);
        logger.info("User " + userId + " add exercise  " + exerciseId + " to scheduled workout  " + scheduledWorkoutId);

        return scheduledWorkout;
    }

    public void addExerciseToScheduledWorkout(ScheduledWorkout scheduledWorkout, Exercise exercise, String userId) {
        if (!authenticationService.checkAccessRightsToScheduledWorkout(scheduledWorkout.getId(), userId)) return;

        MeasurementsOfExercise measurements = MeasurementsOfExercise.builder()
                .listSet(new LinkedList<>()).build();
        measurementsOfExerciseRepository.save(measurements);

        ExerciseToMeasurements exerciseToMeasurements = ExerciseToMeasurements.builder()
                .exercise(exercise)
                .measures(measurements).build();
        exerciseToMeasurementsRepository.save(exerciseToMeasurements);

        ScheduledWorkoutToExerciseMeasurement swtem = ScheduledWorkoutToExerciseMeasurement.builder()
                .scheduledWorkout(scheduledWorkout)
                .exerciseToMeasurements(exerciseToMeasurements).build();
        sWToExMeasurementRepository.save(swtem);
        logger.info("User " + userId + " add exercise  " + exercise + " to scheduled workout  " + scheduledWorkout);

    }

    @Override
    public MeasurementsOfExercise addExerciseMeasurement(String scheduledWorkoutId,
                                                         String exerciseId, Measurement measurement, String userId) {
        if (!authenticationService.checkAccessRightsToScheduledWorkout(scheduledWorkoutId, userId)) return null;

        ScheduledWorkout scheduledWorkout = scheduledWorkoutRepository.findById(scheduledWorkoutId)
                .orElseThrow(() -> new NoSuchElementException("Scheduled Workout id " + scheduledWorkoutId + " has bad value"));

        Exercise exercise = exerciseRepository.findById(exerciseId)
                .orElseThrow(() -> new NoSuchElementException("Exercise id " + scheduledWorkoutId + " has bad value"));

        Optional<ExerciseToMeasurements> exerciseToMeasurements =
                scheduledWorkout.getExerciseToMeasurements().stream().filter(e -> e.getExercise().equals(exercise)).findFirst();
        if (exerciseToMeasurements.isPresent()) {
            MeasurementsOfExercise measures = exerciseToMeasurements.get().getMeasures();
            measures.getListSet().add(measurement);
            measurementsOfExerciseRepository.save(measures);
            return measures;
        }
        return null;
    }

    @Override
    public MeasurementsOfExercise updateExerciseMeasurement(String scheduledWorkoutId, String exerciseId,
                                                            String numberMeasurement, Measurement measurement, String userId) {
        if (!authenticationService.checkAccessRightsToScheduledWorkout(scheduledWorkoutId, userId)) return null;

        ScheduledWorkout scheduledWorkout = scheduledWorkoutRepository.findById(scheduledWorkoutId)
                .orElseThrow(() -> new NoSuchElementException("Scheduled Workout id " + scheduledWorkoutId + " has bad value"));

        Exercise exercise = exerciseRepository.findById(exerciseId)
                .orElseThrow(() -> new NoSuchElementException("Exercise id " + scheduledWorkoutId + " has bad value"));

        Optional<ExerciseToMeasurements> exerciseToMeasurements =
                scheduledWorkout.getExerciseToMeasurements().stream().filter(e -> e.getExercise().equals(exercise)).findFirst();
        if (exerciseToMeasurements.isPresent()) {
            MeasurementsOfExercise measures = exerciseToMeasurements.get().getMeasures();
            Measurement oldMeasurement = measures.getListSet().get(Integer.parseInt(numberMeasurement));

            oldMeasurement.setComment(measurement.getComment());
            oldMeasurement.setMeasure(measurement.getMeasure());

            measurementsOfExerciseRepository.save(measures);
            return measures;
        }
        return null;
    }

    @Override
    public MeasurementsOfExercise delExerciseMeasurement(String scheduledWorkoutId, String exerciseId,
                                                         String numberMeasurement, String userId) {
        if (!authenticationService.checkAccessRightsToScheduledWorkout(scheduledWorkoutId, userId)) return null;

        ScheduledWorkout scheduledWorkout = scheduledWorkoutRepository.findById(scheduledWorkoutId)
                .orElseThrow(() -> new NoSuchElementException("Scheduled Workout id " + scheduledWorkoutId + " has bad value"));

        Exercise exercise = exerciseRepository.findById(exerciseId)
                .orElseThrow(() -> new NoSuchElementException("Exercise id " + scheduledWorkoutId + " has bad value"));

        Optional<ExerciseToMeasurements> exerciseToMeasurements =
                scheduledWorkout.getExerciseToMeasurements().stream().filter(e -> e.getExercise().equals(exercise)).findFirst();
        if (exerciseToMeasurements.isPresent()) {
            MeasurementsOfExercise measures = exerciseToMeasurements.get().getMeasures();
            measures.getListSet().remove(Integer.parseInt(numberMeasurement));
            measurementsOfExerciseRepository.save(measures);
            return measures;
        }
        return null;
    }

    @Override
    public ScheduledWorkout deleteExerciseFromScheduledWorkout(String scheduledWorkoutId, String exerciseId, String userId) {
        if (!authenticationService.checkAccessRightsToScheduledWorkout(scheduledWorkoutId, userId)) return null;

        ScheduledWorkout scheduledWorkout = scheduledWorkoutRepository.findById(scheduledWorkoutId)
                .orElseThrow(() -> new NoSuchElementException("Scheduled Workout id " + scheduledWorkoutId + " has bad value"));

        Exercise exercise = exerciseRepository.findById(exerciseId)
                .orElseThrow(() -> new NoSuchElementException("Exercise id " + scheduledWorkoutId + " has bad value"));

        ExerciseToMeasurements exerciseToMeasurements =
                scheduledWorkout.getExerciseToMeasurements().stream()
                        .filter((e) -> e.getExercise().equals(exercise)).findFirst()
                        .orElseThrow(() -> new IllegalArgumentException(
                                "Scheduled workout(id) " + scheduledWorkoutId +
                                        " doesnt have exercise(id)" + exerciseId));

        MeasurementsOfExercise measurements = exerciseToMeasurements.getMeasures();
        measurementsOfExerciseRepository.removeById(measurements.getId());
        sWToExMeasurementRepository.removeAllByExerciseToMeasurementsId(exerciseToMeasurements.getId());
        exerciseToMeasurementsRepository.removeById(exerciseToMeasurements.getId());
        return scheduledWorkout;
    }

    @Override
    public Workout addWorkout(String workoutComplexId, Workout workout, String userId) {
        if (!authenticationService.checkAccessRightsToWorkoutComplex(workoutComplexId, userId)) return null;

        WorkoutComplex workoutComplex = workoutComplexRepository.findById(workoutComplexId)
                .orElseThrow(() -> new NoSuchElementException("Workout complex id:" + workoutComplexId + " has bad value"));

        Workout newWorkout = workoutRepository.save(workout);
        WComplexToWorkout wComplexToWorkout = WComplexToWorkout.builder()
                .workoutComplex(workoutComplex)
                .workout(newWorkout)
                .build();

        wComplexToWorkoutRepository.save(wComplexToWorkout);
        return newWorkout;
    }

    @Override
    public Workout delWorkout(String workoutComplexId, String workoutId, String userId) {
        if (!authenticationService.checkAccessRightsToWorkoutComplex(workoutComplexId, userId)) return null;

        if (!authenticationService.checkAccessRightsToWorkout(workoutId, userId)) return null;

        Workout workout = workoutRepository.findById(workoutId)
                .orElseThrow(() -> new NoSuchElementException("Workout id " + workoutId + " has bad value"));

        wComplexToWorkoutRepository.removeByWorkoutIdAndWorkoutComplexId(workoutComplexId, workoutId);
        workoutToExerciseRepository.removeAllByWorkoutId(workoutId);
        workoutToDateRepository.removeAllByWorkoutId(workoutId);

        workoutRepository.delete(workout);
        return workout;
    }

    @Override
    public Workout setWorkoutToDate(String workoutId, Date date, String userId) {

        if (!authenticationService.checkAccessRightsToWorkout(workoutId, userId)) return null;

        Workout workout = workoutRepository.findById(workoutId)
                .orElseThrow(() -> new NoSuchElementException("Workout id " + workoutId + " has bad value"));

        ScheduledWorkout scheduledWorkout = ScheduledWorkout.builder()
                .dateWorkout(date)
                .status(ScheduledWorkout.Status.SCHEDULED)
                .exerciseToMeasurements(new LinkedList<>())
                .build();

        scheduledWorkout = scheduledWorkoutRepository.save(scheduledWorkout);
        for (Exercise exercise : workout.getExercises()) {
            addExerciseToScheduledWorkout(scheduledWorkout, exercise, userId);
        }

        WorkoutToDate workoutToDate = WorkoutToDate.builder()
                .workout(workout)
                .scheduledWorkout(scheduledWorkout)
                .build();
        workoutToDateRepository.save(workoutToDate);
        return workout;
    }

    @Override
    public Workout setWorkoutComplexToWorkout(String workoutComplexId, String workoutId, String userId) {
        if (!authenticationService.checkAccessRightsToWorkoutComplex(workoutComplexId, userId)) return null;

        if (!authenticationService.checkAccessRightsToWorkout(workoutId, userId)) return null;


        Workout workout = workoutRepository.findById(workoutId)
                .orElseThrow(() -> new NoSuchElementException("Workout id " + workoutId + " has bad value"));

        WorkoutComplex workoutComplex = workoutComplexRepository.findById(workoutId)
                .orElseThrow(() -> new NoSuchElementException("Workout Complex id " + workoutComplexId + " has bad value"));

        wComplexToWorkoutRepository.removeAllByWorkoutId(workoutId);
        WComplexToWorkout wComplexToWorkout = WComplexToWorkout.builder()
                .workout(workout)
                .workoutComplex(workoutComplex)
                .build();
        wComplexToWorkoutRepository.save(wComplexToWorkout);
        return workout;
    }

    @Override
    public Workout setNameWorkout(String workoutId, String name, String userId) {
        if (!authenticationService.checkAccessRightsToWorkout(workoutId, userId)) return null;

        Workout workout = workoutRepository.findById(workoutId)
                .orElseThrow(() -> new NoSuchElementException("Workout id " + workoutId + " has bad value"));
        workout.setName(name);
        workoutRepository.save(workout);
        return workout;
    }

    @Override
    public void setDateScheduledWorkout(String scheduledWorkoutId, Date date, String userId) {
        if (!authenticationService.checkAccessRightsToScheduledWorkout(scheduledWorkoutId, userId)) return;

        ScheduledWorkout scheduledWorkout = scheduledWorkoutRepository.findById(scheduledWorkoutId)
                .orElseThrow(() -> new NoSuchElementException("Scheduled Workout id " + scheduledWorkoutId + " has bad value"));
        scheduledWorkout.setDateWorkout(date);
        scheduledWorkoutRepository.save(scheduledWorkout);
    }

    @Override
    public void setStatusScheduledWorkout(String scheduledWorkoutId, String status, String userId) {
        if (!authenticationService.checkAccessRightsToScheduledWorkout(scheduledWorkoutId, userId)) return;

        ScheduledWorkout scheduledWorkout = scheduledWorkoutRepository.findById(scheduledWorkoutId)
                .orElseThrow(() -> new NoSuchElementException("Scheduled Workout id " + scheduledWorkoutId + " has bad value"));
        if (status.equals("DONE") && scheduledWorkout.getStatus().equals(ScheduledWorkout.Status.SCHEDULED)) {
            // TODO: Make smth with date and calculating parameters(information)
            scheduledWorkout.setStatus(ScheduledWorkout.Status.DONE);
            scheduledWorkout.calculateInformation(new Date());
        }
        scheduledWorkoutRepository.save(scheduledWorkout);
    }

    @Override
    public WorkoutComplex setNameWorkoutComplex(String workoutComplexId, String name, String userId) {
        if (!authenticationService.checkAccessRightsToWorkoutComplex(workoutComplexId, userId)) return null;

        WorkoutComplex workoutComplex = workoutComplexRepository.findById(workoutComplexId)
                .orElseThrow(() -> new NoSuchElementException("Workout Complex id " + workoutComplexId + " has bad value"));
        workoutComplex.setName(name);
        workoutComplexRepository.save(workoutComplex);
        return workoutComplex;
    }

}
