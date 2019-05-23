package com.netcracker.controller;

import com.netcracker.model.view.request.SignInRequest;
import com.netcracker.model.view.request.SignUpRequest;
import com.netcracker.model.view.response.ResponseMessage;
import com.netcracker.security.details.UserPrincipal;
import com.netcracker.services.api.AuthenticationService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import static com.netcracker.controller.ControllersPaths.ORIGIN;
import static com.netcracker.controller.ControllersPaths.usersController.*;

@RestController
@RequiredArgsConstructor
public class UsersController {
    @Autowired
    private AuthenticationService authenticationService;

    @GetMapping("/test")
    @ApiOperation(value = "")
    public ResponseEntity<?> test(Authentication authentication) {
        if (authentication == null) {
            return new ResponseEntity<>(new ResponseMessage("User registered successfully!"), HttpStatus.OK);
        }
        System.out.println((authentication.getPrincipal()));
        return new ResponseEntity<>(new ResponseMessage("User registered successfully!"), HttpStatus.OK);
    }

    @PostMapping(USER_SIGN_IN)
    @ApiOperation(value = "")
    public ResponseEntity<?> authenticateUser(@RequestBody SignInRequest signInRequest) {
        return authenticationService.signIn(signInRequest.getLogin(), signInRequest.getPassword());
    }
    @PostMapping(USER_SIGN_UP)
    @ApiOperation(value = "")
    public ResponseEntity<?> registerUser(@RequestBody SignUpRequest signUpRequest) {
        return  authenticationService.signUp(signUpRequest);
    }
}
