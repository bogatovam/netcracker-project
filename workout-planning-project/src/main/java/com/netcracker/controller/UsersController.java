package com.netcracker.controller;

import com.netcracker.model.documents.User;
import com.netcracker.services.api.AuthenticationService;
import com.netcracker.services.api.PlanningService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import static com.netcracker.controller.ControllersPaths.usersController.*;

@RestController
@RequiredArgsConstructor
public class UsersController {
    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private PlanningService planningService;

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

    @GetMapping(GET_USER_BY_ID)
    @ApiOperation(value = "Get User")
    public ResponseEntity<?> getUser(
            @ApiParam(value = "User id")
            @PathVariable String id,
            Authentication authentication) {
        return  authenticationService.getUserById(id, authentication.getName());
    }

    @DeleteMapping(DELETE_USER_BY_ID)
    @ApiOperation(value = "Delete User")
    public ResponseEntity<?> deleteUser(
            @ApiParam(value = "User id")
            @PathVariable String id,
            Authentication authentication) {
        return  planningService.deleteUserById(id, authentication.getName());
    }
}
