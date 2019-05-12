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
                .orElseThrow(() -> new NoSuchElementException("Workout id " + workoutId + " has bad value"));

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
        wComplexToWorkoutRepository.removeAllByWorkoutComplexId(workoutComplexId);

        workoutComplexRepository.removeById(workoutComplexId);
        return workoutComplex;
    }

    @Override
    public ScheduledWorkout deleteScheduledWorkout(String workoutId, String scheduledWorkoutId) {
        ScheduledWorkout scheduledWorkout = scheduledWorkoutRepository.findById(scheduledWorkoutId)
                .orElseThrow(() -> new NoSuchElementException("Scheduled Workout id " + scheduledWorkoutId + " has bad value"));
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
        Exercise exercise = exerciseRepository.findById(exerciseId)
                .orElseThrow(() -> new NoSuchElementException("Exercise id " + exerciseId + " has bad value"));
        exercise.setName(name);
        return exercise.getName();
    }

    @Override
    public Workout addListExercises(String workoutId, List<String> exerciseIdList) {
        Workout workout = workoutRepository.findById(workoutId)
                .orElseThrow(() -> new NoSuchElementException("Workout id " + workoutId + " has bad value"));

        for (String exerciseId : exerciseIdList) {
            Exercise exercise = exerciseRepository.findById(exerciseId).
                    orElseThrow(() -> new NoSuchElementException("Exercise id " + exerciseId + " has bad value"));
            if (!workoutToExerciseRepository.findByWorkoutIdAndExerciseId(workoutId, exerciseId).isPresent()) {
                WorkoutToExercise workoutToExercise = WorkoutToExercise.builder()
                        .workout(workout)
                        .exercise(exercise)
                        .build();
                workoutToExerciseRepository.save(workoutToExercise);
            } else
                // #TODO Add Logger
                // #TODO Change return value
                System.out.println("Exercise " + exercise.getName() + " with id:" + exerciseId + " already exist in workout with id:" + workoutId);
        }
        workoutRepository.save(workout);
        return workout;
    }

    @Override
    public Workout delListExercises(String workoutId, List<String> exerciseIdList) {
        Workout workout = workoutRepository.findById(workoutId)
                .orElseThrow(() -> new NoSuchElementException("Workout id " + workoutId + " has bad value"));

        for (String exerciseId : exerciseIdList) {
            Exercise exercise = exerciseRepository.findById(exerciseId)
                    .orElseThrow(() -> new NoSuchElementException("Exercise id " + exerciseId + " has bad value"));

            Optional<WorkoutToExercise> workoutToExercise =
                    workoutToExerciseRepository.findByWorkoutIdAndExerciseId(workoutId, exerciseId);
            if (workoutToExercise.isPresent()) {
                workoutToExerciseRepository.removeById(workoutToExercise.get().getId());
            } else
                // #TODO Add Logger
                // #TODO Change return value
                System.out.println("Exercise " + exercise.getName() + " with id:" + exerciseId + " don't exist in workout with id:" + workoutId);
        }
        workoutRepository.save(workout);
        return workout;
    }

    @Override
    public ScheduledWorkout addExerciseToScheduledWorkout(String scheduledWorkoutId, String exerciseId) {
        ScheduledWorkout scheduledWorkout = scheduledWorkoutRepository.findById(scheduledWorkoutId)
                .orElseThrow(() -> new NoSuchElementException("Scheduled Workout id " + scheduledWorkoutId + " has bad value"));

        Exercise exercise = exerciseRepository.findById(exerciseId)
                .orElseThrow(() -> new NoSuchElementException("Exercise id " + scheduledWorkoutId + " has bad value"));

        for (ExerciseToMeasurements e : scheduledWorkout.getExerciseToMeasurements()) {
            if (e.getExercise().equals(exercise)) {
                throw new IllegalArgumentException(
                        "Exercise with id:" + exercise.getId() + " already exists in scheduled workout with id" + scheduledWorkoutId);
            }
        }

        addExerciseToScheduledWorkout(scheduledWorkout, exercise);
        return scheduledWorkout;
    }

    public Boolean addExerciseToScheduledWorkout(ScheduledWorkout scheduledWorkout, Exercise exercise) {
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
        return true;
    }

    @Override
    public MeasurementsOfExercise addExerciseMeasurement(String scheduledWorkoutId,
                                                         String exerciseId, Measurement measurement) {
        ScheduledWorkout scheduledWorkout = scheduledWorkoutRepository.findById(scheduledWorkoutId)
                .orElseThrow(() -> new NoSuchElementException("Scheduled Workout id " + scheduledWorkoutId + " has bad value"));

        Exercise exercise = exerciseRepository.findById(exerciseId)
                .orElseThrow(() -> new NoSuchElementException("Exercise id " + scheduledWorkoutId + " has bad value"));

        for (ExerciseToMeasurements e : scheduledWorkout.getExerciseToMeasurements()) {
            if (e.getExercise().equals(exercise)) {
                MeasurementsOfExercise measures = e.getMeasures();
                measures.getListSet().add(measurement);
                measurementsOfExerciseRepository.save(measures);
                return measures;
            }
        }
        return null;
    }

    @Override
    public MeasurementsOfExercise updateExerciseMeasurement(String scheduledWorkoutId, String exerciseId,
                                                            String numberMeasurement, Measurement measurement) {
        ScheduledWorkout scheduledWorkout = scheduledWorkoutRepository.findById(scheduledWorkoutId)
                .orElseThrow(() -> new NoSuchElementException("Scheduled Workout id " + scheduledWorkoutId + " has bad value"));

        Exercise exercise = exerciseRepository.findById(exerciseId)
                .orElseThrow(() -> new NoSuchElementException("Exercise id " + scheduledWorkoutId + " has bad value"));

        for (ExerciseToMeasurements e : scheduledWorkout.getExerciseToMeasurements()) {
            if (e.getExercise().equals(exercise)) {
                MeasurementsOfExercise measures = e.getMeasures();
                Measurement oldMeasurement = measures.getListSet().get(Integer.parseInt(numberMeasurement));

                oldMeasurement.setComment(measurement.getComment());
                oldMeasurement.setMeasure(measurement.getMeasure());

                measurementsOfExerciseRepository.save(measures);
                return measures;
            }
        }
        return null;
    }

    @Override
    public MeasurementsOfExercise delExerciseMeasurement(String scheduledWorkoutId, String exerciseId,
                                                         String numberMeasurement) {
        ScheduledWorkout scheduledWorkout = scheduledWorkoutRepository.findById(scheduledWorkoutId)
                .orElseThrow(() -> new NoSuchElementException("Scheduled Workout id " + scheduledWorkoutId + " has bad value"));

        Exercise exercise = exerciseRepository.findById(exerciseId)
                .orElseThrow(() -> new NoSuchElementException("Exercise id " + scheduledWorkoutId + " has bad value"));

        for (ExerciseToMeasurements e : scheduledWorkout.getExerciseToMeasurements()) {
            if (e.getExercise().equals(exercise)) {
                MeasurementsOfExercise measures = e.getMeasures();
                measures.getListSet().remove(Integer.parseInt(numberMeasurement));
                measurementsOfExerciseRepository.save(measures);
                return measures;
            }
        }
        return null;
    }


    @Override
    public ScheduledWorkout deleteExerciseFromScheduledWorkout(String scheduledWorkoutId, String exerciseId) {
        ScheduledWorkout scheduledWorkout = scheduledWorkoutRepository.findById(scheduledWorkoutId)
                .orElseThrow(() -> new NoSuchElementException("Scheduled Workout id " + scheduledWorkoutId + " has bad value"));

        Exercise exercise = exerciseRepository.findById(exerciseId)
                .orElseThrow(() -> new NoSuchElementException("Exercise id " + scheduledWorkoutId + " has bad value"));

        for (ExerciseToMeasurements e : scheduledWorkout.getExerciseToMeasurements()) {
            if (e.getExercise().equals(exercise)) {
                MeasurementsOfExercise measurements = e.getMeasures();
                measurementsOfExerciseRepository.removeById(measurements.getId());
                sWToExMeasurementRepository.removeAllByExerciseToMeasurementsId(e.getId());
                exerciseToMeasurementsRepository.removeById(e.getId());
                return scheduledWorkout;
            }
        }
        return scheduledWorkout;
    }

    @Override
    public Workout addWorkout(String workoutComplexId, Workout workout) {
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
    public Workout delWorkout(String workoutComplexId, String workoutId) {
        Workout workout = workoutRepository.findById(workoutId)
                .orElseThrow(() -> new NoSuchElementException("Workout id " + workoutId + " has bad value"));

        wComplexToWorkoutRepository.removeByWorkoutIdAndWorkoutComplexId(workoutComplexId, workoutId);
        workoutToExerciseRepository.removeAllByWorkoutId(workoutId);
        workoutToDateRepository.removeAllByWorkoutId(workoutId);

        workoutRepository.delete(workout);
        return workout;
    }

    @Override
    public Workout setWorkoutToDate(String workoutId, Date date) {
        Workout workout = workoutRepository.findById(workoutId)
                .orElseThrow(() -> new NoSuchElementException("Workout id " + workoutId + " has bad value"));

        ScheduledWorkout scheduledWorkout = ScheduledWorkout.builder()
                .dateWorkout(date)
                .status(ScheduledWorkout.Status.SCHEDULED)
                .exerciseToMeasurements(new LinkedList<>())
                .build();

        scheduledWorkout = scheduledWorkoutRepository.save(scheduledWorkout);

        for (Exercise exercise : workout.getExercises()) {
            addExerciseToScheduledWorkout(scheduledWorkout, exercise);
        }

        WorkoutToDate workoutToDate = WorkoutToDate.builder()
                .workout(workout)
                .scheduledWorkout(scheduledWorkout)
                .build();
        workoutToDateRepository.save(workoutToDate);
        return workout;
    }

    @Override
    public Workout setWorkoutComplexToWorkout(String workoutComplexId, String workoutId) {
        Workout workout = workoutRepository.findById(workoutId)
                .orElseThrow(() -> new NoSuchElementException("Workout id " + workoutId + " has bad value"));

        WorkoutComplex workoutComplex = workoutComplexRepository.findById(workoutId)
                .orElseThrow(() -> new NoSuchElementException("Workout Complex id " + workoutComplexId + " has bad value"));

        //WorkoutComplex sourceWorkoutComplex = workoutComplexRepository.findWorkoutComplexesByWorkoutsId(workoutId).asListRemaining().get(0);

        wComplexToWorkoutRepository.removeAllByWorkoutId(workoutId);
        WComplexToWorkout wComplexToWorkout = WComplexToWorkout.builder()
                .workout(workout)
                .workoutComplex(workoutComplex)
                .build();
        wComplexToWorkoutRepository.save(wComplexToWorkout);
        return workout;
    }

    @Override
    public Workout setNameWorkout(String workoutId, String name) {
        Workout workout = workoutRepository.findById(workoutId)
                .orElseThrow(() -> new NoSuchElementException("Workout id " + workoutId + " has bad value"));
        workout.setName(name);
        workoutRepository.save(workout);
        return workout;
    }

    @Override
    public void setDateScheduledWorkout(String scheduledWorkoutId, Date date) {
        ScheduledWorkout scheduledWorkout = scheduledWorkoutRepository.findById(scheduledWorkoutId)
                .orElseThrow(() -> new NoSuchElementException("Scheduled Workout id " + scheduledWorkoutId + " has bad value"));
        scheduledWorkout.setDateWorkout(date);
        scheduledWorkoutRepository.save(scheduledWorkout);
    }

    @Override
    public void setStatusScheduledWorkout(String scheduledWorkoutId, String status) {
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
    public WorkoutComplex setNameWorkoutComplex(String workoutComplexId, String name) {
        WorkoutComplex workoutComplex = workoutComplexRepository.findById(workoutComplexId)
                .orElseThrow(() -> new NoSuchElementException("Workout Complex id " + workoutComplexId + " has bad value"));
        workoutComplex.setName(name);
        workoutComplexRepository.save(workoutComplex);
        return workoutComplex;
    }

}
