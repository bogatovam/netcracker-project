package com.netcracker.controller;

import com.netcracker.model.documents.User;
import com.netcracker.services.api.AuthenticationService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
            @RequestBody User user) {
        return authenticationService.signIn(user);
    }
    @PostMapping(USER_SIGN_UP)
    @ApiOperation(value = "Register User")
    public ResponseEntity<?> registerUser(
            @ApiParam(value = "Your sign up information")
            @RequestBody User user) {
        return  authenticationService.signUp(user);
    }
}
