package com.netcracker.services.impl;

import com.google.common.collect.Lists;
import com.netcracker.model.documents.*;
import com.netcracker.repository.documents.*;
import com.netcracker.repository.edges.*;
import com.netcracker.services.api.AuthenticationService;
import com.netcracker.services.api.DataDisplayService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Data
@Service
@RequiredArgsConstructor
public class DataDisplayServiceImpl implements DataDisplayService {

    private final UserRepository userRepository;
    private final ExerciseRepository exerciseRepository;
    private final WorkoutRepository workoutRepository;
    private final WorkoutComplexRepository workoutComplexRepository;

    private final AuthenticationService authenticationService;

    private final WComplexToWorkoutRepository wComplexToWorkoutRepository;
    private final WorkoutToExerciseRepository workoutToExerciseRepository;
    private final UserToWComplexRepository userToWComplexRepository;

    @Override
    public ResponseEntity<?> getUserById(String userId, String authUserId) {
        User sourceUser = getUserById(authUserId);
        if (sourceUser.getId().equals(userId)) {
            return ResponseEntity.ok(sourceUser);
        } else {
            return new ResponseEntity<>("User is not authorized to take action",
                    HttpStatus.FORBIDDEN);
        }
    }

    public User getUserById(String userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("User has bad value"));
    }

    @Override
    public List<Exercise> getAllExercise() {
        return Lists.newArrayList(exerciseRepository.findAll());
    }

    @Override
    public List<WorkoutComplex> getAllWorkoutComplex(String userId) {
        return Lists.newArrayList(workoutComplexRepository.findWorkoutComplexesByUserId("user/" + userId).asListRemaining());
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
    public List<Exercise> getExercises(String workoutId, String userId) {
        Workout workout = getWorkoutById(workoutId, userId);
        return workout == null ? null : workout.getExercises();
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
    public List<Workout> getWorkouts(String workoutComplexId, String userId) {
        if (!authenticationService.checkAccessRightsToWorkoutComplex(workoutComplexId, userId))
            return null;
        return getWorkoutComplexById(workoutComplexId, userId).getWorkouts();
    }

    @Override
    public ResponseEntity<?> getAllWorkout(String userId) {
        List<Workout> result = new ArrayList<>();
        try {
            User user = getUserById(userId);
            user.getWorkoutsComplexes().forEach(wc -> {
                result.addAll(wc.getWorkouts());
            });
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
