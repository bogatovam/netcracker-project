package com.netcracker.controller;

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

import static com.netcracker.controller.ControllersPaths.WorkoutComplexController.*;

@RestController
public class WorkoutComplexController {
    @Autowired
    DataDisplayService dataDisplayService;

    @Autowired
    PlanningService planningService;

    @PostMapping(CREATE_WORKOUT_COMPLEX)
    @ApiOperation(value = "Create workout complex")
    public ResponseEntity<?> createWorkoutComplex(
            @ApiParam(value = "Body of сreated workout complex")
            @RequestBody WorkoutComplex workoutComplex,
            Authentication authentication) {
        try {
            WorkoutComplex result = planningService.createWorkoutComplex(workoutComplex, authentication.getName());
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        catch (NoSuchElementException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(GET_WORKOUT_COMPLEX_BY_ID)
    @ApiOperation(value = "Get workout complex by id")
    public ResponseEntity<?> getWorkoutById(
            @ApiParam(value = "Workout complex id")
            @PathVariable String id,
            Authentication authentication) {
        WorkoutComplex workoutComplexById = dataDisplayService
                .getWorkoutComplexById(id, authentication.getName());
        if (workoutComplexById == null)
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Workout complex with id: " + id + " doesn't exist");
        return ResponseEntity.ok(workoutComplexById);
    }

    @GetMapping(GET_ALL_WORKOUTS_COMPLEXES)
    @ApiOperation(value = "Get all workout complexes (for current user)")
    public ResponseEntity<?> getAllWorkoutComplex(Authentication authentication) {
        try {
            List<WorkoutComplex> allWorkoutComplex = dataDisplayService.getAllWorkoutComplex(authentication.getName());
            return new ResponseEntity<>(allWorkoutComplex, HttpStatus.OK);
        }
        catch (NoSuchElementException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(GET_WORKOUTS_BY_ID)
    @ApiOperation(value = "Get workout complex workouts")
    public ResponseEntity<?> getWorkouts(
            @ApiParam(value = "Workout complex id")
            @PathVariable String id,
            Authentication authentication) {
        try {
            List<Workout> workouts = dataDisplayService.getWorkouts(id, authentication.getName());
            return workouts!= null ?
                    new ResponseEntity<>(workouts, HttpStatus.OK):
                    new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        catch (NoSuchElementException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(ADD_WORKOUT_BY_ID)
    @ApiOperation(value = "Add workout to workout complex")
    public ResponseEntity<?> addWorkout(
            @ApiParam(value = "Workout complex id")
            @PathVariable String id,
            @ApiParam(value = "Added workout")
            @RequestBody Workout workout,
            Authentication authentication) {
        try {
            Workout result = planningService.addWorkout(id, workout, authentication.getName());
            return result!= null ?
                    new ResponseEntity<>(result, HttpStatus.OK):
                    new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        catch (NoSuchElementException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(UPDATE_WORKOUT_COMPLEX)
    @ApiOperation(value = "Update workout complex")
    public ResponseEntity<?> updateWorkout(
            @ApiParam(value = "Body of updated workout complex")
            @RequestBody WorkoutComplex workoutComplex,
            Authentication authentication) {
        try {
            WorkoutComplex result = planningService.updateWorkoutComplex(workoutComplex, authentication.getName());
            return result!= null ?
                    new ResponseEntity<>(result, HttpStatus.OK):
                    new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        catch (NoSuchElementException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(DEL_WORKOUT_BY_ID)
    @ApiOperation(value = "Delete workout from workout complex")
    public ResponseEntity<?> delWorkout(
            @ApiParam(value = "Workout complex id")
            @PathVariable String id,
            @ApiParam(value = "Deleted workout id")
            @PathVariable String wid,
            Authentication authentication) {
        try {
            Workout result = planningService.deleteWorkout(id, wid, authentication.getName());
            return result!= null ?
                    new ResponseEntity<>(result, HttpStatus.OK):
                    new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        catch (NoSuchElementException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(DELETE_WORKOUT_COMPLEX_BY_ID)
    @ApiOperation(value = "Delete workout complex id")
    public ResponseEntity<?> deleteWorkoutComplex(
            @ApiParam(value = "Workout complex id")
            @PathVariable String id,
            Authentication authentication) {
        try {
            WorkoutComplex result = planningService.deleteWorkoutComplex(id, authentication.getName());
            return result!= null ?
                    new ResponseEntity<>(result, HttpStatus.OK):
                    new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        catch (NoSuchElementException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
