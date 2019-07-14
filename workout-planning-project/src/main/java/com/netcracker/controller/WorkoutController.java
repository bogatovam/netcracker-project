package com.netcracker.controller;

import com.netcracker.model.documents.Exercise;
import com.netcracker.model.documents.Workout;
import com.netcracker.model.documents.WorkoutComplex;
import com.netcracker.services.api.DataDisplayService;
import com.netcracker.services.api.PlanningService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

import static com.netcracker.controller.ControllersPaths.WorkoutController.*;

@RestController
public class WorkoutController {
    @Autowired
    DataDisplayService dataDisplayService;

    @Autowired
    PlanningService planningService;

    @PostMapping(CREATE_WORKOUT_WITH_COMPLEX)
    @ApiOperation(value = "Create workout with complex")
    public ResponseEntity<?> createWorkoutWithWorkoutComplex(
            @ApiParam(value = "Workout complex id")
            @PathVariable String wcid,
            @ApiParam(value = "Body of created workout")
            @RequestBody Workout workout,
            Authentication authentication) {
        try {
            Workout result = planningService.createWorkout(wcid, workout, authentication.getName());
            return result!= null ?
                    new ResponseEntity<>(result, HttpStatus.OK):
                    new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        catch (NoSuchElementException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(UPDATE_WORKOUT)
    @ApiOperation(value = "Update workout with complex")
    public ResponseEntity<?> updateWorkout(
            @ApiParam(value = "Body of updated workout")
            @RequestBody Workout workout,
            Authentication authentication) {
        try {
            Workout result = planningService.updateWorkout(workout, authentication.getName());
            return result!= null ?
                    new ResponseEntity<>(result, HttpStatus.OK):
                    new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        catch (NoSuchElementException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(GET_WORKOUT_BY_ID)
    @ApiOperation(value = "Get workout by id")
    public ResponseEntity<?> getWorkoutById(
            @ApiParam(value = "Workout id")
            @PathVariable String id,
            Authentication authentication) {
        try {
            Workout result = dataDisplayService.getWorkoutById(id, authentication.getName());
            return result!= null ?
                    new ResponseEntity<>(result, HttpStatus.OK):
                    new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        catch (NoSuchElementException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(GET_ALL_WORKOUT)
    @ApiOperation(value = "Get all workout")
    public ResponseEntity<?> getAllWorkout(Authentication authentication) {
        try {
            List<Workout> result = dataDisplayService.getAllWorkout(authentication.getName());
            return result!= null ?
                    new ResponseEntity<>(result, HttpStatus.OK):
                    new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        catch (NoSuchElementException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(GET_SOURCE_WORKOUT_COMPLEX_BY_ID)
    @ApiOperation(value = "Get source workout complex")
    public ResponseEntity<?> getSourceWorkoutComplex(
            @ApiParam(value = "Workout id")
            @PathVariable String id,
            Authentication authentication) {
        try {
            WorkoutComplex result = dataDisplayService.getSourceWorkoutComplex(id, authentication.getName());
            return result!= null ?
                    new ResponseEntity<>(result, HttpStatus.OK):
                    new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        catch (NoSuchElementException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(CHANGE_SOURCE_WORKOUT_COMPLEX)
    @ApiOperation(value = "Change source workout complex of workout")
    public ResponseEntity<?> changeSourceWorkoutComplex(
            @ApiParam(value = "Workout complex id")
            @PathVariable String id,
            @ApiParam(value = "Workout complex id")
            @PathVariable String oldwcid,
            @ApiParam(value = "Workout complex id")
            @PathVariable String newwcid,
            Authentication authentication) {
        try {
            WorkoutComplex result = planningService.changeSourceWorkoutComplex(id, oldwcid, newwcid, authentication.getName());
            return result!= null ?
                    new ResponseEntity<>(result, HttpStatus.OK):
                    new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        catch (NoSuchElementException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(GET_EXERCISES_BY_ID)
    @ApiOperation(value = "Get all exercises")
    public ResponseEntity<?> getExercises(
            @ApiParam(value = "Workout id")
            @PathVariable String id,
            Authentication authentication) {
        try {
            List<Exercise> result = dataDisplayService.getExercises(id, authentication.getName());
            return result!= null ?
                    new ResponseEntity<>(result, HttpStatus.OK):
                    new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        catch (NoSuchElementException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(DELETE_WORKOUT_BY_ID)
    @ApiOperation(value = "Delete workout by id")
    public ResponseEntity<?> deleteWorkout(
            @ApiParam(value = "Workout id")
            @PathVariable String id,
            Authentication authentication) {
        try {
            Workout result = planningService.deleteWorkout(id, authentication.getName());
            return result!= null ?
                    new ResponseEntity<>(result, HttpStatus.OK):
                    new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        catch (NoSuchElementException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
