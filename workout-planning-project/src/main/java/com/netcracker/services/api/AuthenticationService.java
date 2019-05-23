package com.netcracker.services.api;

import com.netcracker.model.view.request.SignUpRequest;
import org.springframework.http.ResponseEntity;

public interface AuthenticationService {
    ResponseEntity<?> signIn(String login, String password);
    ResponseEntity<?> signUp(SignUpRequest signUpRequest);
}
