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
    public User getUserById(String userId, String authUserId) throws NoSuchElementException {
        User sourceUser = getUserById(authUserId);
        if (sourceUser.getId().equals(userId)) {
            return sourceUser;
        }
        return null;
    }

    public User getUserById(String userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("User with id: " + userId + " doesn't exist"));
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
    public Exercise getExercise(String exerciseId) throws NoSuchElementException {
        return exerciseRepository.findById(exerciseId)
                .orElseThrow(() -> new NoSuchElementException("Exercise with id: " + exerciseId + " doesn't exist"));
    }

    @Override
    public Workout getWorkoutById(String workoutId, String userId) {
        Workout workout = workoutRepository.findById(workoutId)
                .orElseThrow(() -> new NoSuchElementException("Workout  with id: " + workoutId + " doesn't exist"));
        if (!authenticationService.checkAccessRightsToWorkout(workoutId, userId))
            return null;
        return workout;
    }

    @Override
    public WorkoutComplex getWorkoutComplexById(String workoutComplexId, String userId) throws NoSuchElementException {
        WorkoutComplex workoutComplex = workoutComplexRepository.findById(workoutComplexId)
                .orElseThrow(() -> new NoSuchElementException("Workout Complex  with id: " + workoutComplexId + "doesn't exist"));
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
    public WorkoutComplex getSourceWorkoutComplex(String workoutId, String userId) throws NoSuchElementException {
        authenticationService.checkAccessRightsToWorkout(workoutId, userId);
        return wComplexToWorkoutRepository.findByWorkoutId(workoutId)
                .orElseThrow(() -> new NoSuchElementException("Workout  with id: " + workoutId + " doesn't exist"))
                .getWorkoutComplex();
    }

    @Override
    public List<Workout> getWorkouts(String workoutComplexId, String userId) throws NoSuchElementException {
        if (!authenticationService.checkAccessRightsToWorkoutComplex(workoutComplexId, userId))
            return null;
        return getWorkoutComplexById(workoutComplexId, userId).getWorkouts();
    }

    @Override
    public List<Workout> getAllWorkout(String userId) throws NoSuchElementException {
        List<Workout> result = new ArrayList<>();
        User user = getUserById(userId);
        user.getWorkoutsComplexes().forEach(wc -> {
            result.addAll(wc.getWorkouts());
        });
        return result;
    }
}
