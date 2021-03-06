package com.netcracker.controller;

import com.netcracker.model.view.request.SignInRequest;
import com.netcracker.model.view.request.SignUpRequest;
import com.netcracker.model.view.response.ResponseMessage;
import com.netcracker.security.details.UserPrincipal;
import com.netcracker.services.api.AuthenticationService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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

    @PostMapping(USER_SIGN_IN)
    @ApiOperation(value = "Authenticate User")
    public ResponseEntity<?> authenticateUser(
            @ApiParam(value = "Your sign in information")
            @RequestBody SignInRequest signInRequest) {
        return authenticationService.signIn(signInRequest.getLogin(), signInRequest.getPassword());
    }
    @PostMapping(USER_SIGN_UP)
    @ApiOperation(value = "Register User")
    public ResponseEntity<?> registerUser(
            @ApiParam(value = "Your sign up information")
            @RequestBody SignUpRequest signUpRequest) {
        return  authenticationService.signUp(signUpRequest);
    }
}
