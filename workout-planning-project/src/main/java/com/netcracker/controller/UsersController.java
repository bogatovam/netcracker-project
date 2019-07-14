package com.netcracker.controller;

import com.netcracker.model.documents.User;
import com.netcracker.services.api.AuthenticationService;
import com.netcracker.services.api.DataDisplayService;
import com.netcracker.services.api.PlanningService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

import static com.netcracker.controller.ControllersPaths.usersController.*;

@RestController
@RequiredArgsConstructor
public class UsersController {
    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private PlanningService planningService;

    @Autowired
    DataDisplayService dataDisplayService;

    @PostMapping(USER_SIGN_IN)
    @ApiOperation(value = "Authenticate User")
    public ResponseEntity<?> authenticateUser(
            @ApiParam(value = "Your sign in information")
            @RequestBody User user) {
        try {
            User result = authenticationService.signIn(user);
            return result!= null ?
                    new ResponseEntity<>(result, HttpStatus.OK):
                    new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        catch (NoSuchElementException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping(USER_SIGN_UP)
    @ApiOperation(value = "Register User")
    public ResponseEntity<?> registerUser(
            @ApiParam(value = "Your sign up information")
            @RequestBody User user) {
        try {
            String result = authenticationService.signUp(user);
            return result!= null ?
                    new ResponseEntity<>(result, HttpStatus.OK):
                    new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        catch (NoSuchElementException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(GET_USER_BY_ID)
    @ApiOperation(value = "Get User")
    public ResponseEntity<?> getUser(
            @ApiParam(value = "User id")
            @PathVariable String id,
            Authentication authentication) {
        try {
            User result = dataDisplayService.getUserById(id, authentication.getName());
            return result!= null ?
                    new ResponseEntity<>(result, HttpStatus.OK):
                    new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        catch (NoSuchElementException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(DELETE_USER_BY_ID)
    @ApiOperation(value = "Delete User")
    public ResponseEntity<?> deleteUser(
            @ApiParam(value = "User id")
            @PathVariable String id,
            Authentication authentication) {
        try {
            User result = planningService.deleteUserById(id, authentication.getName());
            return result!= null ?
                    new ResponseEntity<>(result, HttpStatus.OK):
                    new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        catch (NoSuchElementException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
