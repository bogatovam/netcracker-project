package com.netcracker.services.impl;

import com.google.common.collect.Lists;
import com.netcracker.model.documents.Exercise;
import com.netcracker.model.documents.User;
import com.netcracker.model.documents.WorkoutComplex;
import com.netcracker.model.edges.UserToWComplex;
import com.netcracker.repository.documents.ExerciseRepository;
import com.netcracker.repository.documents.UserRepository;
import com.netcracker.repository.documents.WorkoutComplexRepository;
import com.netcracker.repository.edges.UserToWComplexRepository;
import com.netcracker.services.api.DemoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DemoServiceImpl implements DemoService {
    private final WorkoutComplexRepository workoutComplexRepository;
    private final UserToWComplexRepository userToWComplexRepository;
    private final UserRepository userRepository;
    private final ExerciseRepository exerciseRepository;

    @Override
    public List<WorkoutComplex> getAllWorkoutComplexByUser(String userId) {
        return Lists.newArrayList(workoutComplexRepository.findWorkoutComplexesByUserId("user/"+userId).asListRemaining());
    }

    @Override
    public WorkoutComplex createWorkoutComplex(String userId, WorkoutComplex source) {
        WorkoutComplex workoutComplex = null;
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            workoutComplex = workoutComplexRepository.save(source);
            userToWComplexRepository.save(new UserToWComplex(user.get(), workoutComplex));
        }
        return workoutComplex;
    }

    @Override
    public List<Exercise> getAllExercise() {
        return Lists.newArrayList(exerciseRepository.findAll());
    }

    @Override
    public Exercise getExercise(String exerciseId) {
        Optional<Exercise> exercise = exerciseRepository.findById(exerciseId);
        return exercise.orElse(null);
    }
}
