package com.netcracker.services.api;

import com.netcracker.model.view.request.SignUpRequest;
import org.springframework.http.ResponseEntity;

public interface AuthenticationService {
    ResponseEntity<?> signIn(String login, String password);

    ResponseEntity<?> signUp(SignUpRequest signUpRequest);

    Boolean checkAccessRightsToWorkout(String workoutId, String userId);
    Boolean checkAccessRightsToWorkoutComplex(String workoutComplexId, String userId);
    Boolean checkAccessRightsToScheduledWorkout(String scheduledWorkoutId, String userId);

}
