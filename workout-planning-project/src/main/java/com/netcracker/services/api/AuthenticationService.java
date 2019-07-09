package com.netcracker.services.api;

import com.netcracker.model.documents.User;
import org.springframework.http.ResponseEntity;

public interface AuthenticationService {
    ResponseEntity<?> signIn(User user);

    ResponseEntity<?> signUp(User user);

    Boolean checkAccessRightsToWorkout(String workoutId, String userId);
    Boolean checkAccessRightsToWorkoutComplex(String workoutComplexId, String userId);
    Boolean checkAccessRightsToScheduledWorkout(String scheduledWorkoutId, String userId);

}
