package com.netcracker.services.impl;

import com.netcracker.model.documents.User;
import com.netcracker.model.documents.Workout;
import com.netcracker.model.documents.WorkoutComplex;
import com.netcracker.repository.documents.UserRepository;
import com.netcracker.repository.edges.WComplexToWorkoutRepository;
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
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationServiceImpl.class);

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider tokenProvider;
    private final WComplexToWorkoutRepository wComplexToWorkoutRepository;

    private WorkoutComplex getSourceWorkoutComplex(String workoutId) {
        return wComplexToWorkoutRepository.findByWorkoutId(workoutId)
                .orElseThrow(() -> {
                    logger.error("Invalid WorkoutId:" + workoutId);
                    return new IllegalArgumentException("Invalid WorkoutId:" + workoutId);
                })
                .getWorkoutComplex();
    }

    @Override
    public User signIn(User user) throws RuntimeException {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getLogin(), user.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

        String jwt = tokenProvider.generateToken(authentication);
        logger.info("Authentication for " + userPrincipal.getUser() + " was successful");

        User authUser = userPrincipal.getUser();
        authUser.setToken(jwt);
        authUser.setPassword("");
        return authUser;
    }

    @Override
    public String signUp(User user) {
        String message = "User object has bad fields";
        if (user.isValid()) {
            if (userRepository.existsByLogin(user.getLogin())) {
                message = "Username is already taken!";
            } else if (userRepository.existsByEmail(user.getEmail())) {
                message = "Email is already in use!";
            } else {
                user.setPassword(passwordEncoder.encode(user.getPassword()));
                user.setRoles(user.getRoles());
                userRepository.save(user);
                message = "User registered successfully!";
            }
        }
        return message;
    }

    public Boolean checkAccessRightsToWorkout(String workoutId, String userId) {
        WorkoutComplex source = getSourceWorkoutComplex(workoutId);
        return checkAccessRightsToWorkoutComplex(source.getId(), userId);
    }

    @Override
    public Boolean checkAccessRightsToWorkoutComplex(String workoutComplexId, String userId) {
        List<User> owner = userRepository.findUserByWorkoutComplexId("workout-complex/" + workoutComplexId).asListRemaining();
        if (owner == null || owner.isEmpty()) {
            logger.error("While checkAccessRights for user " + userId + " and workout complex " + workoutComplexId + " smth went wrong");
            return false;
        }
        return owner.get(0).getId().equals(userId);
    }
}
