package com.netcracker.services.impl;

import com.netcracker.model.edges.*;
import com.netcracker.model.documents.*;
import com.netcracker.repository.edges.*;
import com.netcracker.repository.documents.*;
import com.netcracker.services.api.AuthenticationService;
import com.netcracker.services.api.DataDisplayService;
import com.netcracker.services.api.PlanningService;

import lombok.RequiredArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class PlanningServiceImpl implements PlanningService {

    private final UserRepository userRepository;
    private final ExerciseRepository exerciseRepository;
    private final WorkoutRepository workoutRepository;
    private final WorkoutComplexRepository workoutComplexRepository;
    private final AuthenticationService authenticationService;

    private final WComplexToWorkoutRepository wComplexToWorkoutRepository;
    private final WorkoutToExerciseRepository workoutToExerciseRepository;
    private final UserToWComplexRepository userToWComplexRepository;

    private final DataDisplayService dataDisplayService;

    private static final Logger logger = LoggerFactory.getLogger(PlanningServiceImpl.class);

    @Override
    public User deleteUserById(String userId, String authUserId) throws NoSuchElementException {
        User sourceUser = userRepository.findById(authUserId)
                .orElseThrow(() -> new NoSuchElementException("User with id: "+ authUserId + " doesn't exist"));
        if (sourceUser.getId().equals(userId)) {
            sourceUser.getWorkoutsComplexes().forEach((workoutComplex) -> {
                deleteWorkoutComplex(workoutComplex.getId(), userId);
            });
            userRepository.removeById(userId);
            return sourceUser;
        }
        return null;
    }

    @Override
    public Exercise createExercise(Exercise exercise, String userId) {
        return exerciseRepository.save(exercise);
    }

    @Override
    public Workout createWorkout(String workoutComplexId, Workout workout, String userId) throws NoSuchElementException {
        Workout newWorkout;
        WorkoutComplex sourceWorkoutComplex;
        WComplexToWorkout wComplexToWorkout;

        if (!authenticationService.checkAccessRightsToWorkoutComplex(workoutComplexId, userId))
            return null;

        sourceWorkoutComplex = workoutComplexRepository.findById(workoutComplexId)
                .orElseThrow(() -> new NoSuchElementException(
                        "Workout complex with id:" + workoutComplexId + " doesn't exist"));

        newWorkout = workoutRepository.save(workout);
        wComplexToWorkout = WComplexToWorkout.builder().
                workoutComplex(sourceWorkoutComplex).
                workout(newWorkout).build();
        wComplexToWorkoutRepository.save(wComplexToWorkout);

        newWorkout.getExercises().forEach(exercise->{
            WorkoutToExercise workoutToExercise = WorkoutToExercise.builder()
                    .exercise(exercise).workout(newWorkout).build();
            workoutToExerciseRepository.save(workoutToExercise);
        });
        logger.info("User " + userId + " create workout  " + workout);

        return newWorkout;
    }

    @Override
    public WorkoutComplex createWorkoutComplex(WorkoutComplex workoutComplex, String userId) throws NoSuchElementException {
        User sourceUser;
        UserToWComplex userToWComplex;
        WorkoutComplex newWorkoutComplex;

        sourceUser = dataDisplayService.getUserById(userId);

        newWorkoutComplex = workoutComplexRepository.save(workoutComplex);

        userToWComplex = UserToWComplex.builder()
                .user(sourceUser)
                .wcomplex(newWorkoutComplex).build();
        userToWComplexRepository.save(userToWComplex);

        logger.info("User " + userId + " create workout complex  " + workoutComplex);
        return newWorkoutComplex;
    }

    @Override
    public Exercise deleteExercise(String exerciseId, String userId) throws NoSuchElementException {
        Exercise delExercise = exerciseRepository.findById(exerciseId).
                orElseThrow(() -> new NoSuchElementException(
                        "Exercise id " + exerciseId + " has bad value"));
        workoutToExerciseRepository.removeAllByExerciseId(delExercise.getId());
        exerciseRepository.removeById(exerciseId);
        logger.info("User " + userId + " delete exercise " + exerciseId);
        return delExercise;
    }

    @Override
    public WorkoutComplex changeSourceWorkoutComplex(String workoutId, String oldWorkoutComplexId, String newWorkoutComplexId, String userId) {
        Workout workout = dataDisplayService.getWorkoutById(workoutId, userId);
        WorkoutComplex workoutComplex = dataDisplayService.getWorkoutComplexById(newWorkoutComplexId, userId);

        wComplexToWorkoutRepository.removeAllByWorkoutId(workoutId);
        WComplexToWorkout newConnection = WComplexToWorkout.builder().workout(workout)
                .workoutComplex(workoutComplex).build();
        wComplexToWorkoutRepository.save(newConnection);
        return workoutComplex;
    }

    @Override
    public WorkoutComplex deleteWorkoutComplex(String workoutComplexId, String userId) throws NoSuchElementException {
        if (!authenticationService.checkAccessRightsToWorkoutComplex(workoutComplexId, userId))
            return null;

        WorkoutComplex workoutComplex = workoutComplexRepository.findById(workoutComplexId)
                .orElseThrow(() -> new NoSuchElementException(
                        "Workout complex with id:" + workoutComplexId + " doesn't exist"));

        workoutComplex.getWorkouts().forEach(workout -> {
            deleteWorkout(workout.getId(), userId);
        });

        userToWComplexRepository.removeAllByWcomplexId(workoutComplexId);
        wComplexToWorkoutRepository.removeAllByWorkoutComplexId(workoutComplexId);

        workoutComplexRepository.removeById(workoutComplexId);
        logger.info("User " + userId + " delete workout complex " + workoutComplexId);
        return workoutComplex;
    }

    @Override
    public String setExerciseName(String exerciseId, String name, String userId) throws NoSuchElementException {
        Exercise exercise = exerciseRepository.findById(exerciseId)
                .orElseThrow(() -> new NoSuchElementException("Exercise id " + exerciseId + " has bad value"));
        exercise.setName(name);
        logger.info("User " + userId + " set exercise" + exercise + " name  " + name);

        return exercise.getName();
    }

    @Override
    public Workout addListExercises(String workoutId, List<Exercise> exerciseList, String userId) throws NoSuchElementException {
        if (!authenticationService.checkAccessRightsToWorkout(workoutId, userId)) return null;
        Workout workout = workoutRepository.findById(workoutId)
                .orElseThrow(() -> new NoSuchElementException("Workout id " + workoutId + " has bad value"));

        exerciseList.forEach(exercise -> {
            WorkoutToExercise workoutToExercise = WorkoutToExercise.builder().workout(workout).exercise(exercise).build();
            workoutToExerciseRepository.save(workoutToExercise);
        });
        workoutRepository.save(workout);
        logger.info("User " + userId + " add list exercises " + exerciseList + " to workout " + workoutId);

        return workout;
    }

    @Override
    public Workout delListExercises(String workoutId, List<Exercise> exerciseList, String userId) {
        if (!authenticationService.checkAccessRightsToWorkout(workoutId, userId)) return null;

        Workout workout = workoutRepository.findById(workoutId)
                .orElseThrow(() -> new NoSuchElementException("Workout id " + workoutId + " has bad value"));
        exerciseList.forEach(exercise -> {
            Optional<WorkoutToExercise> workoutToExercise =
                    workoutToExerciseRepository.findByWorkoutIdAndExerciseId(workoutId, exercise.getId());
            if (workoutToExercise.isPresent()) {
                workoutToExerciseRepository.removeById(workoutToExercise.get().getId());
            } else
                System.out.println("Exercise " + exercise.getName() + " with id:" + exercise.getId() + " don't exist in workout with id:" + workoutId);

        });
        workoutRepository.save(workout);
        logger.info("User " + userId + " del list exercises " + exerciseList + " from workout " + workoutId);

        return workout;
    }

    @Override
    public Workout addWorkout(String workoutComplexId, Workout workout, String userId) throws NoSuchElementException {
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
    public Workout deleteWorkout(String workoutId, String userId) throws NoSuchElementException {
        Workout workout = workoutRepository.findById(workoutId)
                .orElseThrow(() -> new NoSuchElementException("Workout id " + workoutId + " has bad value"));

        if (!authenticationService.checkAccessRightsToWorkout(workoutId, userId))
            return null;
        wComplexToWorkoutRepository.removeAllByWorkoutId(workoutId);
        workoutToExerciseRepository.removeAllByWorkoutId(workoutId);

        workoutRepository.delete(workout);
        logger.info("User " + userId + " delete workout  " + workoutId);
        return workout;
    }

    @Override
    public Workout deleteWorkout(String workoutComplexId, String workoutId, String userId) throws NoSuchElementException {
        if (!authenticationService.checkAccessRightsToWorkoutComplex(workoutComplexId, userId)) return null;

        if (!authenticationService.checkAccessRightsToWorkout(workoutId, userId)) return null;

        Workout workout = workoutRepository.findById(workoutId)
                .orElseThrow(() -> new NoSuchElementException("Workout id " + workoutId + " has bad value"));

        wComplexToWorkoutRepository.removeByWorkoutIdAndWorkoutComplexId(workoutComplexId, workoutId);
        workoutToExerciseRepository.removeAllByWorkoutId(workoutId);

        workoutRepository.delete(workout);
        return workout;
    }

    @Override
    public Workout updateWorkout(Workout workout, String userId) throws NoSuchElementException {
        Workout oldWorkout = dataDisplayService.getWorkoutById(workout.getId(), userId);
        delListExercises(oldWorkout.getId(), oldWorkout.getExercises(), userId);
        Workout newWorkout = workoutRepository.save(workout);
        addListExercises(newWorkout.getId(), workout.getExercises(), userId);
        return newWorkout;
    }

    @Override
    public WorkoutComplex updateWorkoutComplex(WorkoutComplex workoutComplex, String userId) {
        if (!authenticationService.checkAccessRightsToWorkoutComplex(workoutComplex.getId(), userId))
            return null;
        return workoutComplexRepository.save(workoutComplex);
    }
}
