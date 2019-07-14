package com.netcracker.services.api;

import com.netcracker.model.documents.User;
import org.springframework.http.ResponseEntity;

public interface AuthenticationService {
    User signIn(User user);

    String signUp(User user);

    Boolean checkAccessRightsToWorkout(String workoutId, String userId);
    Boolean checkAccessRightsToWorkoutComplex(String workoutComplexId, String userId);
}
