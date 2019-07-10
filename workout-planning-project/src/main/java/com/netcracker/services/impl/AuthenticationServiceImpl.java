package com.netcracker.services.impl;

import com.netcracker.model.documents.User;
import com.netcracker.model.documents.Workout;
import com.netcracker.model.documents.WorkoutComplex;
import com.netcracker.model.view.response.ResponseMessage;
import com.netcracker.repository.documents.UserRepository;
import com.netcracker.repository.edges.WComplexToWorkoutRepository;
import com.netcracker.repository.edges.WorkoutToDateRepository;
import com.netcracker.security.details.UserPrincipal;
import com.netcracker.security.jwt.JwtTokenProvider;
import com.netcracker.services.api.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationServiceImpl.class);

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider tokenProvider;
    private final WorkoutToDateRepository workoutToDateRepository;
    private final WComplexToWorkoutRepository wComplexToWorkoutRepository;

    private Workout getSourceWorkout(String scheduledWorkoutId) {
        return workoutToDateRepository.findByScheduledWorkoutId(scheduledWorkoutId)
                .orElseThrow(() -> {
                    logger.info("Invalid scheduledWorkoutId:" + scheduledWorkoutId);
                    return new IllegalArgumentException("Invalid scheduledWorkoutId:" + scheduledWorkoutId);
                })
                .getWorkout();
    }

    private WorkoutComplex getSourceWorkoutComplex(String workoutId) {
        return wComplexToWorkoutRepository.findByWorkoutId(workoutId)
                .orElseThrow(() -> {
                    logger.info("Invalid scheduledWorkoutId:" + workoutId);
                    return new IllegalArgumentException("Invalid scheduledWorkoutId:" + workoutId);
                })
                .getWorkoutComplex();
    }

    @Override
    public ResponseEntity<?> signIn(User user) {
        logger.info("Start authentication for " + user);
        try {
            User authUser = null;

            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getLogin(), user.getPassword())
            );
            logger.info("authentication: " + authentication);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            logger.info("set context with: " + authentication);

            UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
            logger.info("userPrincipal: " + userPrincipal);

            String jwt = tokenProvider.generateToken(authentication);

            logger.info("Authentication for " + userPrincipal.getUser() + " was successful");
            authUser = userPrincipal.getUser();

            authUser.setToken(jwt);
            authUser.setPassword("");
            return ResponseEntity.ok(authUser);
        } catch (RuntimeException e) {
            logger.error("Can not set user authentication: " + e.getMessage());
            return new ResponseEntity<>(new ResponseMessage("Fail -> User is not found!"),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<?> signUp(User user) {
        if (userRepository.existsByLogin(user.getLogin())) {
            return new ResponseEntity<>(new ResponseMessage("Username is already taken!"),
                    HttpStatus.BAD_REQUEST);
        }

        if (userRepository.existsByEmail(user.getEmail())) {
            return new ResponseEntity<>(new ResponseMessage("Email is already in use!"),
                    HttpStatus.BAD_REQUEST);
        }

        if (user.isValid()) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));

            user.setRoles(user.getRoles());

            User result = userRepository.save(user);

            return new ResponseEntity<>(new ResponseMessage("User registered successfully!"), HttpStatus.OK);
        } else return new ResponseEntity<>(new ResponseMessage("User fields have bad value"),
                HttpStatus.BAD_REQUEST);
    }

    public Boolean checkAccessRightsToWorkout(String workoutId, String userId) {
        WorkoutComplex source = getSourceWorkoutComplex(workoutId);
        return checkAccessRightsToWorkoutComplex(source.getId(), userId);
    }

    @Override
    public Boolean checkAccessRightsToWorkoutComplex(String workoutComplexId, String userId) {
        List<User> owner = userRepository.findUserByWorkoutComplexId("workout-complex/" + workoutComplexId).asListRemaining();
        if (owner == null || owner.isEmpty()) {
            logger.info("While checkAccessRights for user " + userId + " and workout " + workoutComplexId + " smth went wrong: user is bad");
            return false;
        }
        return owner.get(0).getId().equals(userId);
    }

    @Override
    public Boolean checkAccessRightsToScheduledWorkout(String scheduledWorkoutId, String userId) {
        Workout workout = getSourceWorkout(scheduledWorkoutId);
        return checkAccessRightsToWorkout(workout.getId(), userId);
    }
}
